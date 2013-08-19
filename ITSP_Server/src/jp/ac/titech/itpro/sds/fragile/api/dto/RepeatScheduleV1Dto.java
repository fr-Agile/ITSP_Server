package jp.ac.titech.itpro.sds.fragile.api.dto;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class RepeatScheduleV1Dto {
    private long startTime;
    private long finishTime;
    private List<Integer> repeatDays;
    private List<Date> excepts;
    
    public RepeatScheduleV1Dto(long startTime, long finishTime, List<Integer> repeatDays, List<Date> excepts) {
        this.setStartTime(startTime);
        this.setFinishTime(finishTime);
        this.setRepeatDays(repeatDays);
        this.setExcepts(excepts);
        
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

    public List<Integer> getRepeatDays() {
        return repeatDays;
    }

    public void setRepeatDays(List<Integer> repeatDays) {
        this.repeatDays = repeatDays;
    }

    public List<Date> getExcepts() {
        return excepts;
    }

    public void setExcepts(List<Date> excepts) {
        this.excepts = excepts;
    }

    
}
