package jp.ac.titech.itpro.sds.fragile.api.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

/**
 *
 */
@Entity
public class GetFriendResultV1Dto {
    
    private String result;
    private List<UserV1Dto> friendList = new ArrayList<UserV1Dto>();

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<UserV1Dto> getFriendList() {
        return friendList;
    }
    public void addFriend(UserV1Dto friend) {
        friendList.add(friend);
    }
}
