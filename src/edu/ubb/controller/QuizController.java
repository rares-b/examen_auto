package edu.ubb.controller;


import edu.ubb.QuizCreation;
import edu.ubb.view.FinalWindow;
import edu.ubb.view.QuizView;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

public class QuizController {

    private RadioButton b1, b2, b3;
    private Text qNo, questions;
    private Label wrongAns;

    private static int wrongAnswers;
    private static int currentQuestion;
    private QuizCreation quiz;

    public QuizController(RadioButton b1, RadioButton b2, RadioButton b3, Text qNo, Text questions, Label wrongAns, QuizCreation quiz) {
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.qNo = qNo;
        this.wrongAns = wrongAns;
        this.questions = questions;
        wrongAnswers = 0;
        currentQuestion = 0;

        this.quiz = quiz;

        this.readQuestion(currentQuestion);
    }

    public static int getCurrentQuestion() {
        return currentQuestion;
    }

    public static int getWrongAnswers() {
        return wrongAnswers;
    }

    /**
     * Reads and displays a new question with its answers.
     * @param i the number of the current question
     */
    public void readQuestion(int i) {
        this.qNo.setText(i + 1 + ". "); // numarul intrebarii
        this.questions.setText(this.quiz.getQuiz().getQuestions().get(i)); // textul intrebarii

        // raspunsurile
        this.b1.setText(this.quiz.getQuiz().getAnswers().get(i).get(0));
        this.b2.setText(this.quiz.getQuiz().getAnswers().get(i).get(1));
        this.b3.setText(this.quiz.getQuiz().getAnswers().get(i).get(2));

        this.wrongAns.setText("Răspunsuri greșite: " + wrongAnswers); // numarul curent de raspunsuri gresite

        // debifam raspunsurile selectate anterior
        this.b1.setSelected(false);
        this.b2.setSelected(false);
        this.b3.setSelected(false);
    }

    /**
     * Verifies the selected answers.
     */
    public void verifyAnswer() {
        // numaram cate raspunsuri au fost selectate
        int nrOfButtonsSelected = 0;
        if (this.b1.isSelected()) nrOfButtonsSelected++;
        if (this.b2.isSelected()) nrOfButtonsSelected++;
        if (this.b3.isSelected()) nrOfButtonsSelected++;


        // daca nu au fost selectate atatea raspunsuri cate raspunsuri corecte sunt, e gresit din start
        if (this.quiz.getQuiz().getCorrectAnswers().get(currentQuestion).size() != nrOfButtonsSelected) {
            wrongAnswers++;
            return;
        }

        // daca raspunsul e selectat si nu se afla in lista de raspunsuri corecte atunci e gresit

        if (b1.isSelected() && !this.quiz.getQuiz().getCorrectAnswers().get(currentQuestion).contains(b1.getText())) {
            wrongAnswers++;
            return;
        }
        if (b2.isSelected() && !this.quiz.getQuiz().getCorrectAnswers().get(currentQuestion).contains(b2.getText())) {
            wrongAnswers++;
            return;
        }
        if (b3.isSelected() && !this.quiz.getQuiz().getCorrectAnswers().get(currentQuestion).contains(b3.getText())) {
            wrongAnswers++;
        }
    }

    /**
     * Action performed when the 'next' button is pressed.
     */
    public void btnNext() {
        this.verifyAnswer();
        currentQuestion++;

        if (wrongAnswers > 4) {
            new FinalWindow().setFinalWindow();
            QuizView.hideStage();
        }

        if (currentQuestion < 26) {
            this.readQuestion(currentQuestion);
        }
        else {
            new FinalWindow().setFinalWindow();
            QuizView.hideStage();
        }
    }
}
