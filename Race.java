import java.util.Arrays;
import java.util.Comparator;

public class Race {
    private Horse[] horses;
    private int raceLength;

    public Race(int raceLength, Horse[] horses) {
        this.raceLength = raceLength;
        this.horses = horses;
    }

    public void startRace() {
        boolean raceFinished = false;
        while (!raceFinished) {
            raceFinished = true; // Assume race finishes unless a horse still needs to move
            for (Horse horse : horses) {
                if (!horse.hasFallen() && horse.getDistanceTravelled() < raceLength) {
                    horse.moveForward();
                    raceFinished = false; // If any horse moves, the race isn't finished
                }
            }
            displayRaceStatus();
            try {
                Thread.sleep(500); // Pause for half a second between each race update for better visualization
            } catch (InterruptedException e) {
                System.out.println("Race thread interrupted.");
            }
        }
        displayFinishers();
    }

    private void displayFinishers() {
        // Sort horses by distance travelled in descending order, placing fallen horses last
        Arrays.sort(horses, new Comparator<Horse>() {
            public int compare(Horse h1, Horse h2) {
                if (h1.hasFallen() && !h2.hasFallen()) return 1;
                if (!h1.hasFallen() && h2.hasFallen()) return -1;
                return h2.getDistanceTravelled() - h1.getDistanceTravelled(); // Descending order
            }
        });

        System.out.println("\nRace Results:");
        for (int i = 0; i < horses.length; i++) {
            if (horses[i].hasFallen()) {
                System.out.println((i + 1) + "th Place: " + horses[i].getName() + " - DNF");
            } else {
                System.out.println((i + 1) + "th Place: " + horses[i].getName());
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
                    track.append(" "); // No dots, space for clean view
                }
            }
            track.append("| " + horse.getName() + " (Confidence: " + horse.getConfidence() + ")");
            System.out.println(track);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Horse[] horses = new Horse[]{
            new Horse('P', "PIPPI LONGSTOCKING", 0.6),
            new Horse('K', "KOKOMO", 0.6),
            new Horse('E', "EL JEFE", 0.4)
        };
        Race race = new Race(50, horses); // Assuming the race length is 50 meters
        race.startRace();
    }
}