package injection.before;

import org.junit.Test;

public class MyApplicationTest {

    @Test
    public void thisIsNotEvenATest() {
        MyApplication app = new MyApplication();
        app.processMessages("ohhaimark", "james@franco.tk");
    }

}
