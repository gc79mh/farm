import java.io.*;
import java.util.*;

public class Farmer extends Entity implements Runnable {
  private final Dog dog;

  public Farmer(int x, int y, Field field, Dog dog) {
    super(x, y, field);
    this.dog = dog;
    waiting = 0;
  }

  public String getType() {
    return "Farmer";
  }

  @Override
  public void run() {
    while (true) {
      if (waiting != 0) {
        waiting--;
        continue;
      }

      move(field.getSize(), field.getSize());
      
      if (field.canFix(x, y)) {
        try {
          Thread.sleep(time * random.nextInt(10));
        } catch (InterruptedException e) {
          System.err.println("Farmer thread interrupted");
        }
        field.fix(x, y);
        continue;
      }
      if (field.canPlant(x, y)) {
        try {
          Thread.sleep(time * random.nextInt(10));
        } catch (InterruptedException e) {
          System.err.println("Farmer thread interrupted");
        }
        field.plantCarrot(x, y);
        continue;
      }

      try {
        Thread.sleep(time);
      } catch (InterruptedException e) {
        System.err.println("Farmer thread interrupted");
      }
    }
  }

  public Dog getDog() {
    return dog;
  }
}
