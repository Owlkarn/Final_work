package Animals;

import Abstract.Pet;

import java.time.LocalDate;

public class Cat extends Pet {
    final static String type = "Кошка";
    public Cat(LocalDate dateOfBirth, String name) {
        super(dateOfBirth, name);
    }

    @Override
    public String getType() {
        return this.type;
    }
}
