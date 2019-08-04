package edu.udacity.java.nano.chat;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session WebSocket Session
 */

@Component
@ServerEndpoint(value = "/chat", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class WebSocketChatServer {

	/**
	 * All chat sessions.
	 */
	private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

	private static void sendMessageToAll(Message msg) {
		// TODO: add send message method.

		for (Entry<String, Session> session : onlineSessions.entrySet()) {
			if (session.getValue().isOpen()) {
				session.getValue().getAsyncRemote().sendObject(msg);
			}
		}

	}

	/**
	 * Open connection, 1) add session, 2) add user.
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) {
		// TODO: add on open connection.
		onlineSessions.put(session.getId(), session);
		
	}

	/**
	 * Send message, 1) get username and session, 2) send message to all.
	 */
	@OnMessage
	public void onMessage(Session session, Message jsonStr) {
		// TODO: add send message.
		jsonStr.setType("SPEAK");
		jsonStr.setOnlineCount(onlineSessions.size());
		sendMessageToAll(jsonStr);
	}

	/**
	 * Close connection, 1) remove session, 2) update user.
	 */
	@OnClose
	public void onClose(Session session) {
		// TODO: add close connection.
		String keyString = null;
		for (Map.Entry<String, Session> sessionMap : onlineSessions.entrySet()) {
			if (session.equals(sessionMap.getValue())) {
				keyString = sessionMap.getKey();
				break;
			}
		}
		onlineSessions.remove(keyString);
	}

	/**
	 * Print exception.
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		error.printStackTrace();
	}

}
