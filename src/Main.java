import view.WelcomeWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        WelcomeWindow mainV = new WelcomeWindow();
        mainV.setWelcomeWindow();

        primaryStage.setOnCloseRequest(e -> {
                Platform.exit();
                System.exit(0);
            });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
