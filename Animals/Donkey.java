package Animals;

import Abstract.PackAnimal;

import java.time.LocalDate;

public class Donkey extends PackAnimal {
    final static String type = "Осел";
    public Donkey(LocalDate dateOfBirth, String name) {
        super(dateOfBirth, name);
    }

    @Override
    public String getType() {
        return Donkey.type;
    }
}
