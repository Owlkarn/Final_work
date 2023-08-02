package Abstract;

import java.time.LocalDate;

public abstract class PackAnimal extends Animal {

    public PackAnimal(LocalDate dateOfBirth, String name) {
        super(dateOfBirth, name);
    }
}
