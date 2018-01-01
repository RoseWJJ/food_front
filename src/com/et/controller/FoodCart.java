package com.et.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.et.model.MyOrder;

public class FoodCart extends HttpServlet {
	MyOrder order= new MyOrder();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();
		String flage = request.getParameter("flage");
		System.out.println(flage);
		if ("delete".equals(flage)) {
			String cid = request.getParameter("cid");
			session.removeAttribute(cid);
			request.getRequestDispatcher("/detail/clientCart.jsp").forward(
					request, response);
		} else if ("order".equals(flage)) {
			
			String deskId = session.getAttribute("deskId").toString();
			System.out.println(deskId+"------");
			int state = 0;
			try {
				//返回订单编号
				Integer orderId = order.saveOrder(deskId, state);
				//循环session中所有的键 cart_fid
				Enumeration <String> keys = session.getAttributeNames();
				while (keys.hasMoreElements()) {
					String key = (String) keys.nextElement();
					if(key.startsWith("cart_")){
						String fid = key.split("cart_")[1];
						Map map = (Map)session.getAttribute(key);
						Integer count =(Integer)map.get("count");
						order.saveOrderDetail(orderId, Integer.parseInt(fid), count);
						
					}
				}
				session.invalidate();
				pw.write("<script>alert('下单成功')</script>");
				response.setHeader("refresh", "1;url="+request.getContextPath()+"/ShowDesk");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			String fid = request.getParameter("fid");
			String fname = request.getParameter("fname");
			String price = request.getParameter("price");

			Object food = session.getAttribute("cart_" + fid);
			if (food == null) {
				Map map = new HashMap();
				map.put("fname", fname);
				map.put("price", price);
				map.put("count", 1);
				session.setAttribute("cart_" + fid, map);
			} else {
				Map map = (Map) food;
				Integer in = (Integer) map.get("count");
				map.put("count", in + 1);
				session.setAttribute("cart_" + fid, map);
			}
			request.getRequestDispatcher("/detail/clientCart.jsp").forward(
					request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
