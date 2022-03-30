package hellojpa1.oneToMany.domain;

import javax.persistence.*;

@Entity
@Table(name="MBR_ONETOMANY")
public class Member {

    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String username;

    /*@Column(name="TEAM_ID")
    private Long teamId;*/

    @ManyToOne  //Member객체와 Team객체와의 관계가 *:1 관계라는것을 명시해준다.
    @JoinColumn(name="TEAM_ID") //조인할 컬럼을 명시해준다.
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;

        /*
        *
        * 양방향을 연관관계를 위해 서로 데이터를 넣어줘야하는데.. 아래 예제를 보자.
        *
        * Ex)

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team); //**
            em.persist(member);
            team.getMembers().add(member); //** 매번 두코드를 작성하기엔 힘들고 실수할 경우가 많다. 그래서 아래코드를 작성하여 위에 'member.setTeam(tema);' 코드 실행시 자동 등록되도록 하는 코드이다.
        *
        * */
        this.team.getMembers().add(this);
    }
}
