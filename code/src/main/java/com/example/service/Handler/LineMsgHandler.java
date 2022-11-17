package com.example.service.Handler;

import com.example.service.model.LineMessage;
import com.example.service.repository.LineRepository;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.source.Source;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutionException;

@LineMessageHandler
public class LineMsgHandler {
    @Autowired
    LineMessagingClient lineMessagingClient;

    @Autowired
    LineRepository lineRepository;
//    @EventMapping
//    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
//        System.out.println("event: " + event);
//        return new TextMessage(event.getMessage().getText());
//    }

    @EventMapping
    public void handleDefaultMessageEvent(MessageEvent<TextMessageContent> messageEvent) {
        /**
         * REPLY_TOKEN: String replyToken   ---> This the unique key to identify the user
         * USER_ID: String lineId
         * MESSAGE: String message
         * USER_NAME: displayName
         * Send message with lineMessagingClient
         */


        try {
            Source source = messageEvent.getSource();
            String lineId = source.getUserId();
            String replyToken = messageEvent.getReplyToken();
            String message = messageEvent.getMessage().getText();
            String displayName = lineMessagingClient.getProfile(lineId).get().getDisplayName();

            LineMessage lineMessage = new LineMessage(replyToken, lineId, message, displayName);
            lineRepository.save(lineMessage);
//            Source source = messageEvent.getSource();
//            String lineId = source.getUserId();
//            String replyToken = messageEvent.getReplyToken();
//            String message = messageEvent.getMessage().getText();
//
//            String displayName = lineMessagingClient
//                    .getProfile(lineId).get().getDisplayName();
//
//            String answer = "Hello";
//            TextMessage responseMessage = new TextMessage(answer);
//
//            lineMessagingClient.replyMessage(new ReplyMessage(replyToken, responseMessage));




        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
