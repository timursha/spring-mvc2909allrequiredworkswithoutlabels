package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.models.Role;
import web.models.User;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceEntityImpl implements UserService, UserDetailsService {

    final private UserDao userDao;
    final private RoleDao roleDao;


    public UserServiceEntityImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    public void saveRoles(List<Role> roles){
        roleDao.createRole(roles);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void removeUserById(Long id) {
        userDao.removeUserById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        return userDao.listUsers();
    }


    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.getUserByEmail(s);
    }
    @Transactional
    @Override
    public List<Role> getAllRoles(){
        return roleDao.getAllRoles();
    }

    @Transactional
    @PostConstruct
    public void init(){
        List<Role> rolesList = new ArrayList<>();
        rolesList.add(new Role("ROLE_ADMIN"));
        rolesList.add(new Role("ROLE_USER"));
        this.saveRoles(rolesList);
        User user = new User("admin", "admin@admin.ru");
        user.setPassword("admin");
        user.setRoles(rolesList);
        this.updateUser(user);
    }
}
