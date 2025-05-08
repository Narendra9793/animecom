package com.animecommunity.animecom.Controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.animecommunity.animecom.Dao.PostRepository;
import com.animecommunity.animecom.Dao.UserRepository;
import com.animecommunity.animecom.Models.Credentials;
import com.animecommunity.animecom.Models.JwtRequest;
import com.animecommunity.animecom.Models.JwtResponse;
import com.animecommunity.animecom.Models.User;
import com.animecommunity.animecom.Security.JwtHelper;
import com.animecommunity.animecom.Services.UserService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {
    

    @Autowired

    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private JwtHelper helper;

    @GetMapping("/test")
    public String test(){
        return "Testing";
    }


    //http://localhost:5050/auth/user-login
    @PostMapping("/user-login")
    public ResponseEntity<JwtResponse> Userlogin(@RequestBody JwtRequest request) {
        System.out.println("-----------------------------------------"+ request.getEmail());
        System.out.println(request.getPassword());
        this.doAuthenticate(request.getEmail(), request.getPassword());
        

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        System.out.println("Token : " + token);
        //////////////////////////
        User loggedUser=this.userRepository.getUserByemail(request.getEmail());
        this.userRepository.save(loggedUser);
        System.out.println(loggedUser);
        //////////////////////////
        JwtResponse response = JwtResponse.builder()
        .jwtToken(token)
        .username(userDetails.getUsername())
        .userId(loggedUser.getUserId())
        .build();
    
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

        //http://localhost:5050/auth/admin-login
        @PostMapping("/admin-login")
        public ResponseEntity<JwtResponse> adminLogin(@RequestBody JwtRequest request) {
            System.out.println("Admin password: " + request.getUsername());
            System.out.println("Admin email: " + request.getEmail());
            System.out.println("Admin password: " + request.getPassword());
        
            // Authenticate against in-memory user
            try {
                Authentication authentication = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
                );
        
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                String token = this.helper.generateToken(userDetails); // your JwtHelper
        
                JwtResponse response = JwtResponse.builder()
                    .jwtToken(token)
                    .username(userDetails.getUsername())
                    .build();
        
                return new ResponseEntity<>(response, HttpStatus.OK);
        
            } catch (BadCredentialsException e) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        


    // http://localhost:7070/api/auth/allpublicpost
    // @GetMapping("/allpublicpost")
    // public List<Post> getPublicPost() {
    //     List<Post> publicPosts= new ArrayList<>();
    //     try {

    //         List<User> publicUsers=this.userRepository.findByAccountStatus(AccountStatus.PUBLIC );
            
    //         for (User pUser : publicUsers) {
    //             for(Post p : pUser.getPosts()){
    //                 publicPosts.add(p);
    //             }
    //         }
            
    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //     }

    //     return publicPosts;
    // }



    //http://localhost:5050/auth/register
    @PostMapping("/register")
    public String register(@RequestBody Credentials cred){
        String str = this.userService.addUser(cred);
        return "User added with name " + str; 
    }

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } 
        catch (BadCredentialsException e) {
            System.out.println("Catch Started=====>");
            e.printStackTrace();

            throw new BadCredentialsException(" Invalid Username or Password  !!");
            
        }


    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }


    

}

