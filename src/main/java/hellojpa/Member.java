package hellojpa;

import javax.persistence.*;
import java.util.Date;

//JPA사용 객체라고 표시
@Entity
//@Table(name="MBR") 테이블명을 지정하여 매핑시킨다.
public class Member {
    @Id //key
    private Long id;

    @Column(name = "name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public Member(){

    }

}
