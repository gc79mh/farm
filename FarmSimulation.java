import java.io.*;
import java.util.*;

public class FarmSimulation {
  public static void main(String[] args) {
    Random random = new Random();

    int time = 250;
    int size = 10;
    Field field = new Field(size);
    Farmer farmer = new Farmer(0, 0, field, new Dog(1, 1, field));
    
    //farmer.getDog().setTarget(rabbit);

    DisplayManager dm = new DisplayManager(field,farmer);

    Thread farmerThread = new Thread(farmer);
    Thread dogThread = new Thread(farmer.getDog());
        
    farmerThread.start();
    dogThread.start();
      
    int rabbitTimer = 0;

    for (int i = 0; i < 2000; i++) {
      
      if (rabbitTimer == 0) {
        rabbitTimer = random.nextInt(100);
        Rabbit rabbit = new Rabbit(random.nextInt(size),random.nextInt(size),field);
        Thread rabbitThread = new Thread(rabbit);
        rabbitThread.start();
        dm.addRabbit(rabbit);

      }
      rabbitTimer--;

      dm.display();

      try {
        Thread.sleep(time);
      } catch (InterruptedException e) {
        System.err.println("Main thread interrupted");
      }
    }

  }
}

