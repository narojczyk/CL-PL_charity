package pl.coderslab.charity.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.charity.utils.SQLConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = SQLConstants.CATEGORY)
@Getter
@Setter
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column @NotEmpty
    private String name;

}
