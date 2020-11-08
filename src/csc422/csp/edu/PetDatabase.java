package csc422.csp.edu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PetDatabase {

    // ArrayList variable
    private ArrayList<Pet> pets = new ArrayList<>();

    private String fileName = "pets.txt";


    // Method to load from text file
    public void load(){
        try {
            Scanner s = new Scanner(new File(fileName));
            while(s.hasNextLine()){
                String line = s.nextLine();
                if (line.trim().equals("")) continue;
                String[] pair = line.split(" ");
                add(new Pet(pair[0], Integer.parseInt(pair[1])));
            }
            s.close();
            System.out.println("Pets loaded");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PetDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to save to text file
    public void save(){
        try {
            PrintWriter writer = new PrintWriter(fileName);
            for(Pet p:pets){
                writer.println(p.getName() + " " + p.getAge());
            }
            writer.close();
            System.out.println("Pets saved");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PetDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Add a pet
    public void add(Pet p){
        pets.add(p);
    }

    // List of pets
    public ArrayList<Pet> getPets(){
        return pets;
    }

    // Get the size of the pet list
    public int getCount(){
        return pets.size();
    }

    // Remove a pet from the DB
    public void remove(int id){
        pets.remove(id);
    }

    // Driver program to initialize text file
    public static void main(String[] args){
        PetDatabase db = new PetDatabase();
        db.load();
        db.add(new Pet("John", 10));
        db.add(new Pet("Mark", 6));
        for(Pet p:db.getPets()){
            System.out.println(p.getName() + ":" + p.getAge());
        }
        db.save();
    }
}
