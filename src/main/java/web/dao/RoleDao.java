package web.dao;

import web.models.Role;

import java.util.List;


public interface RoleDao {
    void createRole(List<Role> roles);
    List<Role> getAllRoles();
}