# SpringBoot-JPA
## ★ 영속성 컨텍스트 (PersistenceContext) ★

- "엔티티를 영구 저장하는 환경" 이라는 뜻
- EntityManager를 통해서 영속성 컨텍스트에 접근 가능
  (EntityManager 1 : 1 PersistenceContext) 관계

<br>

### ► 1차 캐시
- em.find(), em.update() ... 등 메소드 실행시 디비에 바로 적용하지 않고 영속성 컨텍스트에서 해당 객체의 @Id로 지정된 키값이 있는지 찾아서 있으면 디비에서 찾지 않고 바로 가져온다.
  
- 만약 영속성 컨텍스으세 없는 값을 조회시 디비에서 조회해온후 영속성 컨텍스트에 1차캐시에 등록후 결과를 반환해준다.

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

- @ManyToOne : Member 객체 : Team 객체 는 *:1 관계이다.<br>
  Member객체에서 Team객체를 가지는 변수에 지정해주는 명령어.

### 양방향 연관관계
객체간의 양방향 관계는 사실상 멤버->팀, 팀->멤버 각 단방향 2개인 상태를 말한다.<Br>
  → 멤버 객체의 TEAM 변수를 수정할 것인지, 팀 객체의 멤버 변수를 수정할 것인지 혼돈이 생긴다.
- mappedBy(등록 및 수정 등.. 관리를 받을 객체 변수에 사용) <BR> 
  : 관게가 있는 객체의 변수명을 지정해주면된다. (Member객체의 team변수명과 매핑중이다 라는뜻)
- JoinColumn (등록 및 수정 등 관리를 할 객체 변수에 사용) - 연관관계의 주인(Owner)<BR>



