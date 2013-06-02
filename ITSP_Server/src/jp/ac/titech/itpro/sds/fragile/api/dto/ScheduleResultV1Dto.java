package jp.ac.titech.itpro.sds.fragile.api.dto;

import java.util.Date;

import javax.persistence.Entity;

/**
 *
 */
@Entity
public class ScheduleResultV1Dto {
    
    private String result;
    
    private Date startTime; 

    private Date finishTime; 
    
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}
