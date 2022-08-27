package petrusel.myapp.v1.service;

import org.springframework.stereotype.Service;
import petrusel.myapp.v1.model.Role;
import petrusel.myapp.v1.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleById(Integer id) {
        return roleRepository.getReferenceById(id);
    }

    public void updateRole(Integer id, Role role) {
        Role existingRole = roleRepository.getReferenceById(id);
        existingRole.setName(role.getName());
        System.out.println("RoleService: noul role este " + existingRole);
        roleRepository.save(existingRole);
    }
}
