package jp.ac.titech.itpro.sds.fragile.api;

import java.util.logging.Logger;

import javax.inject.Named;

import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.service.UserService;
import jp.ac.titech.itpro.sds.fragile.utils.AddressChecker;
import jp.ac.titech.itpro.sds.fragile.api.dto.RegisterV1ResultDto;

import com.google.api.server.spi.config.Api;


@Api(name = "registerEndpoint", version = "v1")
public class RegisterV1Endpoint {

    private final static Logger logger = 
            Logger.getLogger(RegisterV1Endpoint.class.getName());

    private static String SUCCESS = "success";
    private static String FAIL = "fail";
    private static int PASS_LENGTH = 6;

    public RegisterV1ResultDto register(@Named("firstName") String firstName,
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
            } else if (!AddressChecker.check(email)) {
                result.setResult(FAIL);
            } else if (!password.equals(passwordAgain)){
                result.setResult(FAIL);
            } else if (password.length() < PASS_LENGTH) {
                result.setResult(FAIL);
            } else {
                User user = UserService.createUser(firstName, lastName, email, password);
                if (user == null) {
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
