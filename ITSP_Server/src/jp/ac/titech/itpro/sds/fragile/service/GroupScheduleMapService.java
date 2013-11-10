package jp.ac.titech.itpro.sds.fragile.service;

import jp.ac.titech.itpro.sds.fragile.meta.GroupScheduleMapMeta;
import jp.ac.titech.itpro.sds.fragile.model.Group;
import jp.ac.titech.itpro.sds.fragile.model.GroupScheduleMap;
import jp.ac.titech.itpro.sds.fragile.model.User;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public class GroupScheduleMapService {
    private static GroupScheduleMapMeta meta = GroupScheduleMapMeta.get();
    
    public static GroupScheduleMap createGroupScheduleMap (User user, Group group) {
        GroupScheduleMap groupScheduleMap = new GroupScheduleMap();
        Key key = Datastore.allocateId(GroupScheduleMap.class);
        groupScheduleMap.setKey(key);
        groupScheduleMap.getGroup().setModel(group);
        groupScheduleMap.getOwener().setModel(user);
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(groupScheduleMap);
        tx.commit();
        return groupScheduleMap;
    }
}
