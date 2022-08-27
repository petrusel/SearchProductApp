package petrusel.myapp.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import petrusel.myapp.v1.model.Role;
import petrusel.myapp.v1.service.RoleService;

@Controller
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/updateUserRole/{id}")
    public String updateUserRoleForm(@PathVariable Integer id, Model model) {
        Role role = roleService.getRoleById(id);
        System.out.println("RoleController: update role form " + role);
        model.addAttribute("role", role);
        return "update_users_role";
    }

    @PostMapping("/updateUserRole/{id}")
    public String saveUserRole(@PathVariable Integer id, Role role) {
        System.out.println("RoleController: save new role " + role);
        roleService.updateRole(id, role);
        return "redirect:/usersList";
    }
}
