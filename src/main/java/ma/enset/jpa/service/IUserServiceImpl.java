package ma.enset.jpa.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.jpa.entities.Role;
import ma.enset.jpa.entities.User;
import ma.enset.jpa.repositories.RoleRepository;
import ma.enset.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class IUserServiceImpl implements IUserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

   /* public IUserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    *c'est mieux de faire l'injection de dependance par le constructeur
    *pour cela j'utilise lombok pour generer le constructeur
    */

    @Override
    public User saveUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByRoleName(roleName);
        if(user.getRoles()!=null) {
            user.getRoles().add(role);
            role.getUsers().add(user);
        }
        /*
        * userRepository.save(user);
        * je peux ne pas faire cette ligne
        * car j'ai deja fait l'annotation @Transactional
        * qui fait que chaque modification dans la base de donnees
         */

    }

    @Override
    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user!=null)
        {
            if(user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new RuntimeException("Bad credentials!");
    }
}
