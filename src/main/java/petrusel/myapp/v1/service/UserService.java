package petrusel.myapp.v1.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import petrusel.myapp.v1.model.Role;
import petrusel.myapp.v1.model.User;
import petrusel.myapp.v1.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User userRegistration) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User(userRegistration.getUsername(), userRegistration.getEmail(),
                passwordEncoder.encode(userRegistration.getPassword()), true, List.of(new Role("USER")));
        System.out.println("UserService: save registered user details");
        userRepository.save(user);
    }

    public User getUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        System.out.println("UserService: este returnat un user in functie de username");
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        User user = userRepository.getReferenceById(id);
        userRepository.delete(user);
    }

}
