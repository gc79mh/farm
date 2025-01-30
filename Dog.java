import java.io.*;
import java.util.*;

public class Dog extends Entity implements Runnable {
  private Rabbit target = null;
  private List<Rabbit> rabbits = new ArrayList<>();

  public Dog(int x, int y, Field field) {
    super(x, y, field);
  }

  public String getType() {
    return "Dog";
  }

  @Override
  public void run() {
    while (true) {
      if (target != null) {
        sniff();
        chase();
      }
      else {
        sniff();
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

  public void addRabbit(Rabbit rabbit) {
    rabbits.add(rabbit);
  }

  public void sniff() {
    for (Rabbit rabbit : rabbits) {
      int dist = ((x - rabbit.getX())^2 + (y - rabbit.getY())^2);
      if (dist <= 25 && rabbit.getAlive()) {
        target = rabbit;
      }
    }
  }

  public void chase() {
    if (x < target.getX()) x++;
    else if (y < target.getY()) y++;
    else if (x > target.getX()) x--;
    else if (y > target.getY()) y--;

    if (x == target.getX() && y == target.getY()) {
      target.die();
      target = null;
    }
  }

}
