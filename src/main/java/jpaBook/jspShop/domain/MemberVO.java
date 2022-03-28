package jpaBook.jspShop.domain;

import javax.persistence.*;

@Entity
@Table(name="MEMBER")
public class MemberVO {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MEMBER_ID")
    private long id;
    private String anme;
    private String city;
    private String street;
    private String zipcode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnme() {
        return anme;
    }

    public void setAnme(String anme) {
        this.anme = anme;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
