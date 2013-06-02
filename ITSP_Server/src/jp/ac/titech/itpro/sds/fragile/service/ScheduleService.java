package jp.ac.titech.itpro.sds.fragile.service;

import java.util.Map;

import jp.ac.titech.itpro.sds.fragile.meta.ScheduleMeta;
import jp.ac.titech.itpro.sds.fragile.model.Schedule;

import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public class ScheduleService {
    private static ScheduleMeta meta = ScheduleMeta.get();

    public static Schedule createSchedule(Map<String, Object> input) {
        Schedule schedule = new Schedule();
        Key key = Datastore.allocateId(Schedule.class);
        BeanUtil.copy(input, schedule);
        schedule.setKey(key);
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(schedule);
        tx.commit();
        return schedule;
    }
}
