package com.aslufcode.pizza_backend.controllers;

import com.aslufcode.pizza_backend.command.FeedbackCommand;
import com.aslufcode.pizza_backend.command.FeedbackInvoker;
import com.aslufcode.pizza_backend.dao.FeedbackDao;
import com.aslufcode.pizza_backend.models.Feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackDao feedbackDAO;

    @PostMapping("/submit")
    public void submitFeedback(@RequestBody Feedback feedback) {
        FeedbackCommand feedbackCommand = new FeedbackCommand(feedback, feedbackDAO);
        FeedbackInvoker invoker = new FeedbackInvoker();
        invoker.setCommand(feedbackCommand);
        invoker.executeCommand();
    }
}
