package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // url: /META_INF/persistence.xml 설정 파일을 지정한다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        //JPA는 무조건 Transaction안에서 처리되어야 한다.
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            //코드 작성
/*

- JPA 사용법
            //객체 조회(Key : 1L)
            Member findMember = em.find(Member.class, 1L);

            //객체 등록
            em.persist(member);

            //객체 삭제
            em.remove(findMember);

            //객체 수정(em.find로 조회된 객체에 값만 수정해도 디비에 데이터 수정됨.)
            //JPA가 Commit하기 직전에 수정된 것들을 업데이트 시킨 후에 커밋을 한다.
            findMember.setName("HelloJPA");
*/

/*
* - JPQL 사용법
*
* */

            List<Member> resultList = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();

            for(Member member : resultList){
                System.out.println(">> : " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }
}
