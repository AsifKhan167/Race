import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Race {
    private Horse[] horses;
    private int raceLength;

    public Race(int raceLength, Horse[] horses) {
        this.raceLength = raceLength;
        this.horses = horses;
        resetHorses();
    }

    private void resetHorses() {
        for (Horse horse : horses) {
            horse.resetForNewRace();
        }
    }

    public void startRace() {
        boolean raceFinished = false;
        while (!raceFinished) {
            raceFinished = true;
            for (Horse horse : horses) {
                if (!horse.hasFallen() && horse.getDistanceTravelled() < raceLength) {
                    horse.moveForward();
                    raceFinished = false;
                }
            }
            displayRaceStatus();
            try {
                Thread.sleep(500); // Half-second pause for visualization
            } catch (InterruptedException e) {
                System.out.println("Race thread interrupted.");
            }
        }
        updateConfidence();
        displayFinishers();
    }

    private void updateConfidence() {
        Arrays.sort(horses, new Comparator<Horse>() {
            public int compare(Horse h1, Horse h2) {
                return h2.getDistanceTravelled() - h1.getDistanceTravelled();
            }
        });

        if (horses[0].getDistanceTravelled() >= raceLength && !horses[0].hasFallen()) {
            horses[0].increaseConfidence();
        }
		
		for (int i = 1; i < horses.length; i++) {
            if (horses[i].getDistanceTravelled() >= raceLength && !horses[i].hasFallen()) {
                horses[i].increaseConfidenceByHalf();
            }
        }

        for (Horse horse : horses) {
            if (horse.hasFallen()) {
                horse.decreaseConfidence();
            }
        }
    }

    private void displayFinishers() {
        System.out.println("\nRace Results:");
        for (int i = 0; i < horses.length; i++) {
            String suffix = "th";
            switch ((i + 1) % 10) {
                case 1:
                    suffix = (i + 1) % 100 == 11 ? "th" : "st";
                    break;
                case 2:
                    suffix = (i + 1) % 100 == 12 ? "th" : "nd";
                    break;
                case 3:
                    suffix = (i + 1) % 100 == 13 ? "th" : "rd";
                    break;
            }
            if (horses[i].hasFallen()) {
                System.out.println((i + 1) + suffix + " Place: " + horses[i].getName() + " - DNF (Did Not Finish)");
            } else {
                System.out.println((i + 1) + suffix + " Place: " + horses[i].getName());
            }
        }
    }

    private void displayRaceStatus() {
        System.out.println("\nRace progress:");
        for (Horse horse : horses) {
            StringBuilder track = new StringBuilder("|");
            for (int i = 0; i < raceLength; i++) {
                if (i == horse.getDistanceTravelled() && horse.hasFallen()) {
                    track.append('X');
                } else if (i == horse.getDistanceTravelled()) {
                    track.append(horse.getSymbol());
                } else {
                    track.append(" ");
                }
            }
            track.append("| " + horse.getName() + " (Confidence: " + String.format("%.2f", horse.getConfidence()) + ")");
            System.out.println(track);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Horse[] horses = new Horse[]{
            new Horse('P', "Pippi Longstocking", 0.6),
            new Horse('K', "Kokomo", 0.6),
            new Horse('E', "El Jefe", 0.4)
        };

        Scanner scanner = new Scanner(System.in);
        boolean keepRacing = true;

        while (keepRacing) {
            Race race = new Race(50, horses);
            race.startRace();

            System.out.println("Do you want to race again? (yes/no)");
            String input = scanner.nextLine();
            if (!input.equalsIgnoreCase("yes")) {
                keepRacing = false;
            }
        }

        scanner.close();
    }
}