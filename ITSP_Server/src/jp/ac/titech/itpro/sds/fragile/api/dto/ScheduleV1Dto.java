package jp.ac.titech.itpro.sds.fragile.api.dto;

import javax.persistence.Entity;


@Entity
public class ScheduleV1Dto {
    private long startTime;
    private long finishTime;
    private String key;
    
    public ScheduleV1Dto(long startTime, long finishTime,String key) {
        this.setStartTime(startTime);
        this.setFinishTime(finishTime);
        this.setKey(key);
    }
    
    public long getStartTime() {
        return startTime;
    }
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    public long getFinishTime() {
        return finishTime;
    }
    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    
}