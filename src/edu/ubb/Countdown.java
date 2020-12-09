package edu.ubb;

import edu.ubb.view.FinalWindow;
import edu.ubb.view.QuizView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class Countdown {

    private static final Integer STARTTIME = 1800; // timpul disponibil in secunde
    private Timeline timeline;
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

    public Countdown(Label timerLabel) {
        // Bind the timerLabel text property to the timeSeconds property
        timerLabel.textProperty().bind(timeSeconds.asString());
    }

    /**
     * Starts the countdown
     */
    public void startTime() {

        // setam secundele disponibile si cream un timeline care face timerul sa scada secundele
        timeSeconds.set(STARTTIME);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME),
                        new KeyValue(timeSeconds, 0)));
        timeline.play();

        // cream un Timer care va opri testul si va apela fereastra finala cand timpul s-a terminat
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask(){
            @Override
            public void run() {
                // daca a fost apelata deja fereastra finala in cazul in care utilizatorul a raspuns gresit
                // la mai mult de 4 intrebari, atunci terminam threadul
                if (FinalWindow.getInvoked()) {
                    myTimer.cancel();
                    myTimer.purge();
                    return;
                }

                // facem actiunea pe threadul pe care avem aplicatia de javafx
                Platform.runLater(() -> {
                    QuizView.hideStage();
                    new FinalWindow().setFinalWindow();}
                    );
                }
            }, STARTTIME * 1000);
    }
}
