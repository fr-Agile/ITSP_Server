package jp.ac.titech.itpro.sds.fragile.api.container;

import java.util.List;

import jp.ac.titech.itpro.sds.fragile.api.dto.RepeatScheduleV1Dto;

public class RepeatScheduleListContainer {
    private List<RepeatScheduleV1Dto> list;

    public List<RepeatScheduleV1Dto> getList() {
        return list;
    }

    public void setList(List<RepeatScheduleV1Dto> list) {
        this.list = list;
    }
}
