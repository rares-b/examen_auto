package edu.ubb.view;

import edu.ubb.Countdown;
import edu.ubb.QuizCreation;
import edu.ubb.controller.QuizController;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class QuizView {
    private static Stage subStage;

    QuizView() {
        subStage = new Stage();
        subStage.setTitle("Examen Auto");

        Scene scene = new Scene(new Group());

        subStage.setWidth(1000);
        subStage.setHeight(400);

        Text qNo = new Text();
        qNo.setLayoutX(20);
        qNo.setLayoutY(56);
        qNo.setStyle("-fx-font-size:18;");

        Text questions = new Text();
        questions.setWrappingWidth(subStage.getWidth() - 70);
        questions.setLayoutX(50);
        questions.setLayoutY(56);
        questions.setStyle("-fx-font-size:18;");

        RadioButton b1 = new RadioButton();
        b1.setMaxWidth(subStage.getWidth() - 70);
        b1.setWrapText(true);
        b1.setLayoutX(50);
        b1.setLayoutY(120);
        b1.setStyle("-fx-font-size:16;");

        RadioButton b2 = new RadioButton();
        b2.setMaxWidth(subStage.getWidth() - 70);
        b2.setWrapText(true);
        b2.setLayoutX(50);
        b2.setLayoutY(175);
        b2.setStyle("-fx-font-size:16;");

        RadioButton b3 = new RadioButton();
        b3.setMaxWidth(subStage.getWidth() - 70);
        b3.setWrapText(true);
        b3.setLayoutX(50);
        b3.setLayoutY(230);
        b3.setStyle("-fx-font-size:16;");

        Button next = new Button("Următoarea întrebare");
        next.setLayoutX(57);
        next.setLayoutY(299);
        next.setPrefWidth(157);
        next.setPrefHeight(34);

        Label wrongAns = new Label();
        wrongAns.setStyle("-fx-font-size:12");
        wrongAns.setLayoutX(700);
        wrongAns.setLayoutY(310);

        Label timerLabel = new Label();
        timerLabel.setStyle("-fx-font-size: 2em;");
        timerLabel.setLayoutX(925);
        timerLabel.setLayoutY(5);

        // cream timerul si pornim countdownul
        Countdown countdown = new Countdown(timerLabel);
        countdown.startTime();

        ((Group) scene.getRoot()).getChildren().addAll(qNo, questions, b1, b2, b3, next, wrongAns, timerLabel);

        // cream un nou quiz
        QuizCreation newQuiz = new QuizCreation();
        QuizController quiz = new QuizController(b1, b2, b3, qNo, questions, wrongAns, newQuiz);

        // event pentru butonul de next
        next.setOnAction(e -> {
            quiz.btnNext();
            if (QuizController.getCurrentQuestion() == 25) next.setText("Finalizează examenul");
        });

        subStage.setScene(scene);
        subStage.show();

        // cand stingem fereastra oprim threadul de timer
        subStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }


    public static void hideStage()
    {
        QuizView.subStage.hide();
    }

}
