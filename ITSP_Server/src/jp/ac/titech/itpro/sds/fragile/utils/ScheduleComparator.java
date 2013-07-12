package jp.ac.titech.itpro.sds.fragile.utils;

import java.util.Comparator;

import jp.ac.titech.itpro.sds.fragile.model.Schedule;

public class ScheduleComparator implements Comparator<Schedule> {

    public int compare(Schedule arg0, Schedule arg1) {
        // compare with startTime
        return (int) (arg0.getStartTime() - arg1.getStartTime());
    }
}
