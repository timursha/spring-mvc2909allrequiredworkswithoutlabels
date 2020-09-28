package web.models;

import org.hibernate.annotations.ManyToAny;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String role;

    public Role( String role) {
        if (role.contains("ROLE_ADMIN")) {
            this.id = 1L;
        } else if (role.contains("ROLE_USER")) {
            this.id = 2L;
        }
        this.role = role;
    }
    @Transient
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role() {
    }

    public String getName() {
        return role;
    }

    public void setName(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public String toString() {
        return role;
    }
}
