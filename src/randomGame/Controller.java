package randomGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    public TextArea mainTextPanel;
    public TextArea lowerBound;
    public TextArea upperBound;
    public TextField numberGuessingInput;
    public Button submitNumberGuessingInput;
    public Label youWonLabel;
    public Button playAgain;

    RandomService randomService = new RandomService();

    @FXML
    private void initialize() {
        lowerBound.setText("0");
        upperBound.setText("100");
        randomService.generateRandomNumber();
        youWonLabel.visibleProperty().set(false);
    }

    public void guessingNumberSubmitted(ActionEvent actionEvent) {
        submitNumberGuessingInput.setOnAction(event -> {
                    try {
                        if (numberGuessingInput != null && (Integer.parseInt(numberGuessingInput.getText()) <= 100) && (Integer.parseInt(numberGuessingInput.getText()) >= 0))
                            if (randomService.isEqualToRandom(Integer.parseInt(numberGuessingInput.getText()))) {
                                youWonLabel.visibleProperty().set(true);
                                mainTextPanel.setText("You've guessed the number correctly! Generated number was " + randomService.getRandomNumber() + ". Took you " + randomService.getPreviousGuesses().size() + " tries:\n" + randomService.getPreviousGuesses());
                            } else {
                                randomService.addGuess(Integer.parseInt(numberGuessingInput.getText()));
                                lowerBound.setText(String.valueOf(randomService.getLowerBound()));
                                upperBound.setText(String.valueOf(randomService.getUpperBound()));
                                mainTextPanel.setText(String.valueOf(randomService.getPreviousGuesses()));
                            }
                        else {
                            mainTextPanel.setText("Please enter correct number from 0 to 100.");
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        mainTextPanel.setText("Please enter correct number from 0 to 100.");
                    }
                }
        );
    }


    public void restartGame(ActionEvent actionEvent) {
        playAgain.setOnAction(event -> {
            youWonLabel.visibleProperty().set(false);
            randomService.restart();
            lowerBound.setText(String.valueOf(randomService.getLowerBound()));
            upperBound.setText(String.valueOf(randomService.getUpperBound()));
            numberGuessingInput.clear();
            randomService.generateRandomNumber();
            mainTextPanel.clear();
        });
    }
}
