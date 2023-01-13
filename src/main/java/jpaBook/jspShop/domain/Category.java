package jpaBook.jspShop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Category  extends SupportEntity{
    @Id @GeneratedValue
    private long id;
    private String name;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    //oneToMany는 기본이 LAZY이기때문에 설정 안해줘도댐
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    //Category 와 item 간의 다대다 관계를 해결하기 위해 두 테이블 사이에 CATEGORY_ITEM 테이블을 생성
    //Join컬럼은 Category 객체가 조인 걸 Item 테이블의 컬럼 명
    //inverseJoin컬럼은 Item테이블이 Category 테이블에 조인 걸 컬럼 명
    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM",
        joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )
    private List<Items> items = new ArrayList<>();
}
