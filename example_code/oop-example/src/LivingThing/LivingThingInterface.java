package LivingThing;

import java.time.LocalDate;

// Interface
public interface LivingThingInterface {
    public void makeSound();
    public String getName();
    public LocalDate getDateOfBirth();
    public int calculateAge();
    public Boolean isHungry();
    public Boolean isDeadByAge();
    public Boolean isSleeping();
}