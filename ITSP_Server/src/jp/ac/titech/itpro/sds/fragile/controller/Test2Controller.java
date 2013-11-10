package jp.ac.titech.itpro.sds.fragile.controller;

import java.util.Date;
import java.util.List;

import jp.ac.titech.itpro.sds.fragile.model.Schedule;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.service.ScheduleService;
import jp.ac.titech.itpro.sds.fragile.service.UserService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;


public class Test2Controller extends Controller {

    @Override
    public Navigation run() throws Exception {
        User user = UserService.getUserByEmail("a@a.com");
        Date day = new Date();
        List<Schedule> list = ScheduleService.getScheduleByUser(user, day.getTime() - 1000 * 60 * 300, day.getTime() + 1000 * 60 * 600);
        return forward("test.jsp");
    }
}
