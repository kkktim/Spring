package kr.co.farmstory.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.farmstory.vo.ArticleVo;

@Mapper
@Repository
public interface MainDao {

	public List<ArticleVo> selectLatests(String type);
	
}
