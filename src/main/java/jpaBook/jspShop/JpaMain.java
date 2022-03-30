package jpaBook.jspShop;

import jpaBook.jspShop.domain.MemberVO;
import jpaBook.jspShop.domain.Order;
import jpaBook.jspShop.domain.OrderItem;
import jpaBook.jspShop.domain.OrderStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        // url: /META_INF/persistence.xml 설정 파일을 지정한다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        //JPA는 무조건 Transaction안에서 처리되어야 한다.
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            MemberVO member = new MemberVO();
            member.setName("OrderA");
            em.persist(member);
            Order order = new Order();
            order.setMember(member);
            order.setStatus(OrderStatus.ORDER);
            order.addOrderItem(new OrderItem());
            em.persist(order);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }
}