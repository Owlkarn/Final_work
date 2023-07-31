package CommandsPack;

import java.util.ArrayList;

public class PetCommands {
    private ArrayList<Commands> commands = new ArrayList<>();

    public PetCommands() {
    }
    
    public void addCommand(Commands com) {
        commands.add(com);
    }
    public ArrayList<Commands> getCommands() {
        return commands;
    }
}
