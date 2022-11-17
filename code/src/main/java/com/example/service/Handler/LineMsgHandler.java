package com.example.service.Handler;

import com.example.service.model.LineMessage;
import com.example.service.model.LineUser;
import com.example.service.repository.LineMessageRepository;
import com.example.service.repository.LineUserRepository;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.source.Source;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sound.sampled.Line;
import java.util.concurrent.ExecutionException;

@LineMessageHandler
public class LineMsgHandler {
    @Autowired
    LineMessagingClient lineMessagingClient;

    @Autowired
    LineMessageRepository lineMessageRepository;

    @Autowired
    LineUserRepository lineUserRepository;

    @EventMapping
    public void handleDefaultMessageEvent(MessageEvent<TextMessageContent> messageEvent) {
        /**
         * REPLY_TOKEN: String replyToken   ---> This the unique key to identify the user
         * USER_ID: String lineId
         * MESSAGE: String message
         * Send message with lineMessagingClient
         */
        Source source = messageEvent.getSource();
        String lineId = source.getUserId();
        String replyToken = messageEvent.getReplyToken();
        String message = messageEvent.getMessage().getText();
        LineMessage lineMessage = new LineMessage(lineId, message);
        lineMessageRepository.save(lineMessage);

        String displayName = "NONE";
        String language = "NONE";
        String pictureUrl = "NONE";

        /**
         * Test the boot with "hello bot"
         */
        if( message.equalsIgnoreCase("hello bot") ){
            String greet = "Hello everyone. I am the bot created by Thomas Lai\n Nice to meet you!";
            TextMessage responseMessage = new TextMessage(greet);
            lineMessagingClient.replyMessage(new ReplyMessage(replyToken, responseMessage));
            return;
        }

        try {
            /**
             * Profile Information
             * USER_ID: String lineId --> Unique
             * Language: String language
             * Last_UPDATE_TIME: date
             */
            UserProfileResponse profile = lineMessagingClient.getProfile(lineId).get();
            try {
                displayName = profile.getDisplayName();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                language = profile.getLanguage();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                pictureUrl = profile.getPictureUrl().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        LineUser lineUser = new LineUser(lineId, displayName, pictureUrl, language, replyToken);
        lineUserRepository.save(lineUser);
    }
}
