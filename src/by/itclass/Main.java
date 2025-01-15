package by.itclass;

import by.itclass.model.Cat;
import by.itclass.model.Dog;

import java.util.ArrayList;
import java.util.HashMap;

import static by.itclass.utils.CompetitionUtils.*;

public class Main {
    public static void main(String[] args) {

        var cats = new ArrayList<Cat>();
        var dogs = new ArrayList<Dog>();
        var errors = new HashMap<String, String>();

        readFile(cats, dogs, errors);

        // printResults(cats, dogs, errors);

    var youngCats = sortByBirthDate(filterByDate(cats, true));
    var oldCats = sortByBirthDate(filterByDate(cats, false));
    var youngDogs = sortByBirthDate(filterByDate(dogs, true));
    var oldDogs = sortByBirthDate(filterByDate(dogs, false));

    printResults(youngCats, youngDogs, oldCats, oldDogs);
    }
}



