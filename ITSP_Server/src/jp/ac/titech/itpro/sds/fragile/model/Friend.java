package jp.ac.titech.itpro.sds.fragile.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

@Model(schemaVersion = 1)
public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Attribute(name = "fF")
    private  ModelRef<User> friendFrom = new ModelRef<User>(User.class);
    @Attribute(name = "fT")
    private  ModelRef<User> friendTo = new ModelRef<User>(User.class);

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    /**
     * Returns the key.
     *
     * @return the key
     */
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
        Friend other = (Friend) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    public ModelRef<User> getFriendFrom() {
        return friendFrom;
    }

    public ModelRef<User> getFriendTo() {
        return friendTo;
    }
}