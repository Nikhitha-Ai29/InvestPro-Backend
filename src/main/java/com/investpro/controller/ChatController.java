package com.investpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.investpro.dto.ChatRequest;
import com.investpro.service.ChatService;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public String chat(@RequestBody ChatRequest request) {
        return chatService.getReply(request.getMessage());
    }
}