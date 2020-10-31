package csc422.csp.edu;

import java.util.ArrayList;

public class PetDatabase {

    // ArrayList variable
    private ArrayList<Pet> pets = new ArrayList<>();

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

}
