package jp.ac.titech.itpro.sds.fragile.model;

import java.io.Serializable;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;

@Model(schemaVersion = 1, kind = "sched")
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(name = "us")
    private  ModelRef<User> user = new ModelRef<User>(User.class);
    
    private ModelRef<Event> event = new ModelRef<Event>(Event.class);
    
    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;
    
    @Attribute(name = "nA")
    private String name;
    
    @Attribute(name = "sT")
    private long startTime;
    
    @Attribute(name = "fT")
    private long finishTime;

    @Attribute(name = "gI")
    private String googleId;
    
    /**
     * Returns the user.
     *
     * @return the user 
     */
    public ModelRef<User> getUser() {
        return user;
    }
    
    public ModelRef<Event> getEvent() {
        return event;
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
     * Returns the name.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name
     *            the name
     */
    public void setName(String name) {
        this.name = name;
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
     * Returns the google id.
     *
     * @return the google id
     */
    public String getGoogleId() {
        return googleId;
    }

    /**
     * Sets the google id.
     *
     * @param googleId 
     *            the google id
     */
    public void setGoogleId(String googleId) {
        this.googleId = googleId;
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
