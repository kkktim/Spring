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
	
	
	//SELECT
	public int selectCountTotal(String type) {
		return dao.selectCountTotal(type);
	}
	public FileVo selectFile(int fid) {
		return dao.selectFile(fid);
	}
	public ArticleVo selectArticle(int no) {
		return dao.selectArticle(no);
	}
	public List<ArticleVo> selectArticles(String type, int start) {
		return dao.selectArticles(type, start);
	}
	//UPDATE
	public void updateArticle(ArticleVo av) {
		dao.updateArticle(av);
	}
	//DELETE
	public void deleteFile(int fid) {
		dao.deleteFile(fid);
	}
	public void deleteArticle(int no) {
		dao.deleteArticle(no);
	}
	
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
	//파일 삭제
	public void deleteAttachedFile(FileVo fv) {
		String path = new File(uploadDir).getAbsolutePath()+"/"+fv.getNName();
		File attfile = new File(path);
		attfile.delete();
	}
	
	//페이지처리
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
		int groupStart = (groupCurrent - 1) * 10 + 1;
		int groupEnd = groupCurrent * 10;
		if(groupEnd > lastPageNum) {
			groupEnd = lastPageNum;
		}
		int[] groups = {groupStart, groupEnd};
		return groups;
	}
}
