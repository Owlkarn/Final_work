package Animals;

import Abstract.Pack_animal;

import java.time.LocalDate;

public class Donkey extends Pack_animal {
    final static String type = "Осел";
    public Donkey(LocalDate dateOfBirth, String name) {
        super(dateOfBirth, name);
    }
}
