package com.wu.parker.websocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: wusq
 * @date: 2018/12/10
 */
@ServerEndpoint("/websocket/{token}")
@Component
public class WebSocketController {

    private static final Logger log = LoggerFactory.getLogger(WebSocketController.class);

    public static Map<String, Session> SESSION_MAP = new ConcurrentHashMap<>();

    /**
     * 连接建立成功调用的方法
     * @param session
     * @param token
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {
        log.info("{}建立连接", token);
        SESSION_MAP.put(token, session);
        log.info("建立连接后session数量={}", SESSION_MAP.size());
        send(token, "Hello, connection opened!");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        for(Map.Entry<String, Session> entry:SESSION_MAP.entrySet()){
            log.info("session.id={}", session.getId());
            if(session.getId().equals(entry.getValue().getId())){
                log.info("{}发来消息", entry.getKey());
            }
            break;
        }
        log.info("接收到消息：{}", message);

    }

    @OnClose
    public void onClose(Session session) {
        log.info("有连接关闭");
        Map<String, Session> map = new HashMap<>();
        SESSION_MAP.forEach((k, v) -> {
            if(!session.getId().equals(v.getId())){
                map.put(k, v);
            }
        });
        SESSION_MAP = map;
        log.info("断开连接后session数量={}", SESSION_MAP.size());
    }

    void send(String token, String message){
        Session session = SESSION_MAP.get(token);
        if(session != null){
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("发送信息错误{}", e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

}
