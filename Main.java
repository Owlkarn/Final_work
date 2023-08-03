import Abstract.Animal;
import Abstract.PackAnimal;
import Abstract.Pet;
import Animals.*;
import CommandsPack.Commands;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static ArrayList<Pet> MY_PETS = new ArrayList<>();
    static ArrayList<PackAnimal> MY_PACK_ANIMALS = new ArrayList<>();

    public static void main(String[] args) {
        menu();
    }

    private static void menu () {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("�������� ����� ����:\n" +
                    "1. ������� ����� ��������\n" +
                    "2. ���������� ������ ������ ���������\n" +
                    "3. ������� �������� ����� ��������\n" +
                    "4. ���������� ������ ��������");
            String command = scanner.nextLine();

            switch (command){
                case "1":
                    addNewAnimal();
                    break;
                case "2":
                    watchAnimalCommands(selectAnimal(animalListSelect()));
                    break;
                case "3":
                    addCommandToAnimal(selectAnimal(animalListSelect()));
                    break;
                case "4":
                    watchAnimalList(animalListSelect());
                    break;
                default:
                    System.out.println("������� ������������ ��������");
            }
        }
    }

    private static void watchAnimalList(ArrayList<Animal> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i+1)+". " + list.get(i).getType() + " " + list.get(i).getName() + " " + list.get(i).getDateOfBirth());
            }
        }
    }

    private static ArrayList<Animal> animalListSelect() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Animal> list = new ArrayList<>();
        String animalType;

        do {
            System.out.println("�������� ������ ��������:\n" +
                    "1. �������� ��������\n" +
                    "2. ������� ��������");
            animalType = scanner.nextLine();

            switch (animalType) {
                case "1":
                    list.addAll(MY_PETS);
                    break;
                case "2":
                    list.addAll(MY_PACK_ANIMALS);
                    break;
                default:
                    System.out.println("������� ������������ ��������");
            }
        } while (!animalType.equals("1") && !animalType.equals("2"));

        if (list.size() == 0) {
            System.out.println("� ������ ������ ��� ��� ��������");
            return null;
        }
        return list;
    }

    private static Animal selectAnimal (ArrayList<Animal> list) {
        if (list != null) {
            System.out.println("������� ����� ������� ���������:");

            watchAnimalList(list);

            int number = 0;
            boolean notZero = false;
            while (!notZero){
                try {
                    number = numberCheck(list.size()) - 1;
                    notZero = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("������������ ����.");
                }
            }
            return list.get(number);
        }
        return null;
    }

    private static void watchAnimalCommands (Animal animal) {
        if (animal != null) {
            if (animal.getCommands().getCommands().size() == 0)
                System.out.println("�������� �� ����� ������");
            else {
                for (Commands command : animal.getCommands().getCommands())
                    System.out.println("- " + command);
            }
        }
    }

    private static void addCommandToAnimal(Animal animal) {
        if (animal != null) {
            boolean addingCommands = true;
            ArrayList<Commands> cmd = new ArrayList<>();
            Commands[] allCommands = Commands.values();
            if (animal.getCommands().getCommands().size() != 0) {
                for (Commands command : allCommands) {
                    if (!animal.getCommands().getCommands().contains(command)) {
                        cmd.add(command);
                    }
                }
            } else {
                cmd.addAll(Arrays.asList(allCommands));
            }

                while (addingCommands) {
                    if (animal.getCommands().getCommands().size() == allCommands.length) {
                        System.out.println("�������� ��� ����� ��� �������");
                        addingCommands = false;
                    } else {
                        System.out.println("��������� �������:");
                        int i = 1;
                        for (Commands com : cmd) {
                            if (!animal.getCommands().getCommands().contains(com)) {
                                System.out.println(i + ". " + com);
                                i++;
                            }
                        }

                        System.out.println("0 - ��������� ����:");

                        try {
                            int choice = numberCheck(i - 1);
                            Commands selectedCommand = cmd.get(choice - 1);
                            animal.getCommands().addCommand(selectedCommand);
                            cmd.remove(choice - 1);
                        } catch (IllegalArgumentException e) {
                            addingCommands = false;
                        }
                    }
                }
        }
    }

    private static void addNewAnimal() {
        Scanner scanner = new Scanner(System.in);
        String animalType;

        do {
            System.out.println("����� �������� �� ������ ��������? ������� �����:\n" +
                    "1. �����\n" +
                    "2. ������\n" +
                    "3. �����\n" +
                    "4. ������\n" +
                    "5. �������\n" +
                    "6. ����");
            animalType = scanner.nextLine();

            switch (animalType) {
                case "1":
                    MY_PETS.add(new Cat(setBirthDate(), getPetName()));
                    break;
                case "2":
                    MY_PETS.add(new Dog(setBirthDate(), getPetName()));
                    break;
                case "3":
                    MY_PETS.add(new Hamster(setBirthDate(), getPetName()));
                    break;
                case "4":
                    MY_PACK_ANIMALS.add(new Horse(setBirthDate(), getPetName()));
                    break;
                case "5":
                    MY_PACK_ANIMALS.add(new Camel(setBirthDate(), getPetName()));
                    break;
                case "6":
                    MY_PACK_ANIMALS.add(new Donkey(setBirthDate(), getPetName()));
                    break;
                default:
                    System.out.println("������� ������������ ��������");
            }

        } while (!animalType.equals("1") && !animalType.equals("2") && !animalType.equals("3") &&
                !animalType.equals("4") && !animalType.equals("5") && !animalType.equals("6"));
    }

    private static String getPetName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("������� ��� ���������: ");
        return scanner.nextLine();
    }
    private static LocalDate setBirthDate() {
        Scanner scanner = new Scanner(System.in);
        LocalDate birthDate = null;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("������� ���� �������� � ������� dd.mm.yyyy:");
            String input = scanner.nextLine();

            String[] parts = input.split("\\.");

            if (parts.length != 3) {
                System.out.println("�������� ������ ����.");
                continue;
            }

            try {
                int day = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int year = Integer.parseInt(parts[2]);

                if (year < 1990) {
                    throw new DateTimeException("��� ������ ���� �� ������ 1990.");
                }

                birthDate = LocalDate.of(year, month, day);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("������� �������� ��������.");
            } catch (DateTimeException e) {
                System.out.println("������: " + e.getMessage());
            }
        }

        return birthDate;
    }

    private static int numberCheck(int maxNumber) {
        Scanner scanner = new Scanner(System.in);
        int number;

        while (true) {
            System.out.printf("������� ����� �� 1 �� %d: ", maxNumber);
            try {
                number = scanner.nextInt();

                if (number == 0) {
                    throw new IllegalArgumentException();
                }

                if (number >= 1 && number <= maxNumber) {
                    return number;
                } else {
                    System.out.printf("������������ ����.\n " +
                            "������� ����� �� 1 �� %d.%n", maxNumber);
                }
            } catch (InputMismatchException e) {
                System.out.printf("������������ ����.\n " +
                        "������� ����� �� 1 �� %d.%n", maxNumber);
            }
        }
    }

}
