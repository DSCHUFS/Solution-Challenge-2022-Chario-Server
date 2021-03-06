package ChariO.GiBoo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

@Table(name="Fac")
public class Facility {

    @Id
    @GeneratedValue
    @Column(name= "f_id") //PK
    private Long id;

    private String f_name;
    private String f_logo;

    private String f_ars;

    private String f_phone;

    private String f_home;
    private String f_pay;

    private int f_minimal;

    private String f_intro;

    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL)
    private List<Contents> contentsList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL)
    private List<Donation> donationList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL)
    private List<Subscribe> subscribeList = new ArrayList<>();

    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL)
    private List<FacilityCategory> facilityCategoryList = new ArrayList<>();


    public void addDonation(Donation donation) {
        this.donationList.add(donation);
        donation.setFacility(this);
    }

    public void addSubscribe(Subscribe subscribe) {
        this.subscribeList.add(subscribe);
        subscribe.setFacility(this);
    }

    public void addCategory(FacilityCategory facilityCategory) {
        this.facilityCategoryList.add(facilityCategory);
        facilityCategory.setFacility(this);
    }
}