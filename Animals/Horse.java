package Animals;

import Abstract.PackAnimal;

import java.time.LocalDate;

public class Horse extends PackAnimal {
    final static String type = "Лошадь";
    public Horse(LocalDate dateOfBirth, String name) {
        super(dateOfBirth, name);
    }

    @Override
    public String getType() {
        return Horse.type;
    }
}
