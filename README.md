# Race
# Horse Race Simulation

## Description
This Java project simulates a horse race with multiple horses competing on a track. Each horse is characterized by a unique name, symbol, confidence level, and can move forward or fall during the race based on their confidence level. The project allows for repeated simulations and displays the race progress in a text-based interface.

## Setup and Installation
1. Ensure you have Java installed on your system or install it via a package manager like Homebrew on macOS or apt on Ubuntu.
2. Clone this repository to your local machine using `git clone [repository-url]`.
3. Navigate to the directory where you cloned the project.

## Running the Simulation
1. Compile the Java code using `javac Horse.java Race.java`.
2. Run the simulation using `java Race`.
3. Follow the on-screen prompts to start the race or race again.

## Usage Guidelines
- Each race simulation is of horses named Pippi Longstocking, Kokomo, and El Jefe, represented by the symbols 'P', 'K', and 'E', respectively.
- The horses' movements and chances of falling are determined by their confidence levels, which are affected by race outcomes.
- After each race, you can choose to simulate another race by entering 'yes' when prompted.

## Dependencies
This project uses only the Java Standard Library and has no external dependencies.

## Features
- Interactive race simulation with a user-driven loop to keep racing as desired.
- Confidence system where horses' performance affects their future races.
- Visual representation of the race and results in the terminal.
