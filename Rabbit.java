import java.io.*;
import java.util.*;

public class Rabbit extends Entity implements Runnable {
  private boolean alive = true;

  public Rabbit(int x, int y, Field field) {
    super(x, y, field);
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
      field.eatCarrot(x, y);
      try {
        Thread.sleep(time);
      } catch (InterruptedException e) {
        System.err.println("Rabbit thread interrupted");
      }
    }
  }
}
