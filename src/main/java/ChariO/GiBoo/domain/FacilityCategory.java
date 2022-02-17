package ChariO.GiBoo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="Fac_Cate")
public class FacilityCategory {

    @Id
    @GeneratedValue
    @Column(name="f_cate_id") //PK
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="f_id")
    private Facility facility;

    @ManyToOne
    @JoinColumn(name="cate_id")
    private Category category;
}
