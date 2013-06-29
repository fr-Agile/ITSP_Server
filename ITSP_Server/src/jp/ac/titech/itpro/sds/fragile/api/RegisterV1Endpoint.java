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

    public final static String SUCCESS = "success";
    public final static String FAIL = "fail";
    public final static String NULL_FNAME = "null_fname";
    public final static String NULL_LNAME = "null_lname";
    public final static String NULL_EMAIL = "null_email";
    public final static String NULL_PASS = "null_pass";
    public final static String NULL_PASSA = "null_passa";
    public final static String INVALID_ADDRESS = "invalid_address";
    public final static String EXISTING_ADDRESS = "existing_address";
    public final static String SHORT_PASS = "short_pass";
    public final static String DIFFERENT_PASS = "different_pass";
    public final static String UNEXPECTED_ERROR = "unexpected_error";
    public final static int PASS_LENGTH = 6;

    public RegisterV1ResultDto register(@Named("firstName") String firstName,
            @Named("lastName") String lastName,
            @Named("email") String email,
            @Named("password") String password,
            @Named("passwordAgain") String passwordAgain) {

        RegisterV1ResultDto result = new RegisterV1ResultDto();
        try {
            result.setResult(SUCCESS);
            validateFirstName(result, firstName);
            validateLastName(result, lastName);
            validateEmail(result, email);
            validatePassword(result, password, passwordAgain);
            if (SUCCESS.equals(result.getResult())) {
                User user = UserService.createUser(firstName, lastName, email, password);
                if (user == null) {
                    result.setResult(FAIL);
                    result.addError(UNEXPECTED_ERROR);
                } else {
                    result.setResult(SUCCESS);
                }
            }
        } catch (Exception e) {
            logger.warning(e.getMessage());
            result.setResult(FAIL);
            result.addError(UNEXPECTED_ERROR);
        }
        return result;
    }
    private void validateFirstName(RegisterV1ResultDto result, String firstName) {
        if (firstName == null) {
            result.setResult(FAIL);
            result.addError(NULL_FNAME);
        }
    }
    private void validateLastName(RegisterV1ResultDto result, String lastName) {
        if (lastName == null) {
            result.setResult(FAIL);
            result.addError(NULL_LNAME);
        }
    }
    private void validateEmail(RegisterV1ResultDto result, String email) {
        if (email == null) {
            result.setResult(FAIL);
            result.addError(NULL_EMAIL);
        } else if (!AddressChecker.check(email)) {
            result.setResult(FAIL);
            result.addError(INVALID_ADDRESS);
        } else if (UserService.getUserByEmail(email) != null) {
            result.setResult(FAIL);
            result.addError(EXISTING_ADDRESS);
        }
    }
    private void validatePassword(RegisterV1ResultDto result, String password, String passwordAgain) {
        if (password == null) {
            result.setResult(FAIL);
            result.addError(NULL_PASS);
        } else if(password.length() < PASS_LENGTH) {
            result.setResult(FAIL);
            result.addError(SHORT_PASS);
        }
        if (passwordAgain == null) {
            result.setResult(FAIL);
            result.addError(NULL_PASSA);
        } else if(!password.equals(passwordAgain)) {
            result.setResult(FAIL);
            result.addError(DIFFERENT_PASS);
        }
    }
}
