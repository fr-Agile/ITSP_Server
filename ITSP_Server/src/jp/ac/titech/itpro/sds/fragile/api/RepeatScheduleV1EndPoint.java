package jp.ac.titech.itpro.sds.fragile.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Named;

import org.slim3.datastore.Datastore;

import jp.ac.titech.itpro.sds.fragile.api.container.RepeatScheduleContainer;
import jp.ac.titech.itpro.sds.fragile.api.container.RepeatScheduleListContainer;
import jp.ac.titech.itpro.sds.fragile.api.dto.RepeatScheduleResultV1Dto;
import jp.ac.titech.itpro.sds.fragile.api.dto.RepeatScheduleV1Dto;
import jp.ac.titech.itpro.sds.fragile.api.dto.ScheduleResultV1Dto;
import jp.ac.titech.itpro.sds.fragile.model.RepeatSchedule;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.service.RepeatScheduleService;
import jp.ac.titech.itpro.sds.fragile.service.ScheduleService;
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
            RepeatScheduleContainer contain) {
        // repeatBeginとrepeatEndが指定されなかった場合、いくらでも繰り返すようにする
        // そのために、repeatBeginを0、repeatEndを最大値に設定
        long repeatBegin = 0;
        long repeatEnd = Long.MAX_VALUE;
        return createRepeatScheduleWithTerm(startTime, finishTime, 
            repeatBegin, repeatEnd, email, contain);
    }
    public RepeatScheduleResultV1Dto createRepeatScheduleWithTerm(
            @Named("startTime") long startTime,
            @Named("finishTime") long finishTime,
            @Named("repeatBegin") long repeatBegin,
            @Named("repeatEnd") long repeatEnd,
            @Named("email") String email,
            RepeatScheduleContainer contain) {
        RepeatScheduleResultV1Dto result = new RepeatScheduleResultV1Dto();
        User user = UserService.getUserByEmail(email);
        try {
            if (startTime < 0 || finishTime < 0) {
                logger.warning("startTime < 0 || finishTime < 0");
                result.setResult(FAIL);
            } else if (startTime >= finishTime) {
                logger.warning("startTime >= finishTime");
                result.setResult(FAIL);
            } else if (repeatBegin < 0 || repeatEnd < 0) {
                logger.warning("repeatBegin < 0 || repeatEnd < 0");
                result.setResult(FAIL);
            } else if (repeatBegin >= repeatEnd) {
                logger.warning("repeatBegin >= repeatEnd");
                result.setResult(FAIL);
            } else if (user == null) {
                logger.warning("RS user not found");
                result.setResult(FAIL);
            } else {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("startTime", startTime);
                map.put("finishTime", finishTime);
                map.put("repeatBegin", repeatBegin);
                map.put("repeatEnd", repeatEnd);
                
                List<Integer> repeatDays = contain.getIntegers();
                List<Date> excepts = contain.getDates();
                
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
    
    public RepeatScheduleResultV1Dto editRepeatSchedule(
            @Named("keyS") String keyS,
            @Named("startTime") long startTime,
            @Named("finishTime") long finishTime,
            RepeatScheduleContainer contain){
        RepeatScheduleResultV1Dto result = new RepeatScheduleResultV1Dto();    
        if(keyS != null){    
            List<Integer> repeatDays = contain.getIntegers();
            List<Date> excepts = contain.getDates();
            RepeatScheduleService.editRepeatSchedule(keyS,startTime,finishTime,repeatDays,excepts);
                result.setResult(SUCCESS);
            }else{
                result.setResult(FAIL);
            }
            return result;
        }
    
    public List<RepeatScheduleV1Dto> getRepeatSchedule(
        @Named("startTime") long startTime,
        @Named("finishTime") long finishTime,
        @Named("email") String email
        ){
        List<RepeatScheduleV1Dto> result = new ArrayList<RepeatScheduleV1Dto>();

        List<RepeatSchedule> repeatSchedules = RepeatScheduleService
                .getRepeatScheduleByUser(
                    UserService.getUserByEmail(email),
                    startTime,
                    finishTime);
        try {
            if (repeatSchedules == null) {
                logger.warning("repeat schedule not found");
            } else {
                for (RepeatSchedule repeatSchedule : repeatSchedules) {
                    RepeatScheduleV1Dto dto = new RepeatScheduleV1Dto(
                        repeatSchedule.getStartTime(), 
                        repeatSchedule.getFinishTime(), 
                        repeatSchedule.getRepeatBegin(),
                        repeatSchedule.getRepeatEnd(),
                        repeatSchedule.getRepeatDays(),
                        Datastore.keyToString(repeatSchedule.getKey()),
                        repeatSchedule.getExcepts());
                    result.add(dto);
                }
            }
        } catch (Exception e) {
            logger.warning("Exception" + e);
        }
        return result;
    }
    
    public RepeatScheduleResultV1Dto deleteRepeatSchedule(@Named("keyS") String keyS){
        RepeatScheduleResultV1Dto result = new RepeatScheduleResultV1Dto();    
        if(keyS != null){    
            if(RepeatScheduleService.deleteRepeatSchedule(keyS)){
                result.setResult(SUCCESS);
            }else{
                result.setResult(FAIL);
            }    
            result.setResult(SUCCESS);
        }else{
            result.setResult(FAIL);
        }
        return result;    
    }
    
    public RepeatScheduleResultV1Dto createRepeatScheduleList (
            @Named("email") String email,
            RepeatScheduleListContainer repeatScheduleListContainer) {
        RepeatScheduleResultV1Dto result = new RepeatScheduleResultV1Dto();
        
        try {
            for (RepeatScheduleV1Dto repeatScheduleDto : repeatScheduleListContainer.getList()) {
                long startTime = repeatScheduleDto.getStartTime();
                long finishTime = repeatScheduleDto.getFinishTime();
                long repeatBegin = repeatScheduleDto.getRepeatBegin();
                long repeatEnd = repeatScheduleDto.getRepeatEnd();
                RepeatScheduleContainer contain = new RepeatScheduleContainer();
                contain.setIntegers(repeatScheduleDto.getRepeatDays());
                contain.setDates(repeatScheduleDto.getExcepts());
                
                RepeatScheduleResultV1Dto resultThisTime = 
                        this.createRepeatScheduleWithTerm(startTime, finishTime, 
                            repeatBegin, repeatEnd, email, contain);
                if (!SUCCESS.equals(resultThisTime.getResult())) {
                    result.setResult(FAIL);
                    break;
                }
            }
            result.setResult(SUCCESS);
            logger.warning("new repeat schedule list added");
            
        } catch (Exception e) {
            logger.warning("Exception" + e);
            result.setResult(FAIL);
        }
        return result;
    }
    
    public RepeatScheduleResultV1Dto deleteAllRepeatSchedule(
            @Named("email") String email) {
        RepeatScheduleResultV1Dto result = new RepeatScheduleResultV1Dto();
        
        try {
            User user = UserService.getUserByEmail(email);
            
            if (user == null) {
                logger.warning("user not found");
                result.setResult(FAIL);
            } else {
            
                List<RepeatSchedule> list = RepeatScheduleService.getRepeatScheduleByUser(user);
                for (RepeatSchedule repeatSchedule : list) {
                    RepeatScheduleService.deleteRepeatSchedule(
                        Datastore.keyToString(repeatSchedule.getKey()));
                }
                
                logger.warning("delete all repeat schedule SUCCESS");
                result.setResult(SUCCESS);
            }
        } catch (Exception e) {
            logger.warning("Exception" + e);
            result.setResult(FAIL);
        }
        return result;
    }
}
