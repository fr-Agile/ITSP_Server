package jp.ac.titech.itpro.sds.fragile.api.container;

import java.util.Date;
import java.util.List;

public class RepeatScheduleContainer {
    
    private List<Integer> integers;
    private List<Date> dates;
    
    public List<Integer> getIntegers() {
        return integers;
    }
    public void setIntegers(List<Integer> integers) {
        this.integers = integers;
    }
    public List<Date> getDates() {
        return dates;
    }
    public void setDates(List<Date> dates) {
        this.dates = dates;
    }
    
    
}
