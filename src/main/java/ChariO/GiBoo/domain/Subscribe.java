package ChariO.GiBoo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@Table(name="User_Sub")
public class Subscribe {

    @Id
    @GeneratedValue
    @Column(name="u_f_sub_id") //PK
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name= "u_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name= "f_id")
    private Facility facility;
}
