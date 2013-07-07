package jp.ac.titech.itpro.sds.fragile.api;

import java.util.logging.Logger;

import javax.inject.Named;

import jp.ac.titech.itpro.sds.fragile.api.dto.FriendResultV1Dto;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.service.UserService;
import jp.ac.titech.itpro.sds.fragile.model.Friend;
import jp.ac.titech.itpro.sds.fragile.service.FriendService;

import com.google.api.server.spi.config.Api;

@Api(name = "friendEndpoint", version = "v1")
public class FriendV1Endpoint {
    private final static Logger logger = Logger
        .getLogger(FriendV1Endpoint.class.getName());

    private static String SUCCESS = "success";
    private static String FAIL = "fail";

    private static String NULLMY = "nullmy";
    private static String NOFRIEND = "nofriend";
    private static String ALREADY = "already";

    public FriendResultV1Dto Friendship(@Named("email") String email,
            @Named("myemail") String myemail) {

        FriendResultV1Dto result = new FriendResultV1Dto();

        try {
            if (email == null){ 
                result.setResult(FAIL);
            }else if(myemail == null){
                result.setResult(NULLMY);
            } else {
                User friend = UserService.getUserByEmail(email);
                User me = UserService.getUserByEmail(myemail);
                if (friend == null){
                    result.setResult(NOFRIEND);
                }else if(me == null) {
                    result.setResult(NULLMY);
                } else if (FriendService.isFriend(me.getKey(), friend.getKey())) {
                    result.setResult(ALREADY);
                } else {
                    Friend friendship = FriendService.createFriend(me, friend);
                    if (friendship == null) {
                        result.setResult(FAIL);
                    } else {
                        result.setResult(SUCCESS);
                    }

                }
            }
        } catch (Exception e) {
            logger.warning(e.getMessage());
            result.setResult(FAIL);
        }
        return result;
    }
}