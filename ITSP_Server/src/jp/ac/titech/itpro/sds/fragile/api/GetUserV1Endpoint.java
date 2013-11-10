package jp.ac.titech.itpro.sds.fragile.api;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;

import jp.ac.titech.itpro.sds.fragile.model.Friend;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.service.FriendService;
import jp.ac.titech.itpro.sds.fragile.service.UserService;
import jp.ac.titech.itpro.sds.fragile.utils.CopyUtils;
import jp.ac.titech.itpro.sds.fragile.api.constant.CommonConstant;
import jp.ac.titech.itpro.sds.fragile.api.dto.GetFriendResultV1Dto;
import jp.ac.titech.itpro.sds.fragile.api.dto.GetUserResultV1Dto;
import jp.ac.titech.itpro.sds.fragile.api.dto.UserV1Dto;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;


@Api(name = "getUserEndpoint", version = "v1")
public class GetUserV1Endpoint {

    private final static Logger logger = 
            Logger.getLogger(GetUserV1Endpoint.class.getName());

    private static final String SUCCESS = CommonConstant.SUCCESS;
    private static final String FAIL = CommonConstant.FAIL;
    
    public GetUserResultV1Dto getUser(@Named("email") String email) {

        GetUserResultV1Dto result = new GetUserResultV1Dto();
        try {
            if (email == null) {
                
            } else {
                User user = UserService.getUserByEmail(email);
                if (user == null) {
                    result.setResult(FAIL);
                } else {
                    UserV1Dto userDto = new UserV1Dto();
                    CopyUtils.copyUser(userDto, user);
                    result.setUser(userDto);
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
