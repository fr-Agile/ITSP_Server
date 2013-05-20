package jp.ac.titech.itpro.sds.fragile.service;

import java.util.List;
import java.util.Map;

import jp.ac.titech.itpro.sds.fragile.meta.FriendMeta;
import jp.ac.titech.itpro.sds.fragile.model.Friend;
import jp.ac.titech.itpro.sds.fragile.utils.Encrypter;

import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public class FriendService {
    private static FriendMeta meta = FriendMeta.get();

    public static Friend createFriend(Map<String, Object> input) {
        Friend friend = new Friend();
        Key key = Datastore.allocateId(Friend.class);
        BeanUtil.copy(input, friend);
        friend.setKey(key);        
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(friend);
        tx.commit();
        return friend;
    }
/*
    public static List<Friend> getUserByEmail(String email) {
        try {
            return Datastore
                .query(meta)
                .filter(meta.email.equal(email))
                .asList();
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
    */
}
