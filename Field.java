import java.io.*;
import java.util.*;

public class Field {
  private final int size;
  private final char[][] grid;

  public Field(int size) {
    this.size = size;
    this.grid = new char[size][size];
    for (char[] row : grid) Arrays.fill(row, '.');
  }

  public int getSize() {
    return size;
  }

  public char[][] getGrid() {
    return grid;
  }
  
  public synchronized boolean canPlant(int x, int y) {
    return grid[x][y] == '.';
  }

  public synchronized void plantCarrot(int x, int y) {
    if (grid[x][y] == '.') grid[x][y] = 'C';
  }

  public synchronized boolean canFix(int x, int y) {
    return grid[x][y] == 'D';
  }
  public synchronized void fix(int x, int y) {
    if (grid[x][y] == 'D') grid[x][y] = '.';
  }

  public synchronized boolean canEat(int x, int y) {
    return grid[x][y] == 'C';
  }

  public synchronized void eatCarrot(int x, int y) {
    if (grid[x][y] == 'C') grid[x][y] = 'D';
  }

  public synchronized void removeRabbit(int x, int y) {
    grid[x][y] = '.';
  }

}
