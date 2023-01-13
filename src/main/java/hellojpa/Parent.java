package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    //casecade가 설정된 인스턴스 객체까지 모두 em.persist(Children children) 날려줄거임!
    //orphanRemoval = true 시 find한 parent객체에서 childList.remove(0)를 사용하여 child 객체 제거시 DELETE 쿼리가 발생하게해준다.
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Children> childList = new ArrayList<>();


    public void addChild(Children child){
        childList.add(child);
        child.setParent(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Children> getChildList() {
        return childList;
    }

    public void setChildList(List<Children> childList) {
        this.childList = childList;
    }
}
