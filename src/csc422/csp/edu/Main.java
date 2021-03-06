package csc422.csp.edu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Create new scanner
    private static final Scanner input = new Scanner(System.in);
    // Create the pet database
    private static final PetDatabase db = new PetDatabase();

    // Main
    public static void main(String[] args) {

        // Load all the pets
        db.load();

        // Title message
        System.out.println("Pet Database Program.");

        // Program menu loop
        loop:
        while(true){

            // Switch for menu choices
            switch (getChoice()){

                // Show all of the pets in the DB
                case 1:
                    showAllPets();
                    break;
                // Add a pet to the DB
                case 2:
                    addPet();
                    break;
                // Update a pet
                case 3:
                    updatePet();
                    break;
                // Remove a pet
                case 4:
                    removePet();
                    break;
                // Search for a pet by name
                case 5:
                    searchName();
                    break;
                // Search for a pet by age
                case 6:
                    searchAge();
                    break;
                // Break loop
                case 7:
                    break loop;
                default:
                    System.out.println("Please enter only 1-7");
            }
        }

        // Save all the pets
        db.save();
    }

    /* Methods */

    // Method for updating a pet
    private static void updatePet() {
        // Displays all pets
        showAllPets();
        // Getting input
        System.out.print("Enter the pet ID you can to update: ");
        int id = input.nextInt();
        System.out.print("Enter new name and new age: ");

        // Getting the pet at entered ID
        Pet pet = db.getPets().get(id);

        // Getting the name and age values
        String oldName = pet.getName();
        int oldAge = pet.getAge();
        String newName = input.next();
        int newAge = input.nextInt();

        //Assigning a new name and age to a pet
        pet.setName(newName);
        pet.setAge(newAge);

        // Output
        System.out.println(oldName + " " + oldAge + " has been updated to " + newName + " " + newAge + ".");
    }

    // Method for removing a pet
    public static void removePet() {
        // Displays the list of pets
        showAllPets();

        // Variables for error handling
        boolean invalid = true;
        int choice = 0;
        int size = db.getCount()-1;

        // input loop
        while (invalid){

            try{
                // Prompts user for ID
                System.out.print("Enter the pet ID to remove: ");
                choice = Integer.parseInt(input.nextLine());

                if(choice >= 0 && choice <= size){
                    invalid = false;
                }
                else{
                    System.out.println("Invalid input. Please enter only 0 through " + size + ".");
                }
            }catch (NumberFormatException e){
                System.out.println("Please only enter an integer.");
            }
        }
        //Gets ID and removes selected ID from the pet DB
        System.out.println(db.getPets().get(choice).getName() + " " + db.getPets().get(choice).getAge() + " is removed");
        db.remove(choice);
    }

    // Search for pet by name
    private static void searchName(){
        // Getting input
        System.out.println("Enter a name to search");
        // Eat nextLine
        input.nextLine();
        String name = input.nextLine();

        // Variable for keeping track of pets found
        int numberOfPets = 0;

        // Prints table header
        printTableHeader();
        // Gets list of pets
        ArrayList<Pet> pets = db.getPets();

        // For loop for finding each pet by name
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getName().equalsIgnoreCase(name)) {
                printTableRow(i, pets.get(i).getName(), pets.get(i).getAge());
                numberOfPets++;
            }
        }
        // Prints table footer
        printTableFooter(numberOfPets);
    }

    // Search for pet by age
    private static void searchAge() {
        // Getting input
        System.out.print("Enter age to search: ");
        int age = input.nextInt();

        // Variable for keeping track of pets found
        int numberOfPets = 0;

        // Prints table header
        printTableHeader();
        // Gets list of pets
        ArrayList<Pet> pets = db.getPets();

        // For loop for finding each pet by age
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getAge() == age) {
                printTableRow(i, pets.get(i).getName(), pets.get(i).getAge());
                numberOfPets++;
            }
        }
        // Prints table footer
        printTableFooter(numberOfPets);
    }

    // Gets and returns choice
    public static int getChoice(){
        // Menu
        System.out.println("What would you like to do?");
        System.out.println(" 1) View all pets");
        System.out.println(" 2) Add more pets");
        System.out.println(" 3) Update an existing pet");
        System.out.println(" 4) Remove an existing pet");
        System.out.println(" 5) Search pets by name");
        System.out.println(" 6) Search pets by age");
        System.out.println(" 7) Exit program");

        System.out.print("Your choice: ");
        // Returns choice
        return input.nextInt();
    }

    // Method for printing the header of the table
    private static void printTableHeader(){
        System.out.println("+----------------------+");
        System.out.printf("|%3s | %-10s|%4s |\n", "ID", "NAME", "AGE");
        System.out.println("+----------------------+");
    }

    // Method for printing a table row
    private static void printTableRow(int id, String name, int age){
        System.out.printf("|%3d | %-10s|%4d |\n", id, name, age);
    }

    // method for printing the footer of the table
    private static void printTableFooter(int count){
        System.out.println("+----------------------+");
        System.out.println(count + " rows in set.");
    }

    // Method for displaying all pets
    private static void showAllPets() {
        // Prints table header
        printTableHeader();

        // Gets the pets from the DB
        ArrayList<Pet> pets = db.getPets();

        // For loop to print each pet
        for (int i = 0; i < db.getCount(); i++) {
            printTableRow(i, pets.get(i).getName(), pets.get(i).getAge());
        }

        // Prints table footer
        printTableFooter(db.getCount());
    }

    // Method for adding a pet
    public static void addPet() {

        // Variable for keeping track of how many pets have been added
        int tmp = db.getCount();
        // Variable for tracking pets added
        int track;

        // if the database has 5 pets don't let the user add any more
        if(tmp >= 5){
            System.out.println("You can only save 5 pets. Remove pets to add more.");
        }
        else{

            // Eating next line
            input.nextLine();

            // Loop for getting pet input - stops if user enters "done"
            while(true){

                track = db.getCount();

                // if 5 pets have been added already stop the add loop
                if(track >= 5){
                    System.out.println("You have reached the 5 pet limit.");
                    break;
                }
                else{
                    System.out.print("add pet (name, age) 'done' to stop: ");
                    String line = input.nextLine().trim();
                    if (line.equalsIgnoreCase("done")) break;

                    //Splits input into values to add to the DB
                    String[] result = line.split(" ");

                    // if the input is more than two values inform the user and re prompts
                    if(result.length > 2){
                        System.out.println("Please enter only two values (name, age)");
                    }
                    else{

                        // Validates age
                        if(Integer.parseInt(result[1]) < 1 || Integer.parseInt(result[1]) > 20){
                            System.out.println("Age should be between 1 and 20 try again.");
                        }
                        else{
                            db.add(new Pet(result[0], Integer.parseInt(result[1])));
                        }
                    }
                }
            }
            // Lets the user know how many pets have been added
            int petsAdded = db.getCount()-tmp;
            System.out.println(petsAdded + " pets added.");
        }
    }
}