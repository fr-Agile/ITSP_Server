package jp.ac.titech.itpro.sds.fragile.api.dto;

import javax.persistence.Entity;

/**
 *
 */
@Entity
public class ScheduleResultV1Dto {
    
    private String result;
    
    private String startTime; 

    private String finishTime; 
    
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
}
