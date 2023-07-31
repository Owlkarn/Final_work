package Abstract;

import java.time.LocalDate;
import CommandsPack.PetCommands;
public abstract class Animal {
    protected static int idCounter = 1;
    public  int id;
    public LocalDate dateOfBirth;
    public String name;
    public PetCommands commands;
    public String type;

    

    public Animal(LocalDate dateOfBirth, String name) {
        this.id = getNextId();
        this.dateOfBirth = dateOfBirth;
        this.name = name;
        this.commands = new PetCommands();
        this.type = type;
    }

    public String getType() {
        return type;
    }
    public static int getNextId() {
        return idCounter++;
    }

    public int getId() {
        return id;
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
