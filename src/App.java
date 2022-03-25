import LivingThing.Human;

public class App {
    public static void main(String[] args) {
        Human human = new Human("Yuki Yae", "1899-02-02");
        System.out.println("Name: " + human.getName());
        System.out.println("Age: " + human.calculateAge());
        System.out.println("Is hungry: " + human.isHungry());
        System.out.println("Is sleeping: " + human.isSleeping());
        System.out.println("Is dead by age: " + human.isDeadByAge());
    }
}