package ChariO.GiBoo.domain;

import jdk.jfr.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    @Column(name = "u_id") //PK
    private Long id;

    private String u_username;
    private String u_name;
    private String u_email;
    private int u_phone;
    private int u_birth;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Donation> donationList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Subscribe> subscribeList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserCategory> userCategoryList = new ArrayList<>();

    //==연관관계 method==//
    public void addDonation(Donation donation) {
        this.donationList.add(donation);
        donation.setUser(this);
    }
}