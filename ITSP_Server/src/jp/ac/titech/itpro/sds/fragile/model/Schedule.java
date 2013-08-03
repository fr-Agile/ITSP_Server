package jp.ac.titech.itpro.sds.fragile.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;

@Model(schemaVersion = 1, kind = "sched")
public class Schedule implements Serializable {

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
    private Boolean isRepeat;
    
    @Attribute(name = "ex")
    private List<Date> excepts;
    
    
    /**
     * Returns the user.
     *
     * @return the user 
     */
    public ModelRef<User> getUser() {
        return user;
    }
    
    
    /**
     * Returns the key.
     *
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key
     *            the key
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Returns the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version
     *            the version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * Returns the start time.
     *
     * @return the start time
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time.
     *
     * @param startTime
     *            the start time
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    
    /**
     * Returns the finish time.
     *
     * @return the finish time
     */
    public long getFinishTime() {
        return finishTime;
    }

    /**
     * Sets the finish time.
     *
     * @param finishTime
     *            the finish time
     */
    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }
    
    /**
     * Returns whether a schedule is repeated or not.
     *
     * @return whether a schedule is repeated or not.
     */
    public Boolean getIsRepeat() {
        return isRepeat;
    }

    /**
     * Sets whether a scheuld is repeated or not.
     *
     * @param isRepeat
     *            whether a schedule is repeated or not.
     */
    public void setIsRepeat(Boolean isRepeat) {
        this.isRepeat = isRepeat;
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
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        Schedule other = (Schedule) obj;
        
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        
        return true;
    }

}
