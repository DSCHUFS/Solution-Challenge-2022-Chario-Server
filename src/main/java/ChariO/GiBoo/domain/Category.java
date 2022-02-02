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
public class Category {

    @Id
    @GeneratedValue
    @Column(name= "cate_id") //PK
    private Long id;

    private String cate_name;
}
