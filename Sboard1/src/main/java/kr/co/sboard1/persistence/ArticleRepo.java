package kr.co.sboard1.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.sboard1.entity.ArticleEntity;

public interface ArticleRepo extends JpaRepository<ArticleEntity, Integer>{

	List<ArticleEntity> findByTitleContaining(String searchKeyword);
	
}
