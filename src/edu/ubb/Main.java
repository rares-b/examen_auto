package edu.ubb;

import edu.ubb.repository.QuizRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        primaryStage.setTitle("Examen Auto");
        Scene scene = new Scene(root, 300, 275);
        scene.setFill(Color.LIGHTPINK);
        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        QuizRepository quizRepository = new QuizRepository();
        System.out.println(quizRepository.getQuiz().getQuestions().get(4));
        System.out.println(quizRepository.getQuiz().getAnswers().get(4).get(1));
        System.out.println(quizRepository.getQuiz().getCorrectAnswers().get(4));
        System.out.println(quizRepository.getQuiz().getQuestions().size());
    }
}
