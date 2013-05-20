package jp.ac.titech.itpro.sds.fragile.api;

import java.util.Date;

import javax.inject.Named;

import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.service.UserService;


import com.google.api.server.spi.config.Api;

@Api(name = "scheduleEndpoint", version = "v1")
public class ScheduleV1EndPoint {
    
    public Boolean createSchedule(@Named("startTime") Date startTime, @Named("finishTime") Date finishTime, @Named("email") String email){
        Boolean result = false;
        try {
            if (email == null || startTime == null || finishTime == null) {
                result = false;
            } else {
                User user = UserService.getUserByEmail(email);
                if (user == null) {
                    result = false;
                } else {
                    if (startTime.compareTo(finishTime)>0){
                        result = false;
                    } else {
                        result = true;
                    }
                }
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}
