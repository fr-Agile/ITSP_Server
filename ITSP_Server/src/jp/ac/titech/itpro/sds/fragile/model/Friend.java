package jp.ac.titech.itpro.sds.fragile.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;
    
    //���[���A�h���X�ɂ�錟���̎���
    
    @Attribute(name = "fRA")
    private String friendA;
    
    @Attribute(name = "fRB")
    private String friendB;
    
    @Attribute(name = "fS")
    private boolean friendShip;

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
    
    public String getFriendA() {
        return friendA;
    }
    
    public String getFriendB() {
        return friendB;
    }
    
    public boolean getFriendShip() {
        return friendShip;
    }
    
    public void setFriendA(String friendA) {
        this.friendA = friendA;
    }
    
    public void setFriendB(String friendB) {
        this.friendB = friendB;
    }
    
    public void setFriendShip(boolean friendShip) {
        this.friendShip = friendShip;
    }
}