package LivingThing;

import java.time.Period;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

// Abstraction
public abstract class LivingThingClass implements LivingThingInterface {
    // Encapsulation
    String name;
    LocalDate date_of_birth;
    Boolean isHungry = false;
    Boolean isSleeping = false;

    public LivingThingClass(String name, String date_of_birth) {
        this.setName(name);
        this.setDateOfBirth(date_of_birth);
    }

    public abstract void makeSound();

    public String getName() {
        return this.name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    public LocalDate getDateOfBirth() {
        return date_of_birth;
    }

    public void setDateOfBirth(String date_of_birth) {
        try {
            this.date_of_birth = LocalDate.parse(date_of_birth);
        } catch (DateTimeParseException e) {
            System.out.println("Exception : " + e);
        }
    }

    public int calculateAge() {
        LocalDate today = LocalDate.now();
        return Period.between(this.date_of_birth, today).getYears();
    }

    public Boolean isHungry() {
        return this.isHungry;
    }

    public void setIsHungry(Boolean isHungry) {
        this.isHungry = isHungry;
    }

    public abstract Boolean isDeadByAge();

    public Boolean isSleeping() {
        return this.isSleeping;
    }

    public void setIsSleeping(Boolean isSleeping) {
        this.isSleeping = isSleeping;
    }
}