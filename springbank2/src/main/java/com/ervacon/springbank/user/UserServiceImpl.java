package com.ervacon.springbank.user;

import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationException;
import org.springframework.security.BadCredentialsException;
import org.springframework.security.providers.AuthenticationProvider;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.ervacon.springbank.domain.ClientRepository;

@Transactional
public class UserServiceImpl implements UserService, AuthenticationProvider {

	private UserRepository userRepository;
	private ClientRepository clientRepository;
	
	public UserServiceImpl(UserRepository userRepository, ClientRepository clientRepository) {
		Assert.notNull(userRepository, "A user repository is required");
		this.userRepository = userRepository;
		Assert.notNull(clientRepository, "A client repository is required");
		this.clientRepository = clientRepository;
	}
	
	public User registerUser(String userName, String password, long clientId)
			throws UserServiceException {
		if (!StringUtils.hasText(userName)) {
			throw new UserServiceException("Invalid user name");
		}
		if (userRepository.findUser(userName) != null) {
			throw new UserServiceException("User name '" + userName + "' is already in use");
		}
		if (clientRepository.getClient(clientId) == null) {
			throw new UserServiceException("Identified client does not exist");
		}
		User newUser = new User(userName, password, clientId);
		userRepository.store(newUser);
		return newUser;
	}
	
	public User login(String userName, String password) throws UserServiceException {
		User user = userRepository.findUser(userName);
		if (user == null) {
			throw new UserServiceException("Unknown user '" + userName + "'");
		}
		if (user.checkPassword(password)) {
			return user;
		}
		else {
			throw new UserServiceException("Incorrect password");
		}
	}
	
	// implementing AuthenticationProvider
	
	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		// supports all types of authentication requests
		return true;
	}

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			User user = login((String)authentication.getPrincipal(), (String)authentication.getCredentials());
			return new UserWrappingAuthenticationToken(user);
		}
		catch (UserServiceException e) {
			throw new BadCredentialsException(e.getMessage(), e);
		}
	}
}
