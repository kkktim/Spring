package kr.co.kmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.co.kmarket.dao.ProductDao;
import kr.co.kmarket.entity.ProductEntity;
import kr.co.kmarket.persistence.ProductRepo;
import kr.co.kmarket.vo.CartVo;
import kr.co.kmarket.vo.CategoriesVo;
import kr.co.kmarket.vo.ProductVo;

@Service
public class ProductService {
	@Autowired
	private ProductDao dao;
	@Autowired
	private ProductRepo repo;
	
	public ProductVo selectProduct(int pid) {
		return dao.selectProduct(pid);
	}
	//리스트 - MyBatis
//	public List<ProductVo> selectProducts(ProductVo pv){
//		return dao.selectProducts(pv);
//	}
	//리스트 - JPA
	public Page<ProductEntity> selectProducts(Pageable pagable, CategoriesVo cates, String order) {
		
		return repo.selectArticlesAddsalePrice(cates.getCate1(),
											   cates.getCate2(),
											   pagable, 
											   PageRequest.of(0, 10, Sort.by("sold").descending()));
	}
	
	public CategoriesVo selectCateTitles(ProductVo pv) {
		return dao.selectCateTitles(pv);
	}
	
	public int insertCart(CartVo cv) {
		return dao.insertCart(cv);
	}
	
	public List<CartVo> selectCarts(String uid){
		return dao.selectCarts(uid);
	}
}
