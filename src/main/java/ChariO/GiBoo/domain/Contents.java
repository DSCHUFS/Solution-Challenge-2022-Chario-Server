package ChariO.GiBoo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

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
    private String c_image;
    private String c_url;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date c_pub_date;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="f_id")
    private Facility facility;

    @OneToMany(mappedBy = "contents", cascade = CascadeType.ALL)
    private List<CategoryContents> categoryContentsList = new ArrayList<>();
}
