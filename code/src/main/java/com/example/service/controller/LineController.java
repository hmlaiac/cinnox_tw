package com.example.service.controller;

import com.example.service.model.BotReply;
import com.example.service.model.LineMessage;
import com.example.service.model.LineUser;
import com.example.service.repository.LineMessageRepository;
import com.example.service.repository.LineUserRepository;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.message.TextMessage;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/line")
public class LineController {
    @Autowired
    private LineUserRepository lineUserRepository;

    @Autowired
    private LineMessagingClient lineMessagingClient;

    @PostMapping("/chat")
    public String chat(@RequestBody BotReply botReply){
        String id = botReply.getMessageId();
        String message = botReply.getMessage();
        LineUser lineUser = lineUserRepository.findLineUserByUserId(id).get();

        String replyToken = lineUser.getToken();
        TextMessage responseMessage = new TextMessage(message);

        lineMessagingClient.replyMessage(new ReplyMessage(replyToken, responseMessage));

        return "SEND_MESSAGE_SUCCESS";
    }
}
