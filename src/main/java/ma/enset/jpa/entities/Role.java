package ma.enset.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data  @AllArgsConstructor  @NoArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,length = 50)
    private String roleName;
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    /*@JoinTable(name = "USERS_ROLES",
            joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))*/
    /*
    *@ToString.Exclude: pour eviter la boucle infinie
     */
    @ToString.Exclude
    private List<User> users=new ArrayList<>();
}
