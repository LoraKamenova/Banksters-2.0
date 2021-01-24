package softuni.banksters.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import softuni.banksters.domain.models.serivice.UserServiceModel;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsername(String username);

    List<UserServiceModel> findAllUsers();

    void setUserRole(String id, String role);

}
