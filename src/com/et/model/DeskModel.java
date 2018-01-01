package com.et.model;

import java.util.List;
import java.util.Map;

import com.et.tool.OracleTool;
import com.et.tool.PageTool;

public class DeskModel {
	/**
	 * 获取个数
	 * 
	 * @param name
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public static List<Map> getAllInfor() throws Exception {
		String sql = "select * from desk";
		
		List<Map> list = OracleTool.query(sql);
		return list;
	}

	public static Integer getTableListCount(String name) throws Exception {
		if (name == null) {
			name = "";
		}
		String sql = "select count(rowid) as CR from  DESK where DNAME  like '%"
				+ name + "%'";
		
		List<Map> list = OracleTool.query(sql);
		// 由于list中等的类型是map 所以 我们知道 获取 map中的值 是通过键 获取list中的元素是通过下标 所以
		return Integer.parseInt(list.get(0).get("CR").toString());
	}

	public static PageTool getTableLsitPager(String name, Integer curPage)
			throws Exception {
		if (name == null) {
			name = "";
		}
		Integer count = DeskModel.getTableListCount(name);
		
		// 传入参数 当前页， 总个数，每页显示个数
		PageTool pt = new PageTool(curPage, count, null);
		String sql = "select * from(select rownum rn,t.* from DESK t where DNAME like '%"
				+ name
				+ "%')"
				+ "where rn >="
				+ pt.getStartIndex()
				+ " and  rn <=" + pt.getEndIndex();

		List<Map> list = OracleTool.query(sql);
		pt.setData(list);
		return pt;

	}

	public static void saveDesk(String deskName) throws Exception {
		String sql = "insert into desk values((select max(deskid)+1 from desk),'"
				+ deskName + "',0,'')";

		int count = OracleTool.execute(sql);

	}

	public static void deleteDesk(String deskid) throws Exception {
		String sql = "delete from desk  where deskid = " + deskid;
		int count = OracleTool.execute(sql);

	}

}
