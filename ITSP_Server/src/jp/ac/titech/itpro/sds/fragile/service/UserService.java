package jp.ac.titech.itpro.sds.fragile.service;

import java.util.List;
import java.util.Map;

import jp.ac.titech.itpro.sds.fragile.meta.UserMeta;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.utils.Encrypter;

import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public class UserService {
    private static UserMeta meta = UserMeta.get();

    public static User createUser(Map<String, Object> input) {
        User user = new User();
        Key key = Datastore.allocateId(User.class);
        BeanUtil.copy(input, user);
        user.setKey(key);
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(user);
        tx.commit();
        return user;
    }

    public static List<User> getUserByEmail(String email) {
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
}
