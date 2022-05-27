package kr.co.kmarket;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.co.kmarket.service.MainService;
import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.vo.ProductVo;

@SpringBootTest
class KmarketApplicationTests {
	
	@Autowired
	private MainService service;
	

	void contextLoads() {
	}
	
	
	@Test
	public void search() {
		String keyword = "";
		int order = 1;
		int start = 0;
		
		List<ProductVo> products = service.selectKeyword(keyword, order, start);
		
		List<ProductVo> searchedList = products.stream().filter(h -> h.getPrice() > 50000)
				.collect(Collectors.toList());
		
//		List<ProductVo> searchedList = products.stream()
//				.filter(i -> i.getName().contains("김치"))
//				.collect(Collectors.toList());
		
		for(ProductVo pv : searchedList) {
			System.out.println("pid : "+pv.getPid());
		}
	}

}
