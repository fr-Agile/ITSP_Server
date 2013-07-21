package jp.ac.titech.itpro.sds.fragile.utils;

import jp.ac.titech.itpro.sds.fragile.api.dto.UserV1Dto;
import jp.ac.titech.itpro.sds.fragile.model.User;

public class CopyUtils {
    public static void copyUser(UserV1Dto dto, User data) {
        dto.setEmail(data.getEmail());
        dto.setFirstName(data.getFirstName());
        dto.setLastName(data.getLastName());
    }
}
