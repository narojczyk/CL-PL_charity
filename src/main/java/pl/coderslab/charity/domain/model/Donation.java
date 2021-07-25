package pl.coderslab.charity.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.utils.SQLConstants;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = SQLConstants.DONATION)
@Getter
@Setter
@ToString
@Transactional
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int quantity;

    @OneToMany
    @JoinColumn(name="id_"+SQLConstants.CATEGORY)
    private List<Category> categories;

    @OneToOne
    @JoinColumn(name=SQLConstants.INSTITUTION+"_id")
    private Institution institution;

    @Column
    private String street;
    @Column
    private String city;
    @Column
    private String zipCode;
    @Column
    private LocalDate pickUpDate;
    @Column
    private LocalTime pickUpTime;
    @Column
    private String pickUpComment;
}
