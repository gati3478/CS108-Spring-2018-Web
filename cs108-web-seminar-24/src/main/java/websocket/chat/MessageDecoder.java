package websocket.chat;

import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.util.logging.Logger;

public class MessageDecoder implements Decoder.Text<Message> {
    private final Logger log = Logger.getLogger(getClass().getName());

    @Override
    public Message decode(String json) {
        log.info("Incoming message : " + json);

        Gson gson = new Gson();
        return gson.fromJson(json, Message.class);
    }

    @Override
    public boolean willDecode(String json) {
        return (json != null);
    }

    public void init(EndpointConfig endpointConfig) {
        log.info("Message decoder has been initialized");
    }

    @Override
    public void destroy() {
        log.info("Message decoder is being gracefully removed");
    }

}
