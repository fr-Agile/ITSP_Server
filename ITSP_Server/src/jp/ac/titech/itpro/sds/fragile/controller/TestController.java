package jp.ac.titech.itpro.sds.fragile.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.service.ScheduleService;
import jp.ac.titech.itpro.sds.fragile.service.UserService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;


public class TestController extends Controller {

    @Override
    public Navigation run() throws Exception {
        User user = UserService.createUser("test", "test", "a@a.com", "test");
        Map<String, Object> map = new HashMap<String, Object>();
        Date day = new Date();
        String name = "test1";
        map.put("startTime", day.getTime());
        map.put("finishTime", day.getTime() + 1000 * 60 * 30);
        ScheduleService.createSchedule(name, map, user);
        Map<String, Object> map2 = new HashMap<String, Object>();
        name = "test2";
        map2.put("startTime", day.getTime() + 1000 * 60 * 60);
        map2.put("finishTime", day.getTime() + 1000 * 60 * 150);
        ScheduleService.createSchedule(name, map2, user);
        return forward("test.jsp");
    }
}
