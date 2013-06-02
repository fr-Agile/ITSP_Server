package jp.ac.titech.itpro.sds.fragile.api;

import java.util.logging.Logger;

import javax.inject.Named;

import jp.ac.titech.itpro.sds.fragile.api.dto.FriendResultV1Dto;
import jp.ac.titech.itpro.sds.fragile.api.dto.ScheduleResultV1Dto;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.service.UserService;
import jp.ac.titech.itpro.sds.fragile.model.Friend;
import jp.ac.titech.itpro.sds.fragile.service.FriendService;

import com.google.api.server.spi.config.Api;

@Api(name = "FriendEndpoint", version = "v1")
public class FriendV1Endpoint {
    private final static Logger logger = Logger.getLogger(
        FriendV1Endpoint.class.getName());
    
    private static String SUCCESS = "success";
    private static String FAIL = "fail";

    public FriendResultV1Dto Friendship(@Named("email") String email,@Named("me") User me){
        
        FriendResultV1Dto result = new FriendResultV1Dto();

        try{
            if (email == null || me == null) {
                result.setResult(FAIL);
            } else {
                User friend = UserService.getUserByEmail(email);
                if (friend == null) {
                    result.setResult(FAIL);
                } else {
                    if (FriendService.isFriend(me.getKey(), friend.getKey())) {
                        result.setResult(FAIL);
                    } else {
                        Friend friendship =
                            FriendService.createFriend(me, friend);
                        if (friendship == null) {
                            result.setResult(FAIL);
                        } else {
                            result.setResult(SUCCESS);
                        }
                    }
                }
            }
        }catch (Exception e) {
            result.setResult(FAIL);
        }
        return result;
    }
}