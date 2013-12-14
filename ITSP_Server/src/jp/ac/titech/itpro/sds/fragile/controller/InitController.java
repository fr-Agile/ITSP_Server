package jp.ac.titech.itpro.sds.fragile.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.ac.titech.itpro.sds.fragile.model.Event;
import jp.ac.titech.itpro.sds.fragile.model.Friend;
import jp.ac.titech.itpro.sds.fragile.model.Group;
import jp.ac.titech.itpro.sds.fragile.model.RegistrationId;
import jp.ac.titech.itpro.sds.fragile.model.RepeatSchedule;
import jp.ac.titech.itpro.sds.fragile.model.Schedule;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.model.UserGroupMap;
import jp.ac.titech.itpro.sds.fragile.service.FriendService;
import jp.ac.titech.itpro.sds.fragile.service.GroupService;
import jp.ac.titech.itpro.sds.fragile.service.RepeatScheduleService;
import jp.ac.titech.itpro.sds.fragile.service.ScheduleService;
import jp.ac.titech.itpro.sds.fragile.service.UserService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

public class InitController extends Controller {
    private static final long TIME_1215 = 1387033200000l;
    private static final long HOUR = 1000 * 60 * 60;
    private static final long DAY = 1000 * 60 * 60 * 24;

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
            Datastore.query(
                jp.ac.titech.itpro.sds.fragile.meta.FriendMeta.get()).asList();
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
                .query(
                    jp.ac.titech.itpro.sds.fragile.meta.RegistrationIdMeta
                        .get()).asList();
        for (RegistrationId s : allRegistrationIds) {
            keys.add(s.getKey());
        }
        List<RepeatSchedule> allRepeatSchedules =
            Datastore
                .query(
                    jp.ac.titech.itpro.sds.fragile.meta.RepeatScheduleMeta
                        .get()).asList();
        for (RepeatSchedule s : allRepeatSchedules) {
            keys.add(s.getKey());
        }
        List<UserGroupMap> allUserGroupMaps =
            Datastore
                .query(
                    jp.ac.titech.itpro.sds.fragile.meta.UserGroupMapMeta.get())
                .asList();
        for (UserGroupMap s : allUserGroupMaps) {
            keys.add(s.getKey());
        }
        List<Event> allEvents =
            Datastore
                .query(jp.ac.titech.itpro.sds.fragile.meta.EventMeta.get())
                .asList();
        for (Event s : allEvents) {
            keys.add(s.getKey());
        }

        List<Schedule> allSchedules = ScheduleService.getAllSchedule();
        for (Schedule s : allSchedules) {
            keys.add(s.getKey());
        }
        Datastore.delete(keys);

        User user1 =
            UserService.createUser("克彦", "権藤", "gondow@test.com", "111111");
        User user2 =
            UserService.createUser("直樹", "米崎", "yonezaki@test.com", "111111");
        User user3 =
            UserService.createUser("卓雄", "渡部", "watanabe@test.com", "111111");

        ScheduleService.createSchedule("お出かけ", TIME_1215 + 11 * HOUR, TIME_1215
            + 17
            * HOUR, user1);
        ScheduleService.createSchedule(
            "会議",
            TIME_1215 + 9 * HOUR + 2 * DAY,
            TIME_1215 + 13 * HOUR + 2 * DAY,
            user1);
        ScheduleService.createSchedule(
            "会議",
            TIME_1215 + 12 * HOUR + 3 * DAY,
            TIME_1215 + 15 * HOUR + 3 * DAY,
            user1);
        ScheduleService.createSchedule(
            "会議",
            TIME_1215 + 11 * HOUR + 4 * DAY,
            TIME_1215 + 14 * HOUR + 4 * DAY,
            user1);
        ScheduleService.createSchedule(
            "秘密の予定",
            TIME_1215 + 20 * HOUR + 4 * DAY,
            TIME_1215 + 21 * HOUR + 4 * DAY,
            user1);
        RepeatScheduleService.createRepeatSchedule(
            "ゼミ",
            15 * HOUR,
            19 * HOUR,
            Arrays.asList(2),
            user1);

        ScheduleService.createSchedule("飲み会", TIME_1215 + 20 * HOUR, TIME_1215
            + 23
            * HOUR, user2);
        ScheduleService.createSchedule(
            "会議",
            TIME_1215 + 9 * HOUR + 1 * DAY,
            TIME_1215 + 12 * HOUR + 1 * DAY,
            user2);
        ScheduleService.createSchedule(
            "会議",
            TIME_1215 + 14 * HOUR + 2 * DAY,
            TIME_1215 + 16 * HOUR + 2 * DAY,
            user2);
        RepeatScheduleService.createRepeatSchedule(
            "授業",
            10 * HOUR,
            13 * HOUR,
            Arrays.asList(3),
            user2);
        ScheduleService.createSchedule(
            "ゴルフ",
            TIME_1215 + 14 * HOUR + 6 * DAY,
            TIME_1215 + 17 * HOUR + 6 * DAY,
            user2);
        RepeatScheduleService.createRepeatSchedule(
            "ゼミ",
            14 * HOUR,
            20 * HOUR,
            Arrays.asList(3),
            user2);
        RepeatScheduleService.createRepeatSchedule(
            "ゼミ",
            12 * HOUR,
            17 * HOUR,
            Arrays.asList(5),
            user2);

        ScheduleService.createSchedule(
            "ジョギング",
            TIME_1215 + 11 * HOUR,
            TIME_1215 + 14 * HOUR,
            user3);
        ScheduleService.createSchedule(
            "会議",
            TIME_1215 + 13 * HOUR + 4 * DAY,
            TIME_1215 + 15 * HOUR + 4 * DAY,
            user2);
        ScheduleService.createSchedule(
            "ジョギング",
            TIME_1215 + 10 * HOUR + 6 * DAY,
            TIME_1215 + 11 * HOUR + 6 * DAY,
            user3);
        RepeatScheduleService.createRepeatSchedule(
            "ゼミ",
            10 * HOUR,
            13 * HOUR,
            Arrays.asList(5),
            user3);
        RepeatScheduleService.createRepeatSchedule(
            "ジム",
            17 * HOUR,
            19 * HOUR,
            Arrays.asList(1, 2, 3, 4, 5),
            user3);

        FriendService.createFriend(user1, user2);
        FriendService.createFriend(user2, user1);
        FriendService.createFriend(user1, user3);
        FriendService.createFriend(user3, user1);
        FriendService.createFriend(user2, user3);
        FriendService.createFriend(user3, user2);

        GroupService.createGroup(
            "Teachers",
            user1,
            Arrays.asList(user1, user2, user3));

        return forward("test.jsp");
    }
}
