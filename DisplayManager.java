import java.io.*;
import java.util.*;

public class DisplayManager {
  private List<Entity> entities = new ArrayList<>();
  private Field field;
  private char[][] grid;

  public DisplayManager(Field field) {
    this.field = field;
    this.grid = field.getGrid();
  }

  public void addEntity(Entity entity) {
    entities.add(entity);
  }

  public void display() {
    System.out.print("\033[?25l");
    System.out.print("\033[H");

    System.out.flush();
    for (int y = 0 ; y < field.getSize() ; y++) {
      for (int x = 0 ; x < field.getSize() ; x++) {
        boolean drawn = false;
        for (Entity entity : entities) {
          if (x == entity.getX() && y == entity.getY() && entity.getType() == "Farmer") {
            System.out.print("👨");
            drawn = true;
            break;
          }
          else if (x == entity.getX() && y == entity.getY() && entity.getType() == "Dog") {
            System.out.print("🐶");
            drawn = true;
            break;
          }
          else if (x == entity.getX() && y == entity.getY() && entity.getType() == "Rabbit") {
            if (((Rabbit) entity).getAlive()) {
              System.out.print("🐰");
              drawn = true;
              break;
            }
          }
        }

        if (grid[x][y] == 'C' && !drawn) {
          System.out.print("🥕");
        }
        else if (grid[x][y] == 'D' && !drawn) {
          System.out.print("🟫");
        }
        else if (!drawn){
          System.out.print("🟩");
        }

      }
      System.out.print("\n");
    }
  }

}
