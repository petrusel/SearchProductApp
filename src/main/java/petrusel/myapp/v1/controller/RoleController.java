package petrusel.myapp.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import petrusel.myapp.v1.model.Role;
import petrusel.myapp.v1.service.RoleService;

@Controller
@RequestMapping("/user")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/{id}/editRole")
    public String updateUserRoleForm(@PathVariable Integer id, Model model) {
        Role role = roleService.getRoleById(id);
        model.addAttribute("role", role);
        return "edit_user_role";
    }

    @PostMapping("/{id}/editRole")
    public String saveUserRole(@PathVariable Integer id, Role role) {
        roleService.updateRole(id, role);
        return "redirect:/user/list";
    }
}
