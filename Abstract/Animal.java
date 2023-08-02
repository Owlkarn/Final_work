package Abstract;

import java.time.LocalDate;
import CommandsPack.PetCommands;
public abstract class Animal {
    protected static int idCounter = 1;
    private int id;
    private LocalDate dateOfBirth;
    private String name;
    private PetCommands commands;
    

    public Animal(LocalDate dateOfBirth, String name) {
        this.id = getNextId();
        this.dateOfBirth = dateOfBirth;
        this.name = name;
        this.commands = new PetCommands();
    }

    public String getType() {
        return null;
    }

    public static int getNextId() {
        return idCounter++;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetCommands getCommands() {
        return commands;
    }
}
