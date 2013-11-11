package jp.ac.titech.itpro.sds.fragile.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jp.ac.titech.itpro.sds.fragile.meta.FriendMeta;
import jp.ac.titech.itpro.sds.fragile.meta.RegistrationIdMeta;
import jp.ac.titech.itpro.sds.fragile.model.Friend;
import jp.ac.titech.itpro.sds.fragile.model.RegistrationId;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.utils.CommonUtils;
import jp.ac.titech.itpro.sds.fragile.utils.Encrypter;

import org.mortbay.log.Log;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.android.gcm.server.*;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;


public class RegisterAndroidService {
    private static RegistrationIdMeta meta = RegistrationIdMeta.get();

    // RegistrationIDとユーザーのセットをDBに保存
    public static RegistrationId registerId(User user, String id) {
        try {
            // すでに登録されている場合は削除
            if (Datastore
                .query(meta)
                .filter(meta.userRef.equal(user.getKey()))
                .count() > 0) {
            
                List<Key> keys = Datastore.query(meta).filter(meta.userRef.equal(user.getKey())).asKeyList();
                for (int i = 0, n = keys.size(); i < n; i++) {
                    Key k = keys.get(i);
                    Datastore.delete(k);
                }
            }
            
            // 新しく登録
            RegistrationId regId = new RegistrationId();
            Key key = Datastore.allocateId(RegistrationId.class);
            regId.setKey(key);
            regId.getUserRef().setModel(user);
            regId.setRegisterId(id);
            Transaction tx = Datastore.beginTransaction();
            Datastore.put(regId);
            tx.commit();
            return regId;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String getRegisterIdFromUser(User user) {
        try {
            return Datastore
                    .query(meta)
                    .filter(meta.userRef.equal(user.getKey()))
                    .asSingle().getRegisterId();
            
        } catch (Exception e) {
            return null;
        }
    }
    
    // 端末のregistrationIDを指定してメッセージを送信
    public static Result sendMessageFromRegisterId(String id, String value, String msg) {
        final int RETRY_COUNT = 5;
        String registrationId = id;
        
        Sender sender = new Sender(CommonUtils.GCM_API_KEY);
        Message message = new Message.Builder().addData("value", value).addData("msg", msg).build();
        
        try {
            Result result = sender.send(message, registrationId, RETRY_COUNT);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // 端末のregistrationIDを指定して、グループ作成時のメッセージを送信
    public static Result sendGroupMessageFromRegisterId(String id, String email, String address, String start, String end, String key){
    	final int RETRY_COUNT = 5;
        
        Sender sender = new Sender(CommonUtils.GCM_API_KEY);
        Message message = new Message.Builder().addData("value", "groupSchedule")
        									   .addData("email", email)
        									   .addData("address", address)
        									   .addData("start", start)
        									   .addData("end", end)
        									   .addData("key", key)
        									   .build();

        
        try {
            Result result = sender.send(message, id, RETRY_COUNT);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
