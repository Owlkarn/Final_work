package Animals;

import Abstract.Pack_animal;

import java.time.LocalDate;

public class Camel extends Pack_animal {

    final static String type = "Верблюд";
    public Camel(LocalDate dateOfBirth, String name) {
        super(dateOfBirth, name);

    }
}
