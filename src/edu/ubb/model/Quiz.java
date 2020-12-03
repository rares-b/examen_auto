package edu.ubb.model;

import java.util.List;

public class Quiz {
    private int id, number, nrIncorrectAns, nrCorrectAns;
    private List<String> questions;
    private List<List<String>> answers, correctAnswers;

    public Quiz(int id, int number, int nrIncorrectAns, int nrCorrectAns, List<String> questions, List<List<String>> answers, List<List<String>> correctAnswers) {
        this.id = id;
        this.number = number;
        this.nrIncorrectAns = nrIncorrectAns;
        this.nrCorrectAns = nrCorrectAns;
        this.questions = questions;
        this.answers = answers;
        this.correctAnswers = correctAnswers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNrIncorrectAns() {
        return nrIncorrectAns;
    }

    public void setNrIncorrectAns(int nrIncorrectAns) {
        this.nrIncorrectAns = nrIncorrectAns;
    }

    public int getNrCorrectAns() {
        return nrCorrectAns;
    }

    public void setNrCorrectAns(int nrCorrectAns) {
        this.nrCorrectAns = nrCorrectAns;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public List<List<String>> getAnswers() {
        return answers;
    }

    public void setAnswers(List<List<String>> answers) {
        this.answers = answers;
    }

    public List<List<String>> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(List<List<String>> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", number=" + number +
                ", nrIncorrectAns=" + nrIncorrectAns +
                ", nrCorrectAns=" + nrCorrectAns +
                ", questions=" + questions +
                ", answers=" + answers +
                ", correctAnswers=" + correctAnswers +
                '}';
    }
}
