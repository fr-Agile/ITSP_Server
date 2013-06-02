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

    public static List<Friend> getFriendToList(Key to) {
        try {
            return Datastore
                .query(meta)
                .filter(meta.friendFrom.equal(to))
                .asList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static List<Friend> getFriendFromList(Key from) {
        try {
            return Datastore
                .query(meta)
                .filter(meta.friendTo.equal(from))
                .asList();
        } catch (Exception e) {
            return null;
        }
    }
}
