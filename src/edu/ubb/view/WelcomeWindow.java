package edu.ubb.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class WelcomeWindow {

    private static Stage stage;

    public void setWelcomeWindow()
    {
        stage = new Stage();
        stage.setTitle("Examen Auto");
        Scene scene = new Scene(new Group());

        stage.setWidth(800);
        stage.setHeight(400);

        Label txt = new Label("Bun Venit");
        txt.setStyle("-fx-font-size:30; -fx-font-weight:bold;");
        txt.setTextAlignment(TextAlignment.CENTER);
        txt.setLayoutX(325);
        txt.setLayoutY(10);

        String instructionsText = """
                Examenul auto constă în 26 de întrebări. Fiecare întrebare poate avea unul sau mai multe răspunsuri corecte.
                Aveți la dispoziție 30 de minute pentru a răspunde la întrebări. Dacă rămân întrebări fără răspuns la finalul celor 30 de minute, acestea vor fi considerate greșite.
                Dacă răspundeți greșit la mai mult de 4 întrebări examenul se va termina și veți primi calificativul "RESPINS".
                """;
        Label instructions = new Label(instructionsText);
        instructions.setStyle("-fx-font-size:12;");
        instructions.setMaxWidth(stage.getWidth());
        instructions.setTextAlignment(TextAlignment.CENTER);
        instructions.setWrapText(true);
        instructions.setLayoutY(95);


        Button start = new Button("Start Examen Auto");
        start.setLayoutX(260);
        start.setLayoutY(200);
        start.setPrefWidth(249);
        start.setPrefHeight(50);

        // cand utilizatorul apasa pe butonul de start se creeaza un quiz nou si se inchide fereastra curenta
        start.setOnAction(e -> {
            new QuizView();
            stage.hide();
        });

        ((Group) scene.getRoot()).getChildren().addAll(txt, instructions, start);

        stage.setScene(scene);
        stage.show();
    }
}
