import java.io.*;
import java.util.*;

public class Entity {
  protected int x, y;
  protected int time = 250;
  protected int waiting = 0;
  protected final Field field;
  protected final Random random = new Random();

  public Entity(int x, int y, Field field) {
    this.x = x;
    this.y = y;
    this.field = field;
  }
  
  public void setTime(int time) {
    this.time = time;
  }
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }

  public void move(int maxX, int maxY) {
    x = Math.max(0, Math.min(maxX - 1, x + random.nextInt(3) - 1));
    y = Math.max(0, Math.min(maxY - 1, y + random.nextInt(3) - 1));
  }
}
