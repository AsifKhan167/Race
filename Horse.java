import java.util.Random;

public class Horse {
    private String name;
    private char symbol;
    private int distanceTravelled;
    private boolean fallen;
    private double confidence;
    private Random random = new Random(); 

    public Horse(char horseSymbol, String horseName, double horseConfidence) {
        this.symbol = horseSymbol;
        this.name = horseName;
        setConfidence(horseConfidence); 
        this.distanceTravelled = 0;
        this.fallen = false;
    }

    public void moveForward() {
        if (!fallen && random.nextDouble() < confidence) {
            distanceTravelled += random.nextInt(3) + 1; // Moves 1 to 3 steps forward
        }
        if (!fallen && random.nextDouble() < (0.03 * (1 - 1.2*confidence))) {
            fall();
        }
    }

    public void fall() {
        this.fallen = true;
    }

    public void increaseConfidence() {
        if (confidence < 0.9) {
            confidence += 0.1;
        } else {
            confidence = 1.0;
        }
    }
    
    public void increaseConfidenceByHalf() {
        if (confidence < 0.95) {
            confidence += 0.05;
        } else {
            confidence = 1.0;
        }
    }

    public void decreaseConfidence() {
        if (confidence > 0.3) {
            confidence -= 0.1;
        } else {
            confidence = 0.3; // Ensures confidence does not drop below 0.1
        }
    }

    public void resetForNewRace() {
        this.distanceTravelled = 0;
        this.fallen = false;
    }

    public void goBackToStart() {
        this.distanceTravelled = 0;
    }

    public void setSymbol(char newSymbol) {
        this.symbol = newSymbol;
    }

    public void setConfidence(double newConfidence) {
        if (newConfidence >= 0.0 && newConfidence <= 1.0) {
            this.confidence = newConfidence;
        } else {
            throw new IllegalArgumentException("Confidence must be between 0.0 and 1.0.");
        }
    }

    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public boolean hasFallen() {
        return fallen;
    }

    public String getName() {
        return name;
    }

    public double getConfidence() {
        return confidence;
    }

    public char getSymbol() {
        return symbol;
    }
}
