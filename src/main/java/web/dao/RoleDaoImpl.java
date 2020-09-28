package web.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Repository("roleDao")

public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    EntityManager entityManager;
    protected EntityManager getEntityManager(){
        return this.entityManager;
    }
    @Transactional
    @Override
    public void createRole(List<Role> roles) {
        roles.forEach(role -> entityManager.persist(role));
    }

    @Transactional
    @Override
    public List<Role> getAllRoles() {
        List<Role> setRoles = new ArrayList<>();
        List<Role> listRoles= entityManager
                .createQuery("select r from Role r")
                .getResultList();
        setRoles.addAll(listRoles);
        return setRoles;
    }
}
