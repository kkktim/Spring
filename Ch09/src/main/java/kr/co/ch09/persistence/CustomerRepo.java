package kr.co.ch09.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.ch09.vo.CustomerVo;

public interface CustomerRepo extends JpaRepository<CustomerVo, Integer>{

}
