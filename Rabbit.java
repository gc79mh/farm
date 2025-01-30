import java.io.*;
import java.util.*;

public class Rabbit extends Entity implements Runnable {
  private boolean alive = true;

  public Rabbit(int x, int y, Field field) {
    super(x, y, field);
  }

  public String getType() {
    return "Rabbit";
  }

  public void die() {
    alive = false;
  }

  public boolean getAlive() {
    return alive;
  }

  @Override
  public void run() {
    while (alive) {
      move(field.getSize(), field.getSize());
      if (field.canEat(x, y)) {
        try {
          Thread.sleep(time * random.nextInt(10));
        } catch (InterruptedException e) {
          System.err.println("Farmer thread interrupted");
        }
        field.eatCarrot(x, y);
        continue;
      }
      try {
        Thread.sleep(time);
      } catch (InterruptedException e) {
        System.err.println("Rabbit thread interrupted");
      }
    }
  }
}
