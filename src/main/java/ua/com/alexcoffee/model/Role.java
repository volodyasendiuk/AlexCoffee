package ua.com.alexcoffee.model;

import ua.com.alexcoffee.enums.RoleEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends Model {

    private static final long serialVersionUID = 1L;

    @Column(name = "title", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum title;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = CascadeType.REMOVE)
    private List<User> users = new ArrayList<>();

    public Role() {
        super();
    }

    public Role(RoleEnum title) {
        super();
        this.title = title;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title.name())
                .append("\nDescription: ").append(description);
        return sb.toString();
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

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
