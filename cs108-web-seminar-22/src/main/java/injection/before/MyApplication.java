package injection.before;

public class MyApplication {

    private EmailService email = new EmailService();

    public void processMessages(String msg, String rec) {
        // Do some msg validation, manipulation logic etc
        email.sendEmail(msg, rec);
    }

}
