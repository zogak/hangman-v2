package com.team6.hangman.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;

@Slf4j
public class ChatSocketHandler extends TextWebSocketHandler {

    private HashMap<String, WebSocketSession> sessions = new HashMap<String, WebSocketSession>();

    // Client에서 ws.send(data)가 호출되면 실행
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message){
        String payload = message.getPayload();
        log.info("chat: "); log.info(payload);

        try{
            for(String key : sessions.keySet()){ // 접속한 모든 세션에 메시지 전송
                WebSocketSession ss = sessions.get(key);
                ss.sendMessage(new TextMessage(payload));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        super.afterConnectionEstablished(session);
        sessions.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
        sessions.remove(session.getId());
        super.afterConnectionClosed(session, status);
    }

}
