package hellojpa;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

//JPA사용 객체라고 표시
@Entity
@Table(name="MBR") //테이블명을 지정하여 매핑시킨다.

//allocationSize : 시퀀스를 미리 해당 값만큼 증가시켜놓고 현재 값이 해당 시퀀스만큼 도달하기전에는 메모리에서 값을 사용하기때문에 그전까지는 시퀀스를 호출하지 않는다.
//ex) allocationSize = 50 : nextValue가 50이 될때까지 메모리에서 호출해서 사용하고 51이 될때 nextValue를 해주는 방식이다.
//서버가 내려갈때 메모리가 초기화가 되기때문에 너무 높은 값으로 해놓으면 시퀀스 값이 중간에 비어 버릴수가 있다.
@SequenceGenerator(name ="member_seq_generator", sequenceName = "member_seq", initialValue = 1, allocationSize = 1)
public class Member {
    @Id //키를 직접 할당할 경우
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_gnenerator")
    private Long id;

    @Column(name = "name", nullable=false, length = 10, columnDefinition = "varchar(10) default 'EMPTY'")
    private String username;

    private int age;

    @Enumerated
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public Member(){

    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
