package jp.ac.titech.itpro.sds.fragile.api;

import java.util.logging.Logger;

import javax.inject.Named;

import org.slim3.datastore.Datastore;

import jp.ac.titech.itpro.sds.fragile.api.constant.CommonConstant;
import jp.ac.titech.itpro.sds.fragile.api.constant.FriendConstant;
import jp.ac.titech.itpro.sds.fragile.api.dto.FriendResultV1Dto;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.service.RegisterAndroidService;
import jp.ac.titech.itpro.sds.fragile.service.UserService;
import jp.ac.titech.itpro.sds.fragile.model.Friend;
import jp.ac.titech.itpro.sds.fragile.service.FriendService;

import com.google.api.server.spi.config.Api;

@Api(name = "friendEndpoint", version = "v1")
public class FriendV1Endpoint {
    private final static Logger logger = Logger
        .getLogger(FriendV1Endpoint.class.getName());
    

    private static final String SUCCESS = CommonConstant.SUCCESS;
    private static final String FAIL = CommonConstant.FAIL;

    private static final String NULLMY = FriendConstant.NULLMY;
    private static final String NOFRIEND = FriendConstant.NOFRIEND;
    private static final String ALREADY = FriendConstant.ALREADY;
    private static final String MINE = FriendConstant.MINE;

    public FriendResultV1Dto Friendship(@Named("email") String email,
            @Named("myemail") String myemail) {

        FriendResultV1Dto result = new FriendResultV1Dto();

        try {
            if (email == null){ 
                result.setResult(FAIL);
            }else if(email.equals(myemail)){
                result.setResult(MINE);
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
    
    public FriendResultV1Dto DeleteFriend(@Named("toemail") String toemail,
            @Named("fromemail") String fromemail) {
        
        FriendResultV1Dto result = new FriendResultV1Dto();
    
        try {
            User to = UserService.getUserByEmail(toemail);
            User from = UserService.getUserByEmail(fromemail);
            
            if((to != null)&&(from != null)){
                Friend f1 = FriendService.getFriend(to.getKey(), from.getKey());
                Friend f2 = FriendService.getFriend(from.getKey(), to.getKey());
                if(f1 != null){
                  FriendService.deleteFriend(Datastore.keyToString(f1.getKey()));
                }
                if(f2 != null){
                  FriendService.deleteFriend(Datastore.keyToString(f2.getKey()));
                }
                // toに対しプッシュ通知を送る
                String regId = RegisterAndroidService.getRegisterIdFromUser(to);
                RegisterAndroidService.sendMessageFromRegisterId(regId, "delFriend", fromemail);
            }
            result.setResult(SUCCESS);
        } catch (Exception e) {
            logger.warning(e.getMessage());
            result.setResult(FAIL);
        }
        return result;
    }
}