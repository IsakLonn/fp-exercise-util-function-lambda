package se.lexicon.data;


import se.lexicon.model.Person;
import se.lexicon.util.PersonGenerator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


/**
 * Create implementations for all methods.
 * I have already provided an implementation for the first method.
 */
public class DataStorageImpl implements DataStorage {

    private static final DataStorage INSTANCE;

    static {
        INSTANCE = new DataStorageImpl();
    }

    private final List<Person> personList;

    private DataStorageImpl() {
        personList = PersonGenerator.getInstance().generate(1000);
    }

    static DataStorage getInstance() {
        return INSTANCE;
    }


    @Override
    public List<Person> findMany(Predicate<Person> filter) {
        List<Person> result = new ArrayList<>();
        for (Person person : personList) {
            if (filter.test(person)) {
                result.add(person);
            }
        }
        return result;
    }

    @Override
    public Person findOne(Predicate<Person> filter) {
        for (Person person : personList) {
            if (filter.test(person)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public String findOneAndMapToString(Predicate<Person> filter, Function<Person, String> personToString) {
        for (Person person : personList) {
            if (filter.test(person)) {
                return personToString.apply(person);
            }
        }
        return null;
    }

    @Override
    public List<String> findManyAndMapEachToString(Predicate<Person> filter, Function<Person, String> personToString) {
        List<String> result = new ArrayList<>();

        for (Person person : personList) {
            if (filter.test(person)) {
                result.add(personToString.apply(person));
            }
        }
        return result;
    }

    @Override
    public void findAndDo(Predicate<Person> filter, Consumer<Person> consumer) {
        for (Person person : personList) {
            if (filter.test(person)) {
                consumer.accept(person);
            }
        }
    }

    @Override
    public List<Person> findAndSort(Comparator<Person> comparator) {
        List<Person> compared = new ArrayList<>(personList);

        for (int i = 0; i < compared.size(); i++) {
            for (int j = i; j < compared.size(); j++) {
                if(comparator.compare(compared.get(i), compared.get(j)) > 0) {
                    Person temp = personList.get(i);
                    personList.set(i, personList.get(j));
                    personList.set(j, temp);
                }
            }
        }
        return compared;
    }

    @Override
    public List<Person> findAndSort(Predicate<Person> filter, Comparator<Person> comparator) {
        List<Person> compared = new ArrayList<>();

        for (int i = 0; i < personList.size(); i++) {
                if(filter.test(personList.get(i))) compared.add(personList.get(i));
        }

        for (int i = 0; i < compared.size(); i++) {
            for (int j = i; j < compared.size(); j++) {
                if(comparator.compare(compared.get(i), compared.get(j)) > 0) {
                    Person temp = personList.get(i);
                    personList.set(i, personList.get(j));
                    personList.set(j, temp);
                }
            }
        }
        return compared;
    }
}
