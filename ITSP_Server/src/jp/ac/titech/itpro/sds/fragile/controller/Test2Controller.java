package jp.ac.titech.itpro.sds.fragile.controller;

import java.util.ArrayList;
import java.util.List;

import jp.ac.titech.itpro.sds.fragile.model.Friend;
import jp.ac.titech.itpro.sds.fragile.model.Group;
import jp.ac.titech.itpro.sds.fragile.model.RegistrationId;
import jp.ac.titech.itpro.sds.fragile.model.RepeatSchedule;
import jp.ac.titech.itpro.sds.fragile.model.Schedule;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.model.UserGroupMap;
import jp.ac.titech.itpro.sds.fragile.service.ScheduleService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

public class Test2Controller extends Controller {

    @Override
    public Navigation run() throws Exception {
        List<Key> keys = new ArrayList<Key>();
        List<User> allUsers =
            Datastore
                .query(jp.ac.titech.itpro.sds.fragile.meta.UserMeta.get())
                .asList();
        for (User s : allUsers) {
            keys.add(s.getKey());
        }
        List<Friend> allFriends =
            Datastore
                .query(jp.ac.titech.itpro.sds.fragile.meta.FriendMeta.get())
                .asList();
        for (Friend s : allFriends) {
            keys.add(s.getKey());
        }
        List<Group> allGroups =
            Datastore
                .query(jp.ac.titech.itpro.sds.fragile.meta.GroupMeta.get())
                .asList();
        for (Group s : allGroups) {
            keys.add(s.getKey());
        }
        List<RegistrationId> allRegistrationIds =
            Datastore
                .query(jp.ac.titech.itpro.sds.fragile.meta.RegistrationIdMeta.get())
                .asList();
        for (RegistrationId s : allRegistrationIds) {
            keys.add(s.getKey());
        }
        List<RepeatSchedule> allRepeatSchedules =
            Datastore
                .query(jp.ac.titech.itpro.sds.fragile.meta.RepeatScheduleMeta.get())
                .asList();
        for (RepeatSchedule s : allRepeatSchedules) {
            keys.add(s.getKey());
        }
        List<UserGroupMap> allUserGroupMaps =
                Datastore
                    .query(jp.ac.titech.itpro.sds.fragile.meta.UserGroupMapMeta.get())
                    .asList();
        for (UserGroupMap s : allUserGroupMaps) {
                keys.add(s.getKey());
            }

        List<Schedule> allSchedules = ScheduleService.getAllSchedule();
        for (Schedule s : allSchedules) {
            keys.add(s.getKey());
        }
        Datastore.delete(keys);
        return forward("test.jsp");
    }
}
