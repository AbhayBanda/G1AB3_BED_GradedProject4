package com.greatlearning.ems.entity;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name="_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String password;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public  User(){}

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void addRole(Role role) {
        this.roles.add(role);
        role.getUsers().add(this);
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        Set<Role> modifiedRoles = roles.stream()
                .map(role -> {
                    if (!role.getName().startsWith("ROLE_")) {
                        Role modifiedRole = new Role("ROLE_" + role.getName());
                        modifiedRole.setId(role.getId());
                        return modifiedRole;
                    }
                    return role;
                })
                .collect(Collectors.toSet());
        this.roles = modifiedRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    //    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", userName='" + userName + '\'' +
//                ", password='" + password + '\'' +
//                '}';
//    }

    public void setId(Integer id) {
        this.id = id;
    }
}
