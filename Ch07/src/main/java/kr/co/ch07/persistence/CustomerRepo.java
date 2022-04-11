package kr.co.ch07.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.ch07.vo.CustomerVo;

//custid 는 int
//객체 참조 타입 integer 를 적음
public interface CustomerRepo extends JpaRepository<CustomerVo, Integer> {

}
