package kr.co.kmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kmarket.dao.MainDao;
import kr.co.kmarket.vo.CategoriesVo;
import kr.co.kmarket.vo.ProductVo;
import kr.co.kmarket.vo.SearchVo;

@Service
public class MainService {
	@Autowired
	private MainDao dao;
	
	public List<CategoriesVo> selectCategories(){
		return dao.selectCategories();
	}
	
	public List<ProductVo> selectMainProducts(String order){
		return dao.selectMainProducts(order);
	}
	
	//제품 search
	public List<ProductVo> selectKeyword(String keyword, int order, int start){
		return dao.selectKeyword(keyword, order, start);
	}
	public List<ProductVo> searchFulltext(SearchVo sv){
		return dao.searchFulltext(sv);
	}
	
	
	
	//페이지 처리
	public int getCurrentPage(String pg) {
		//로그인해서 바로 들어오면 pg는 null
		int currentPage = 1;
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		return currentPage;
	}
	public int getLastPageNum(int total){
		int lastPageNum = 0;
		
		if(total % 5 == 0) {
			lastPageNum = total / 5;
		}else {
			lastPageNum = (total / 5) + 1;
		}
		
		return lastPageNum;
	}
	public int getLimitStart(int currentPage) {
		//xml - start 구하기
		return (currentPage - 1) * 5;
	}
	public int getPageStartNum(int total, int start) {
		return total - start;
	}
	public int[] getPageGroup(int currentPage, int lastPageNum) {
		int groupCurrent = (int) Math.ceil(currentPage/5.0);
		int groupStart = (groupCurrent - 1) * 5 + 1;
		int groupEnd = groupCurrent * 5;
		if(groupEnd > lastPageNum) {
			groupEnd = lastPageNum;
		}
		int[] groups = {groupStart, groupEnd};
		return groups;
	}
}
