package injection.after;

public class SmsServiceImpl implements MessageService {

    @Override
    public void sendMessage(String msg, String rec) {
        // logic to send SMS
        System.out.println("SMS sent to " + rec + " with Message = " + msg);
    }

}
