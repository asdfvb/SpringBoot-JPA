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
    }
}
