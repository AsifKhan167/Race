import java.util.Random;

public class Horse {
    private String name;
    private char symbol;
    private int distanceTravelled;
    private boolean fallen;
    private double confidence;
    private Random random = new Random();  // Add a Random object for generating random numbers

    public Horse(char horseSymbol, String horseName, double horseConfidence) {
        this.symbol = horseSymbol;
        this.name = horseName;
        this.confidence = horseConfidence;
        this.distanceTravelled = 0;
        this.fallen = false;
    }

    public void moveForward() {
        if (!fallen) {
            // Use confidence and randomness to affect movement; higher confidence has a higher chance to move further
            if (random.nextDouble() < confidence) {
                distanceTravelled += random.nextInt(3) + 1;  // Moves 1 to 3 steps forward based on a random value
            }
            // Introduce a chance of falling based on confidence (higher confidence has a slight risk of falling)
            if (random.nextDouble() < (0.01 * (1 - confidence))) {
                fallen = true;
            }
        }
    }

    public void fall() {
        this.fallen = true;
    }

    public double getConfidence() {
        return confidence;
    }

    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void goBackToStart() {
        this.distanceTravelled = 0;
    }

    public boolean hasFallen() {
        return fallen;
    }

    public void setConfidence(double newConfidence) {
        this.confidence = newConfidence;
    }

    public void setSymbol(char newSymbol) {
        this.symbol = newSymbol;
    }
}
