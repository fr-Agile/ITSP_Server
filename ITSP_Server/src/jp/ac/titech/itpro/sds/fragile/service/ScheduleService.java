package jp.ac.titech.itpro.sds.fragile.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.ac.titech.itpro.sds.fragile.api.constant.GoogleConstant;
import jp.ac.titech.itpro.sds.fragile.meta.ScheduleMeta;
import jp.ac.titech.itpro.sds.fragile.model.Event;
import jp.ac.titech.itpro.sds.fragile.model.Schedule;
import jp.ac.titech.itpro.sds.fragile.model.User;

import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public class ScheduleService {
    private static ScheduleMeta meta = ScheduleMeta.get();

    public static Schedule createSchedule(String name,
            Map<String, Object> input, User user) {
        Schedule schedule = new Schedule();
        Key key = Datastore.allocateId(Schedule.class);
        BeanUtil.copy(input, schedule);
        schedule.setKey(key);
        schedule.setName(name);
        schedule.getUser().setModel(user);
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(schedule);
        tx.commit();
        return schedule;
    }

    public static Schedule createSchedule(String name,
            long startTime, long finishTime, User user) {
        Map<String, Object> map = new java.util.HashMap<String, Object>();
        map.put("startTime", startTime);
        map.put("finishTime", finishTime);
        return createSchedule(name, map, user);
    }
    
    public static List<Schedule> createEvent(String name,
            List<String> emailList, Event event, Map<String, Object> input) {
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        for (String email : emailList) {
            User user = UserService.getUserByEmail(email);
            Schedule schedule = new Schedule();
            Key key = Datastore.allocateId(Schedule.class);
            BeanUtil.copy(input, schedule);
            schedule.setKey(key);
            schedule.setName(name);
            schedule.getUser().setModel(user);
            schedule.getEvent().setModel(event);
            Transaction tx = Datastore.beginTransaction();
            Datastore.put(schedule);
            tx.commit();
            scheduleList.add(schedule);
        }
        return scheduleList;

    }

    public static boolean deleteSchedule(String keyS) {
        Key key = Datastore.stringToKey(keyS);
        Datastore.delete(key);
        return true;
    }

    public static void editSchedule(String keyS, String name, Long startTime,
            Long finishTime) {
        Key key = Datastore.stringToKey(keyS);
        Schedule schedule = Datastore.get(Schedule.class, key);
        schedule.setName(name);
        schedule.setStartTime(startTime);
        schedule.setFinishTime(finishTime);
        Datastore.put(schedule);
    }

    public static void editSchedule(String keyS, String name, long startTime,
            long finishTime, String googleId) {
        Key key = Datastore.stringToKey(keyS);
        Schedule schedule = Datastore.get(Schedule.class, key);
        schedule.setName(name);
        schedule.setStartTime(startTime);
        schedule.setFinishTime(finishTime);
        schedule.setGoogleId(googleId);
        Datastore.put(schedule);
    }

    public static List<Schedule> getScheduleByUser(User user) {
        try {
            return Datastore
                .query(meta)
                .filter(meta.user.equal(user.getKey()))
                .asList();
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Schedule> getScheduleByUser(User user, Long startTime,
            Long finishTime) {
        try {
            return Datastore
                .query(meta)
                .filter(meta.user.equal(user.getKey()))
                .filterInMemory(meta.startTime.greaterThanOrEqual(startTime))
                .filterInMemory(meta.finishTime.lessThanOrEqual(finishTime))
                .asList();
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Schedule> getGoogleScheduleByUser(User user) {
        try {
            return Datastore
                .query(meta)
                .filter(meta.user.equal(user.getKey()))
                .filterInMemory(
                    meta.googleId.notEqual(GoogleConstant.UNTIED_TO_GOOGLE))
                .asList();
        } catch (Exception e) {
            return null;
        }
    }

    public static Schedule getScheduleByKey(String keyS) {
        try {
            Key key = Datastore.stringToKey(keyS);
            return Datastore.query(meta).filter(meta.key.equal(key)).asSingle();
        } catch (Exception e) {
            return null;
        }

    }

    public static List<Schedule> getAllSchedule() {
        try {
            return Datastore.query(meta).asList();
        } catch (Exception e) {
            return null;
        }

    }

}
