package jp.ac.titech.itpro.sds.fragile.api.dto;

import javax.persistence.Entity;

import jp.ac.titech.itpro.sds.fragile.model.User;

/**
 *
 */
@Entity
public class FriendResultV1Dto {
    
    private String result;
    
    private String email; 

    private User me; 
    
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public User getMe() {
        return me;
    }

    public void setMe(User me) {
        this.me = me;
    }
}
