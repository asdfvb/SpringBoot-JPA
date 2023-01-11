package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//상위 테이블에 DType에 들어갈 값을 지정해준다.
//지정 안할시 객체명이 들어간다.
@DiscriminatorValue(value = "A")
public class Album extends Item {
    private String artist;
}

