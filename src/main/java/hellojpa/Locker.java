package hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Locker {

    @Id @GeneratedValue
    private long id;
    private String name;

    @OneToOne(mappedBy = "locker")
    private Member member;
}
