package jp.ac.titech.itpro.sds.fragile.api.container;

import java.util.List;

import jp.ac.titech.itpro.sds.fragile.api.dto.ScheduleV1Dto;

public class ScheduleListContainer {
    private List<ScheduleV1Dto> list;

    public List<ScheduleV1Dto> getList() {
        return list;
    }

    public void setList(List<ScheduleV1Dto> list) {
        this.list = list;
    }
}
