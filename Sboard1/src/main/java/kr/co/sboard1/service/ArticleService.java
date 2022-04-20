package kr.co.sboard1.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.sboard1.dao.ArticleDao;
import kr.co.sboard1.persistence.ArticleRepo;
import kr.co.sboard1.persistence.FileRepo;
import kr.co.sboard1.vo.ArticleVo;
import kr.co.sboard1.vo.FileVo;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao dao;
	
	@Autowired
	private ArticleRepo repo;
	@Autowired
	private FileRepo fileRepo;
	
	public int insertArticle(ArticleVo av) {
		//JPA
//		repo.save(av);
		//MyBatis
		dao.insertArticle(av);
		
		return av.getNo();
	}
	public void insertFile(FileVo fv) {
		//JPA
//		fileRepo.save(fv);
		//MyBatis
		dao.insertFile(fv);
	}
	
	public ArticleVo selectArticle(int no) {
		//JPA
//		return repo.findById(no).get();
		//MyBatis
		return dao.selectArticle(no);
	}
	public List<ArticleVo> selectArticles(int start) {
		//JPA
		return repo.findAll();
		//MyBatis
//		return dao.selectArticles(start);
		
	}
	public int selectCountTotal() {
		//JPA
		//MyBatis
		int total = dao.selectCountTotal();
		return total;
	}
	
	public void updateArticle() {}
	public void deleteArticle() {}
	
	//application.properties 에 선언된 파일 저장 경로 가져옴
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	public FileVo fileUpload(MultipartFile fname) {
		String path = new File(uploadDir).getAbsolutePath();
		
		String oName = fname.getOriginalFilename();
		String ext = oName.substring(oName.lastIndexOf("."));
		String nName = UUID.randomUUID().toString()+ext;
		FileVo fv = null;
		try {
			//첨부파일 저장
			fname.transferTo(new File(path+"/"+nName));
			//첨부파일 정보객체 생성
			fv = new FileVo();
			fv.setOName(oName);
			fv.setNName(nName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return fv;
	}
	public void fileDownload() {}
	
	public int getLastPageNum(int total){
		int lastPageNum = 0;
		
		if(total % 10 == 0) {
			lastPageNum = total / 10;
		}else {
			lastPageNum = (total / 10) + 1;
		}
		
		return lastPageNum;
	}
	public int getCurrentPage(String pg) {
		//로그인해서 바로 들어오면 pg는 null
		int currentPage = 1;
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		return currentPage;
	}
	public int getLimitStart(int currentPage) {
		//xml start 구하기
		return (currentPage - 1) * 10;
	}
	public int getPageStartNum(int total, int start) {
		return total - start;
	}
	public int[] getPageGroup(int currentPage, int lastPageNum) {
		int groupCurrent = (int) Math.ceil(currentPage/10.0);
		System.out.println("그룹 커런트 : "+groupCurrent);
		int groupStart = (groupCurrent - 1) * 10 + 1;
		int groupEnd = groupCurrent * 10;
		if(groupEnd > lastPageNum) {
			groupEnd = lastPageNum;
		}
		int[] groups = {groupStart, groupEnd};
		return groups;
	}
}
