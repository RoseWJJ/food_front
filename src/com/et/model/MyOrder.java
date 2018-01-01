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

		// ����list�еȵ�������map ���� ����֪�� ��ȡ map�е�ֵ ��ͨ���� ��ȡlist�е�Ԫ����ͨ���±� ����
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
