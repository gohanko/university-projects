import LivingThing.LivingThingClass;
import LivingThing.Human;

public class App {
    public static void main(String[] args) {
        Human human = new Human("Yuki Yae", "1899-02-02");
        System.out.println("Name: " + human.getName());
        System.out.println("Age: " + human.calculateAge());
        System.out.println("Is hungry: " + human.isHungry());
        System.out.println("Is sleeping: " + human.isSleeping());
        System.out.println("Is dead by age: " + human.isDeadByAge());

        System.out.println("---------------------------------");

        // Polymorphism: It uses the child's functions even though the 
        // variable type is of the parent.
        LivingThingClass human_2 = new Human("Yuki Mae", "1899-02-02");
        System.out.println("Name: " + human_2.getName());
        System.out.println("Age: " + human_2.calculateAge());
        System.out.println("Is hungry: " + human_2.isHungry());
        System.out.println("Is sleeping: " + human_2.isSleeping());
        System.out.println("Is dead by age: " + human_2.isDeadByAge());
    }
}