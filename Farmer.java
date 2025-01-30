import java.io.*;
import java.util.*;

public class Farmer extends Entity implements Runnable {
  private final Dog dog;

  public Farmer(int x, int y, Field field, Dog dog) {
    super(x, y, field);
    this.dog = dog;
  }

  public String getType() {
    return "Farmer";
  }

  public Dog getDog() {
    return dog;
  }

  @Override
  public void run() {
    while (true) {
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

}
