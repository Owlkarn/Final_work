package Animals;

import Abstract.Pet;

import java.time.LocalDate;

public class Hamster extends Pet {
    final static String type = "Хомяк";
    public Hamster(LocalDate dateOfBirth, String name) {
        super(dateOfBirth, name);
    }

    @Override
    public String getType() {
        return Hamster.type;
    }
}
