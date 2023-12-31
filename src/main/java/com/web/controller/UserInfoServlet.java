package com.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.common.CommonView;
import com.web.service.UserInfoService;
import com.web.service.impl.UserInfoServiceImpl;
import com.web.vo.UserInfoVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

@WebServlet("/user-info/*")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserInfoService uiService = new UserInfoServiceImpl();
	private Gson gson = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = CommonView.getCmd(request);
		String json = "";

		if ("list".equals(cmd)) {
			json = gson.toJson(uiService.selectUserInfoList(null));
		} else if ("view".equals(cmd) || "update".equals(cmd)) {
			int uiNum = getUiNum(request);
			json = gson.toJson(uiService.selectUserInfo(uiNum));
		}

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(json);
	}

	private final static Gson GSON = new Gson();

	public static <T> T casting(HttpServletRequest request, Class<T> clazz) throws IOException {
		BufferedReader br = request.getReader();
		String str = null;
		StringBuffer sb = new StringBuffer();
		while ((str = br.readLine()) != null) {
			sb.append(str);
		}
		return GSON.fromJson(sb.toString(), clazz);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cmd = CommonView.getCmd(request);

		UserInfoVO userInfo = casting(request, UserInfoVO.class);
		
		String userInfoJson = GSON.toJson(userInfo);
		JsonObject userInfoJsonObj = GSON.fromJson(userInfoJson, JsonObject.class);
		String uiBirth = userInfoJsonObj.get("uiBirth").getAsString().replace("-", "");
		userInfoJsonObj.addProperty("uiBirth", uiBirth);
		
		userInfo = GSON.fromJson(userInfoJsonObj, UserInfoVO.class);
		System.out.println(userInfo);

		if ("insert".equals(cmd)) {
			int result = uiService.insertUserInfo(userInfo);
			request.setAttribute("msg", "유저 등록 성공");
			request.setAttribute("url", "/user-info/login");
			if (result != 1) {
				request.setAttribute("msg", "유저 등록 실패");
				request.setAttribute("url", "/user-info/insert");
			}
		} else if ("update".equals(cmd)) {
			int result = uiService.updateUserInfo(userInfo);
			request.setAttribute("msg", "유저 수정 성공");
			request.setAttribute("url", "/user-info/view?uiNum=" + getUiNum(request));
			if (result != 1) {
				request.setAttribute("msg", "유저 수정 실패");
				request.setAttribute("url", "/user-info/update?uiNum=" + getUiNum(request));
			}
		} else if ("delete".equals(cmd)) {
			int uiNum = getUiNum(request);
			int result = uiService.deleteUserInfo(uiNum);
			request.setAttribute("msg", "유저 삭제 성공");
			request.setAttribute("url", "/user-info/list");
			if (result != 1) {
				request.setAttribute("msg", "유저 삭제 실패");
				request.setAttribute("url", "/user-info/view?uiNum=" + getUiNum(request));
			}
		}
		CommonView.forwardMessage(request, response);
	}

	private int getUiNum(HttpServletRequest request) {
		String uiNumStr = request.getParameter("uiNum");
		int uiNum = 0;
		if (uiNumStr != null && !uiNumStr.isEmpty()) {
			uiNum = Integer.parseInt(uiNumStr);
		}
		return uiNum;
	}
}