import Abstract.Animal;
import Abstract.Pack_animal;
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
    static ArrayList<Pack_animal> MY_PACK_ANIMALS = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    public static void menu () {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Выберите пункт меню:\n" +
                    "1. Завести новое животное\n" +
                    "2. Посмотреть список команд животного\n" +
                    "3. Обучить животное новым командам\n" +
                    "4. Посмотреть список животных\n");
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
                    System.out.println("Введено некорректное значение");
            }
        }
    }

    public static void watchAnimalList(ArrayList<Animal> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i+1)+". " + list.get(i).getType() + " " + list.get(i).getName());
            }
        }


    }

    public static ArrayList<Animal> animalListSelect() {
        ArrayList<Animal> list = new ArrayList<>();
        String animalType;

        do {
            System.out.println("Выберите группу животных:\n" +
                    "1. Домашние животные\n" +
                    "2. Вьючные животные\n");
            animalType = scanner.nextLine();

            switch (animalType) {
                case "1":
                    list.addAll(MY_PETS);
                    break;
                case "2":
                    list.addAll(MY_PACK_ANIMALS);
                    break;
                default:
                    System.out.println("Введено некорректное значение");
            }
        } while (!animalType.equals("1") && !animalType.equals("2"));

        if (list.size() == 0) {
            System.out.println("В данном списке еще нет животных");
            return null;
        }
        return list;
    }

    public static Animal selectAnimal (ArrayList<Animal> list) {

        System.out.println("Введите номер нужного животного:");

        watchAnimalList(list);

        int number = numberCheck(list.size()) - 1;

        return list.get(number);
    }

    public static void watchAnimalCommands (Animal animal) {
        if (animal != null) {
            if (animal.getCommands().getCommands().size() == 0)
                System.out.println("Животное не знает команд");
            else {
                for (Commands command : animal.getCommands().getCommands())
                    System.out.println("- " + command);
            }
        }
    }


    public static void addCommandToAnimal(Animal animal) {
        if (animal != null) {
            boolean addingCommands = true;
            ArrayList<Commands> cmd = new ArrayList<>();
            Commands[] allCommands = Commands.values();
            cmd.addAll(Arrays.asList(allCommands));

            while (addingCommands) {
                System.out.println("Доступные команды:");
                for (Commands command : cmd) {
                    System.out.println(cmd.indexOf(command) + 1 + ". " + command);
                }

                System.out.println("0 - закончить ввод:");
                int choice = numberCheck(cmd.size());

                if (choice == 0) {
                    addingCommands = false;
                } else if (choice > 0 && choice <= cmd.size()) {
                    Commands selectedCommand = cmd.get(choice - 1);
                    animal.getCommands().addCommand(selectedCommand);
                    cmd.remove(choice - 1);
                } else {
                    System.out.println("Введено неверное значение.");
                }
            }
        }
    }

    public static void addNewAnimal() {
        String animalType;

        do {
            System.out.println("Какое животное вы хотите добавить? Введите номер:\n" +
                    "1. Кошка\n" +
                    "2. Собака\n" +
                    "3. Хомяк\n" +
                    "4. Лошадь\n" +
                    "5. Верблюд\n" +
                    "6. Осел\n");
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
                    System.out.println("Введено некорректное значение");
            }

            //scanner.nextLine();
        } while (!animalType.equals("1") && !animalType.equals("2") && !animalType.equals("3") &&
                !animalType.equals("4") && !animalType.equals("5") && !animalType.equals("6"));
    }

    public static String getPetName() {
        System.out.println("Введите имя животного: ");
        return scanner.nextLine();
    }
    public static LocalDate setBirthDate() {
        LocalDate birthDate = null;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Введите дату рождения в формате dd.mm.yyyy:");
            String input = scanner.nextLine();

            String[] parts = input.split("\\.");

            if (parts.length != 3) {
                System.out.println("Неверный формат даты.");
                continue;
            }

            try {
                int day = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int year = Integer.parseInt(parts[2]);

                if (year < 1990) {
                    throw new DateTimeException("Год должен быть не меньше 1990.");
                }

                birthDate = LocalDate.of(year, month, day);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Введены неверные значения.");
            } catch (DateTimeException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        return birthDate;
    }

    public static int numberCheck(int maxNumber) {
        int number = 0;

        while (true) {
            System.out.printf("Введите число от 1 до %d: ", maxNumber);
            try {
                number = scanner.nextInt();

                if (number >= 1 && number <= maxNumber) {
                    return number;
                } else {
                    System.out.printf("Некорректный ввод. Пожалуйста, введите число от 1 до %d.%n", maxNumber);
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.printf("Некорректный ввод. Пожалуйста, введите число от 1 до %d.%n", maxNumber);
            }
        }
    }


}
