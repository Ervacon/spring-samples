package com.ervacon.spring2sample.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ervacon.spring2sample.domain.Country;
import com.ervacon.spring2sample.domain.Skill;
import com.ervacon.spring2sample.domain.User;

public class StubUserManager implements UserManager {

    private Map<Long, User> users = new TreeMap<Long, User>();
    private Map<String, Country> countries = new TreeMap<String, Country>();

    public StubUserManager() {
        loadCountries();
        loadUsers();
    }

    public Collection<User> findAllUsers() {
        List<User> userList = new ArrayList<User>();
        for (User user : this.users.values()) {
            userList.add(cloneUser(user));
        }
        return userList;
    }

    public User findUserById(Long id) {
        User user = this.users.get(id);

        if (user != null) {
            return cloneUser(user);
        }
        else {
        	return null;
        }
    }

    public void saveUser(User user) {
        // passed in should be a clone - simply replace
        putUser(user);
    }

    public Collection<Country> findAllCountries() {
        return this.countries.values();
    }

    public Country findCountryByCode(String code) {
        return this.countries.get(code);
    }

    //internal helpers

    private void loadCountries() {
        putCountry(new Country("AT", "Austria"));
        putCountry(new Country("UK", "United Kingdom"));
        putCountry(new Country("US", "United States"));
    }

    private void putCountry(Country country) {
        this.countries.put(country.getCode(), country);
    }

    private void loadUsers() {
        User u = new User();
        u.setId(1L);
        u.setFirstName("Harry");
        u.setLastName("Potter");
        u.setNotes("Promising Wizard...");
        u.setCountry(findCountryByCode("UK"));
        u.setSex('M');
        u.setHouse("Gryffindor");
        u.getPreferences().setReceiveNewsletter(true);
        u.getPreferences().setInterests(new String[]{"Quidditch"});
        u.setSkills(Arrays.asList(new Skill[] { Skill.QUIDDITCH }));

        putUser(u);

        u = new User();
        u.setId(2L);
        u.setFirstName("Ronald");
        u.setLastName("Weasly");
        u.setNotes("Friends with Harry Potter.");
        u.setCountry(findCountryByCode("UK"));
        u.setSex('M');
        u.setHouse("Gryffindor");

        putUser(u);

        u = new User();
        u.setId(3L);
        u.setFirstName("Hermione");
        u.setLastName("Granger");
        u.setNotes("Friends with Harry Potter.");
        u.setCountry(findCountryByCode("UK"));
        u.setSex('F');
        u.setHouse("Gryffindor");
        u.setSkills(Arrays.asList(new Skill[] { Skill.HERBOLOGY, Skill.POTIONS }));

        putUser(u);
    }

    private void putUser(User user) {
        this.users.put(user.getId(), user);
    }

    private User cloneUser(User user) {
        try {
            return (User)user.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Unable to clone user.");
        }
    }
}
