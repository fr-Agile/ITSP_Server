package jp.ac.titech.itpro.sds.fragile.api.dto;

import javax.persistence.Entity;

/**
 *
 */
@Entity
public class PushMessageResultV1Dto {

    
    private String result;
    
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
