package jp.ac.titech.itpro.sds.fragile.api.dto;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class GroupV1Dto {
    private String name;
    private UserV1Dto owner;
    private List<UserV1Dto> userlList;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public UserV1Dto getOwner() {
        return owner;
    }
    public void setOwner(UserV1Dto owner) {
        this.owner = owner;
    }
    public List<UserV1Dto> getUserlList() {
        return userlList;
    }
    public void setUserlList(List<UserV1Dto> userlList) {
        this.userlList = userlList;
    }
}
