package hellojpa1.oneToMany.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Lockers {
    @Id @GeneratedValue
    private Long id;

    private  String name;

    //양방향 통신에 읽기전용이된다.(mappedby속성)
    @OneToOne(mappedBy = "locker")
    private Members member;
}
