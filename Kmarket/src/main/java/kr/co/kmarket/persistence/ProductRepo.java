package kr.co.kmarket.persistence;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.co.kmarket.entity.ProductEntity;

public interface ProductRepo extends JpaRepository<ProductEntity, Integer>{

	@Query(nativeQuery = true, value = "SELECT *, FLOOR(`price`*(1 - `discount`/100)) AS `salePrice`  FROM km_product \r\n"
								+ "		WHERE cate1=:cate1 AND cate2=:cate2")
	public Page<ProductEntity> selectArticlesAddsalePrice(@Param("cate1") int cate1, 
										   				  @Param("cate2") int cate2,
										   				  Pageable pageable,
										   				  PageRequest of);

}
