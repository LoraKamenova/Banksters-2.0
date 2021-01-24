package softuni.banksters.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import softuni.banksters.domain.entities.Analysis;
import softuni.banksters.domain.entities.User;
import softuni.banksters.domain.models.serivice.AnalysisServiceModel;
import softuni.banksters.domain.models.serivice.UserServiceModel;
import softuni.banksters.repository.AnalysisRepository;
import softuni.banksters.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private RoleService roleService;
    private BCryptPasswordEncoder encoder;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
    }

//    @Test
//    public void userService_createUser_ReturnsCorrect(){
//        UserService userService = new UserServiceImpl(this.userRepository, this.roleService, this.modelMapper, this.encoder);
//
//        Set<RoleService> set = new HashSet<>();
//        set.add("USER");
//
//        UserServiceModel toBeSaved = new UserServiceModel();
//        toBeSaved.setUsername("Visa Inc.");
//        toBeSaved.setEmail("V");
//        toBeSaved.setPassword("American company");;
//        toBeSaved.setAuthorities("USER");
//
//        UserServiceModel actual = userService.registerUser(toBeSaved);
//        UserServiceModel expected = this.modelMapper.map(this.userRepository.findAll().get(0), UserServiceModel.class);
//
//        Assert.assertEquals(expected.getId(), actual.getId());
//        Assert.assertEquals(expected.getUsername(), actual.getUsername());
//        Assert.assertEquals(expected.getEmail(), actual.getEmail());
//        Assert.assertEquals(expected.getPassword(), actual.getPassword());
//    }

    @Test
    public void analysisService_findAnalysisWithValidId_ReturnsCorrect() {
        UserService userService = new UserServiceImpl(this.userRepository, this.roleService, this.modelMapper, this.encoder);

        User user = new User();
        user.setUsername("Pesho");
        user.setPassword("V");
        user.setEmail("American company");


        user = this.userRepository.saveAndFlush(user);

        UserServiceModel actual = userService.findUserByUsername("Pesho");
        UserServiceModel expected = this.modelMapper.map(user, UserServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getPassword(), actual.getPassword());
        Assert.assertEquals(expected.getUsername(), actual.getUsername());
        Assert.assertEquals(expected.getEmail(), actual.getEmail());

    }

    @Test
    public void userService_findAllUserWithValidId_ReturnsCorrect() {
        UserService userService = new UserServiceImpl(this.userRepository, this.roleService, this.modelMapper, this.encoder);

        User user = new User();
        user.setUsername("Pesho2");
        user.setPassword("V");
        user.setEmail("American company");


        user = this.userRepository.saveAndFlush(user);

        User user2 = new User();
        user2.setUsername("Pesho");
        user2.setPassword("V2");
        user2.setEmail("American ");


        user2 = this.userRepository.saveAndFlush(user2);

        List<UserServiceModel> actual = userService.findAllUsers();

        Assert.assertEquals(2, actual.size());
    }
}
