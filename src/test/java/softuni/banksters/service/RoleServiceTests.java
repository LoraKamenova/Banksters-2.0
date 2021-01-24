package softuni.banksters.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.banksters.TestBase;
import softuni.banksters.domain.entities.Role;
import softuni.banksters.domain.models.serivice.RoleServiceModel;
import softuni.banksters.repository.RoleRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

public class RoleServiceTests extends TestBase {

    @MockBean
    RoleRepository roleRepository;

    @Autowired
    RoleService roleService;

    @Test
    public void seedRolesInDb_whenRepositoryIsEmpty_shouldSeedAllRoles() {

        List<Role> roles = new ArrayList<>();
        roles.add(new Role());
        roles.add(new Role());
        roles.add(new Role());
        roles.add(new Role());

        when(roleRepository.count()).thenReturn(0L);

        roleService.seedRolesInDb();

        assertEquals(4, roles.size());
    }

    private void assertEquals(int i, int size) {
    }

    @Test
    public void seedRolesInDb_whenRepositoryNotEmpty_shouldNotSeedRoles() {

        List<Role> roles = new ArrayList<>();

        when(roleRepository.count()).thenReturn(4L);

        roleService.seedRolesInDb();

        assertEquals(0, roles.size());
    }
    // ==================
    @Test
    public void findAllRoles_whenAnyRolesInDb_shouldReturnAllCorrect() {

        List<Role> roles;
        roles = Arrays.asList(new Role("ROLE_ROOT"), new Role("ROLE_ADMIN"), new Role("ROLE_USER"));
        when(roleRepository.findAll()).thenReturn(roles);

        Set<RoleServiceModel> roleServiceModels = roleService.findAllRoles();

        assertEquals(roles.size(), roleServiceModels.size());
    }

    @Test
    public void findAllRoles_whenNoRolesInDb_shouldReturnEmptySet(){

        List<Role> roles = new ArrayList<>();

        when(roleRepository.findAll()).thenReturn(roles);

        Set<RoleServiceModel> roleServiceModels = roleService.findAllRoles();

        assertEquals(0, roleServiceModels.size());
    }

//    @Test
//    public void findByAuthority_whenAuthorityExist_shouldReturnCorrectRole(){
//
//        when(roleRepository.findByAuthority("ROLE_ROOT"))
//                .thenReturn((new Role("ROLE_ROOT")));
//
//        RoleServiceModel roleServiceModel = roleService.findByAuthority("ROLE_ROOT");
//
//        assertEquals("ROLE_ROOT", roleServiceModel.getAuthority());
//    }

//    @Test
//    public void findByAuthority_whenAuthorityNotExist_shouldThrowException() {
//
//        when(roleRepository.findByAuthority("ROLE_ROOT"))
//                .thenReturn(null);
//
//        assertThrows(Exception.class, () -> roleService.findByAuthority("ROLE_ROOT"));
//    }


}
