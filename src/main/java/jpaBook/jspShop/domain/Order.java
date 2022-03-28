package jpaBook.jspShop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="ORDERS")
public class Order {

    @Id @GeneratedValue
    @Column(name="ORDER_ID")
    private long id;

    @Column(name="MEMBER_ID")
    private long memberId;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
