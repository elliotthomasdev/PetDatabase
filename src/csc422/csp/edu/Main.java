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
                // Break from the menu loop
                case 3:
                    break loop;
            }
        }
    }

    /* Methods */

    // Gets and returns choice
    public static int getChoice(){
        // Menu
        System.out.println("What would you like to do?");
        System.out.println(" 1) View all pets");
        System.out.println(" 2) Add a new pet");
        System.out.println(" 3) Exit program ");
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

        // Eating next line
        input.nextLine();

        // Loop for getting pet input - stops if user enters "done"
        while(true){
            System.out.print("add pet (name, age) 'done' to stop: ");
            String line = input.nextLine().trim();
            if (line.equalsIgnoreCase("done")) break;

            //Splits input into values to add to the DB
            String[] result = line.split(" ");
            db.add(new Pet(result[0], Integer.parseInt(result[1])));
        }
        // Lets the user know how many pets have been added
        int count = db.getCount()-tmp;
        System.out.println(count + " pets added.");
    }
}
