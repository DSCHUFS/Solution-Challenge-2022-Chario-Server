package ChariO.GiBoo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="User_Don")
public class Donation {

    @Id
    @GeneratedValue
    @Column(name = "u_f_id")
    private Long Id;

    @ManyToOne
    @JoinColumn(name= "u_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="f_id")
    private Facility facility;

    @Column(name="u_m_price")
    private int donationPrice;

    @Column(name="don_date")
    private int donationDate;

}