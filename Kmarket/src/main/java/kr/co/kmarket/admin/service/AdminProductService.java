package kr.co.kmarket.admin.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.kmarket.admin.dao.AdminProductDao;
import kr.co.kmarket.admin.vo.Cate1Vo;
import kr.co.kmarket.admin.vo.Cate2Vo;
import kr.co.kmarket.admin.vo.ProductVo;

@Service
public class AdminProductService {
	@Autowired
	private AdminProductDao dao;
	
	public List<Cate1Vo> selectCate1(){
		return dao.selectCate1();
	}

	public List<Cate2Vo> selectCate2(int cate1){
		return dao.selectCate2(cate1);
	}
	
	public void insertProduct(ProductVo pv) {
		dao.insertProduct(pv);
	}
	
	//썸네일 업로드
	@Value("${spring.servlet.multipart.location}")
	private String uploadDir;
	
	public ProductVo uploadThumbnail(ProductVo pv) {
		//경로생성
		String path = new File(uploadDir).getAbsolutePath();
		String fullPath = path+"/"+pv.getCate1()+"/"+pv.getCate2()+"/"; 
		
		int i = 0;
		for(MultipartFile mf : pv.getThumbnails()) {
			if(!mf.isEmpty()) {
				//파일 확장자, UUID명 생성
				String name = mf.getOriginalFilename();
				String ext = name.substring(name.lastIndexOf("."));
				String uName = UUID.randomUUID().toString()+ext;
				
				try {
					//디렉터리 생성
					Path root = Paths.get(fullPath);
					Files.createDirectories(root);
					
					//썸네일 이미지 저장
					File file = new File(fullPath+uName);
					mf.transferTo(file);
					
					//UUID 파일명 ProductVo 저장
					if(i==0) pv.setThumb1(uName);
					if(i==1) pv.setThumb2(uName);
					if(i==2) pv.setThumb3(uName);
					if(i==3) pv.setDetail(uName);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}//if end...
			i++;
		}//for end...
		return pv;
	}//upload end...
}
