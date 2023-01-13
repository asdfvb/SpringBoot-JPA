package hellojpa1.oneToMany.domain;

import javax.persistence.*;

@Entity
@Table(name="MBR_ONETOMANY")
public class Members {

    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String username;

    /*@Column(name="TEAM_ID")
    private Long teamId;*/

    /* -다대일 양방향 코드
        @ManyToOne  //Member객체와 Team객체와의 관계가 *:1 관계라는것을 명시해준다.
        @JoinColumn(name="TEAM_ID") //조인할 컬럼을 명시해준다.
        private Team team;
    */

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

    /*
        @ManyToOne
        //일대다 관계에서 양방향 연관관계를 만들기 위한 설정으로 팀 TEAM_ID가 주인이기때문에 해당 변수는 등록,수정을 막아서 양방향관계를 만든다.
        @JoinColumn(name="TEAM_ID", insertable=false, updatable=false)
        private Team team;
    */

    //FetchType.LAZY 설정시 프록시 객체로 생성됨. 따라서 멤버클래스가 생성되도 팀은 조회되지 않고 member.getTeam()이 조회될때 팀이 조회됨.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Team team;

    @OneToOne
    @JoinColumn(name="LOCKER_ID")
    private Lockers locker;


    /*public Team getTeam() {
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
        *

        this.team.getMembers().add(this);

    }*/
}
