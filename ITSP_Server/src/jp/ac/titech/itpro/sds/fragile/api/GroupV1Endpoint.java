package jp.ac.titech.itpro.sds.fragile.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.activation.DataSource;
import javax.inject.Named;

import org.slim3.datastore.Datastore;

import jp.ac.titech.itpro.sds.fragile.api.container.StringListContainer;
import jp.ac.titech.itpro.sds.fragile.api.dto.GroupResultV1Dto;
import jp.ac.titech.itpro.sds.fragile.api.dto.GroupV1Dto;
import jp.ac.titech.itpro.sds.fragile.api.dto.UserV1Dto;
import jp.ac.titech.itpro.sds.fragile.model.Group;
import jp.ac.titech.itpro.sds.fragile.model.User;
import jp.ac.titech.itpro.sds.fragile.model.UserGroupMap;
import jp.ac.titech.itpro.sds.fragile.service.GroupService;
import jp.ac.titech.itpro.sds.fragile.service.UserGroupMapService;
import jp.ac.titech.itpro.sds.fragile.service.UserService;
import jp.ac.titech.itpro.sds.fragile.utils.CopyUtils;

import com.google.api.server.spi.config.Api;

@Api(name = "groupEndpoint", version = "v1")
public class GroupV1Endpoint {
    private final static Logger logger = Logger.getLogger(GroupV1Endpoint.class
        .getName());

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
            if (emails.size() <= 0) {
                result.setResult(FAIL);
            } else if (name == null) {
                result.setResult(NULLNAME);
            } else if (owner == null) {
                result.setResult(NULLOWNER);
            } else {
                Group group =
                    GroupService.getGroup(
                        name,
                        UserService.getUserByEmail(owner));

                if (group != null) {
                    result.setResult(ALREADYGROUP);
                } else {
                    List<User> users = new ArrayList<User>();
                    users.add(UserService.getUserByEmail(owner));
                    for (String email : emails) {
                        users.add(UserService.getUserByEmail(email));
                    }
                    Group newGroup =
                        GroupService.createGroup(
                            name,
                            UserService.getUserByEmail(owner),
                            users);

                    if (newGroup == null) {
                        result.setResult(FAIL);
                    } else {
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
<<<<<<< HEAD
  
    
    // 自分が作ったグループ一覧を取得
    public List<GroupV1Dto> getMyGroupList(@Named("email") String email) {
=======

    public List<GroupV1Dto> getGroupList(@Named("email") String email) {
>>>>>>> branch 'master' of https://github.com/fr-Agile/ITSP_Server.git
        List<GroupV1Dto> list = new ArrayList<GroupV1Dto>();
        try {
            User user = UserService.getUserByEmail(email);
            if (user == null) {
<<<<<<< HEAD
                System.out.println("ユーザが見つかりません");
=======
                logger.warning("user not found");
>>>>>>> branch 'master' of https://github.com/fr-Agile/ITSP_Server.git
            } else {
<<<<<<< HEAD
                // userがownerであるグループを取得する
                List<Group> glist = GroupService.getGroupList(user);
                
                if (glist != null) {
                    
                    for(int i = 0;i<glist.size();++i){
                        List<UserV1Dto> userList = new ArrayList<UserV1Dto>();
                        // グループに所属しているユーザを取得する
                        List<UserGroupMap> ugmToUserList = glist.get(i).getUserGroupMapListRef().getModelList();
                        if (ugmToUserList != null) {
                            for (UserGroupMap ugmToUser : ugmToUserList) {
                                UserV1Dto userDto = new UserV1Dto();
                                CopyUtils.copyUser(userDto, ugmToUser
                                    .getUser()
                                    .getModel());
                                userList.add(userDto);
=======
                // userの所属しているグループを取得する
                List<UserGroupMap> ugmToGroupList =
                    UserGroupMapService.getGroupsByUser(user);
                if (ugmToGroupList != null) {
                    for (UserGroupMap ugmToGroup : ugmToGroupList) {
                        try {
                            Group group = ugmToGroup.getGroup().getModel();
                            List<UserV1Dto> userList =
                                new ArrayList<UserV1Dto>();
                            // グループに所属しているユーザを取得する
                            List<UserGroupMap> ugmToUserList =
                                group.getUserGroupMapListRef().getModelList();
                            if (ugmToUserList != null) {
                                for (UserGroupMap ugmToUser : ugmToUserList) {
                                    UserV1Dto userDto = new UserV1Dto();
                                    CopyUtils.copyUser(userDto, ugmToUser
                                        .getUser()
                                        .getModel());
                                    userList.add(userDto);
                                }
>>>>>>> branch 'master' of https://github.com/fr-Agile/ITSP_Server.git
                            }
                            // グループの情報をDtoにつめる
                            GroupV1Dto groupDto = new GroupV1Dto();
                            groupDto.setName(group.getName());
                            groupDto.setKey(Datastore.keyToString(group
                                .getKey()));
                            UserV1Dto owner = new UserV1Dto();
                            CopyUtils.copyUser(owner, group
                                .getUser()
                                .getModel());
                            groupDto.setOwner(owner);
                            groupDto.setUserlList(userList);

                            list.add(groupDto);
                        } catch (Exception e) {

                        }
<<<<<<< HEAD
                        // グループの情報をDtoにつめる
                        GroupV1Dto groupDto = new GroupV1Dto();
                        groupDto.setName(glist.get(i).getName());
                        groupDto.setKey(Datastore.keyToString(glist.get(i).getKey()));
                        UserV1Dto owner = new UserV1Dto();
                        CopyUtils.copyUser(owner, glist.get(i).getUser().getModel());
                        groupDto.setOwner(owner);
                        groupDto.setUserlList(userList);
                        
                        list.add(groupDto);
=======
>>>>>>> branch 'master' of https://github.com/fr-Agile/ITSP_Server.git
                    }
                }
            }
        } catch (Exception e) {
<<<<<<< HEAD
            System.out.println(e.toString());
=======
            logger.warning(e.getMessage());
>>>>>>> branch 'master' of https://github.com/fr-Agile/ITSP_Server.git
        }

        return list;
    }
}
