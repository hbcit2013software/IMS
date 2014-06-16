package cn.edu.hbcit.dao;

import java.sql.Connection;
import java.util.ArrayList;

import net.sf.json.JSONArray;


import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;


import cn.edu.hbcit.pojo.Users;

/**
 * 公共权限类
 * 简要说明:
 * @author 刘杰
 * @version 1.00  2014-6-16下午08:57:57	新建
 */
public class RightsDao {
	protected final Logger log = Logger.getLogger(RightsDao.class.getName());
	/**
	 * 判断权限是否为负责人
	 * @return
	 */
	public boolean isMajorsManager(String username){
		
		ArrayList<Users> list = null;
		boolean flag=false;
		try {
			Connection conn = Base.Connect();
			Users users = new Users();
			QueryRunner qr = new QueryRunner();
			String sql = "SELECT level FROM tb_users WHERE tb_users=username And level=1";
			
			list = (ArrayList<Users>)qr.query(conn, sql, new BeanListHandler(Users.class),username);
			if (list.size()>0){
				flag=true;
			}
			DbUtils.closeQuietly(conn);//关闭连接
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}
	/**
	 * 判断权限是否为系领导
	 * @return
	 */
	public boolean isDepartmentManager(String username){
		
		ArrayList<Users> list = null;
		boolean flag=false;
		try {
			Connection conn = Base.Connect();
			Users users = new Users();
			QueryRunner qr = new QueryRunner();
			String sql = "SELECT level FROM tb_users WHERE tb_users=username AND level=0";
			
			list = (ArrayList<Users>)qr.query(conn, sql, new BeanListHandler(Users.class),username);
			if (list.size()>0){
				flag=true;
			}
			DbUtils.closeQuietly(conn);//关闭连接
					} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}
}
