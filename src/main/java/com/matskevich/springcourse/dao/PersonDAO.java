package com.matskevich.springcourse.dao;

import com.matskevich.springcourse.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Neil Alishev
 */
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom", 24, "tom@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Bob", 52, "bob@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Mike", 18, "mike@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Katy", 34, "katy@gmail.com"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        for (Person person : people) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
        //return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        // можно заменить метод этим лямбда-выражением
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        //работают все три способа:
        people.remove(show(id));                          //1
        /*for (Person person : people) {
            if (person.getId() == id) {                     //2
                people.remove(person);
                break;
            }
        }*/
        /*for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getId() == id) {              //3
                people.remove(i);
                i--;
            }
        }*/
    }
}