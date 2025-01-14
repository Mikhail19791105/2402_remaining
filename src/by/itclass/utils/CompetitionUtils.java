package by.itclass.utils;

import by.itclass.model.Cat;
import by.itclass.model.Dog;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CompetitionUtils {
    public static void readFile(List<Cat> cats, List<Dog> dogs, Map<String, String> errors) {

        try  (var sc = new Scanner(new FileReader("src/by/itclass/resources/animals.txt"))){

            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            System.exit(1);
        }
    }
}
