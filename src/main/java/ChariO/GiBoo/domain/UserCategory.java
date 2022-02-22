package ChariO.GiBoo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@Table(name="User_Category")
public class UserCategory {

    @Id
    @GeneratedValue
    @Column(name="u_c_int_id") //PK
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="u_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="cate_id")
    private Category category;

}
