package jp.ac.titech.itpro.sds.fragile.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ScheduleTest extends AppEngineTestCase {

    private Schedule model = new Schedule();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
