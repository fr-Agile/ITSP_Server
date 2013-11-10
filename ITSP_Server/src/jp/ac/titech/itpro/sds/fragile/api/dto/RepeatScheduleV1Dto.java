package jp.ac.titech.itpro.sds.fragile.api.dto;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class RepeatScheduleV1Dto {
    private long startTime;
    private long finishTime;
    private long repeatBegin;
    private long repeatEnd;
    private List<Integer> repeatDays;
    private List<Date> excepts;
    private String key;
    
    public RepeatScheduleV1Dto(long startTime, long finishTime, long repeatBegin, long repeatEnd,List<Integer> repeatDays, String key,List<Date> excepts) {
        this.setStartTime(startTime);
        this.setFinishTime(finishTime);
        this.setRepeatBegin(repeatBegin);
        this.setRepeatEnd(repeatEnd);
        this.setRepeatDays(repeatDays);
        this.setKey(key);
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

    public long getRepeatBegin() {
        return repeatBegin;
    }

    public void setRepeatBegin(long repeatBegin) {
        this.repeatBegin = repeatBegin;
    }

    public long getRepeatEnd() {
        return repeatEnd;
    }

    public void setRepeatEnd(long repeatEnd) {
        this.repeatEnd = repeatEnd;
    }

    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
}
