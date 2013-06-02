package jp.ac.titech.itpro.sds.fragile.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Named;

import jp.ac.titech.itpro.sds.fragile.api.dto.ScheduleResultV1Dto;
import jp.ac.titech.itpro.sds.fragile.model.Schedule;
import jp.ac.titech.itpro.sds.fragile.service.ScheduleService;

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
            @Named("startTime") Date startTime,
            @Named("finishTime") Date finishTime){
        
        ScheduleResultV1Dto result = new ScheduleResultV1Dto();
        
        try {
            if (startTime == null || finishTime == null) {
                result.setResult(FAIL);
            } else if(startTime.compareTo(finishTime) > 0) {
                result.setResult(FAIL);
            } else {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("startTime", startTime);
                map.put("finishTime", finishTime);
                
                Schedule schedule =
                    ScheduleService.createSchedule(map);
                if (schedule == null) {
                    // TODO createSchedule��null�ɂȂ邱�Ƃ͂���?���̂Ƃ��̏�����?
                    result.setResult(FAIL);
                } else {
                    result.setResult(SUCCESS);
                } 
            }
        } catch (Exception e) {
            result.setResult(FAIL);
        }
        return result;
    }
}
