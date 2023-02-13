package co.kr.shopping.vo;

import lombok.Data;

@Data
public class ReviewVO {
	private int reviewNo;
	private String reviewTitle;
	private String reviewWriter;
	private String reviewContents;
	private String reviewLock;
	private String reviewWriteDate;
	private String reviewChDate;
	private String reviewProductId;
	private int viewCount;
	private String delYn;
	
}
