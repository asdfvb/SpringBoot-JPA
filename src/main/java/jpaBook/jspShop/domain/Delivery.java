package jpaBook.jspShop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Delivery  extends SupportEntity{
    @Id @GeneratedValue
    private long id;
    private String street;
    private String zipcode;
    private String city;
    private DeliveryStatus deliveryStatus;

    @OneToOne(mappedBy = "delivery")
    private Order order;
}
