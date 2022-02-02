package ChariO.GiBoo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="User_Category")
public class UserCategory {

    @Id
    @GeneratedValue
    @Column(name="u_c_int_id") //PK
    private Long id;

    @ManyToOne
    @JoinColumn(name="u_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="cate_id")
    private Category category;

}
