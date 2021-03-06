package jp.ac.titech.itpro.sds.fragile.service;

import java.util.HashMap;
import java.util.Map;

import jp.ac.titech.itpro.sds.fragile.api.constant.GoogleConstant;
import jp.ac.titech.itpro.sds.fragile.meta.UserMeta;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.utils.Encrypter;

import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public class UserService {
    private static UserMeta meta = UserMeta.get();

    private static User createUser(Map<String, Object> input) {
        User user = new User();
        Key key = Datastore.allocateId(User.class);
        BeanUtil.copy(input, user);
        user.setKey(key);
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(user);
        tx.commit();
        return user;
    }
    
    public static User createUser(String firstName, String lastName, String email, String password) {
        String encryptedPass = Encrypter.getHash(password);
        if(encryptedPass == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("firstName", firstName);
        map.put("lastName", lastName);
        map.put("email", email);
        map.put("password", encryptedPass);
        map.put("googleAccount", GoogleConstant.UNTIED_TO_GOOGLE);
        return createUser(map);
    }
    
    public static User getUser(Key key) {
        try {
            return Datastore
                .query(meta)
                .filter(meta.key.equal(key))
                .asSingle();
        } catch (Exception e) {
            return null;
        }
    }


    public static User getUserByEmail(String email) {
        try {
            return Datastore
                .query(meta)
                .filter(meta.email.equal(email))
                .asSingle();
        } catch (Exception e) {
            return null;
        }
    }

    public static User getUserByEmailAndPassword(String email, String password) {
        try {
            return Datastore
                .query(meta)
                .filter(
                    meta.email.equal(email),
                    meta.password.equal(Encrypter.getHash(password)))
                .asSingle();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static User setGoogleAccount(Key key, String googleAccount) {
        User user = Datastore.get(User.class, key);
        user.setGoogleAccount(googleAccount);
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(user);
        tx.commit();
        return user;
    }
}
