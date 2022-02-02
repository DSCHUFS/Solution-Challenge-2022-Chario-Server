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
public class Contents {

    @Id
    @GeneratedValue
    @Column(name="c_id")
    private Long id;

    private String c_title;
    private String c_contents;
    //private String c_image;

    //From Facility.f_id, relations: ManyToOne
}
