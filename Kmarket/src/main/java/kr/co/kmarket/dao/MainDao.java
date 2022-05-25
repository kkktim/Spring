package kr.co.kmarket.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kmarket.vo.CategoriesVo;
import kr.co.kmarket.vo.ProductVo;
import kr.co.kmarket.vo.SearchVo;

@Mapper
@Repository
public interface MainDao {
	
	public List<CategoriesVo> selectCategories();

	public List<ProductVo> selectMainProducts(String order);
	
	//제품 search
	public List<ProductVo> selectKeyword(String keyword, int order, int start);
	
	public List<ProductVo> searchFulltext(SearchVo sv);
}
