package hellojpa1.oneToMany;

import hellojpa1.oneToMany.domain.Members;

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

            /* 멤버 : 팀 (다대일 양방향 예제 코드)
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);


            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            //해당 코드 주석 원인은 Member 객체에 setTeam메소드를 참고해주세요.
//            team.getMembers().add(member);

            em.flush();
            em.clear();

            Member member1 = em.find(Member.class, member.getId());

            List<Member> members = member1.getTeam().getMembers();

            for(Member m : members){
                System.out.println(">> : " + m.getUsername());
            }
            */

            /* -일대다 관계 예제
            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);
            Team team = new Team();
            team.setName("teamA");
            team.getMembers().add(member);

            em.persist(team);
             */


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
