package jp.ac.titech.itpro.sds.fragile.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

import jp.ac.titech.itpro.sds.fragile.meta.UserGroupMapMeta;
import jp.ac.titech.itpro.sds.fragile.model.Group;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.model.UserGroupMap;

public class UserGroupMapService {
    private static UserGroupMapMeta meta = UserGroupMapMeta.get();
    
    public static List<UserGroupMap> getGroupsByUser(User user) {
        try { return Datastore
                .query(meta)
                .filter(meta.user.equal(user.getKey()))
                .asList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static List<UserGroupMap> getUsersByGroup(Group group) {
        try { return Datastore
                .query(meta)
                .filter(meta.group.equal(group.getKey()))
                .asList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static boolean deleteMapByGroup(Group group) {
        try { 
            List<Key> gmapkeylist = Datastore
                                    .query(meta)
                                    .filter(meta.group.equal(group.getKey()))
                                    .asKeyList();
            Datastore.delete(gmapkeylist);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
