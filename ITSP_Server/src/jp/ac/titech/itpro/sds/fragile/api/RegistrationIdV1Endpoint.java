package jp.ac.titech.itpro.sds.fragile.api;

import java.util.logging.Logger;

import javax.inject.Named;

import jp.ac.titech.itpro.sds.fragile.api.dto.RegisterIdResultV1Dto;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.service.RegisterAndroidService;
import jp.ac.titech.itpro.sds.fragile.service.UserService;

import com.google.api.server.spi.config.Api;

@Api(name = "registrationIdEndpoint", version = "v1")
public class RegistrationIdV1Endpoint {
    private final static Logger logger = Logger
        .getLogger(RegistrationIdV1Endpoint.class.getName());

    private static String SUCCESS = "success";
    private static String FAIL = "fail";

    private static String NULLID = "nullID";
    private static String NULLUSER = "nulluser";

    public RegisterIdResultV1Dto registerId(@Named("id") String id, @Named("email") String email){
        
        RegisterIdResultV1Dto result = new RegisterIdResultV1Dto();
        
        try {
            User user = UserService.getUserByEmail(email);
            if (user == null) {
                result.setResult(NULLUSER);
            } else {
                if(RegisterAndroidService.registerId(user, id)  == null){
                    result.setResult(NULLID);
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