package com.example.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
        import org.springframework.security.core.Authentication;
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

        import java.security.Principal;
        import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hibernate.internal.util.collections.ArrayHelper.toList;

@Controller
public class ApplicationUserController {
@Autowired
PostReposritry postReposritry;
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/hiMan")
    public String loginpage(Principal p,Model m){
        ApplicationUser applicationUser=(ApplicationUser) ((UsernamePasswordAuthenticationToken)p).getPrincipal();
        m.addAttribute("user",applicationUserRepository.findById(applicationUser.id).get() );
        return "HomePage.html";
    }
    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup.html";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "logIn.html";
    }

    @PostMapping("/signup")
    public RedirectView signup(@RequestParam(value="username") String username,
                               @RequestParam(value="password") String password,
                               @RequestParam(value="firstName") String firstName,
                               @RequestParam(value="lastName") String lastName,
                               @RequestParam(value="dateOfBirth") String dateOfBirth,
                               @RequestParam(value="bio") String bio){
        ApplicationUser newUser = new ApplicationUser(username,bCryptPasswordEncoder.encode(password),firstName,lastName,dateOfBirth,bio);
        newUser = applicationUserRepository.save(newUser);
        return new RedirectView("/hiMan");
    }


    @GetMapping("/myprofile")
    public String getUserProfilePage(Principal p,Model m){
        ApplicationUser applicationUser=(ApplicationUser) ((UsernamePasswordAuthenticationToken)p).getPrincipal();
        m.addAttribute("user",applicationUserRepository.findById(applicationUser.id).get() );
    return "profile.html";
    }

    @GetMapping("/AddPostt")
    public String addpostt(Principal p,Model m){
        ApplicationUser applicationUser=(ApplicationUser) ((UsernamePasswordAuthenticationToken)p).getPrincipal();
        m.addAttribute("user",applicationUserRepository.findById(applicationUser.id).get() );
        return "PostPage.html";
    }

    @PostMapping("/AddPost")
    public RedirectView addPost(@RequestParam String body,Principal p){
        ApplicationUser userDetails = (ApplicationUser) ((UsernamePasswordAuthenticationToken) p).getPrincipal();
        PostPage postPage=new PostPage(body,userDetails);
        postReposritry.save(postPage);
        return new RedirectView("/AddPostt");
    }

    @GetMapping("/home")
    public String getHomePage(Principal p,Model m){
        ApplicationUser applicationUser=(ApplicationUser) ((UsernamePasswordAuthenticationToken)p).getPrincipal();
        m.addAttribute("user",applicationUserRepository.findById(applicationUser.id).get() );
    return "HomePage.html";
    }

@GetMapping("/view/{id}")
public String getAlbum(@PathVariable(value="id")Integer id ,Model m,Principal p){
    ApplicationUser user=applicationUserRepository.findById(id).get();
    m.addAttribute("user",user);

    ApplicationUser applicationUser=(ApplicationUser) ((UsernamePasswordAuthenticationToken)p).getPrincipal();
    m.addAttribute("userme",applicationUserRepository.findById(applicationUser.id).get() );

    Iterable<ApplicationUser> allUsers = applicationUserRepository.findAll();
    m.addAttribute("allUsers",allUsers);

    return "viewPage.html";
}
@GetMapping("/friends/{id}")
    public String getMyProfile(@PathVariable Integer id, Model m){
        ApplicationUser friend = applicationUserRepository.findById(id).get();
        m.addAttribute("principal", friend);

        Iterable<ApplicationUser> allUsers = applicationUserRepository.findAll();
        m.addAttribute("allUsers",allUsers);
        return "test.html";
    }

    @PostMapping("/AddFriend")
    public RedirectView addFriend(@RequestParam(value = "id") Integer id,@RequestParam(value = "friend") Integer friend) {
        ApplicationUser curfriend = applicationUserRepository.findById(id).get();
        ApplicationUser newfriend = applicationUserRepository.findById(friend).get();

        curfriend.friends.add(newfriend);
        newfriend.friends.add(curfriend);

        applicationUserRepository.save(curfriend);
        applicationUserRepository.save(newfriend);

        return new RedirectView("/friends/" + id);
    }
    @GetMapping(value = "/feed")
    public String getFeed(Model m, Principal p) {
        //get current user
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        Set<ApplicationUser> followFriends = currentUser.getFriends();
        m.addAttribute("followingFriends", followFriends);
        //for the nav bar
        m.addAttribute("myProfile", true);
        m.addAttribute("user", true);

        return "feed";
    }

}
