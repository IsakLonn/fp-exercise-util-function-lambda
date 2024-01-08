package se.lexicon;

import se.lexicon.data.DataStorage;
import se.lexicon.model.Gender;
import se.lexicon.model.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       TODO:  1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message) {
        System.out.println(message);

        Predicate<Person> findPersons = (person) -> Objects.equals(person.getFirstName().toLowerCase(), "erik");

        List<Person> found = storage.findMany(findPersons);
        System.out.println("----------------------");

        for (Person person: found) {
            System.out.println(found);
        }

    }

    /*
        TODO:  2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message) {
        System.out.println(message);

        Predicate<Person> findFemales = (person) -> Objects.equals(person.getGender(), Gender.FEMALE);

        List<Person> found = storage.findMany(findFemales);
        System.out.println("----------------------");

        for (Person person: found) {
            System.out.println(found);
        }

    }

    /*
        TODO:  3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message) {
        System.out.println(message);

        Predicate<Person> findBornAfter = (person) -> person.getBirthDate().isBefore(LocalDate.parse("2000-01-01"));

        List<Person> found = storage.findMany(findBornAfter);
        System.out.println("----------------------");

        for (Person person: found) {
            System.out.println(found);
        }
    }

    /*
        TODO: 4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message) {
        System.out.println(message);

        Predicate<Person> findWithId = (person) -> person.getId() == 123;

        Person found = storage.findOne(findWithId);
        System.out.println("----------------------");

        if(found != null) System.out.println(found);

    }

    /*
        TODO:  5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message) {
        System.out.println(message);

        Predicate<Person> findWithId = (person) -> person.getId() == 456;
        Function<Person, String> convertToString = (person) -> "Name: " + person.getFirstName() + " " + person.getLastName() + " born " + person.getBirthDate();

        String found = storage.findOneAndMapToString(findWithId, convertToString);
        System.out.println("----------------------");

        if(found != null) System.out.println(found);
    }

    /*
        TODO:  6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message) {
        System.out.println(message);

        Predicate<Person> findWithId = (person) -> {
            if (person.getGender() == Gender.MALE) {
                return person.getFirstName().charAt(0) == 'E';
            }
            return false;
        };
        Function<Person, String> convertToString = (person) -> "Name: " + person.getFirstName() + " " + person.getLastName() + " born " + person.getBirthDate();

        List<String> found = storage.findManyAndMapEachToString(findWithId, convertToString);
        System.out.println("----------------------");

        for (String person: found) {
            System.out.println(found);
        }
    }

    /*
        TODO:  7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message) {
        System.out.println(message);

        Predicate<Person> findWithId = (person) -> Period.between(person.getBirthDate(), LocalDate.now()).getYears() < 10;
        Function<Person, String> convertToString = (person) -> person.getFirstName() + " " + person.getLastName() + " " + Period.between(person.getBirthDate(), LocalDate.now()).getYears() + " years";

        List<String> found = storage.findManyAndMapEachToString(findWithId, convertToString);
        System.out.println("----------------------");

        for (String person: found) {
            System.out.println(found);
        }
    }

    /*
        TODO:  8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message) {
        System.out.println(message);

        Predicate<Person> findUlf = (person) -> Objects.equals(person.getFirstName().toLowerCase(), "ulf");
        Consumer<Person> print = System.out::println;

        storage.findAndDo(findUlf, print);
    }

    /*
        TODO:  9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message) {
        System.out.println(message);

        Predicate<Person> findContains = (person) -> person.getLastName().toLowerCase().contains(person.getFirstName().toLowerCase());
        Consumer<Person> print = System.out::println;

        storage.findAndDo(findContains, print);
    }

    /*
        TODO:  10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message) {
        System.out.println(message);

        Predicate<Person> findPalindrome = (person) ->{
            String reversed = "";
            for (char c: person.getFirstName().toLowerCase().toCharArray())
            {
                reversed = c + reversed;
            }
            return person.getFirstName().toLowerCase().equals(reversed);
        };
        Consumer<Person> print = System.out::println;

        storage.findAndDo(findPalindrome, print);
    }

    /*
        TODO:  11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        TODO:  12.	Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        TODO:  13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }
}
