package ChariO.GiBoo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="User_Don")
public class Donation {

    @Id
    @GeneratedValue
    @Column(name = "u_f_id")
    private Long Id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name= "u_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="f_id")
    private Facility facility;

    @Column(name="u_m_price")
    private int donationPrice;

    @Column(name="don_date")
    private String donationDate;

}