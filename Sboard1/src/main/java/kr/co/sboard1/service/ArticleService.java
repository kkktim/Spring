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
	public List<ArticleVo> selectArticles() {
		//JPA
//		return repo.findAll();
		//MyBatis
		return dao.selectArticles();
		
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
}
