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
public class CategoryContents {

    @Id
    @GeneratedValue
    @Column(name= "cate_c_id") //PK
    private Long id;

    //From Categroy.cate_id, relations: ManyToOne

    //From Contents.c_id, relations: ManyToOne
}
