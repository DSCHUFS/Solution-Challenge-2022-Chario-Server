package ChariO.GiBoo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private String u_phone;
    private String u_birth;
    @Column(length = 100, unique = true)
    private String u_uuid;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Donation> donationList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Subscribe> subscribeList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserCategory> userCategoryList = new ArrayList<>();

    //==연관관계 method==//
    public void addDonation(Donation donation) {
        this.donationList.add(donation);
        donation.setUser(this);
    }

    public void addSubscribe(Subscribe subscribe) {
        this.subscribeList.add(subscribe);
        subscribe.setUser(this);
    }

    public void addCategory(UserCategory userCategory) {
        this.userCategoryList.add(userCategory);
        userCategory.setUser(this);
    }
}