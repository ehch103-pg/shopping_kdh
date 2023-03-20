package co.kr.shopping.vo;

import java.util.Date;

import lombok.Data;

@Data
public class FilesVO {
	
	private String fileSeq;
	private String fileCd;
	private String fileOriginName;
	private String fileRoot;
	private String regUser;
	private Date regDate;
}
