package jp.ac.titech.itpro.sds.fragile.api;

import java.util.logging.Logger;

import javax.inject.Named;

import jp.ac.titech.itpro.sds.fragile.api.dto.PushMessageResultV1Dto;
import jp.ac.titech.itpro.sds.fragile.api.dto.RegisterIdResultV1Dto;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.service.RegisterAndroidService;
import jp.ac.titech.itpro.sds.fragile.service.UserService;


import com.google.android.gcm.server.Result;

import com.google.api.server.spi.config.Api;

@Api(name = "pushMessageEndpoint", version = "v1")
public class PushMessageV1Endpoint {
    private final static Logger logger = Logger
        .getLogger(PushMessageV1Endpoint.class.getName());

    private static String SUCCESS = "success";
    private static String FAIL = "fail";

    private static String NULLID = "nullID";
    private static String NULLUSER = "nullUser";
    private static String NULLMSG = "nullmsg";

    public PushMessageResultV1Dto sendMessageFromRegisterId(@Named("msg") String msg, @Named("email") String email){
        
        PushMessageResultV1Dto result = new PushMessageResultV1Dto();
        
        try {
            User user = UserService.getUserByEmail(email);
            if (user == null) {
                result.setResult(NULLUSER);
            } else if (msg == null) {
                result.setResult(NULLMSG);
            } else {
                if(RegisterAndroidService.getRegisterIdFromUser(user) == null){    
                    result.setResult(NULLID);
                } else if(RegisterAndroidService.sendMessageFromRegisterId(RegisterAndroidService.getRegisterIdFromUser(user), msg) == null) {
                    result.setResult(FAIL);
                } else {
                    result.setResult(SUCCESS);
                }
            }
        } catch (Exception e) {
            logger.warning(e.getMessage());
            result.setResult(FAIL);
        }
        return result;
    }
}