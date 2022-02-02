package ChariO.GiBoo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter

@Table(name="FAC") //DB Table Name Mapping
public class Facility {

    @Id
    @GeneratedValue
    @Column(name= "f_id") //PK
    private Long id;

    private String f_name;
    private String f_email;

    private int f_phone;

    private String f_home;
    private String f_pay;

    private int f_minimal;

    private String f_intro;

}