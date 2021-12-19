package com.example.service;

import java.util.Optional;
import java.util.stream.Collectors;

import com.example.entity.User;
import com.example.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository repo;
	
	public Integer saveUser(User user) {
		//---password encoding----
		String pwd = user.getPassword();
		String encPwd = passwordEncoder.encode(pwd);
		user.setPassword(encPwd);
		//---------------------
		return repo.save(user).getId();
	}

	public Optional<User> findUserByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
	
		Optional<User> opt = findUserByEmail(username);
		if(!opt.isPresent()) 
			throw new UsernameNotFoundException("User not exist!");
		else {
			User user = opt.get(); 
			
			return new org.springframework.security.core.userdetails.User(
					user.getEmail(),  //username
					user.getPassword(),  //password
					user.getRoles()  //Set<GrantedAuthority>
					.stream()
					.map(role->new SimpleGrantedAuthority(role))
					.collect(Collectors.toSet())
					);
		}
	}

}