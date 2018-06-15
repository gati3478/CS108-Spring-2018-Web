package websocket.chat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Logger;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
@ServerEndpoint(
        value = "/chat/{username}",
        decoders = MessageDecoder.class,
        encoders = MessageEncoder.class
)
public class ChatEndpoint {
    private final Logger log = Logger.getLogger(getClass().getName());

    private Session session;
    private String username;
    private static final Set<ChatEndpoint> chatEndpoints = new CopyOnWriteArraySet<>();
    private static Map<String, String> users = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException, EncodeException {
        log.info(session.getId() + " connected!");

        this.session = session;
        this.username = username;
        chatEndpoints.add(this);
        users.put(session.getId(), username);

        Message message = new Message();
        message.setFrom(username);
        message.setContent("Connected!");
        broadcast(message);
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException {
        log.info(message.toString());

        message.setFrom(users.get(session.getId()));
        sendMessageToOneUser(message);
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        log.info(session.getId() + " disconnected!");

        chatEndpoints.remove(this);
        Message message = new Message();
        message.setFrom(users.get(session.getId()));
        message.setContent("disconnected!");
        broadcast(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.severe(throwable.toString());
    }

    private static void broadcast(Message message) throws IOException, EncodeException {
        for (ChatEndpoint endpoint : chatEndpoints) {
            synchronized (endpoint) {
                endpoint.session.getBasicRemote().sendObject(message);
            }
        }
    }

    private static void sendMessageToOneUser(Message message) {
        for (ChatEndpoint endpoint : chatEndpoints) {
            synchronized (endpoint) {
                getSessionId(message.getTo()).ifPresent(sessionId -> {
                    if (endpoint.session.getId().equals(sessionId)) {
                        try {
                            endpoint.session.getBasicRemote().sendObject(message);
                        } catch (IOException | EncodeException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    private static Optional<String> getSessionId(String to) {
        if (users.containsValue(to)) {
            return users.keySet()
                    .stream()
                    .filter(sessionId -> users.get(sessionId).equals(to))
                    .findFirst();
        }
        return Optional.empty();
    }

}
