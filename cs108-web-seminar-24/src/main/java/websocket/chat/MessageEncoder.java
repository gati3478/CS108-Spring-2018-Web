package websocket.chat;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.logging.Logger;

public class MessageEncoder implements Encoder.Text<Message> {
    private final Logger log = Logger.getLogger(getClass().getName());

    @Override
    public String encode(Message message) {
        log.info("Converting message object to json format");

        Gson gson = new Gson();
        return gson.toJson(message);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        log.info("Message encoder has been initialized");
    }

    @Override
    public void destroy() {
        log.info("Message encoder is being gracefully removed");
    }

}
