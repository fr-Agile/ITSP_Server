package jp.ac.titech.itpro.sds.fragile.api.dto;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

/**
 *
 */
@Entity
public class GetShareTimeV1ResultDto {
    private String result;
    private List<String> strList = new ArrayList<String>();       // TODO DEBUG用

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    public List<String> getStrList() {
        return strList;
    }
    
    public void addStr(String str) {
        strList.add(str);
    }
}
