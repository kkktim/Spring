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
import kr.co.kmarket.vo.OrderVo;
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
	public List<ProductVo> selectProducts(ProductVo pv){
		return dao.selectProducts(pv);
	}
	//리스트 - JPA
//	public Page<ProductEntity> selectProducts(Pageable pagable, CategoriesVo cates, String order) {
//		
//		return repo.selectArticlesAddsalePrice(cates.getCate1(),
//											   cates.getCate2(),
//											   pagable, 
//											   PageRequest.of(0, 10, Sort.by("sold").descending()));
//	}
	
	public CategoriesVo selectCateTitles(ProductVo pv) {
		return dao.selectCateTitles(pv);
	}
	
	public int insertCart(CartVo cv) {
		return dao.insertCart(cv);
	}
	
	public List<CartVo> selectCarts(String uid){
		return dao.selectCarts(uid);
	}
	
	public int deleteCart(int[] cids) {
		return dao.deleteCart(cids);
	}
	
	public int insertOrder(OrderVo ov) {
		dao.insertOrder(ov);
		return ov.getOid();
	}
	
	public void insertOrderDetail(int oid, int pid, int count) {
		dao.insertOrderDetail(oid, pid, count);
	}
	
	public List<OrderVo> selectOrders(int oid){
		return dao.selectOrders(oid);
	}
	
	public int updateOrder(OrderVo ov) {
		return dao.updateOrder(ov);
	}
	
	public List<OrderVo> selectOrderComplete(int oid){
		return dao.selectOrderComplete(oid);
	}
	
	//포인트 사용
	public void updateMemberPointMinus(int usePoint, String orderer) {
		dao.updateMemberPointMinus(usePoint, orderer);
		
	}
	public void updateMemberPointPlus(int savePoint, String orderer) {
		dao.updateMemberPointPlus(savePoint, orderer);
	}
}
