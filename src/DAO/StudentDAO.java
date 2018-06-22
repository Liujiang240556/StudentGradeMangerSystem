
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Student;
import base.BaseDAO;

/**
 * 模块说明： 学生增删改查
 * 
 */
public class StudentDAO extends BaseDAO {
	//字段编号9,表示表格里面的列数
	private final int fieldNum = 9;
	//每页展示的个数
	private final int showNum = 6;
	private static StudentDAO sd = null;
	
    //对StudentDAO类加锁，在应用中只允许一个StudentDAO的实例存在，保证线程安全
	public static synchronized StudentDAO getInstance() {
		if (sd == null) {
			sd = new StudentDAO();
		}
		return sd;
	}

	// update更新学生信息
	public boolean update(Student stu) {
		boolean result = false;
		if (stu == null) {
			return result;
		}
		try {
			// check  此处表示一定要输入需要更新的学号，如果不输入，将更新失败
			if (queryBySno(stu.getSno()) == 0) {
				return result;
			}
			// update  执行更新操作
			//生成带参数的SQL语句
			String sql = "update student set sex=?,department=?,email=?,tel=?,hometown=?,mark=? where name=? and sno=?";
			//所有问号值的集合
			String[] param = { stu.getSex(), stu.getDepartment(), stu.getEmail(), stu.getTel(), stu.getHomeTown(),
					stu.getMark(), stu.getName(), stu.getSno() };
			//如果成功执行查询后的返回值为1，就表示成功更新，返回true
			int rowCount = db.executeUpdate(sql, param);
			if (rowCount == 1) {
				result = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}

	// delete学生信息
	public boolean delete(Student stu) {
		boolean result = false;
		if (stu == null) {
			return result;
		}
		//生成带参数的SQL语句
		String sql = "delete from student where name=? or sno=?";
		//每个问号值得集合
		String[] param = { stu.getName(), stu.getSno() };
		//如果成功执行查询后的返回值为1，就表示成功更新，返回true
		int rowCount = db.executeUpdate(sql, param);
		if (rowCount == 1) {
			result = true;
		}
		destroy();
		return result;
	}

	// add学生信息
	public boolean add(Student stu) {
		boolean result = false;
		if (stu == null) {
			return result;
		}
		try {
			// check 表示如果学生的学号相同，则添加不成功
			if (queryBySno(stu.getSno()) == 1) {
				return result;
			}
			// insert
			String sql = "insert into student(name,sno,sex,department,hometown,mark,email,tel) values(?,?,?,?,?,?,?,?)";
			String[] param = { stu.getName(), stu.getSno(), stu.getSex(), stu.getDepartment(), stu.getHomeTown(),
					stu.getMark(), stu.getEmail(), stu.getTel() };
			////如果成功执行查询后的返回值为1，就表示成功更新，返回true
			if (db.executeUpdate(sql, param) == 1) {
				result = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}
 

	
	
	
	
	
	// query by name根据学生姓名查询
	public String[][] queryByName(String name) {
		String[][] result = null;
		if (name.length() < 0) {
			return result;
		}
		List<Student> stus = new ArrayList<Student>();
		int i = 0;
		String sql = "select * from student where name like ?  or sno=?";
		String[] param = { "%" + name + "%",name };
		rs = db.executeQuery(sql, param);
		try {
			while (rs.next()) {
				buildList(rs, stus, i);
				i++;
			}
			if (stus.size() > 0) {
				//生成一个二维数组，大小为    查询到的结果集的大小*表格的列数
				result = new String[stus.size()][fieldNum];
				for (int j = 0; j < stus.size(); j++) {
					buildResult(result, stus, j);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}

		return result;
	}

	// query 根据当前页数来查询所有学生信息
	public String[][] list(int pageNum) {
		String[][] result = null;
		if (pageNum < 1) {
			return result;
		}
		List<Student> stus = new ArrayList<Student>();
		int i = 0;
		//减1原因：比如当前页数是2，如果每页显示5个，则第二页应该从第6个数据开始，则刚好是（2-1）*5开始
		int beginNum = (pageNum - 1) * showNum;
		
		//搜索从beginNum开始,向后偏移showNum,也就是向后偏移5个数， 检索记录行 beginNum到beginNum+showNum行
		String sql = "select * from student limit ?,?";
		Integer[] param = { beginNum, showNum };
		//执行查询
		rs = db.executeQuery(sql, param);
		try {
			while (rs.next()) {
				buildList(rs, stus, i);
				i++;
			}
			if (stus.size() > 0) {
				//生成一个二维数组，大小为    查询到的结果集的大小*表格的列数
				
				result = new String[stus.size()][fieldNum];
				for (int j = 0; j < stus.size(); j++) {
					//将查询结果转换成二维数组
					buildResult(result, stus, j);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}

		return result;
	}

	
	
	// 将rs记录添加到list中
	private void buildList(ResultSet rs, List<Student> list, int i) throws SQLException {
		Student stu = new Student();
		stu.setId(i + 1);
		stu.setName(rs.getString("name"));
		stu.setDepartment(rs.getString("department"));
		stu.setEmail(rs.getString("email"));
		stu.setHomeTown(rs.getString("hometown"));
		stu.setMark(rs.getString("mark"));
		stu.setSex(rs.getString("sex"));
		stu.setSno(rs.getString("sno"));
		stu.setTel(rs.getString("tel"));
		list.add(stu);
	}

	
	
	// 将list中记录添加到二维数组中，第一个参数表示一个二维数组，
	private void buildResult(String[][] result, List<Student> stus, int j) {
		Student stu = stus.get(j);
		result[j][0] = String.valueOf(stu.getId());
		result[j][1] = stu.getName();
		result[j][2] = stu.getSno();
		result[j][3] = stu.getSex();
		result[j][4] = stu.getDepartment();
		result[j][5] = stu.getHomeTown();
		result[j][6] = stu.getMark();
		result[j][7] = stu.getEmail();
		result[j][8] = stu.getTel();
	}

	
	
	
	// query by sno 根据学号来查询    如果返回0表示输入的学号为空，返回1表示查询到数据
	private int queryBySno(String sno) throws SQLException {
		int result = 0;
		if ("".equals(sno) || sno == null) {
			return result;
		}
		String checkSql = "select * from student where sno=?";
		String[] checkParam = { sno };
		rs = db.executeQuery(checkSql, checkParam);
		if (rs.next()) {
			result = 1;
		}
		return result;
	}

}
