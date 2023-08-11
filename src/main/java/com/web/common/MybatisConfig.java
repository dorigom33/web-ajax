package com.web.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.web.vo.UserInfoVO;



public class MybatisConfig {

	private static SqlSessionFactory SSF;
	private final static String CONFIG_PATH = "config/mybatis-config.xml";
	static{
		try {
			InputStream  is = Resources.getResourceAsStream(CONFIG_PATH);
			SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
			SSF = ssfb.build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return SSF;
	}
	
//	public static void main(String[] args) {
//		try(SqlSession session = getSqlSessionFactory().openSession()){
//			List<UserInfoVO> list = session.selectList("com.web.mapper.UserInfoMapper.selectUserInfoList");
//			for(UserInfoVO userInfo : list) {
//				System.out.println(userInfo);
//			}
//			UserInfoVO user = session.selectOne("com.web.mapper.UserInfoMapper.selectUserInfo",2);	//여기 들어가는 게 파라미터임 지금은 2
//			System.out.println(user);
//			
////			UserInfoVO newUser = new UserInfoVO();
////	        newUser.setUiName("SANG");
////	        newUser.setUiId("SANGS");
////	        newUser.setUiPwd("sang");
////	        newUser.setUiImgPath(null);
////	        newUser.setUiDesc("sang");
////	        newUser.setUiBirth("19980101");
////	        newUser.setActive("1");
////	        
////	        int insertResult = session.insert("com.web.mapper.UserInfoMapper.insertUserInfo", newUser);
////	        System.out.println(insertResult);
////
////	        UserInfoVO updatedUser = new UserInfoVO();
////	        updatedUser.setUiNum(7);
////	        updatedUser.setUiName("JUNG");
////	        updatedUser.setUiPwd("JUNG");
////	        updatedUser.setUiImgPath(null);
////	        updatedUser.setUiDesc("jung");
////	        updatedUser.setUiBirth("20010319");
////	        updatedUser.setActive("1");
////	        
////	        int updateResult = session.update("com.web.mapper.UserInfoMapper.updateUserInfo", updatedUser);
////	        
////	        int deleteResult = session.delete("com.web.mapper.UserInfoMapper.deleteUserInfo", 3); 
//
//		}catch (Exception e) {
//			
//		}
//	}
	

}