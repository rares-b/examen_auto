package view;

import controller.QuizController;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class FinalWindow {
    private static Stage subStage;
    private static boolean invoked = false; // variabila care arata daca aceasta fereastra a fost invocata, pentru ca timerul sa se opreasca

    public static boolean getInvoked() {
        return invoked;
    }

    public void setFinalWindow() {

        invoked = true;
        subStage = new Stage();
        subStage.setTitle("Rezultat final");
        Scene scene = new Scene(new Group());

        subStage.setWidth(800);
        subStage.setHeight(400);

        Label result = new Label();
        result.setStyle("-fx-font-size:30; -fx-font-weight:bold;");
        result.setTextAlignment(TextAlignment.CENTER);
        result.setLayoutX(325);
        result.setLayoutY(50);

        ImageView rightImg = new ImageView();
        ImageView wrongImg = new ImageView();
        Image right = new Image("/icons/right.png");
        Image wrong = new Image("/icons/wrong.png");

        rightImg.setImage(right);
        rightImg.setFitWidth(22);
        rightImg.setFitHeight(22);
        rightImg.setLayoutX(279);
        rightImg.setLayoutY(172);

        wrongImg.setImage(wrong);
        wrongImg.setFitWidth(20);
        wrongImg.setFitHeight(20);
        wrongImg.setLayoutX(280);
        wrongImg.setLayoutY(194);

        Label rightAnswers = new Label(QuizController.getCurrentQuestion() - QuizController.getWrongAnswers() + " răspunsuri corecte");
        rightAnswers.setStyle("-fx-font-size:18;");
        rightAnswers.setLayoutX(305);
        rightAnswers.setLayoutY(170);

        Label wrongAnswers = new Label(QuizController.getWrongAnswers() + " răspunsuri greșite");
        wrongAnswers.setStyle("-fx-font-size:18;");
        wrongAnswers.setLayoutX(305);
        wrongAnswers.setLayoutY(190);

        Button retry = new Button("Încearcă alt examen");
        retry.setLayoutX(260);
        retry.setLayoutY(250);
        retry.setPrefWidth(249);
        retry.setPrefHeight(50);

        // calificativul in functie de numarul de raspunsuri corecte
        if ((QuizController.getCurrentQuestion() - QuizController.getWrongAnswers() < 22)) {
            result.setText("RESPINS");
        } else {
            result.setText("ADMIS");
        }

        // la retry resetam invoked si facem un quiz nou
        retry.setOnAction(e -> {
            invoked = false;
            new QuizView();
            subStage.hide();
        });

        ((Group) scene.getRoot()).getChildren().addAll(result, rightImg, wrongImg, rightAnswers, wrongAnswers, retry);

        subStage.setScene(scene);
        subStage.show();

        // la inchidere oprim timer threadul
        subStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }
}
