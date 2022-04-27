package kr.co.farmstory.service;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.farmstory.dao.BoardDao;
import kr.co.farmstory.vo.ArticleVo;
import kr.co.farmstory.vo.FileVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao dao;
	
	public void insertFile(FileVo fv) {
		dao.insertFile(fv);
	}
	public int insertArticle(ArticleVo av) {
		dao.insertArticle(av);
		return av.getNo();
	}
	
	public FileVo selectFile(int fid) {
		return dao.selectFile(fid);
	}
	public ArticleVo selectArticle(int no) {
		return dao.selectArticle(no);
	}
	public List<ArticleVo> selectArticles(String type) {
		return dao.selectArticles(type);
	}
	public void updateArticle(ArticleVo av) {}
	public void deleteArticle(int no) {}
	
	//파일 업로드
	@Value("${spring.servlet.multipart.location}")
	private String uploadDir;
	
	public FileVo fileUpload(MultipartFile fname) {
		String path = new File(uploadDir).getAbsolutePath();
		
		String oName = fname.getOriginalFilename();
		String ext = oName.substring(oName.lastIndexOf("."));
		//UUID - Universal Unique ID (범용 공용 식별자) 자바 내장 객체
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
	//파일 다운로드
	public void fileDownload(HttpServletResponse resp, FileVo fv) {
		try {
			// 파일 다운로드 response 헤더수정	
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fv.getOName(), "utf-8"));
			resp.setHeader("Content-Transfer-Encoding", "binary");
			resp.setHeader("Pragma", "no-cache");
			resp.setHeader("Cache-Control", "private");
						
			// 파일 스트림 작업
			String path = new File(uploadDir).getAbsolutePath()+"/"+fv.getNName();
			byte[] fileByte = FileUtils.readFileToByteArray(new File(path));		
						
			resp.getOutputStream().write(fileByte);
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
			
			
//			return 1;
		}catch(Exception e) {
			e.printStackTrace();
//			return 0;
		}
	}
	//다운로드 카운터 +1
	public void downCountPlus(int fid) {
		dao.downCountPlus(fid);
	}
	
}
