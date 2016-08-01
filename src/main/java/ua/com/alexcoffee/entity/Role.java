package ua.com.alexcoffee.entity;

import ua.com.alexcoffee.enums.RoleEnum;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends Model {

    private static final long serialVersionUID = 1L;

    @Column(name = "title", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum title;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
    private Set<User> users = new HashSet<>();

    public Role() {
        super();
    }

    public Role(long id, RoleEnum title) {
        super(id);
        this.title = title;
    }

    public Role(RoleEnum title) {
        super();
        this.title = title;
    }

    public void add(User user) {
        users.add(user);
    }

    public void remove(User user) {
        users.remove(user);
    }

    public void clear() {
        users.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title.name())
                .append("\nDescription: ").append(description);
        return sb.toString();
    }

    public RoleEnum getTitle() {
        return title;
    }

    public void setTitle(RoleEnum title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
