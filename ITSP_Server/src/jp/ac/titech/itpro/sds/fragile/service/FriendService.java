package jp.ac.titech.itpro.sds.fragile.service;

import java.util.List;
import java.util.Map;

import jp.ac.titech.itpro.sds.fragile.meta.FriendMeta;
import jp.ac.titech.itpro.sds.fragile.model.Friend;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.utils.Encrypter;

import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public class FriendService {
    private static FriendMeta meta = FriendMeta.get();

    public static Friend createFriend(User from, User to) {
        Friend friend = new Friend();
        Key key = Datastore.allocateId(Friend.class);
        friend.setKey(key);
        friend.getFriendFrom().setModel(from);
        friend.getFriendTo().setModel(to);
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(friend);
        tx.commit();
        return friend;
    }
    
    public static boolean isFriend(Key from, Key to) {
        try {
            if (Datastore
                .query(meta)
                .filter(meta.friendFrom.equal(from), meta.friendTo.equal(to))
                .count() > 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static List<Friend> getFriendToList(Key a) {
        try {
            return Datastore
                .query(meta)
                .filter(meta.friendFrom.equal(a))
                .asList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static List<Friend> getFriendFromList(Key a) {
        try {
            return Datastore
                .query(meta)
                .filter(meta.friendTo.equal(a))
                .asList();
        } catch (Exception e) {
            return null;
        }
    }
}
