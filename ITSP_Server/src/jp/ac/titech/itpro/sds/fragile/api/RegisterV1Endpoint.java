package jp.ac.titech.itpro.sds.fragile.api;

import java.util.logging.Logger;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.service.UserService;
import jp.ac.titech.itpro.sds.fragile.api.dto.RegisterV1ResultDto;

import com.google.api.server.spi.config.Api;


@Api(name = "createUserV1Endpoint", version = "v1")
public class RegisterV1Endpoint {

    private final static Logger logger = 
            Logger.getLogger(RegisterV1Endpoint.class.getName());

    private static String SUCCESS = "success";
    private static String FAIL = "fail";

    public RegisterV1ResultDto createUser(@Named("firstName") String firstName,
            @Named("lastName") String lastName,
            @Named("email") String email,
            @Named("password") String password,
            @Named("passwordAgain") String passwordAgain) {
            

        RegisterV1ResultDto result = new RegisterV1ResultDto();
        try {
            if (firstName == null 
                    || lastName == null
                    || email == null 
                    || password == null
                    || passwordAgain == null) {
                result.setResult(FAIL);
            } else if (!password.equals(passwordAgain)){
                result.setResult(FAIL);
            } else {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("firstName", firstName);
                map.put("lastName", lastName);
                map.put("email", email);
                map.put("password", password);
                
                User user =
                    UserService.createUser(map);
                if (user == null) {
                    // TODO createUserÇ≈nullÇ…Ç»ÇÈÇ±Ç∆ÇÕÇ†ÇÈ?ÇªÇÃÇ∆Ç´ÇÃèàóùÇÕ?
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