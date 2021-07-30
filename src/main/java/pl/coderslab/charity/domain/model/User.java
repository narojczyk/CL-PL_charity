package pl.coderslab.charity.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.charity.utils.SQLConstants;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = SQLConstants.USER)
@Getter @Setter @ToString(exclude="passwd")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(length = 16, nullable = false, unique=true)
    private String username;

    @Email
    @Column(length = 60, nullable = false, unique=true)
    private String email;

    @NotEmpty
    @Column(length = 300, nullable = false)
    private String passwd;

    @Column(nullable = false)
    private String role;

    // dodać obiekt klasy adres
}
