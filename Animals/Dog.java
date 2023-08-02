package Animals;

import Abstract.Pet;

import java.time.LocalDate;

public class Dog extends Pet {
    final static String type = "Собака";
    public Dog(LocalDate dateOfBirth, String name) {
        super(dateOfBirth, name);
    }

    @Override
    public String getType() {
        return Dog.type;
    }
}
