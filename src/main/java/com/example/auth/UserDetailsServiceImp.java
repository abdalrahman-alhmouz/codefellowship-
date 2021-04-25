package com.example.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
ApplicationUser applicationUser= applicationUserRepository.findByUsername(username);
if (applicationUser==null){
    System.out.println("username doesn't found");
    throw new UsernameNotFoundException("this"+username+"dosent found");
}
        System.out.println("this "+username+" found");

return applicationUser;
    }
}
