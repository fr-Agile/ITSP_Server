package jp.ac.titech.itpro.sds.fragile.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Named;

import jp.ac.titech.itpro.sds.fragile.api.dto.ScheduleResultV1Dto;
import jp.ac.titech.itpro.sds.fragile.api.dto.ScheduleV1Dto;
import jp.ac.titech.itpro.sds.fragile.model.Schedule;
import jp.ac.titech.itpro.sds.fragile.model.User;
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
    
    private static String SUCCESS = "success";
    private static String FAIL = "fail";
    
    public ScheduleResultV1Dto createSchedule(
            @Named("startTime") long startTime,
            @Named("finishTime") long finishTime,
            @Named("email") String email){
        ScheduleResultV1Dto result = new ScheduleResultV1Dto();
        
        User user = UserService.getUserByEmail(email);
        try {
            if (startTime == 0 || finishTime == 0) {
                logger.warning("data not found");
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
                    logger.warning("schedule added");
                } 
            }
        } catch (Exception e) {
            logger.warning("Exception" + e);
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
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("startTime", startTime);
            map.put("finishTime", finishTime);
            
            if (schedules == null) {
                logger.warning("schedule not found");
            } else {
                logger.warning("schedule added");
            }
        } catch (Exception e) {
            logger.warning("Exception" + e);
        }

        return result;
    }
}
