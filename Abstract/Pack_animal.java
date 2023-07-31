package Abstract;

import Abstract.Animal;

import java.time.LocalDate;

public abstract class Pack_animal extends Animal {

    public Pack_animal(LocalDate dateOfBirth, String name) {
        super(dateOfBirth, name);
    }
}
