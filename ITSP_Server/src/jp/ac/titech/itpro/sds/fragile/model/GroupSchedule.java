package jp.ac.titech.itpro.sds.fragile.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GroupSchedule {

    private List<User> userList;
    private long startTime;
    private long finishTime;
    
    
    public GroupSchedule() {
        userList = new ArrayList<User>();
    }
        
    public List<User> getUserList() {
        return userList;
    }
    
    public void addUser(User user) {
        userList.add(user);
    }
    
    public void addAllUser(Collection<User> users) {
        userList.addAll(users);
    }
    
    public long getStartTime() {
        return startTime;
    }
    
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    
    public long getFinishTime() {
        return finishTime;
    }
    
    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }
}
