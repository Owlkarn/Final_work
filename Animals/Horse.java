package Animals;

import Abstract.Pack_animal;

import java.time.LocalDate;

public class Horse extends Pack_animal {
    final static String type = "Лошадь";
    public Horse(LocalDate dateOfBirth, String name) {
        super(dateOfBirth, name);
    }
}
