package jp.ac.titech.itpro.sds.fragile.api.dto;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import jp.ac.titech.itpro.sds.fragile.api.container.DateListContainer;
import jp.ac.titech.itpro.sds.fragile.api.container.IntegerListContainer;

@Entity
public class RepeatScheduleV1Dto {
    private long startTime;
    private long finishTime;
    private IntegerListContainer repeatDays;
    private DateListContainer excepts;
    
    public RepeatScheduleV1Dto(long startTime, long finishTime, List<Integer> repeatDays, List<Date> excepts) {
        this.setStartTime(startTime);
        this.setFinishTime(finishTime);
        this.getRepeatDays().setList(repeatDays);
        this.getExcepts().setList(excepts);
        
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

    public IntegerListContainer getRepeatDays() {
        return repeatDays;
    }

    public void setRepeatDays(IntegerListContainer repeatDays) {
        this.repeatDays = repeatDays;
    }

    public DateListContainer getExcepts() {
        return excepts;
    }

    public void setExcepts(DateListContainer excepts) {
        this.excepts = excepts;
    }

    

    
    
    
}
