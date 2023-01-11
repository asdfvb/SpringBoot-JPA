package hellojpa;

import javax.persistence.*;

@Entity
//상속 관계 매핑중 조인 전략을 사용한다.
//공통 컬럼은 상위 테이블 Item에 등록이되고
//키를 이용하여 조인해서 사용하는 구조
//@Inheritance(strategy = InheritanceType.JOINED)


//default name : DType이라는 컬럼이 생기고 객체명이 들어간다.
//상속 구조를 구별하는 컬럼
//name 지정시 컬럼명을 변경 가능하다.
//SINGLE_TABLE전략에서는 없어도 자동 적용됨.
//@DiscriminatorColumn(name="DIS_TYPE")



//단일 테이블 전략
//한테이블에 모든 컬럼을 등록한다.
//DTYPE이 필수이기때문에 DiscriminatorColumn이 자동으로 적용된다.
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)


//서브타입 테이블로 변환
//abstract 추상 클래스로 상위 클래스를 만들어야한다.
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Item {
    @Id @GeneratedValue
    private long id;
    private String name;
    private int price;
}




