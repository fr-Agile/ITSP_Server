package jp.ac.titech.itpro.sds.fragile.api.dto;


import java.util.List;

import javax.persistence.Entity;

/**
 *
 */
@Entity
public class GetShareTimeV1ResultDto {
    private String result;
    private List<GroupScheduleV1Dto> gsList;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    public List<GroupScheduleV1Dto> getGroupScheduleList() {
        return gsList;
    }

    public void setGroupScheduleList(List<GroupScheduleV1Dto> gsList) {
        this.gsList = gsList;
    }
}
