package websocket.chat;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Message {

    private String from;
    private String to;
    private String content;

    @Override
    public String toString() {
        return String.format("{ \"from\": \"%s\", \"to\": \"%s\", \"msg\": \"%s\"", from, to, content);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
