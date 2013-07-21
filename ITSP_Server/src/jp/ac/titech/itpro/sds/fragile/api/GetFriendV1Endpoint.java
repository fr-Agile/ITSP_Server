package jp.ac.titech.itpro.sds.fragile.api;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;

import jp.ac.titech.itpro.sds.fragile.model.Friend;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.service.FriendService;
import jp.ac.titech.itpro.sds.fragile.service.UserService;
import jp.ac.titech.itpro.sds.fragile.utils.CopyUtils;
import jp.ac.titech.itpro.sds.fragile.api.dto.GetFriendResultV1Dto;
import jp.ac.titech.itpro.sds.fragile.api.dto.UserV1Dto;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;


@Api(name = "getFriendEndpoint", version = "v1")
public class GetFriendV1Endpoint {

    private final static Logger logger = 
            Logger.getLogger(GetFriendV1Endpoint.class.getName());

    private final static String SUCCESS = "success";
    private final static String FAIL = "fail";
    
    
    @ApiMethod(path = "GetFriendResultDto/getFriendTo")
    public GetFriendResultV1Dto getFriendTo(@Named("email") String email) {

        GetFriendResultV1Dto result = new GetFriendResultV1Dto();
        try {
            if (email == null) {
                
            } else {
                User user = UserService.getUserByEmail(email);
                if (user == null) {
                    result.setResult(FAIL);
                } else {
                    List<Friend> friendList = FriendService.getFriendToList(user.getKey());
                    for (Friend friend : friendList) {
                        User friendData = friend.getFriendTo().getModel();
                        UserV1Dto friendDto = new UserV1Dto();
                        CopyUtils.copyUser(friendDto, friendData);
                        
                        result.addFriend(friendDto);
                    }
                    result.setResult(SUCCESS);
                }
            }
        } catch (Exception e) {
            logger.warning(e.getMessage());
            result.setResult(FAIL);
        }
        return result;
    }
    @ApiMethod(path = "GetFriendResultDto/getFriendFrom")
    public GetFriendResultV1Dto getFriendFrom(@Named("email") String email) {

        GetFriendResultV1Dto result = new GetFriendResultV1Dto();
        try {
            if (email == null) {
                
            } else {
                User user = UserService.getUserByEmail(email);
                if (user == null) {
                    result.setResult(FAIL);
                } else {
                    List<Friend> friendList = FriendService.getFriendFromList(user.getKey());
                    for (Friend friend : friendList) {
                        User friendData = friend.getFriendFrom().getModel();
                        UserV1Dto friendDto = new UserV1Dto();
                        friendDto.setFirstName(friendData.getFirstName());
                        friendDto.setLastName(friendData.getLastName());
                        friendDto.setEmail(friendData.getEmail());
                        
                        result.addFriend(friendDto);
                    }
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
