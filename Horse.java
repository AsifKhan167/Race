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
        this.confidence = horseConfidence;
        this.distanceTravelled = 0;
        this.fallen = false;
    }

    public void moveForward() {
        if (!fallen) {
            if (random.nextDouble() < confidence) {
                distanceTravelled += random.nextInt(3) + 1; // Moves 1 to 3 steps forward
            }
            if (random.nextDouble() < (0.05 * (1 - confidence))) {
                fallen = true;
            }
        }
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
        if (confidence > 0.2) {
            confidence -= 0.1;
        } else {
            confidence = 0.1;
        }
    }

    public void resetForNewRace() {
        this.distanceTravelled = 0;
        this.fallen = false;
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
