# SIR Epidemic Spread Simulation

---

## Introduction

This project simulates the spread of an epidemic using the **SIR (Susceptible-Infected-Recovered) model**. It is implemented in Java and provides:

- Real-time simulation based on user input.
- CSV output for analysis.
- Graphical visualization using JFreeChart.

---

## Features

- **Real-time simulation**: Dynamically calculates S, I, R values.
- **CSV output**: Saves simulation data to `SIR_simulation.csv`.
- **Graphical visualization**: Displays results in a line chart.
- **User-friendly**: Terminal-based input and output.

---

## Setup Instructions

### Step 1: Install Java Development Kit (JDK)

- Download and install JDK 11 or later from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html).

### Step 2: Download Required Libraries

1. **JFreeChart**:
   - Download `jfreechart-1.5.3.jar` and `jcommon-1.0.23.jar` from [JFreeChart Official Site](http://www.jfree.org/jfreechart/).
2. **Apache Commons CSV**:
   - Download `commons-csv-1.9.0.jar` from [Apache Commons CSV](https://commons.apache.org/proper/commons-csv/).

### Step 3: Add Libraries to Your Project

1. Create a `lib` folder in your project directory.
2. Place the downloaded JAR files in the `lib` folder.
3. Add the JAR files to your project's library dependencies in your IDE.

---

## Running the Simulation

### Step 1: Clone or Download the Project

- Clone the repository or download the project files.

### Step 2: Open the Project in Your IDE

- Open the project in IntelliJ IDEA, Eclipse, or VS Code.

### Step 3: Compile and Run the Simulation

#### Using Command Line

- **Compile the Java file:**
  ```sh
  javac -cp "lib/*" -d bin src/SIRModel.java
  ```
- **Run the Code:**

  ```sh
  java -cp "lib/*;bin" SIRModel


  ```

3. Enter the following parameters when prompted:
   - Initial susceptible population (S).
   - Initial infected population (I).
   - Initial recovered population (R).
   - Infection rate (\( \beta \)).
   - Recovery rate (\( \gamma \)).
   - Time step (\( \Delta t \)).
   - Simulation duration (days).

### Step 4: View Results

1. A graph will display the simulation results.
2. The simulation data will be saved to `output/SIR_simulation.csv`.

---

## Output

### 1. Graph

- A line chart showing the changes in Susceptible, Infected, and Recovered populations over time.

### 2. CSV File

- The simulation data is saved in `output/SIR_simulation.csv` with the following columns:
  - `Day`: The day of the simulation.
  - `Susceptible`: The number of susceptible individuals.
  - `Infected`: The number of infected individuals.
  - `Recovered`: The number of recovered individuals.

---

## Project Structure

```plaintext
SIR_Simulation/
├── src/
│   └── SIRModel.java
├── lib/
│   ├── jfreechart-1.5.3.jar
│   ├── jcommon-1.0.23.jar
│   └── commons-csv-1.9.0.jar
├── output/
│   └── SIR_simulation.csv
└── README.md

```

## Dependencies

- [JFreeChart](https://www.jfree.org/jfreechart/)
- [Apache Commons CSV](https://commons.apache.org/proper/commons-csv/)
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/)

---

## Contributing

1.  Fork the repository.
2.  Create a new branch (git checkout -b feature/YourFeatureName).
3.  Commit your changes (git commit -m 'Add some feature').
4.  Push to the branch (git push origin feature/YourFeatureName).
5.  Open a pull request.

---

## Why This Project?

- Educational Purpose: Learn how mathematical models like SIR can simulate real-world phenomena.
- Practical Application: Understand the impact of infection and recovery rates on disease spread.
- Hands-on Experience: Gain experience in Java programming, numerical methods, and data visualization.

---

## Future Enhancements

- Add a GUI for better user interaction.
- Extend the model to include vaccination and quarantine effects.
- Implement advanced numerical methods (e.g., Runge-Kutta) for better accuracy.

---

## Acknowledgments

- Inspired by the work of Kermack and McKendrick (1927) on the SIR model.
- Thanks to the developers of JFreeChart and Apache Commons CSV for their libraries.

---

## Contact

For questions or feedback, feel free to reach out:

- Email: subashdhamee@gmail.com

- GitHub: mesubash
