package info.rlira.bm.services.websockets;

import info.rlira.bm.services.to.GeneralRankingTO;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/ranking", encoders={GeneralRankingEncoder.class})
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
    
    public void send(@Observes GeneralRankingTO generalRanking) {
    	//Java 8 Lambda = sessionRegistry.getAll().forEach(session -> session.getAsyncRemote().sendText(toJson(seat)));
    	for(Session session : sessionRegistry.getAll()){
    		session.getAsyncRemote().sendObject(generalRanking);
    	}
    }

}
