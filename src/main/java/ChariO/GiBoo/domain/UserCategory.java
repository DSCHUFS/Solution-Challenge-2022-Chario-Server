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
public class UserCategory {

    @Id
    @GeneratedValue
    @Column(name="u_c_int_id") //PK
    private Long id;

    //From User.u_id, relations:ManyToOne

    //From Category.cate_id, relations:ManyToOne

}
