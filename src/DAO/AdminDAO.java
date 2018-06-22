
package DAO;

import java.sql.SQLException;

import base.BaseDAO;

/**
 * 模块说明： 管理员增删改查
 * DAO层对数据库访问     实现连接数据库  修改、添加等细节 
 */
public class AdminDAO extends BaseDAO {

	private static AdminDAO ad = null;
//对象加锁，在应用中只允许有一个AdminDAO对象
	public static synchronized AdminDAO getInstance() {
		if (ad == null) {
			ad = new AdminDAO();
		}
		return ad;
	}
//  检查登录名和密码是否正确
	public boolean queryForLogin(String username, String password) {
		boolean result = false;
		if (username.length() == 0 || password.length() == 0) {
			return result;
		}
		//查询语句
		String sql = "select * from admin where username=? and password=?";
		String[] param = { username, password };
		//执行查询
		rs = db.executeQuery(sql, param);
		try {
			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}

}
