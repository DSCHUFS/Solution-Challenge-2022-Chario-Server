package ChariO.GiBoo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

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
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="f_id")
    private Facility facility;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="cate_id")
    private Category category;
}
