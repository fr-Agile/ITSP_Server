package jp.ac.titech.itpro.sds.fragile.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Named;

import jp.ac.titech.itpro.sds.fragile.api.constant.CommonConstant;
import jp.ac.titech.itpro.sds.fragile.api.container.ScheduleListContainer;

import org.slim3.datastore.Datastore;
import jp.ac.titech.itpro.sds.fragile.api.dto.ScheduleResultV1Dto;
import jp.ac.titech.itpro.sds.fragile.api.dto.ScheduleV1Dto;
import jp.ac.titech.itpro.sds.fragile.model.Group;
import jp.ac.titech.itpro.sds.fragile.model.GroupScheduleMap;
import jp.ac.titech.itpro.sds.fragile.model.Schedule;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.service.GroupScheduleMapService;
import jp.ac.titech.itpro.sds.fragile.service.GroupService;
import jp.ac.titech.itpro.sds.fragile.service.ScheduleService;
import jp.ac.titech.itpro.sds.fragile.service.UserService;

import com.google.api.server.spi.config.Api;

/**
 *
 */
@Api(name = "scheduleEndpoint", version = "v1")
public class ScheduleV1EndPoint {
    private final static Logger logger = Logger.getLogger(
        ScheduleV1EndPoint.class.getName());
    
    private static final String SUCCESS = CommonConstant.SUCCESS;
    private static final String FAIL = CommonConstant.FAIL;
    
    public ScheduleResultV1Dto createSchedule(
            @Named("startTime") long startTime,
            @Named("finishTime") long finishTime,
            @Named("email") String email){
        ScheduleResultV1Dto result = new ScheduleResultV1Dto();
        
        User user = UserService.getUserByEmail(email);
        try {
            if (startTime < 0 || finishTime < 0) {
                logger.warning("time should be positive");
                result.setResult(FAIL);
            } else if(startTime > finishTime) {
                logger.warning("startTime > finishTime");
                result.setResult(FAIL);
            } else if (user == null){
                logger.warning("user not found");
                result.setResult(FAIL);
            } else {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("startTime", startTime);
                map.put("finishTime", finishTime);
                
                Schedule schedule =
                    ScheduleService.createSchedule(map, user);
                if (schedule == null) {
                    logger.warning("schedule not found");
                    result.setResult(FAIL);
                } else {
                    result.setResult(SUCCESS);
                    logger.warning("new schedule added");
                } 
            }
        } catch (Exception e) {
            logger.warning("Exception" + e);
            result.setResult(FAIL);
        }
        return result;
    }
    
    public ScheduleResultV1Dto createGroupSchedule(
            @Named("startTime") long startTime,
            @Named("finishTime") long finishTime,
            @Named("groupKey") String groupKey ){
        ScheduleResultV1Dto result = new ScheduleResultV1Dto();
        
        Group group = GroupService.getGroup(Datastore.stringToKey(groupKey));
        GroupScheduleMap groupScheduleMap = GroupScheduleMapService.createGroupScheduleMap(group);
        try {
            if (startTime < 0 || finishTime < 0) {
                logger.warning("time should be positive");
                result.setResult(FAIL);
            } else if(startTime > finishTime) {
                logger.warning("startTime > finishTime");
                result.setResult(FAIL);
            } else if (group == null){
                logger.warning("user not found");
                result.setResult(FAIL);
            } else {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("startTime", startTime);
                map.put("finishTime", finishTime);
                
                List<Schedule> scheduleList =
                    ScheduleService.createGroupSchedule(map, groupScheduleMap);
                if (scheduleList == null) {
                    logger.warning("group schedule not found");
                    result.setResult(FAIL);
                } else {
                    result.setResult(SUCCESS);
                    logger.warning("new group schedule added");
                } 
            }
        } catch (Exception e) {
            logger.warning("Exception" + e);
            result.setResult(FAIL);
        }
        return result;
    }

    public ScheduleResultV1Dto deleteSchedule(
        @Named("keyS") String keyS){
        ScheduleResultV1Dto result = new ScheduleResultV1Dto();
        if(ScheduleService.deleteSchedule(keyS)){
            result.setResult(SUCCESS);
        }else{
            result.setResult(FAIL);
        }
        return result;
    }
    
    public ScheduleResultV1Dto editSchedule(
            @Named("keyS") String keyS,
            @Named("startTime") long startTime,
            @Named("finishTime") long finishTime){
            ScheduleResultV1Dto result = new ScheduleResultV1Dto();
            if(keyS != null){
                ScheduleService.editSchedule(keyS,startTime,finishTime);
                result.setResult(SUCCESS);
            }else{
                result.setResult(FAIL);
            }
            return result;
        }
    
    public List<ScheduleV1Dto> getSchedule(
            @Named("startTime") long startTime,
            @Named("finishTime") long finishTime,
            @Named("email") String email){
        List<ScheduleV1Dto> result = new ArrayList<ScheduleV1Dto>();
        
        List<Schedule> schedules = ScheduleService
                .getScheduleByUser(
                    UserService.getUserByEmail(email),
                    startTime,
                    finishTime);
        try {
            
            if (schedules == null) {
                logger.warning("schedule not found");
            } else {
                for (Schedule schedule : schedules) {
                    ScheduleV1Dto dto = 
                            new ScheduleV1Dto(schedule.getStartTime(), 
                                schedule.getFinishTime(),Datastore.keyToString(schedule.getKey()));
                    result.add(dto);
                }
                logger.warning("get schedule SUCCESS");
            }
        } catch (Exception e) {
            logger.warning("get schedule FAIL");
            logger.warning("Exception" + e);
        }

        return result;
    }
    
    public ScheduleResultV1Dto createScheduleList (
            @Named("email") String email,
            ScheduleListContainer scheduleListContainer) {
        ScheduleResultV1Dto result = new ScheduleResultV1Dto();
        
        try {
            for (ScheduleV1Dto scheduleDto : scheduleListContainer.getList()) {
                long startTime = scheduleDto.getStartTime();
                long finishTime = scheduleDto.getFinishTime();
                
                ScheduleResultV1Dto resultThisTime = 
                        this.createSchedule(startTime, finishTime, email);
                if (!SUCCESS.equals(resultThisTime.getResult())) {
                    result.setResult(FAIL);
                    break;
                }
            }
            result.setResult(SUCCESS);
            logger.warning("new schedule list added");
            
        } catch (Exception e) {
            logger.warning("Exception" + e);
            result.setResult(FAIL);
        }
        return result;
    }
    
    public ScheduleResultV1Dto deleteAllSchedule(
            @Named("email") String email) {
        ScheduleResultV1Dto result = new ScheduleResultV1Dto();
        
        try {
            User user = UserService.getUserByEmail(email);
            
            if (user == null) {
                logger.warning("user not found");
                result.setResult(FAIL);
            } else {
            
                List<Schedule> list = ScheduleService.getScheduleByUser(user);
                for (Schedule schedule : list) {
                    ScheduleService.deleteSchedule(Datastore.keyToString(schedule.getKey()));
                }
                
                logger.warning("delete all schedule SUCCESS");
                result.setResult(SUCCESS);
            }
        } catch (Exception e) {
            logger.warning("Exception" + e);
            result.setResult(FAIL);
        }
        return result;
    }
}
