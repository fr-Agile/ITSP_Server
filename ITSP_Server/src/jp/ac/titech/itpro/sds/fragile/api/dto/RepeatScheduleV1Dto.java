package jp.ac.titech.itpro.sds.fragile.api.dto;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import jp.ac.titech.itpro.sds.fragile.api.constant.GoogleConstant;

@Entity
public class RepeatScheduleV1Dto {
    private String name;
    private long startTime;
    private long finishTime;
    private long repeatBegin;
    private long repeatEnd;
    private List<Integer> repeatDays;
    private List<Date> excepts;
    private String key;
    private String googleId;
    
    public RepeatScheduleV1Dto() {}		// List<RepeatScheduleV1Dto> に必要
    
    public RepeatScheduleV1Dto(String name, long startTime, long finishTime, long repeatBegin, long repeatEnd,
    		List<Integer> repeatDays, String key,List<Date> excepts, String googleId) {
        this.setName(name);
        this.setStartTime(startTime);
        this.setFinishTime(finishTime);
        this.setRepeatBegin(repeatBegin);
        this.setRepeatEnd(repeatEnd);
        this.setRepeatDays(repeatDays);
        this.setKey(key);
        this.setExcepts(excepts);        
        this.setGoogleId(googleId);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}
    
}
