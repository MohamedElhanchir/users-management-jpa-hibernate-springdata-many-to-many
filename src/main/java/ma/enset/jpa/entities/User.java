package ma.enset.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
@Data @AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    private String userId;
    @Column(name = "USER_NAME",unique = true,length = 50)
    private String username;
    private String password;
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Role> roles=new ArrayList<>();
    /*
    * quand on fait EAGER, on charge les roles avec l'utilisateur
    * pour cela il faut faire : roles=new ArrayList<>();
     */
}
