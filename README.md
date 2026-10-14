# Tic-Tac-Toe

Console Tic-Tac-Toe in Java on a 5×5 board against the computer.
The player plays crosses, entering coordinates `x y` (1 to 5); the computer plays noughts
with random moves. The winner is whoever gets 4 of their symbols in a row, column, or on one of the main diagonals;
if the board fills up, the game is declared a draw.

## Run

Requires JDK 8+.

```bash
javac -d out src/main/java/TicTakToe.java
java -cp out TicTakToe
```
