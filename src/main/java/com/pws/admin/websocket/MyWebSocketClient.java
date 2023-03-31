package com.pws.admin.websocket;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.Session;

@ClientEndpoint
public class MyWebSocketClient {

@ClientEndpoint
public class WebSocketClient {

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
        // handle incoming messages
    }

    public void sendMessage(Session session, String message) throws Exception {
        session.getBasicRemote().sendText(message);
        // handle outgoing messages
    }

}}
//    @OnOpen
//    public void onOpen(Session session) {
//        System.out.println("WebSocket connection opened");
//        session.getAsyncRemote().sendText("Hello, server!");
//    }
//
//    @OnMessage
//    public void onMessage(String message) {
//        System.out.println("Received message: " + message);
//        // handle incoming messages
//    }
//
//    public void sendMessage(String message) {
//        // handle outgoing messages
//        session.getAsyncRemote().sendText(message);
//    }
//}
//
//
//
