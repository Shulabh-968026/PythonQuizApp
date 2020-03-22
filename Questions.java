package com.example.pythonquizapp;

public class Questions {
    private int answerId;
    private boolean answerType;
    public Questions(int answerId,boolean answerType)
    {
        this.answerId=answerId;
        this.answerType=answerType;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public boolean isAnswerType() {
        return answerType;
    }

    public void setAnswerType(boolean answerType) {
        this.answerType = answerType;
    }
}
