package ChariO.GiBoo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name= "cate_id") //PK
    private Long id;

    private String cate_name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<FacilityCategory> facilityCategoryList = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<UserCategory> userCategoryList = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<CategoryContents> categoryContentsList = new ArrayList<>();

}
