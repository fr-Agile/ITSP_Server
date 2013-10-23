package jp.ac.titech.itpro.sds.fragile.service;

import java.util.List;
import java.util.Map;

import jp.ac.titech.itpro.sds.fragile.meta.RepeatScheduleMeta;
import jp.ac.titech.itpro.sds.fragile.model.RepeatSchedule;
import jp.ac.titech.itpro.sds.fragile.model.User;

import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public class RepeatScheduleService {
    private static RepeatScheduleMeta meta = RepeatScheduleMeta.get();
    
    public static RepeatSchedule createRepeatSchedule(Map<String, Object> input, User user){
        RepeatSchedule repeatSchedule = new RepeatSchedule();
        Key key = Datastore.allocateId(RepeatSchedule.class);
        BeanUtil.copy(input, repeatSchedule);
        repeatSchedule.setKey(key);
        repeatSchedule.getUser().setModel(user);
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(repeatSchedule);
        tx.commit();
        return repeatSchedule;
    }
    
    public static List<RepeatSchedule> getRepeatScheduleByUser(User user) {
        try {
            return Datastore.query(meta).filter(meta.user.equal(user.getKey())).asList();
        } catch (Exception e) {
            return null;
        }
    }

    public static List<RepeatSchedule> getRepeatScheduleByUser(User user, Long startTime, Long finishTime) {
        try {
            return Datastore.query(meta)
                    .filter(meta.user.equal(user.getKey()))
                    .filterInMemory(meta.startTime.greaterThanOrEqual(startTime))
                    .filterInMemory(meta.finishTime.lessThanOrEqual(finishTime))
                    .asList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static boolean deleteRepeatSchedule(String keyS) {
        Key key = Datastore.stringToKey(keyS);
        Datastore.delete(key);
        return true;
    }
}
