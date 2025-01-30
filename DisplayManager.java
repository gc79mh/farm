import java.io.*;
import java.util.*;

public class DisplayManager {
  private Field field;
  private Farmer farmer;
  private List<Rabbit> rabbits = new ArrayList<>();
  private Dog dog;
  private char[][] grid;

  public DisplayManager(Field field, Farmer farmer) {
    this.farmer = farmer;
    this.field = field;
    this.dog = farmer.getDog();
    this.grid = field.getGrid();
  }

  public void addRabbit(Rabbit rabbit) {
    rabbits.add(rabbit);
  }

  public void display() {
    System.out.print("\033[H\033[2J");  
    System.out.flush();
    for (int y = 0 ; y < field.getSize() ; y++) {
      for (int x = 0 ; x < field.getSize() ; x++) {
        boolean drawn = false;

        if (x == farmer.getX() && y == farmer.getY()) {
          System.out.print("👨");
          drawn = true;
        }
        else if (x == dog.getX() && y == dog.getY()) {
          System.out.print("🐶");
          drawn = true;
        }
        for (Rabbit rabbit : rabbits) {
          if (x == rabbit.getX() && y == rabbit.getY() && rabbit.getAlive() && !drawn) {
            System.out.print("🐰");
            drawn = true;
          }
        }
        if (grid[x][y] == 'C' && !drawn) {
          System.out.print("🥕");
        }
        else if (grid[x][y] == 'D' && !drawn) {
          System.out.print("🟫");
        }
        else if (!drawn){
         // System.out.print(grid[x][y]);
          System.out.print("🟩");
        }
        

      }
      System.out.print("\n");
    }
  }

}
