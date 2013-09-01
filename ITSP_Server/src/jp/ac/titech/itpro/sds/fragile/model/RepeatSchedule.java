package jp.ac.titech.itpro.sds.fragile.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;

@Model(schemaVersion = 1, kind = "repeatsched")
public class RepeatSchedule implements Serializable{

    private static final long serialVersionUID = 1L;

    @Attribute(name = "us")
    private  ModelRef<User> user = new ModelRef<User>(User.class);
    
    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;
    
    @Attribute(name = "sT")
    private long startTime;
    
    @Attribute(name = "fT")
    private long finishTime;
    
    @Attribute(name = "rp")
    private List<Integer> repeatDays;
    
    @Attribute(name = "ex")
    private List<Date> excepts;

    public ModelRef<User> getUser() {
        return user;
    }


    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }


    


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RepeatSchedule other = (RepeatSchedule) obj;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        return true;
    }
    
    
    
    
}
