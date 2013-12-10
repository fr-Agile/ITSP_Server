package jp.ac.titech.itpro.sds.fragile.service;

import java.util.List;
import java.util.Map;

import jp.ac.titech.itpro.sds.fragile.meta.GroupMeta;
import jp.ac.titech.itpro.sds.fragile.model.Friend;
import jp.ac.titech.itpro.sds.fragile.model.Group;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.model.UserGroupMap;
import jp.ac.titech.itpro.sds.fragile.utils.Encrypter;

import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public class GroupService {
    private static GroupMeta meta = GroupMeta.get();

    public static Group createGroup(String name, User owner, List<User> users) {

        Group group = new Group();
        Key key = Datastore.allocateId(Group.class);
        group.setKey(key);
        group.setName(name);
        group.getUser().setModel(owner);
        group.setKeyHash(createUserGroup(users, group));
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(group);
        tx.commit();
        return group;
    }

    public static String createUserGroup(List<User> users, Group group) {
        try {
            String hash = "";
            for (User user : users) {
                hash += Datastore.keyToString(user.getKey());
                UserGroupMap userGroupMap = new UserGroupMap();
                Key key = Datastore.allocateId(UserGroupMap.class);
                userGroupMap.setKey(key);
                userGroupMap.getUser().setModel(user);
                userGroupMap.getGroup().setModel(group);
                Transaction tx = Datastore.beginTransaction();
                Datastore.put(userGroupMap);
                tx.commit();
            }
            return hash;
        } catch (Exception e) {
            return null;
        }
    }

    public static Group getGroup(Key groupKey) {
        try {
            return Datastore.query(meta).filter(meta.key.equal(groupKey)).asSingle();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Group getGroup(String name, User owner) {
        try {
            return Datastore
                .query(meta)
                .filter(meta.name.equal(name), meta.user.equal(owner.getKey()))
                .asSingle();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static List<Group> getGroupList(User owner) {
        try {
            return Datastore
                .query(meta)
                .filter(meta.user.equal(owner.getKey()))
                .asList();
        } catch (Exception e) {
            return null;
        }
    }
    
}
