package com.et.model;

import java.util.List;
import java.util.Map;

import com.et.tool.OracleTool;
import com.et.tool.PageTool;

public class FoodTypeModel {
	public List<Map> getFoodType() throws Exception {
		String sql = "select * from foodtype";
	
		List<Map> list = OracleTool.query(sql);
		return list;
	}

	public static Integer getTableListCount(String name) throws Exception {
		if (name == null) {
			name = "";
		}
		String sql = "select count(rowid) as CR from  FOODTYPE where TYPENAME like '%"
				+ name + "%'";
		
		List<Map> list = OracleTool.query(sql);
		// ����list�еȵ�������map ���� ����֪�� ��ȡ map�е�ֵ ��ͨ���� ��ȡlist�е�Ԫ����ͨ���±� ����
		return Integer.parseInt(list.get(0).get("CR").toString());
	}

	public static PageTool getTableLsitPager(String name, Integer curPage)
			throws Exception {
		if (name == null) {
			name = "";
		}
		Integer count = DeskModel.getTableListCount(name);
		System.out.println(count);
		// ������� ��ǰҳ�� �ܸ�����ÿҳ��ʾ����
		PageTool pt = new PageTool(curPage, count, null);
		String sql = "select * from(select rownum rn,t.* from FOODTYPE t where TYPENAME like '%"
				+ name
				+ "%')"
				+ "where rn >="
				+ pt.getStartIndex()
				+ " and  rn <=" + pt.getEndIndex();

		List<Map> list = OracleTool.query(sql);
		pt.setData(list);
		return pt;

	}



	
	public static List<Map> queryFoodType(String FOODID) throws Exception {
		String sql = "select from FOODTYPE  where deskid = " + FOODID;
	
		return OracleTool.query(sql);

	}
	public static List<Map> queryFood(String TYPEID) throws Exception {
		String sql = "select from food  where TYPEID = " + TYPEID;

		return OracleTool.query(sql);

	}
}
