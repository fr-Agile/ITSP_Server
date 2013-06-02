package jp.ac.titech.itpro.sds.fragile.api;

import javax.inject.Named;

import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.service.UserService;
import jp.ac.titech.itpro.sds.fragile.model.Friend;
import jp.ac.titech.itpro.sds.fragile.service.FriendService;

import com.google.api.server.spi.config.Api;

@Api(name = "FriendEndpoint", version = "v1")
public class FriendV1Endpoint {

    public boolean Friendship(@Named("email") String email,@Named("me") User me){
        Boolean result = false;
        try{
            if (email == null || me == null) {
                result = false;
            } else {
                User friend = UserService.getUserByEmail(email);
                if (friend == null) {
                    result = false;
                } else {
                    Friend friendship = FriendService.createFriend(me, friend);
                    if(friendship == null){
                        result = false;
                    }else{
                        result = true;
                    }
                }
            }
        }catch (Exception e) {
            result = false;
        }
        return result;
    }
}