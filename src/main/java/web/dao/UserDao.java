package web.dao;

import web.models.Role;
import web.models.User;
import java.util.List;
import java.util.Set;

public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    void removeUserById(Long id);
    User getUserById(Long id);
    User getUserByEmail(String email);
    List<User> listUsers();
    Set<Role> getRoles();
}
