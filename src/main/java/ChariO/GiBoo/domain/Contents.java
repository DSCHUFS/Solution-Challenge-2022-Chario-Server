package ChariO.GiBoo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Contents {

    @Id
    @GeneratedValue
    @Column(name="c_id")
    private Long id;

    private String c_title;
    private String c_contents;
    //private String c_image;

    @ManyToOne
    @JoinColumn(name="f_id")
    private Facility facility;

    @OneToMany(mappedBy = "contents", cascade = CascadeType.ALL)
    private List<CategoryContents> categoryContentsList = new ArrayList<>();
}
