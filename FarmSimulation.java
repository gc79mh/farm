import java.io.*;
import java.util.*;

public class FarmSimulation {
  public static void main(String[] args) {
    System.out.print("\033[H\033[2J");  
    Random random = new Random();

    int time = Integer.parseInt(args[1]);;
    int size =  Integer.parseInt(args[0]);
    Field field = new Field(size);
    Farmer farmer = new Farmer(size / 2, size / 2, field, new Dog(1, 1, field));
    
    //farmer.getDog().setTarget(rabbit);

    DisplayManager dm = new DisplayManager(field,farmer);

    Thread farmerThread = new Thread(farmer);
    Thread dogThread = new Thread(farmer.getDog());
        
    farmerThread.start();
    dogThread.start();

    dm.addEntity(farmer);
    farmer.setTime(time);
    dm.addEntity(farmer.getDog());
    farmer.getDog().setTime(time);
      
    int rabbitTimer = 0;

    for (int i = 0; i < 2000; i++) {
      
      if (rabbitTimer == 0) {
        rabbitTimer = Integer.parseInt(args[2]);
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

