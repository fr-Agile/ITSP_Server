package jp.ac.titech.itpro.sds.fragile.api.dto;

import javax.persistence.Entity;


@Entity
public class ScheduleV1Dto {
    private long startTime;
    private long finishTime;
    
    public ScheduleV1Dto(long startTime, long finishTime) {
        this.setStartTime(startTime);
        this.setFinishTime(finishTime);
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
    
}
