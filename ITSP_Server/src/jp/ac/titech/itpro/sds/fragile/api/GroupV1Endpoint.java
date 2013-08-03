package jp.ac.titech.itpro.sds.fragile.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;

import jp.ac.titech.itpro.sds.fragile.api.container.StringListContainer;
import jp.ac.titech.itpro.sds.fragile.api.dto.GroupResultV1Dto;
import jp.ac.titech.itpro.sds.fragile.model.Group;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.service.GroupService;
import jp.ac.titech.itpro.sds.fragile.service.UserService;

import com.google.api.server.spi.config.Api;

@Api(name = "groupEndpoint", version = "v1")
public class GroupV1Endpoint {
    private final static Logger logger = Logger
        .getLogger(GroupV1Endpoint.class.getName());

    private static String SUCCESS = "success";
    private static String FAIL = "fail";

    private static String NULLNAME = "nullname";
    private static String NULLOWNER = "nullowner";
    private static String ALREADYGROUP = "alreadygroup";

    public GroupResultV1Dto makeGroup(StringListContainer emailContainer,
            @Named("name") String name, @Named("owner") String owner) {
        
        List<String> emails = emailContainer.getList();
        
        GroupResultV1Dto result = new GroupResultV1Dto();

        try {
            if (emails.size() > 0){ 
                result.setResult(FAIL);
            }else if(name == null){
                result.setResult(NULLNAME);
            }else if(owner == null){
                result.setResult(NULLOWNER);
            } else {
                Group group = GroupService.getGroup(name, UserService.getUserByEmail(owner));
                
                if(group != null){
                    result.setResult(ALREADYGROUP);
                }else{
                    List<User> users = new ArrayList<User>();
                    users.add(UserService.getUserByEmail(owner));
                    for (String email : emails){
                        users.add(UserService.getUserByEmail(email));
                    }
                    Group newGroup = GroupService.createGroup(name, UserService.getUserByEmail(owner), users);
                    
                    if(newGroup==null){
                        result.setResult(FAIL);
                    }else{
                        result.setResult(SUCCESS);
                    }
                }
            }
        } catch (Exception e) {
            logger.warning(e.getMessage());
            result.setResult(FAIL);
        }
        return result;
    }
}