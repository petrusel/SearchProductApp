package petrusel.myapp.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import petrusel.myapp.v1.model.Role;
import petrusel.myapp.v1.model.User;
import petrusel.myapp.v1.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        User userRegistration = new User();
        model.addAttribute("user", userRegistration);
        System.out.println("UserController registration form");
        return "registration_form";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") User userRegistration) {
        System.out.println("UserController: save userRegistration");
        userService.saveUser(userRegistration);
        return "redirect:/registration?success";
    }

    @GetMapping("/login")
    public String login() {
        System.out.println("UserController login form");
        return "login_form";
    }

    @GetMapping("/")
    public String showUserDetails(Model model, Principal principal) {
        String un = principal.getName();
        User loggedUser = userService.getUserByUsername(un);
        model.addAttribute("user", loggedUser);
        System.out.println("UserController: show logged user details");
        return "user_details";
    }

    @GetMapping("/usersList")
    public String showALlUsers(Model model) {
        List<User> users = userService.getAllUsers();
        System.out.println("UserController: show all users");
        model.addAttribute("allUsers", users);
        return "users_list";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        System.out.println("UserController: delete user");
        userService.deleteUser(id);
        return "redirect:/usersList";
    }
}
