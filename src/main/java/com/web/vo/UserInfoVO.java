package com.web.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@Getter
public class UserInfoVO {
	
	private int uiNum;
	private String uiName;
	private String uiPwd;
	private String uiImgPath;
	private String uiDesc;
	private String uiBirth;
	private String credat;
	private String cretim;
	private String lmodat;
	private String lmotim;
	private String active;
	
	
}
class Execute{
	public static void main(String[] args) {
		UserInfoVO user = new UserInfoVO();
		user.setUiNum(1);
		user.setUiName("영우");
		user.setUiPwd("young");
		user.setUiImgPath(null);
		user.setUiDesc("영우");
		user.setUiBirth("19990223");
		user.setActive("1");
		System.out.println(user.getUiNum());
	}
}
