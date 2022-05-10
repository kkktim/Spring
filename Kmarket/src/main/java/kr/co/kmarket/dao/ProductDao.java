package kr.co.kmarket.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kmarket.vo.CategoriesVo;
import kr.co.kmarket.vo.ProductVo;

@Mapper
@Repository
public interface ProductDao {

	
	public ProductVo selectProduct(int pid);
	public List<ProductVo> selectProducts(ProductVo pv);
	
	//카테고리 셀렉트
	public CategoriesVo selectCateTitles(ProductVo pv);
}
