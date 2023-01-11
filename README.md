# SpringBoot-JPA
## ★ 영속성 컨텍스트 (PersistenceContext) ★

- "엔티티를 영구 저장하는 환경" 이라는 뜻
- EntityManager를 통해서 영속성 컨텍스트에 접근 가능
  (EntityManager 1 : 1 PersistenceContext) 관계

<br>

### ► 1차 캐시
- em.find(), em.update() ... 등 메소드 실행시 디비에 바로 적용하지 않고 영속성 컨텍스트에서 해당 객체의 @Id로 지정된 키값이 있는지 찾아서 있으면 디비에서 찾지 않고 바로 가져온다.
  
- 만약 영속성 컨텍스트에 없는 값을 조회시 디비에서 조회해온후 영속성 컨텍스트에 1차캐시에 등록후 결과를 반환해준다.

### ► 영속성엔티티의 동일성 보장
- 영속성 컨텍스트에서 관리중인 키의 
- c객체를 여러번 반복 사용해도 다른값을 가지지 않는다.

### ► 트랜잭션을 지원하는 쓰기 지연
- em.persist() 메소드 실행시 먼저 1차 캐시에 객체를 등록한후, '쓰기 지연 SQL저장소'에 Insert쿼리를 생성하여 저장만 시켜놓는다.
- '쓰기 지연 SQL저장소'에 쌓인 쿼리들은 Transaction.commit() 메소드 실행과 동시에 데이터베이스에 날린다.(flush)

### ► 변경 감지
- 객체가 영속성 컨텍스트에 가장 처음 1차캐시에 등록될때 생성되는 값(@Id 키값, 객체, 객체 스냅샷(원본))
- 영속성 컨텍스트에서 가져온 객체를 수정할 경우 '객체.setName(변경값)'을 실행시 Transaction.commit()메소드 실행 시점에 객체와 객체 스냅샷을 비교하여 변경된 값을 업데이트 쿼리작성하여 '쓰기 지연 SQL저장소'에 저장한다. 

<br>
참고) flush() : Transaction.commit()이 실행될때 호출되는 메소드로서, 영속성 컨텍스트의 변경내용('쓰기 지연 SQL저장소'에 등록된 쿼리)을 데이터베이스에 반영하는 기능 - <label style="color:red;">flush()가 실행된 후에도 1차 캐시의 내용은 유지된다.</label>

#### flush() 호출 방법 
1. EntityManager.flush() - 직접 호출
2. EntityTransaction.commit() - 플러시 자동 호출
3. JQPL 쿼리 실행 - 플러시 자동 호출

---

## ★ 준영속 상태 ★

- 영속 상태의 엔티티가 영속성 컨텍스트에서 분리(detached)
- 영속성 컨텍스트가 제공하는 기능을 사용 못함

#### 준영속 성태 만드는 방법
1. EntityManager.detach(객체) - 영속성 컨텍스트에서 객체를 빼냄
2. em.clear() - 영속성 컨텍스트를 완전히 초기화 시킴.
3. em.close() - 영속성 컨텍스트를 종료함.

<br><br>
## ★ 객체와 테이블 매핑 ★ <label style='color:skyblue;'>참조 - Member.java</label>
1. 객체와 테이블 매핑 : @Entity, @Table
2. 필드와 컬럼 매핑 : @column
3. 기본키 매핑 : @Id
4. 연관관계 매핑 : @ManyToOne, @JoinColumn

### ▶ @Entity

- JPA를 사용해서 테이블과 매핑할 클래스에 필수인 어노테이션<br>
※ <label style='color:red;'>기본생성자 필수</label>(파라미터가 없는 public or protected 생성자)
<br> ※ final클래스, enum, interface, inner클래스에는 사용불가
<br> ※ 저장할 필드에 final 사용 불가
<br>
<br>
- 속성
1. name : 클래스의명을 지정한다. ex) 여러 클래스가 하나의 테이블에 매핑 되어야 할경우 name을 통일하여 매핑할수 있다.

### ▶ @Table

- 엔티티와 매핑할 테이블 지정
- 속성
1. name : 매핑할 테이블 이름 (default - 엔티티 이름을 사용)
2. catalog : 데이터베이스 catalog 매핑
3. schema : 데이터베이스 schema 매핑
4. uniqueConstraints : DDL생성시에 유니트 제약 조건 생성

### ▶ 데이터베이스 스키마 자동 생성
#### persistence.xml에 속성 추가 (property name="hibernate.hbm2ddl.auto" value="create")
- 속성
1. create : 기존테이블 삭제 후 다시 생성(DROP + CREATE)
2. create-drop : create와 같으나 종료시점에 테이블 drop
3. update : 변경문만 반영
4. vadliate : 엔티티와 테이블이 정상 매핑되었는지만 확인
5. none : 사용하지 않음

## ★ 필드와 컬럼 매핑 ★ 

### 매핑 어노테이션 종류

- @Column : 컬럼매핑
- @Temporal : 날짜 타입 매핑
- @Enumerated : enum 타입 매핑 <br>
  1. EnumType.ORDINAL : enum 순서를 데이터베이스에 저장 (default)
  2. EnumType.STRING : enum 이름을 데이터베이스에 저장
- @Lob : BLOB, CLOB 매핑<br>
  1. 매핑 컬럼의 데이터타입에 따라 String이면 CLOB, 나머지는 BLOB 매핑
- @Transient : 특정 필드를 컬럼에 매핑하지 않는다.


### 기본 키 매핑 방법
1. 직접 할당 : @Id만 사용
2. 자동 생성 : @GeneratedValue
* GenerationType.IDENTITY
  * 기본키 생성을 데이터베이스에 위임 (MySQL - Auto Increment)
* GenerationType.Sequence (Oracle - Sequence)
* GenerationType.Table (모든 데이터베이스에 적용 가능하나 성능에 이슈가 있을수 있다.)


### 단방향 연관관계

- 다대일(@ManyToOne) : Member 객체 : Team 객체 는 *:1 관계이다.<br>
  Member객체에서 Team객체를 가지는 변수에 지정해주는 명령어.
- 일대다(@OneToMany) : Member 객체 : Team 객체 는 *:1 관계이다.<br>
  일대다 단점
  - 엔티티가 관리하는 외래키가 다른 테이블에 있음
  - 연관관계 관리를 위해 추가로 UPDATE를 실행
  - <label style="color:red;">일대다 연관관계 매핑보다 다대일 연관관계를 사용하자</label>
- 일대일(@OneToOne)

### 양방향 연관관계
객체간의 양방향 관계는 사실상 멤버->팀, 팀->멤버 각 단방향 2개인 상태를 말한다.<Br>
  → 멤버 객체의 TEAM 변수를 수정할 것인지, 팀 객체의 멤버 변수를 수정할 것인지 혼돈이 생긴다.
- mappedBy(등록 및 수정 불가.. 관리를 받을 객체 변수에 사용) <BR> 
  : 관계가 있는 객체의 변수명을 지정해주면된다. (Member객체의 team변수명과 매핑중이다 라는뜻)
- JoinColumn (등록 및 수정 등 관리를 할 객체 변수에 사용) <BR>
  :연관관계의 주인(Owner) - 테이블에서 외래키를 가진 객체가 주인으로 정하자.


##상속 관계 매핑
1. 각 테이블로 변환 -> 조인 전략 ( 공통 컬럼은 상위 테이블에 등록하고 키로 조인하는전략)
   참고 : hellojpa/Item, Movie, Book, Movie
   1. 장점
      1. 정규화가 되어있다.
      2. 외래 키 참조 무결성 제약조건 활용 가
      3. 저장공간 효율화
   2. 단점
      1. 조회시 조인을 많이 사용, 성능 저하
      2. 조회 쿼리가 복잡함
      3. 저장시 인서트 쿼리가 2번 사용됨.
2. 통합 테이블로 변환 -> 단일 테이블 전략( 모든 컬럼을 하나의 테이블에 등록하여 사용하는 전략)
   1. 장점
      1. 조인이 필요 없다., 성능이 좋음
      2. 조회 쿼리가 단순함. 
   2. 단점
      1. 자식 엔티티가 매핑한 컬럼은 모두 NULL 허용
      2. 단일 테이블에 모든 컬럼을 저장하므로 테이블이 커질 수 있고, 상황에 따라 성능이 느려질수 있다.
3. 서브타입 테이블로 변환 -> 구현 클래스마다 테이블 전략( 테이블 구조 그대로 컬럼을 등록하여 사용하는 전략)
  사용 권장하지 않음!!
   1. 장점
      1. Not Null 제약 조건 사용 가능
      2. 서브타입을 명확하게 구분해서 처리할때 효과적
   2. 단점
      1. 여러 자식 테이블을 함께 조회할 때 성능이 ㄷ느림(UNION으로 자식테이블을 모두 조회함)
      2. 자식 테이블을 통합해서 쿼리하기 어려움

### @MappedSuperClass - 참고) BaseEntity.java 참고
각 테이블별로 반복되는 컬럼들을 공통으로 지정해놓을수 있다. 

- 상속관계 매핑 X
- 엔티티x, 테이블과 매핑 X
- 부모 클래스를 상속 받는 자식 클래스에 매핑 정보만 제공
- 조회, 검색 불가(em.find(BaseEntity) 불가)
- 직접 생성해서 사용할 일이 없으므로 추상 클래스권장

- 상속 관계 매핑 vs MapperSuperClass
  - 상속 관계 매핑 : 상위 클래스 및 하위 클래스가 모두 따로따로 테이블이 생성된다.
  - MapperSuperClass : 하위클래스에 모든 컬럼이 같이 생성된다.

