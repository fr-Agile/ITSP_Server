package jp.ac.titech.itpro.sds.fragile.service;

import java.util.List;
import java.util.Map;

import jp.ac.titech.itpro.sds.fragile.meta.ScheduleMeta;
import jp.ac.titech.itpro.sds.fragile.model.Friend;
import jp.ac.titech.itpro.sds.fragile.model.Schedule;
import jp.ac.titech.itpro.sds.fragile.model.User;

import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public class ScheduleService {
    private static ScheduleMeta meta = ScheduleMeta.get();

    public static Schedule createSchedule(Map<String, Object> input, User user) {
        Schedule schedule = new Schedule();
        Key key = Datastore.allocateId(Schedule.class);
        BeanUtil.copy(input, schedule);
        schedule.setKey(key);
        schedule.getUser().setModel(user);
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(schedule);
        tx.commit();
        return schedule;
    }
    
    public static List<Schedule> getScheduleByUser(Key user) {
        try {
            return Datastore.query(meta).filter(meta.user.equal(user)).asList();
        } catch (Exception e) {
            return null;
        }
    }
}
