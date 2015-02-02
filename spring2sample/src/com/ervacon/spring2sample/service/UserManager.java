package com.ervacon.spring2sample.service;

import java.util.Collection;

import com.ervacon.spring2sample.domain.Country;
import com.ervacon.spring2sample.domain.User;

public interface UserManager {

	Collection<User> findAllUsers();

	User findUserById(Long id);

	void saveUser(User user);

	Collection<Country> findAllCountries();

	Country findCountryByCode(String code);
}
