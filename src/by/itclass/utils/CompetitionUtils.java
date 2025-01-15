package by.itclass.utils;

import by.itclass.exception.CompetitionException;
import by.itclass.model.Animal;
import by.itclass.model.Cat;
import by.itclass.model.Dog;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static by.itclass.utils.AnimalFactory.EMAIL_REGEX;

public class CompetitionUtils {

    public static final LocalDate DELIMITER= LocalDate.now().minusYears(3);
    public static final Predicate<Animal> YOING= it -> it.getBirthDate().isAfter(DELIMITER);
    private static final Predicate<Animal> OLD = it -> it.getBirthDate().isBefore(DELIMITER);
    private static List<? extends Object> youngDogs;

    public static void readFile(List<Cat> cats, List<Dog> dogs, Map<String, String> errors) {

        try  (var sc = new Scanner(new FileReader("src/by/itclass/resources/animals.txt"))){
            while (sc.hasNextLine()) {
              fillingCollection(sc.nextLine(), cats, dogs, errors);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            System.exit(1);
        }
    }

    private static void fillingCollection(String textLine, List<Cat> cats, List<Dog> dogs, Map<String, String> errors) {
        try {
            var animal= AnimalFactory.getInstance(textLine);
            if (animal instanceof Cat) {
                cats.add((Cat) animal);
            } else {
                dogs.add((Dog) animal);
            }
        } catch (CompetitionException e) {
                processException(errors, e);
            }
    }

    private static void processException(Map<String, String> errors, CompetitionException e ) {
            var pattern = Pattern.compile(EMAIL_REGEX);
            var matcher = pattern.matcher(e.getErrorLine());
            if (matcher.find()) {
                errors.put(matcher.group(), String.format("Error in the string \"%s\" -%s",
                        e.getErrorLine(), e.getCause().getMessage()));
            }
    }

    public static <T> List<T> sortByBirthDate(List<T> animals) {
        return animals.stream()
                .sorted()
                .toList();
    }

    public static void printResults(List<Cat> cats, List<Dog> dogs,
                                    Map<String, String> errors) {

        printList(cats);
        printList(dogs);
        printMap(errors);

    }

    private static void printMap(Map<String,  String> errors) {
        if (!errors.isEmpty()) {
            System.out.println("Errors size" + errors.size());
            errors.entrySet().forEach(it -> System.out.println(it.getKey() + ", " + it.getValue()));
        }
    }

    private static <T> void printList(List<T> animals) {
        System.out.printf("%s list size =%d%n", animals.get(0) instanceof Cat ? "Cats" : "Dogs", animals.size());
                animals.forEach(System.out::println);
    }

    public static <T extends Animal> List<T> filterByDate(List<T> animals, boolean isYoung) {
        return animals.stream()
                .filter(isYoung ? YOING : OLD)
                .collect(Collectors.toList());
    }

    public static void printResults(List<Cat> youngCats, List<Dog> youngDogs,
                                    List<Cat> oldCats, List<Dog> oldDogs) {
        System.out.println("First day");
        printList(youngCats);
        printList(youngDogs);
        System.out.println("Second day");
        printList(oldCats);
        printList(oldDogs);
    }
}

