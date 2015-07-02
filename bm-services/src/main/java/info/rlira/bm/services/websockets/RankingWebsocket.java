package info.rlira.bm.services.websockets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/ranking")
public class RankingWebsocket {
	
    @Inject
    private SessionRegistry sessionRegistry;

    @OnOpen
    public void open(Session session, EndpointConfig conf) {
        sessionRegistry.add(session);
    }

    @OnClose
    public void close(Session session, CloseReason reason) {
        sessionRegistry.remove(session);
    }
//
//	public void send(@Observes Seat seat) {//Observes disparado por TheatreBox.buyTicket(int)
//	       sessionRegistry.getAll().forEach(session -> session.getAsyncRemote().sendText(toJson(seat)));
//	}
//	   
//	private String toJson(Seat seat) {
//       final JsonObject jsonObject = Json.createObjectBuilder()
//               .add("id", seat.getId())
//               .add("booked", seat.isBooked())
//               .build();
//       return jsonObject.toString();
//   }
}
