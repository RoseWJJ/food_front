package com.et.model;

import java.util.List;
import java.util.Map;
import com.et.tool.OracleTool;

public class MyOrder {
	public Integer  getOrderId() throws Exception {
		String sql = "select nvl(max(deskid),0)+1  as myId from FOODORDER";
		List<Map> result = OracleTool.query(sql);
		return Integer.parseInt((result.get(0).get("MYID")).toString());
	}

	public Integer saveOrder(String deskId, int state) throws Exception {
		Integer orderId = getOrderId();
		String sql = "insert into FOODORDER values('" + orderId + "','"
				+ deskId + "',sysdate,'" + state + "')";
		
		OracleTool.execute(sql);

		// 由于list中等的类型是map 所以 我们知道 获取 map中的值 是通过键 获取list中的元素是通过下标 所以
		return orderId;
	}

	/**
	 * 1011 1 xxx 2 YYY
	 */
	public void saveOrderDetail(int orderId, Integer fid, Integer count)
			throws Exception {
		String sql = "insert into FOODORDERDETAIL values((select nvl(max(detailid),0)+1 as myId from FOODORDERDETAIL)"
				+ ",'" + orderId + "','" + fid + "','" + count + "')";

		OracleTool.execute(sql);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
