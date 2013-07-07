package jp.ac.titech.itpro.sds.fragile.api.dto;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

/**
 *
 */
@Entity
public class RegisterV1ResultDto {
    
    private String result;
    private List<String> errorList = new ArrayList<String>();

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    public List<String> getErrorList() {
        return errorList;
    }
    
    public void addError(String error) {
        errorList.add(error);
    }
}
