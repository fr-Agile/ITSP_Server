package jp.ac.titech.itpro.sds.fragile.controller;

import java.util.ArrayList;
import java.util.List;

import jp.ac.titech.itpro.sds.fragile.model.Schedule;
import jp.ac.titech.itpro.sds.fragile.service.ScheduleService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;


public class Test2Controller extends Controller {

    @Override
    public Navigation run() throws Exception {
        List<Schedule> all = ScheduleService.getAllSchedule();
        List<Key> keys = new ArrayList<Key>();
        for (Schedule s : all) {
            keys.add(s.getKey());
        }
        Datastore.delete(keys);
        return forward("test.jsp");
    }
}
