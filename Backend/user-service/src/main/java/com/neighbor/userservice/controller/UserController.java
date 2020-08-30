package com.neighbor.userservice.controller;

import com.neighbor.userservice.entity.Hair;
import com.neighbor.userservice.entity.Location;
import com.neighbor.userservice.entity.User;
import com.neighbor.userservice.security.AuthenticationRequest;
import com.neighbor.userservice.security.AuthenticationResponse;
import com.neighbor.userservice.security.JwtUtil;
import com.neighbor.userservice.service.MyUserDetailsService;
import com.neighbor.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "false")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @GetMapping("/api/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/api/users/{id}")
    public Optional<User> getOneUser(@PathVariable int id){
        return userService.findUserById(id);
    }

    @GetMapping("/api/users/{name}")
    public User getUserByName(@PathVariable String name){
        return userService.getByUsername(name);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<Hair> getHair(@PathVariable int id){
        Optional<User> user = userService.findUserById(id);

        if(user.isPresent()){
            Hair hair = user.get().getHair();
            return new ResponseEntity<>(hair,HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();


    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<Location> getLocation(@PathVariable int id){
        Optional<User> user = userService.findUserById(id);

        if(user.isPresent()){
            Location location = user.get().getHair().getLocation();
            return new ResponseEntity<>(location,HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value="/api/users", method=RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody User user){
        return userService.createUser(user) ? ResponseEntity.status(HttpStatus.CREATED).build(): ResponseEntity.badRequest().build();
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity changeUser(@RequestBody User updatedUser ,@PathVariable int id){
        Optional<User> user = userService.findUserById(id);

        if(user.isPresent()){
            updatedUser.setId(user.get().getId());//add the current id then save
            userService.createUser(updatedUser);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity changeHair(@RequestBody Hair hair, @PathVariable int id){
        Optional<User> user = userService.findUserById(id);


        if(user.isPresent()){
            User updatedUser = user.get();
            updatedUser.setHair(hair);
            userService.createUser(updatedUser);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity changeLocation(@RequestBody Location location, @PathVariable int id){
        Optional<User> user = userService.findUserById(id);


        if(user.isPresent()){
            User updatedUser = user.get();
            Hair newHair = updatedUser.getHair();
            newHair.setLocation(location);
            updatedUser.setHair(newHair);
            userService.createUser(updatedUser);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }



/*    @DeleteMapping("/api/users/{name}")
    public void deleteUserByName(@PathVariable String name){
        userService.deleteByUsername(name);
    }*/

    @DeleteMapping("/api/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUserById(id);
    }

    /** AUTHENTICATION URI's */

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public HttpStatus signup(@RequestBody User user) {
        //user.setJoinDate(new Date());
        return userService.createUser(user) ? HttpStatus.CREATED :
                HttpStatus.BAD_REQUEST; //adds a user in database
    }

    //used to grant a new jwt token
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }catch(BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt=jwtTokenUtil.generateToken(userDetails);
        final User user=userService.getByUsername(userDetails.getUsername());
        return ResponseEntity.ok(new AuthenticationResponse(jwt,user.getId(),user.getEmail(),user.getRole(),user.getProfileImage(),user.getBio(),user.getHair()));

    }
    //check to see if token is expired
    @RequestMapping(value = "/auth/validate", method = RequestMethod.POST)
    public ResponseEntity<?> isTokenBad(@RequestBody String token) {
        boolean valid = jwtTokenUtil.isTokenExpired(token);
        return ResponseEntity.ok(valid);
    }
}
