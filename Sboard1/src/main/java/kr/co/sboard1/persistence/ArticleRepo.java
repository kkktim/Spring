package kr.co.sboard1.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.sboard1.vo.ArticleVo;

public interface ArticleRepo extends JpaRepository<ArticleVo, Integer>{

	
}
