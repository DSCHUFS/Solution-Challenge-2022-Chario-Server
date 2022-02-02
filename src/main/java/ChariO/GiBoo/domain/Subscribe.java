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

    @ManyToOne
    @JoinColumn(name= "u_id")
    private User user;

    @ManyToOne
    @JoinColumn(name= "f_id")
    private Facility facility;
}
