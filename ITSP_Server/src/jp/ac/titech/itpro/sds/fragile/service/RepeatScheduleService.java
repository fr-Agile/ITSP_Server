package jp.ac.titech.itpro.sds.fragile.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import jp.ac.titech.itpro.sds.fragile.api.constant.GoogleConstant;
import jp.ac.titech.itpro.sds.fragile.meta.RepeatScheduleMeta;
import jp.ac.titech.itpro.sds.fragile.model.RepeatSchedule;
import jp.ac.titech.itpro.sds.fragile.model.User;

import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public class RepeatScheduleService {
    private static RepeatScheduleMeta meta = RepeatScheduleMeta.get();

    public static RepeatSchedule createRepeatSchedule(
            Map<String, Object> input, User user) {
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

    public static RepeatSchedule createRepeatSchedule(String name,
            long startTime, long finishTime, List<Integer> repeatDays, User user) {
        Map<String, Object> map = new java.util.HashMap<String, Object>();
        map.put("name", name);
        map.put("startTime", startTime);
        map.put("finishTime", finishTime);
        map.put("repeatDays", repeatDays);
        map.put("repeatBegin", 0);
        map.put("repeatEnd", 1390000000000l);
        return createRepeatSchedule(map, user);
    }

    public static List<RepeatSchedule> getRepeatScheduleByUser(User user) {
        try {
            return Datastore
                .query(meta)
                .filter(meta.user.equal(user.getKey()))
                .asList();
        } catch (Exception e) {
            return null;
        }
    }

    public static List<RepeatSchedule> getGoogleRepeatScheduleByUser(User user) {
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

    public static void editRepeatSchedule(String keyS, String name,
            Long startTime, Long finishTime, List<Integer> repeatDays,
            List<Date> excepts) {
        Key key = Datastore.stringToKey(keyS);
        RepeatSchedule rs = Datastore.get(RepeatSchedule.class, key);
        rs.setName(name);
        rs.setStartTime(startTime);
        rs.setFinishTime(finishTime);
        rs.setRepeatDays(repeatDays);
        rs.setExcepts(excepts);
        Datastore.put(rs);
    }

    public static void editRepeatSchedule(String keyS, String name,
            Long startTime, Long finishTime, List<Integer> repeatDays,
            List<Date> excepts, String googleId) {
        Key key = Datastore.stringToKey(keyS);
        RepeatSchedule rs = Datastore.get(RepeatSchedule.class, key);
        rs.setName(name);
        rs.setStartTime(startTime);
        rs.setFinishTime(finishTime);
        rs.setRepeatDays(repeatDays);
        rs.setExcepts(excepts);
        rs.setGoogleId(googleId);
        Datastore.put(rs);
    }

    public static List<RepeatSchedule> getRepeatScheduleByUser(User user,
            Long startTime, Long finishTime) {
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

    public static RepeatSchedule getRepeatScheduleByKey(String keyS) {
        try {
            Key key = Datastore.stringToKey(keyS);
            return Datastore.query(meta).filter(meta.key.equal(key)).asSingle();
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
