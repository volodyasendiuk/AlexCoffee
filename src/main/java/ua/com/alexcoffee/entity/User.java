package ua.com.alexcoffee.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User extends Model implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "vkontakte")
    private String vkontakte;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "skype")
    private String skype;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<>();

    public User() {
        super();
    }

    public User(long id, String name, String email, String phone, Role role) {
        super(id);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public User(String name, String email, String phone, Role role) {
        super();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name)
                .append("\nRole: ").append(role.getDescription())
                .append("\nUsername: ").append(username)
                .append("\nEmail: ").append(email)
                .append("\nPhone: ").append(phone)
                .append("\nDescription: ").append(description);
        return sb.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + role.getTitle().name()));
        return roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVkontakte() {
        return vkontakte;
    }

    public void setVkontakte(String vkontakte) {
        this.vkontakte = vkontakte;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setAllInfo(String name, String username, String password, String email,
                                             String phone, String vkontakte, String facebook, String skype,
                                             String description, Role role) {
        setName(name);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setPhone(phone);
        setVkontakte(vkontakte);
        setFacebook(facebook);
        setSkype(skype);
        setDescription(description);
        setRole(role);
    }
}
