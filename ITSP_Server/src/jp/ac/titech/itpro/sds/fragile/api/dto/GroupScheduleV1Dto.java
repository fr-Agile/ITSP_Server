package jp.ac.titech.itpro.sds.fragile.api.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class GroupScheduleV1Dto {
    private List<UserV1Dto> userList;
    private long startTime;
    private long finishTime;
    
    public GroupScheduleV1Dto() {
        userList = new ArrayList<UserV1Dto>();
    }

    public List<UserV1Dto> getUserList() {
        return userList;
    }
    
    public void addUser(UserV1Dto user) {
        userList.add(user);
    }
    
    public void addAllUser(Collection<UserV1Dto> users) {
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
