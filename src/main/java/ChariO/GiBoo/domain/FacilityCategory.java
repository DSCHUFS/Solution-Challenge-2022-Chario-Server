package ChariO.GiBoo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class FacilityCategory {

    @Id
    @GeneratedValue
    @Column(name="f_cate_id") //PK
    private Long id;

    //From Category.cate_id, relations:ManyToOne

    //From Facility.f_id, relations:ManyToOne
}
