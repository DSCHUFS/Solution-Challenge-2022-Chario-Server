package ChariO.GiBoo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="Category_Contents")
public class CategoryContents {

    @Id
    @GeneratedValue
    @Column(name= "cate_c_id") //PK
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cate_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "c_id")
    private Contents contents;
}
