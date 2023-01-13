package jpaBook.jspShop.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Delivery  extends SupportEntity{
    @Id @GeneratedValue
    private long id;
    private String street;
    private String zipcode;
    private String city;
    private DeliveryStatus deliveryStatus;


    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;
}
