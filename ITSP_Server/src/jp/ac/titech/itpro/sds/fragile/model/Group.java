package jp.ac.titech.itpro.sds.fragile.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

@Model(schemaVersion = 1)
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Attribute(persistent = false)
    private InverseModelListRef<UserGroupMap, Group> UserGroupMapListRef =
            new  InverseModelListRef<UserGroupMap, Group>(UserGroupMap.class, "group",  this);
    
    @Attribute(name = "name")
    private String name;     //グループの名前
    
    @Attribute(name = "owner")
    private  ModelRef<User> user = new ModelRef<User>(User.class);   //グループを作成したユーザー
    
    @Attribute(name = "frinedlist")
    private String key_hash;   //ユーザーのキーのリストのhash値
    
    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;
    
    public InverseModelListRef<UserGroupMap, Group> getUserGroupMapListRef() {
        return UserGroupMapListRef;
    }

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

    public ModelRef<User> getUser() {
        return user;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getKey_hash() {
        return key_hash;
    }
    
    public void setKey_hash(String key_hash) {
        this.key_hash = key_hash;
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
        Group other = (Group) obj;
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