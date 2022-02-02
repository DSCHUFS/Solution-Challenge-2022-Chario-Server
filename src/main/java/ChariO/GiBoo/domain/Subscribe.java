package ChariO.GiBoo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="User_Sub")
public class Subscribe {

    @Id
    @GeneratedValue
    @Column(name="u_f_sub_id") //PK
    private Long id;

    //From User.u_id ManyToOne

    //From Facility.f_id //ManyToOne
}
