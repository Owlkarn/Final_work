package Animals;

import Abstract.PackAnimal;

import java.time.LocalDate;

public class Camel extends PackAnimal {

    final static String type = "Верблюд";
    public Camel(LocalDate dateOfBirth, String name) {
        super(dateOfBirth, name);
    }

    @Override
    public String getType() {
        return Camel.type;
    }
}
