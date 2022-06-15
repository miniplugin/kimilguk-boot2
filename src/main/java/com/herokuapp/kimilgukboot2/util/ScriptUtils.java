package com.herokuapp.kimilgukboot2.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ScriptUtils {
	// 공통사용메서드
	public static void init(HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
	}

	// 완료메세지 출력 후 페이지 이동하는 기능(아래)
	public static void alertAndMovePage(HttpServletResponse response, String alertText, String nextPage) throws IOException {
		init(response);
		PrintWriter out = response.getWriter();
		out.println("<script>alert('" + alertText + "');location.href='" + nextPage + "';</script>");
		out.flush();// 메모리에서 out객체를 삭제한다.(자원관리)
	}

	// 메시지 출력 후 페이지 이동없는 처리(아래)
	public static void alert(HttpServletResponse response, String alertText) throws IOException {
		init(response);
		PrintWriter out = response.getWriter();// 스트림 방식으로 출력 객체생성:예외처리필수
		out.println("<script>alert('" + alertText + "');</script>");
		out.flush();// 객체삭제
	}

	// 메시지 출력 후 페이지 이동없는 처리(아래)
	public static void alertAndBackPage(HttpServletResponse response, String alertText) throws IOException {
		init(response);
		PrintWriter out = response.getWriter();// 스트림 방식으로 출력 객체생성:예외처리필수
		out.println("<script>alert('" + alertText + "');history.go(-1)</script>");
		out.flush();// 객체삭제
	}
}
