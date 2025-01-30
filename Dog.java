import java.io.*;
import java.util.*;

public class Dog extends Entity implements Runnable {
  private Rabbit target = null;

  public Dog(int x, int y, Field field) {
    super(x, y, field);
  }

  @Override
  public void run() {
    while (true) {
      if (target != null) {
        chase();
      }
      else {
        move(field.getSize(), field.getSize());
      }
      try {
        Thread.sleep(time);
      } catch (InterruptedException e) {
        System.err.println("Dog thread interrupted");
      }
    }
  }

  public void setTarget(Rabbit target) {
    this.target = target;
  }

  public void chase() {
    if (x < target.getX()) x++;
    else if (x > target.getX()) x--;
    if (y < target.getY()) y++;
    else if (y > target.getY()) y--;

    if (x == target.getX() && y == target.getY()) {
      target.die();
      target = null;
    }
  }

}
