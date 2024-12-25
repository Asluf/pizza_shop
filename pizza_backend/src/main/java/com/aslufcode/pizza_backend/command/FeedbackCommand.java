package com.aslufcode.pizza_backend.command;

import com.aslufcode.pizza_backend.models.Feedback;
import com.aslufcode.pizza_backend.dao.FeedbackDao;

public class FeedbackCommand implements Command {
    private final Feedback feedback;
    private final FeedbackDao feedbackDAO;

    public FeedbackCommand(Feedback feedback, FeedbackDao feedbackDAO) {
        this.feedback = feedback;
        this.feedbackDAO = feedbackDAO;
    }

    @Override
    public void execute() {
        feedbackDAO.saveFeedback(feedback);
    }
}
