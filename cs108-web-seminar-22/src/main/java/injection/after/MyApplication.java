package injection.after;

public class MyApplication implements Consumer {

    private MessageService service;

    public MyApplication(MessageService svc) {
        service = svc;
    }

    @Override
    public void processMessages(String msg, String rec) {
        // do some msg validation, manipulation logic etc
        service.sendMessage(msg, rec);
    }

}
