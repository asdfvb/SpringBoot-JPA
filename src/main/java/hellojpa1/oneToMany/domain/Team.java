package hellojpa1.oneToMany.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;

    /* 다대일 양방향시 코드
        @OneToMany(mappedBy = "team") //mappedBy : 관게가 있는 객체의 변수명을 지정해주면된다. (Member객체의 team변수명과 매핑중이다 라는뜻)
        private List<Member> members = new ArrayList<>();
    */

    @OneToMany
    @JoinColumn(name="TEAM_ID")
    private List<Members> members = new ArrayList<>();

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Members> getMembers() {
        return members;
    }

    public void setMembers(List<Members> members) {
        this.members = members;
    }
}
