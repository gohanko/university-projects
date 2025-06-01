package LivingThing;

// Inheritance
public class Human extends LivingThingClass {
    public Human(String name, String date_of_birth) {
        super(name, date_of_birth);
    }

    // Overriding
    public void makeSound() {
        System.out.println("Hello!");
    }

    public String getName() {
        return "Mr. " + this.name;
    }

    public Boolean isDeadByAge() {
        if (this.calculateAge() > 100) {
            return true;
        }

        return false;
    }
}