package jp.ac.titech.itpro.sds.fragile.api.dto;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 */
@Entity
public class RegisterV1ResultDto {
    @Id
    private String userId;

    private String result;

    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
