package randomGame;

import java.util.ArrayList;
import java.util.Random;

public class RandomService {
    int upperBound = 100;
    int lowerBound = 0;
    ArrayList<String> previousGuesses = new ArrayList<>();

    int randomNumber;

    public void generateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(100);
    }

    public boolean isEqualToRandom(int guess) {
        return (randomNumber == guess);
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public ArrayList<String> getPreviousGuesses() {
        return previousGuesses;
    }

    public void setNewBounds(int random, int guess) {
        if (guess > random && guess < upperBound) {
            upperBound = guess;
        } else if (guess < random && guess > lowerBound) {
            lowerBound = guess;
        }
    }

    public int getUpperBound() {
        return upperBound;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public void restart() {
        upperBound = 100;
        lowerBound = 0;
        previousGuesses.clear();
    }

    public void addGuess(int guess) {
        setNewBounds(getRandomNumber(), guess);
        previousGuesses.add("#" + previousGuesses.size() + " Guessed number: " + guess + ". New interval: [" + lowerBound + "; " + upperBound + "]");
    }
}
