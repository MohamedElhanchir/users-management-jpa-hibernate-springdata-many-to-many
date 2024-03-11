package ma.enset.jpa.service;

import ma.enset.jpa.entities.Role;
import ma.enset.jpa.entities.User;

public interface IUserService {
    User saveUser(User user);
    Role saveRole(Role role);
    User findUserByUsername(String username);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String username, String roleName);
    User authenticate(String username, String password);
}
