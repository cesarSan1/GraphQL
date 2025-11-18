package ws.beauty.salon.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false, length= 32)
    private Integer id;

    @Column(name ="role_name", nullable = false,length = 32)
    private String name;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    //@Builder.Default
    private List<User> users= new ArrayList<>();

    public void addUser(User user){
        users.add(user);
        user.setRole(name);
    }

}
