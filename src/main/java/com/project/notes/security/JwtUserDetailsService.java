package com.project.notes.security;

import com.project.notes.model.Account;
import com.project.notes.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    //JwtUserDetailsService(AccountRepository accountRepository) {
    //    this.accountRepository = accountRepository;
    //}
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByUsername(username);
        //Set<Role> roles = user.getRoles();
        //roles.forEach(role -> { authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRolename())); });
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + account.getRole()));
        UserDetails userDetails = (UserDetails)new User(account.getUsername(), account.getPassword(), authorities);
        return userDetails;
    }

}
