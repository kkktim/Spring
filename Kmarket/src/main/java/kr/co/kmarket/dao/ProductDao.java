package kr.co.kmarket.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kmarket.vo.ProductVo;

@Mapper
@Repository
public interface ProductDao {

	public List<ProductVo> selectProducts(ProductVo pv);
}
