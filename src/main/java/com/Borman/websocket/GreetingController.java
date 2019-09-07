package com.Borman.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class GreetingController {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Autowired
    WebSocketEventListener webSocketEventListener;


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {

        Thread.sleep(1000); // simulated delay
        System.out.println("Message received: " + message.getName());

        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");

    }


    @MessageMapping("/timer")
    @SendTo("/timerresponse/response")
    public Webstats timer(String request, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        Webstats webstats = new Webstats();
        webstats.setUsersOnline(webSocketEventListener.getUsersConnected());
        webstats.setGlobalTimeSpent(atomicInteger.incrementAndGet());
        return webstats;
    }

}
