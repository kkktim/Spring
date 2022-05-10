package kr.co.kmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kmarket.dao.ProductDao;
import kr.co.kmarket.vo.CategoriesVo;
import kr.co.kmarket.vo.ProductVo;

@Service
public class ProductService {
	@Autowired
	private ProductDao dao;
	
	public ProductVo selectProduct(int pid) {
		return dao.selectProduct(pid);
	}
	public List<ProductVo> selectProducts(ProductVo pv){
		return dao.selectProducts(pv);
	}
	
	public CategoriesVo selectCateTitles(ProductVo pv) {
		return dao.selectCateTitles(pv);
	}
}
