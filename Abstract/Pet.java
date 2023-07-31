package Abstract;

import Abstract.Animal;

import java.time.LocalDate;

public abstract class Pet extends Animal {

    public Pet(LocalDate dateOfBirth, String name) {
        super(dateOfBirth, name);
    }
}
