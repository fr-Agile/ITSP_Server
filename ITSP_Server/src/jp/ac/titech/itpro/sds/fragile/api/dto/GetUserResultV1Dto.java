package jp.ac.titech.itpro.sds.fragile.api.dto;

import javax.persistence.Entity;

/**
 *
 */
@Entity
public class GetUserResultV1Dto {
    
    private String result;
    private UserV1Dto user;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public UserV1Dto getUser() {
        return user;
    }
    public void setUser(UserV1Dto user) {
        this.user = user;
    }
}
