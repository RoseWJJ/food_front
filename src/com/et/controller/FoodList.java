package com.et.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.et.model.FoodTypeModel;
import com.et.model.MyFood;
import com.et.tool.PageTool;

public class FoodList extends HttpServlet {

	FoodTypeModel ftm = new FoodTypeModel();
	MyFood mf = new MyFood();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String foodName = request.getParameter("foodName");
		// 获取当前餐桌 编号 存入session 方便结账时使用；
		String typeid = "";
		try {
			if (request.getParameter("typeid") != null) {
				typeid = request.getParameter("typeid");
			}
			HttpSession session = request.getSession();
			if (request.getParameter("DeskId") != null) {
				session.setAttribute("deskId", request.getParameter("DeskId"));
			}
			String curPage = request.getParameter("curPage");
			String curPageInt = "1";
			if (curPage != null) {
				curPageInt = curPage;
			}
			List foodTypelist = ftm.getFoodType();
			request.setAttribute("FoodtypeList", foodTypelist);
			PageTool foodList = mf.getFoodPager(curPageInt, typeid, foodName);
			// 菜品
			request.setAttribute("foodList", foodList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/detail/caidan.jsp").forward(request,
				response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
