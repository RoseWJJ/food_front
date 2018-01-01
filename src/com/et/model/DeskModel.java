package com.et.model;

import java.util.List;
import java.util.Map;

import com.et.tool.OracleTool;
import com.et.tool.PageTool;

public class DeskModel {
	/**
	 * ��ȡ����
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
		// ����list�еȵ�������map ���� ����֪�� ��ȡ map�е�ֵ ��ͨ���� ��ȡlist�е�Ԫ����ͨ���±� ����
		return Integer.parseInt(list.get(0).get("CR").toString());
	}

	public static PageTool getTableLsitPager(String name, Integer curPage)
			throws Exception {
		if (name == null) {
			name = "";
		}
		Integer count = DeskModel.getTableListCount(name);
		
		// ������� ��ǰҳ�� �ܸ�����ÿҳ��ʾ����
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
