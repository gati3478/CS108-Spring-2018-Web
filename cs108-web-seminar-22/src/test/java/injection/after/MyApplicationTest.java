package injection.after;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class MyApplicationTest {

    @Test
    public void testProcess1() {
        MessageService service = mock(MessageService.class);

        MyApplication app = new MyApplication(service);

        String msg = "ohhaidoggy";
        String rec = "lisa";
        app.processMessages(msg, rec);

        verify(service, times(1)).sendMessage(msg, rec);
    }

    @Test
    public void testProcess2() {
        MessageService service = spy(SmsServiceImpl.class);

        MyApplication app = new MyApplication(service);

        String msg = "ahmmm";
        String rec = "ididnaht";
        app.processMessages(msg, rec);

        verify(service, times(1)).sendMessage(msg, rec);
    }

}
