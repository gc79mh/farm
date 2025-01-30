import java.io.*;
import java.util.*;

public class FarmSimulation {
  public static void main(String[] args) {

    Random random = new Random();

    int size =  Integer.parseInt(args[0]);
    int time = Integer.parseInt(args[1]);
    int rabbitTime = Integer.parseInt(args[2]);

    Field field = new Field(size);

    Farmer farmer = new Farmer(size / 2, size / 2, field, new Dog(size / 2, size / 2, field));
    farmer.setTime(time);
    farmer.getDog().setTime(time);

    Thread farmerThread = new Thread(farmer);
    Thread dogThread = new Thread(farmer.getDog());
    farmerThread.start();
    dogThread.start();

    DisplayManager dm = new DisplayManager(field);
    dm.addEntity(farmer);
    dm.addEntity(farmer.getDog());
      
    int rabbitTimer = 0;

    System.out.print("\033[H\033[2J");

    for (int i = 0; i < 2000; i++) {
      
      if (rabbitTimer == 0) {
        rabbitTimer = rabbitTime;
        Rabbit rabbit = new Rabbit(random.nextInt(size),random.nextInt(size),field);
        rabbit.setTime(time);
        Thread rabbitThread = new Thread(rabbit);
        rabbitThread.start();

        farmer.getDog().addRabbit(rabbit);
        dm.addEntity(rabbit);

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

