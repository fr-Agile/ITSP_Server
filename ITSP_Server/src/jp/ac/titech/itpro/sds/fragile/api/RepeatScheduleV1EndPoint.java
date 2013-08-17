package jp.ac.titech.itpro.sds.fragile.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Named;

import jp.ac.titech.itpro.sds.fragile.api.container.DateListContainer;
import jp.ac.titech.itpro.sds.fragile.api.container.IntegerListContainer;
import jp.ac.titech.itpro.sds.fragile.api.dto.RepeatScheduleResultV1Dto;
import jp.ac.titech.itpro.sds.fragile.api.dto.RepeatScheduleV1Dto;
import jp.ac.titech.itpro.sds.fragile.model.RepeatSchedule;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.service.RepeatScheduleService;
import jp.ac.titech.itpro.sds.fragile.service.UserService;

import com.google.api.server.spi.config.Api;

@Api(name = "repeatScheduleEndpoint", version = "v1")
public class RepeatScheduleV1EndPoint {
    private final static Logger logger = Logger.getLogger(
        RepeatScheduleV1EndPoint.class.getName());
    
    private static String SUCCESS = "success";
    private static String FAIL = "fail";
    
    public RepeatScheduleResultV1Dto createRepeatSchedule(
            @Named("startTime") long startTime,
            @Named("finishTime") long finishTime,
            @Named("email") String email,
            IntegerListContainer repeatDays,
            DateListContainer excepts) {
        RepeatScheduleResultV1Dto result = new RepeatScheduleResultV1Dto();
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
                map.put("repeatDays", repeatDays);
                map.put("excepts", excepts);
                
                RepeatSchedule repeatSchedule =
                    RepeatScheduleService.createRepeatSchedule(map, user);
                if (repeatSchedule == null) {
                    logger.warning("repeat schedule not found");
                    result.setResult(FAIL);
                } else {
                    result.setResult(SUCCESS);
                    logger.warning("repeat schedule added");
                } 
            }
        } catch (Exception e) {
            logger.warning("Exception" + e);
            result.setResult(FAIL);
        }
        return result;
    }
    
    public List<RepeatScheduleV1Dto> getRepeatSchedule(
        @Named("startTime") long startTime,
        @Named("finishTime") long finishTime,
        @Named("email") String email,
        IntegerListContainer repeatDays,
        DateListContainer excepts){
        List<RepeatScheduleV1Dto> result = new ArrayList<RepeatScheduleV1Dto>();

        List<RepeatSchedule> repeatSchedules = RepeatScheduleService
                .getRepeatScheduleByUser(
                    UserService.getUserByEmail(email),
                    startTime,
                    finishTime);
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("startTime", startTime);
            map.put("finishTime", finishTime);
            map.put("repeatDays", repeatDays);
            map.put("excepts", excepts);
            if (repeatSchedules == null) {
                logger.warning("repeat schedule not found");
            } else {

                logger.warning("repeat schedule added");
                for (RepeatSchedule repeatSchedule : repeatSchedules) {
                    RepeatScheduleV1Dto dto = 
                            new RepeatScheduleV1Dto(repeatSchedule.getStartTime(), repeatSchedule.getFinishTime(), repeatSchedule.getRepeatDays(), repeatSchedule.getExcepts());
                    result.add(dto);
                }
            }
        } catch (Exception e) {
            logger.warning("Exception" + e);
        }

        return result;
    }
}
