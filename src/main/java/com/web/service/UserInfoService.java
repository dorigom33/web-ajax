package com.web.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.web.vo.UserInfoVO;

public interface UserInfoService {
	List<UserInfoVO> selectUserInfoList(UserInfoVO user);

	UserInfoVO selectUserInfo(int uiNum);

	int insertUserInfo(UserInfoVO user);

	int updateUserInfo(UserInfoVO user);

	int deleteUserInfo(int uiNum);
}
