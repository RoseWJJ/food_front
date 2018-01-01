package com.et.model;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.et.tool.OracleTool;
import com.et.tool.PageTool;

public class MyFood {

	public static Integer getFoodCount(String typeID,String foodName) throws Exception{
		String sql = null;
		if(typeID!=null && !typeID.isEmpty()){
			sql = "select count(rowid) as CR from  FOOD  WHERE TYPEID = "+typeID;
		}else{
			sql = "select count(rowid) as CR from  FOOD";
		}
		if(foodName!=null && !foodName.isEmpty()){
			sql = "select count(rowid) as CR from  FOOD  WHERE foodName like '%"+foodName+"%'";
		}
		
		
		
		List<Map>list = OracleTool.query(sql);
		
		//����list�еȵ�������map ���� ����֪�� ��ȡ map�е�ֵ ��ͨ����  ��ȡlist�е�Ԫ����ͨ���±� ����
		return Integer.parseInt(list.get(0).get("CR").toString()) ;
	}
	
	
	public static PageTool getFoodPager(String curPage,String typeIds,String foodName) throws Exception{
	
		Integer count = getFoodCount(typeIds,foodName);
		//������� ��ǰҳ�� �ܸ�����ÿҳ��ʾ����
		int cr = 1;
		if(curPage!=null){
			cr = Integer.parseInt(curPage);
		}
		PageTool pt = new PageTool(cr, count,6);
		String sql = "";
		
		if(typeIds !=null && !typeIds.isEmpty()){
			int typeId = Integer.parseInt(typeIds);
			sql="select * from(select ft.typename,rownum rn,t.* from FOOD t inner join foodtype ft on t.typeid=ft.typeid where t.typeid = '"+typeId+"')" +
			"where rn >="+ pt.getStartIndex() +" and  rn <="+pt.getEndIndex();
		}else{
			sql="select * from(select rownum rn,t.* from FOOD t )where rn >="+ pt.getStartIndex() +" and  rn <="+pt.getEndIndex();
		}
		if(foodName !=null && !foodName.isEmpty()){
			sql="select * from(select ft.typename,rownum rn,t.* from FOOD t inner join foodtype ft on t.typeid=ft.typeid where foodname like '%"+foodName+"%')" +
			"where rn >="+ pt.getStartIndex() +" and  rn <="+pt.getEndIndex();
			
		}
		List<Map> list =OracleTool.query(sql);
		pt.setData(list);
		return pt;	
	}	 
}
