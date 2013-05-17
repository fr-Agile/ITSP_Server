package jp.ac.titech.itpro.sds.fragile.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FriendTest extends AppEngineTestCase {

    private Friend model = new Friend();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
