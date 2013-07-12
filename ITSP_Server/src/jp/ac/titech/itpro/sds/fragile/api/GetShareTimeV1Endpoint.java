package jp.ac.titech.itpro.sds.fragile.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.inject.Named;

import jp.ac.titech.itpro.sds.fragile.model.Schedule;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.service.ScheduleService;
import jp.ac.titech.itpro.sds.fragile.service.UserService;
import jp.ac.titech.itpro.sds.fragile.utils.CopyUtils;
import jp.ac.titech.itpro.sds.fragile.utils.ScheduleComparator;
import jp.ac.titech.itpro.sds.fragile.api.dto.GetShareTimeV1ResultDto;
import jp.ac.titech.itpro.sds.fragile.api.dto.GroupScheduleV1Dto;
import jp.ac.titech.itpro.sds.fragile.api.dto.UserV1Dto;

import com.google.api.server.spi.config.Api;


@Api(name = "getShareTimeEndpoint", version = "v1")
public class GetShareTimeV1Endpoint {

    private final static Logger logger = 
            Logger.getLogger(GetShareTimeV1Endpoint.class.getName());

    private final static String SUCCESS = "success";
    private final static String FAIL = "fail";

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
                List<Schedule> scheduleList = ScheduleService.getScheduleByUser(user.getKey());
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
        
        for (ScheduleChangedTime sct : sctList) {
            long sctTime = sct.getTime();
            if(finishTime <= sctTime) {
                break;
            }
            else if (current.getStartTime() < sctTime) {
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
