package jp.ac.titech.itpro.sds.fragile.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.inject.Named;

import jp.ac.titech.itpro.sds.fragile.model.RepeatSchedule;
import jp.ac.titech.itpro.sds.fragile.model.Schedule;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.service.RepeatScheduleService;
import jp.ac.titech.itpro.sds.fragile.service.ScheduleService;
import jp.ac.titech.itpro.sds.fragile.service.UserService;
import jp.ac.titech.itpro.sds.fragile.utils.CopyUtils;
import jp.ac.titech.itpro.sds.fragile.utils.ScheduleComparator;
import jp.ac.titech.itpro.sds.fragile.api.constant.CommonConstant;
import jp.ac.titech.itpro.sds.fragile.api.dto.GetShareTimeV1ResultDto;
import jp.ac.titech.itpro.sds.fragile.api.dto.GroupScheduleV1Dto;
import jp.ac.titech.itpro.sds.fragile.api.dto.UserV1Dto;

import com.google.api.server.spi.config.Api;


@Api(name = "getShareTimeEndpoint", version = "v1")
public class GetShareTimeV1Endpoint {

    private static final Logger logger = 
            Logger.getLogger(GetShareTimeV1Endpoint.class.getName());

    private static final String SUCCESS = CommonConstant.SUCCESS;
    private static final String FAIL = CommonConstant.FAIL;

    public GetShareTimeV1ResultDto getShareTime(
            @Named("emailCSV") String emailCSV,
            @Named("startTime") long startTime,
            @Named("finishTime") long finishTime) {
        
        /*
         *  TODO emailについて、List型は受け付けてくらないみたいなので要検討
         */
        String [] emailArray = emailCSV.split(",");
        List<String> emailList = new ArrayList<String>(Arrays.asList(emailArray));

        GetShareTimeV1ResultDto result = new GetShareTimeV1ResultDto();
        try {
            if (startTime > finishTime) {
                throw new Exception("startTime > finishTime");
            }
            
            List<Schedule> allSchedule = new ArrayList<Schedule>();
            for (String email : emailList) {
                User user = UserService.getUserByEmail(email);
                if (user == null) {
                    throw new Exception("user not found");
                }
                List<Schedule> scheduleList = ScheduleService.getScheduleByUser(user, startTime, finishTime);
                // repeatScheduleをscheduleに変換して追加
                List<RepeatSchedule> repeatSchedules = 
                        RepeatScheduleService.getRepeatScheduleByUser(user);
                addRepeatSchedule(scheduleList, repeatSchedules, startTime, finishTime, user);
                allSchedule.addAll(mergeSchedule(scheduleList));
                
            }
            // どの時間帯に誰が忙しいか、のリストを作る
            List<GroupScheduleV1Dto> gsList = getGroupScheduleList(allSchedule, startTime, finishTime);
            
            result.setGroupScheduleList(gsList);
            result.setResult(SUCCESS);
            

        } catch (Exception e) {
            logger.warning(e.getMessage());
            result.setResult(FAIL);
        }
        return result;
    }
    
    /*
     * repeatScheduleをscheduleに変換してscheduleListに追加
     * startTimeの週の日曜日からfinishTimeの週の土曜までの繰り返しスケジュールを追加
     */
    private void addRepeatSchedule(List<Schedule> scheduleList,
            List<RepeatSchedule> repeatSchedules, long startTime, long finishTime, User user) {
        // start, finishをCalendar型に変換
        Calendar startDate = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
        startDate.setTimeInMillis(startTime);
        Calendar finishDate = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
        finishDate.setTimeInMillis(finishTime);
        // startの週の日曜日, finishの週の土曜日を求める
        
		Calendar startSun = (Calendar) startDate.clone();
		int dayOfWeek = startDate.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
		
		startSun.add(Calendar.DAY_OF_MONTH, -dayOfWeek);
		startSun.set(Calendar.HOUR_OF_DAY, 0);
		startSun.set(Calendar.MINUTE, 0);
		startSun.set(Calendar.SECOND, 0);
		startSun.set(Calendar.MILLISECOND, 0);

		Calendar finishSat = (Calendar) finishDate.clone();
		dayOfWeek = finishDate.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
		
		finishSat.add(Calendar.DAY_OF_MONTH, -dayOfWeek + 6);
		finishSat.set(Calendar.HOUR_OF_DAY, 23);
		finishSat.set(Calendar.MINUTE, 59);
		finishSat.set(Calendar.SECOND, 59);
		finishSat.set(Calendar.MILLISECOND, 999);
       
        
        Calendar sun = (Calendar) startSun.clone();
        // startの週の日曜から順に追加していく
        while (sun.getTimeInMillis() < finishSat.getTimeInMillis()) {
            for (RepeatSchedule repeat : repeatSchedules) {
                for (int day : repeat.getRepeatDays()) {
                    // TODO exceptsの処理を追加する
                    // scheduleの始まりと終わりを設定する
                    Calendar start = (Calendar) sun.clone();
                    Calendar finish = (Calendar) sun.clone();
                    // 日曜からのずれを足し込む
                    start.add(Calendar.DAY_OF_MONTH, day);
                    finish.add(Calendar.DAY_OF_MONTH, day);
                    if ((start.getTimeInMillis() >= repeat.getRepeatBegin()) &&
                            (finish.getTimeInMillis() <= repeat.getRepeatEnd())) {
                        // 時間を設定する
                        start.add(Calendar.MILLISECOND, (int) repeat.getStartTime());
                        finish.add(Calendar.MILLISECOND, (int) repeat.getFinishTime());
                        // Schedule型を作成しリストに追加する
                        Schedule schedule = new Schedule();
                        schedule.getUser().setModel(user);
                        schedule.setStartTime(start.getTimeInMillis());
                        schedule.setFinishTime(finish.getTimeInMillis());
                        scheduleList.add(schedule);
                    }
                }
            }
            // 一週間ずつずらす
            sun.add(Calendar.DAY_OF_MONTH, 7);
        }
    }

    /*
     * 重なっているスケジュールをマージする
     * userは全て同じであることを仮定している
     */
    private List<Schedule> mergeSchedule(List<Schedule> list) {
        List<Schedule> mergeList = new ArrayList<Schedule>();
        
        if (list == null) {
            return null;
        } else if(list.size() == 0) {
            return mergeList;
        }
        List<Schedule> sortedList = new ArrayList<Schedule>(list);
        Collections.sort(sortedList, new ScheduleComparator());

        // 一つ目のscheduleから順にマージしていく
        Schedule current = new Schedule();
        Schedule first = sortedList.get(0);
        User user = first.getUser().getModel();
        current.setStartTime(first.getStartTime());
        current.setFinishTime(first.getFinishTime());
        current.getUser().setModel(user);
        
        for (int i=1; i<sortedList.size(); i++) {
            Schedule next = sortedList.get(i);
            if (current.getFinishTime() >= next.getStartTime() && 
                    current.getFinishTime() < next.getFinishTime()) {
                /*
                 * current: ----------------
                 * next   :        ---------------
                 * Then, merge current and next
                 * current: ----------------------
                 */
                current.setFinishTime(next.getFinishTime());                
            } else if (current.getFinishTime() < next.getStartTime()) {
                /*
                 * current: -----
                 * next   :        ---------------
                 * Then, add current to mergeList, and set next to current
                 * current:        ---------------
                 */
                mergeList.add(current);
                current = new Schedule();
                current.getUser().setModel(user);
                current.setStartTime(next.getStartTime());
                current.setFinishTime(next.getFinishTime());
            }
        }
        mergeList.add(current);
        return mergeList;
    }
    
    /*
     * スケジュールのリストから、誰がどの時間帯に忙しいのか、のリストを返す
     */
    private List<GroupScheduleV1Dto> getGroupScheduleList(
        List<Schedule> scheduleList, long startTime, long finishTime) {
        
        // 全てのスケジュールの開始と終了の時間のリストを作成
        List<ScheduleChangedTime> sctList = getScheduleChangedTimeList(scheduleList);
        
        Collections.sort(sctList);
        Set<UserV1Dto> busyUsers = new HashSet<UserV1Dto>();
        List<GroupScheduleV1Dto> gsList = new ArrayList<GroupScheduleV1Dto>();
        GroupScheduleV1Dto current = new GroupScheduleV1Dto();
        current.setStartTime(startTime);
        
        try {
        for (ScheduleChangedTime sct : sctList) {
            long sctTime = sct.getTime();
            if(finishTime <= sctTime) {
                break;
            }
            else if (current.getStartTime() < sctTime) {	// 最初はスキップされる
                current.setFinishTime(sctTime);
                current.addAllUser(busyUsers);
                gsList.add(current);
                
                current = new GroupScheduleV1Dto();
                current.setStartTime(sctTime);
            }
            
            UserV1Dto userDto = new UserV1Dto();
            CopyUtils.copyUser(userDto, sct.getUser());
            if (sct.isStartTime()) {
                busyUsers.add(userDto);
            } else {
                busyUsers.remove(userDto);
            }
        }
        current.setFinishTime(finishTime);
        current.addAllUser(busyUsers);
        gsList.add(current);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return gsList;
    }
    
    /*
     * スケジュールのリストから、全てのスケジュールの開始と終了の時間をリストにする
     */    
    private List<ScheduleChangedTime> getScheduleChangedTimeList(List<Schedule> scheduleList) {
        List<ScheduleChangedTime> sctList = new ArrayList<ScheduleChangedTime>();
        for (Schedule schedule : scheduleList) {
           User user = schedule.getUser().getModel();
           sctList.add(new ScheduleChangedTime(user, schedule.getStartTime(), true));
           sctList.add(new ScheduleChangedTime(user, schedule.getFinishTime(), false));
        }
        return sctList;
    }
    
    private class ScheduleChangedTime implements Comparable<ScheduleChangedTime> {
        private User user;
        private long time;
        private boolean isStartTime;
        
        public ScheduleChangedTime(User user, long time, boolean isStart) {
            this.user = user;
            this.time = time;
            this.isStartTime = isStart;
        }
        public User getUser() {
            return user;
        }
        public long getTime() {
            return time;
        }
        public boolean isStartTime() {
            return isStartTime;
        }
        public int compareTo(ScheduleChangedTime o) {
            return (int) (this.getTime() - o.getTime());
        }
    }
}
