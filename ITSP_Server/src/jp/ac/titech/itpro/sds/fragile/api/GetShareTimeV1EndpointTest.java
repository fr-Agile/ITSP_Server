package jp.ac.titech.itpro.sds.fragile.api;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import jp.ac.titech.itpro.sds.fragile.api.dto.GroupScheduleV1Dto;
//import jp.ac.titech.itpro.sds.fragile.api.GetShareTimeV1Endpoint.GroupSchedule;
import jp.ac.titech.itpro.sds.fragile.model.Schedule;
import jp.ac.titech.itpro.sds.fragile.model.User;

import org.junit.Test;

import com.google.appengine.api.datastore.Key;

public class GetShareTimeV1EndpointTest {
    
    /*
    private GetShareTimeV1Endpoint endpoint = new GetShareTimeV1Endpoint();

    @Test
    public void testMergeSchedule() {
        User user = new User();
        user.setEmail("test");
        List<Schedule> scList = new ArrayList<Schedule>();
        
        Schedule sc = new Schedule();
        sc.getUser().setModel(user);
        sc.setStartTime(3);
        sc.setFinishTime(5);
        scList.add(sc);
        
        sc = new Schedule();
        sc.getUser().setModel(user);
        sc.setStartTime(4);
        sc.setFinishTime(7);
        scList.add(sc);
        
        sc = new Schedule();
        sc.getUser().setModel(user);
        sc.setStartTime(10);
        sc.setFinishTime(12);
        scList.add(sc);
        
        sc = new Schedule();
        sc.getUser().setModel(user);
        sc.setStartTime(8);
        sc.setFinishTime(9);
        scList.add(sc);
        
        List<Schedule> mergeList = endpoint.mergeSchedule(scList);
        
        assertTrue(mergeList.size() == 3);
        assertTrue(mergeList.get(0).getStartTime() == 3);
        assertTrue(mergeList.get(0).getFinishTime() == 7);
        assertTrue(mergeList.get(1).getStartTime() == 8);
        assertTrue(mergeList.get(1).getFinishTime() == 9);
        assertTrue(mergeList.get(2).getStartTime() == 10);
        assertTrue(mergeList.get(2).getFinishTime() == 12);
        
    }

    @Test
    public void testGetGroupScheduleList() {
        User user = new User();
        user.setEmail("test");
        User user2 = new User();
        user2.setEmail("test2");
        User user3 = new User();
        user3.setEmail("test3");
        List<Schedule> scList = new ArrayList<Schedule>();
        
        Schedule sc = new Schedule();
        sc.getUser().setModel(user);
        sc.setStartTime(3);
        sc.setFinishTime(5);
        scList.add(sc);
        
        sc = new Schedule();
        sc.getUser().setModel(user);
        sc.setStartTime(7);
        sc.setFinishTime(9);
        scList.add(sc);
        
        sc = new Schedule();
        sc.getUser().setModel(user2);
        sc.setStartTime(2);
        sc.setFinishTime(6);
        scList.add(sc);
        
        sc = new Schedule();
        sc.getUser().setModel(user2);
        sc.setStartTime(15);
        sc.setFinishTime(17);
        scList.add(sc);
        
        sc = new Schedule();
        sc.getUser().setModel(user3);
        sc.setStartTime(4);
        sc.setFinishTime(7);
        scList.add(sc);
        
        sc = new Schedule();
        sc.getUser().setModel(user3);
        sc.setStartTime(2);
        sc.setFinishTime(3);
        scList.add(sc);
        
        List<GroupScheduleV1Dto> gsList = endpoint.getGroupScheduleList(scList, 0, 16);
        System.err.println(gsList.size());
        assertTrue(gsList.size() == 9);
        assertTrue(gsList.get(0).getStartTime() == 0);
        assertTrue(gsList.get(0).getFinishTime() == 2);
        assertTrue(gsList.get(0).getUserList().size() == 0);
        
        assertTrue(gsList.get(1).getStartTime() == 2);
        assertTrue(gsList.get(1).getFinishTime() == 3);
        assertTrue(gsList.get(1).getUserList().size() == 2);
        
        assertTrue(gsList.get(2).getStartTime() == 3);
        assertTrue(gsList.get(2).getFinishTime() == 4);
        assertTrue(gsList.get(2).getUserList().size() == 2);
        
        assertTrue(gsList.get(3).getStartTime() == 4);
        assertTrue(gsList.get(3).getFinishTime() == 5);
        assertTrue(gsList.get(3).getUserList().size() == 3);
        
        assertTrue(gsList.get(4).getStartTime() == 5);
        assertTrue(gsList.get(4).getFinishTime() == 6);
        assertTrue(gsList.get(4).getUserList().size() == 2);
        
        assertTrue(gsList.get(5).getStartTime() == 6);
        assertTrue(gsList.get(5).getFinishTime() == 7);
        assertTrue(gsList.get(5).getUserList().size() == 1);
        
        assertTrue(gsList.get(6).getStartTime() == 7);
        assertTrue(gsList.get(6).getFinishTime() == 9);
        assertTrue(gsList.get(6).getUserList().size() == 1);
        
        assertTrue(gsList.get(8).getStartTime() == 15);
        assertTrue(gsList.get(8).getFinishTime() == 16);
        assertTrue(gsList.get(8).getUserList().size() == 1);
    }

    @Test
    public void testGetScheduleChangedTimeList() {
        fail("Not yet implemented");
    }


    */
}
