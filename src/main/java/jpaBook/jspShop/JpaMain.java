package jpaBook.jspShop;

import hellojpa.Book;
import jpaBook.jspShop.domain.*;

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

            Books book = new Books();
            book.setName("JPA");
            book.setAuthor("김영환");
            em.persist(book);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }
}