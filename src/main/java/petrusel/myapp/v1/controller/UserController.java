package petrusel.myapp.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") User userRegistration) {
        userService.saveUser(userRegistration);
        return "redirect:/registration?success";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String showUserDetails(Model model, Principal principal) {
        String un = principal.getName();
        User loggedUser = userService.getUserByUsername(un);
        model.addAttribute("user", loggedUser);
        return "user_details";
    }

    @GetMapping("/user/list")
    public String showALlUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("allUsers", users);
        return "users_list";
    }

    @GetMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/user/list";
    }
}
