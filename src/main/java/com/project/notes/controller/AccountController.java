package com.project.notes.controller;

import com.project.notes.dto.AuthRequestDto;
import com.project.notes.dto.AuthResponseDto;
import com.project.notes.dto.UserDto;
import com.project.notes.model.Account;
import com.project.notes.model.exception.UsernameAlreadyExistsException;
import com.project.notes.security.JwtTokenUtil;
import com.project.notes.security.JwtUserDetailsService;
import com.project.notes.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = {"*","https://black-tree-097fbec03.4.azurestaticapps.net", "https://www.getpostman.com", "http://localhost:4200/"}, allowCredentials = "true", maxAge=3600)
//@CrossOrigin(origins = {"https://black-tree-097fbec03.4.azurestaticapps.net"}, allowCredentials = "true", maxAge=3600)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("https://spring-notes-spring-notes.azuremicroservices.io/api/account")
public class AccountController {
    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService jwtUserDetailsService;

    public AccountController(AccountService accountService,
                             AuthenticationManager authenticationManager,
                             JwtTokenUtil jwtTokenUtil,
                             JwtUserDetailsService jwtUserDetailsService) {
        this.accountService = accountService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) throws Exception {
        // If credential is valid, it uses the JwtTokenUtil class to generate a new access token,
        // which is then attached to the response object of type AuthResponse.
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword()));
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authRequestDto.getUsername());
            String jwtToken            = jwtTokenUtil.generateToken(userDetails);
            AuthResponseDto authResponseDto = accountService.fillAuthResponseDto(jwtToken, authRequestDto.getUsername());
            return ResponseEntity.ok().body(authResponseDto);
        } catch(BadCredentialsException exception) {
            //throw new Exception("bad credentials exception");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @GetMapping("/logout") //TODO: implémenté cette fonctionalité
    public ResponseEntity<?> logout() throws Exception {
        try {
            return ResponseEntity.ok().body("logged out");
        } catch(Exception exception) {
            throw new Exception("Error AccountController -> logout() : " + exception.getMessage());
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> createAccount(@RequestBody Account account) throws Exception {
        try {
            Account accountCreated = accountService.createAccount(account);
            return ResponseEntity.ok(accountCreated);
        } catch (UsernameAlreadyExistsException exception) {
            return ResponseEntity.status(409).body(exception.getMessage());
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable("id") int id, @RequestBody Account account) throws Exception {
        accountService.updateAccount(account);
        return ResponseEntity.ok().body(account);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable("id") int id) throws Exception {
        accountService.deleteAccount(id);
        return ResponseEntity.ok().body(id);
    }


    @GetMapping("/searchUsers/{search_str}")
    public ResponseEntity<List<UserDto>> searchUsers(@PathVariable("search_str") String search_str) {
        List<UserDto> userDtoList = accountService.searchUsers(search_str);
        return ResponseEntity.ok(userDtoList);
    }

    /**
     * Get list of shared users for a given note
     */
    @GetMapping("getSharedUsers/{note_id}")
    private ResponseEntity<List<UserDto>> getSharedUsers(@PathVariable("note_id") int note_id) throws Exception {
        try {
            List<UserDto> result = accountService.getSharedAccountByNote(note_id);
            return ResponseEntity.ok().body(result);
        } catch(Exception exception) {
            throw new Exception("Error AccountController -> getSharedUsers(note_id) : " + exception.getMessage());
        }
    }
}
