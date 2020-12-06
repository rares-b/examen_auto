package edu.ubb.repository;

import edu.ubb.model.Quiz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QuizRepository {

    private Quiz quiz;

    public QuizRepository() {
        this.createQuiz();
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    private void createQuiz() {
        String line;
        int currentLine = 1;
        List<String> questions = new ArrayList<>();
        List<List<String>> answers = new ArrayList<>();
        List<List<String>> correctAnswers = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("L5_map.txt"));

            while ((line = bufferedReader.readLine()) != null) {
                if (currentLine % 3 == 1) {
                    questions.add(line);
                }
                if (currentLine % 3 == 2) {
                    answers.add(Arrays.asList(line.split(";")));
                }
                if (currentLine % 3 == 0) {
                    correctAnswers.add(Arrays.asList(line.split(";")));
                }
                currentLine++;
            }
            bufferedReader.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("Unable to open file");
        }
        catch (IOException ex) {
            System.out.println("Error reading file");
        }

        RandomQuiz randomQuiz = new RandomQuiz(questions, answers, correctAnswers);

        quiz = new Quiz(new Random().nextInt(1000), new Random().nextInt(1000),
                randomQuiz.nrAnswers - randomQuiz.nrCorrectAnswers, randomQuiz.nrCorrectAnswers,
                randomQuiz.questions, randomQuiz.answers, randomQuiz.correctAnswers);

    }


    static class RandomQuiz {
        List<String> questions = new ArrayList<>();
        List<List<String>> answers = new ArrayList<>();
        List<List<String>> correctAnswers = new ArrayList<>();
        int nrAnswers = 0;
        int nrCorrectAnswers = 0;

        public RandomQuiz(List<String> allQuestions, List<List<String>> allAnswers, List<List<String>> allCorrectAnswers) {
            Random random = new Random();

            for (int i = 0; i < 26; i++) {
                int randomIndex = random.nextInt(allQuestions.size());

                this.questions.add(allQuestions.get(randomIndex));
                this.answers.add(allAnswers.get(randomIndex));
                this.correctAnswers.add(allCorrectAnswers.get(randomIndex));

                allQuestions.remove(randomIndex);
                allAnswers.remove(randomIndex);
                allCorrectAnswers.remove(randomIndex);
            }
            for (List<String> a : answers) {
                nrAnswers += a.size();
            }
            for (List<String> a : correctAnswers) {
                nrCorrectAnswers += a.size();
            }
        }
    }

}
