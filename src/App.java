import java.util.ArrayList;

class Vehicle {
    protected String brand = "Ford";
    public void honk(int potato) {
      System.out.println("Tuut, tuut!");
      System.out.println(potato);
    }
  }
  
  class Car extends Vehicle {
    public String modelName = "Mustang";
    public void honk(int ramen) {
        System.out.println(ramen);
    }
  }
  
  class CarCass {
      public static void main(String[] args) {
      Car myFastCar = new Car();
      myFastCar.honk(3);
      }
  }