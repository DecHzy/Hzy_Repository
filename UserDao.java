package dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import beans.deptdata;
import beans.drdata;
import beans.hosps;
import beans.patdata;
import beans.remaindata;
import beans.resdata;
import util.DBUtil;

public class UserDao {
	/**
	 * 添加医生时同时添加剩余量
	 * 
	 * @param drdata
	 * @param date
	 * @return
	 */
	public static void addNewDrRemain(drdata dr,Date date) {
		Connection conn=null;
		String sql = null;
		PreparedStatement stm=null;
		remaindata rmdata = new remaindata();
		List<Date> list = new ArrayList<Date>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, 1);
		Date date1 = calendar.getTime();
		list.add(date1);
		calendar.add(calendar.DATE, 1);
		Date date2 = calendar.getTime();
		list.add(date2);
		calendar.add(calendar.DATE, 1);
		Date date3 = calendar.getTime();
		list.add(date3);
		try {
			conn=DBUtil.getConnection();
			rmdata.setDeptName(dr.getDeptName());
			rmdata.setDrName(dr.getDrName());
			rmdata.setHospName(dr.getHospName());
			System.out.println(rmdata.getDeptName());
			for (int i = 0; i < 3; i++) {
				rmdata.setResDate(list.get(i));
				for (int j = 1; j < 6; j++) {
					rmdata.setTime(j);
					String sql2 = "insert into remaindata (deptName,drName,hospName,resDate,time,remain) values (?,?,?,?,?,10)";
					stm = conn.prepareStatement(sql2);
					stm.setString(1, dr.getDeptName());
					stm.setString(2, dr.getDrName());
					stm.setString(3, dr.getHospName());
					stm.setDate(4, new java.sql.Date(list.get(i).getTime()));
					stm.setInt(5, j);
					stm.executeUpdate();
				}
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	/**
	 * 删除医生相关的剩余量
	 *
	 */
	public static void deleteDrRemain(drdata dr) {
		Connection conn = null;
		PreparedStatement stm = null;
		String sql = null;
		try {
			conn=DBUtil.getConnection();
			sql="delete from remaindata where drName = ? and hospName = ?";
			stm=conn.prepareStatement(sql);
			stm.setString(1, dr.getDrName());
			stm.setString(2, dr.getHospName());
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	/**
	 * 查询所有医院和医院简介
	 * 
	 * @return
	 */
	public static List<hosps> getHospital() {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<hosps> hospList = new ArrayList<hosps>();
		hosps hos = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from hosps";
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				hos = new hosps();
				hos.setHospName(rs.getString("hospName"));
				hos.setHospIntro(rs.getString("hospIntro"));
				hospList.add(hos);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBUtil.close(conn, stm, rs);
		}

		return hospList;
	}

	/**
	 * 删除医院
	 * 
	 * @param hospsName
	 * @return
	 */
	public static int DeleteHospital(String hospsName) {
		Connection conn = null;
		PreparedStatement stm = null;
		int result = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from hosps where hospName=" + hospsName;
			stm = conn.prepareStatement(sql);
			result = stm.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBUtil.close(conn, stm, null);
		}
		return result;
	}

	/**
	 * 查询某个医院的科室简介
	 * 
	 * @param hopname
	 * @return
	 */
	public static List<deptdata> getDepartment(String hopname) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String sql = "";
		List<deptdata> deptList = new ArrayList<deptdata>();
		deptdata dept = null;
		try {
			conn = DBUtil.getConnection();

			if (hopname != null || !"".equals(hopname))
				sql = "select * from deptdata where hospName = '" + hopname + "'";
			else {
				sql = "select * from deptdata ";
			}
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				dept = new deptdata();
				dept.setHospName(rs.getString("hospName"));
				dept.setDeptName(rs.getString("deptName"));
				dept.setDeptIntro(rs.getString("deptIntro"));
				deptList.add(dept);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			System.out.println(hopname + "/t/t/t " + sql);
			DBUtil.close(conn, stm, rs);
		}

		return deptList;
	}

	/**
	 * 下拉框选择医院后显示科室信息
	 * 
	 * @param hopname
	 * @return
	 */
	public static List<deptdata> getDeptnameByHopname(String hopname) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String sql = "";
		List<deptdata> deptList = new ArrayList<deptdata>();
		deptdata dept = null;
		try {
			conn = DBUtil.getConnection();

			if (hopname != null || !"".equals(hopname))
				sql = "select * from deptdata where hospName = '" + hopname + "'";
			else {
				sql = "select * from deptdata ";
			}
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				dept = new deptdata();
				dept.setHospName(rs.getString("hospName"));
				dept.setDeptName(rs.getString("deptName"));
				dept.setDeptIntro(rs.getString("deptIntro"));
				deptList.add(dept);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			System.out.println(hopname + "/t/t/t " + sql);
			DBUtil.close(conn, stm, rs);
		}

		return deptList;
	}

	/**
	 * 患者注册
	 * 
	 * @param patient
	 * @return
	 */
	public static int addUser(patdata patient) {
		int result = 0;
		Connection conn = null;
		PreparedStatement stm = null;
		PreparedStatement stm1 = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into patdata (patName,age,sex,password,address,remarks,phoneNum) values (?,?,?,?,?,?,?)";
			stm = conn.prepareStatement(sql);
			stm.setString(1, patient.getPatName());
			stm.setInt(2, patient.getAge());
			stm.setString(3, patient.getSex());
			stm.setString(4, patient.getPassword());
			stm.setString(5, patient.getAddress());
			stm.setString(6, patient.getRemarks());
			stm.setString(7, patient.getPhoneNum());
			// stm.setDate(4,new java.sql.Date(user.getBirthday().getTime()));
			result = stm.executeUpdate();
			String sql1 = "select * from patdata";
			stm1 = conn.prepareStatement(sql1);
			rs = stm1.executeQuery();
			rs.last();
			return rs.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, null);
			DBUtil.close(null, stm1, rs);
		}
		return result;
	}

	/**
	 * 患者登录
	 * 
	 * @param userid
	 * @param password
	 * @return
	 */
	public static patdata userLogin(String userid, String password) {
		patdata goal = null;
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from patdata where id = ? and password = ? ";
			stm = conn.prepareStatement(sql);
			stm.setString(1, userid);
			stm.setString(2, password);
			rs = stm.executeQuery();
			if (rs.next()) {
				goal = new patdata();
				goal.setId(rs.getInt("id"));
				goal.setAge(rs.getInt("age"));
				goal.setAddress(rs.getString("address"));
				goal.setPatName(rs.getString("patName"));
				goal.setSex(rs.getString("sex"));
				goal.setPassword(rs.getString("password"));
				goal.setRemarks(rs.getString("remarks"));
				return goal;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, rs);
		}
		return goal;
	}

	/**
	 * 查看医院的详细信息
	 * 
	 * @param hospitalName
	 * @return
	 */
	public static String getHospIntro(String hospitalName) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String hospIntro = "";
		try {
			conn = DBUtil.getConnection();
			String sql = " select * from hosps where hospName = ?";
			stm = conn.prepareStatement(sql);
			stm.setString(1, hospitalName);
			rs = stm.executeQuery();
			if (rs.next()) {
				hospIntro = rs.getString("hospIntro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, rs);
		}
		return hospIntro;
	}

	/**
	 * 查看科室的详细信息
	 * 
	 * @param hospName
	 * @param deptName
	 * @return
	 */
	public static String getDeptIntro(String hospName, String deptName) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String deptIntro = "";
		try {
			conn = DBUtil.getConnection();
			String sql = " select * from deptdata where hospName = ? and deptName = ?";
			stm = conn.prepareStatement(sql);
			stm.setString(1, hospName);
			stm.setString(2, deptName);
			rs = stm.executeQuery();
			if (rs.next()) {
				deptIntro = rs.getString("deptIntro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, rs);
		}
		return deptIntro;
	}

	/**
	 * 查看医生的详细信息
	 * 
	 * @param hospName
	 * @param deptName
	 * @param drName
	 * @return
	 */
	public static String getIntro(String hospName, String deptName, String drName) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String intro = "";
		try {
			conn = DBUtil.getConnection();
			String sql = " select * from drdata where hospName = ? and deptName = ? and drName =?";
			stm = conn.prepareStatement(sql);
			stm.setString(1, hospName);
			stm.setString(2, deptName);
			stm.setString(3, drName);
			rs = stm.executeQuery();
			if (rs.next()) {
				intro = rs.getString("intro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, rs);
		}
		return intro;
	}

	/**
	 * 初始化剩余量
	 */
	public static void setRemain(Date date) {
		Connection conn = null;
		PreparedStatement stm = null;
		PreparedStatement stm2 = null;
		List<Date> list = new ArrayList<Date>();
		remaindata rmdata = new remaindata();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, 1);
		Date date1 = calendar.getTime();
		list.add(date1);
		calendar.add(calendar.DATE, 1);
		Date date2 = calendar.getTime();
		list.add(date2);
		calendar.add(calendar.DATE, 1);
		Date date3 = calendar.getTime();
		list.add(date3);
		ResultSet rs = null;
		String sql = null;
		conn = DBUtil.getConnection();
		try {
			sql = "select * from remaindata";
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if (!rs.next()) {
				sql = "select * from drdata";
				stm = conn.prepareStatement(sql);
				rs = stm.executeQuery();
				while (rs.next()) {
					rmdata.setDeptName(rs.getString("deptName"));
					rmdata.setDrName(rs.getString("drName"));
					rmdata.setHospName(rs.getString("hospName"));
					System.out.println(rmdata.getDeptName());
					for (int i = 0; i < 3; i++) {
						rmdata.setResDate(list.get(i));
						for (int j = 1; j < 6; j++) {
							rmdata.setTime(j);
							String sql2 = "insert into remaindata (deptName,drName,hospName,resDate,time,remain) values (?,?,?,?,?,10)";
							stm2 = conn.prepareStatement(sql2);
							stm2.setString(1, rs.getString("deptName"));
							stm2.setString(2, rs.getString("drName"));
							stm2.setString(3, rs.getString("hospName"));
							stm2.setDate(4, new java.sql.Date(list.get(i).getTime()));
							stm2.setInt(5, j);
							stm2.executeUpdate();
						}
					}
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, rs);
			DBUtil.close(null, stm2, null);
		}
	}

	/**
	 * 根据日期自动刷新预约剩余量
	 */
	public static void UpdataRemain(Date date) {
		Connection conn = null;
		PreparedStatement stm1 = null;
		PreparedStatement stm2 = null;
		PreparedStatement stm3 = null;
		ResultSet rs = null;
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		Date date01;
		calendar.add(calendar.DATE, 1);
		date01 = calendar.getTime();
		try {
			conn = DBUtil.getConnection();
			// 查看是否需要更新信息
			String sql = "select * from remaindata";
			stm1 = conn.prepareStatement(sql);
			rs = stm1.executeQuery();
			if (rs.next()) {
				System.out.println(date01);
				if (rs.getDate("resDate") == new java.sql.Date(date01.getTime()))
					;
				return;
			}
			// 删除已过期的预约信息
			sql = " delete from remaindata where resDate = ? ";
			stm1 = conn.prepareStatement(sql);
			stm1.setDate(1, new java.sql.Date(date.getTime()));
			stm1.executeUpdate();
			calendar.add(calendar.DATE, 2);
			date01 = calendar.getTime();
			// 搜索获取所有的医生
			sql = "select * from drdata";
			stm2 = conn.prepareStatement(sql);
			rs = stm2.executeQuery();
			// 为每个医生每天五个时间添加剩余量存入数据库
			while (rs.next()) {
				for (int i = 1; i <= 5; i++) {
					sql = "insert into remaindata(hospName,deptName,drName,resDate,time,remain) values(?,?,?,?,?,10)";
					stm3 = conn.prepareStatement(sql);
					stm3.setString(1, rs.getString("hospName"));
					stm3.setString(2, rs.getString("deptName"));
					stm3.setString(3, rs.getString("drName"));
					stm3.setDate(4, new java.sql.Date(date01.getTime()));
					stm3.setInt(5, i);
					stm3.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm1, rs);
			DBUtil.close(null, stm2, null);
			DBUtil.close(null, stm3, null);
		}
	}

	/**
	 * 根据时间医生获取可预约时间段
	 * 
	 * @param drdata
	 * @param date
	 * @return
	 */
	public static int[] selectTime(drdata drdata, Date date) {
		int Time[] = { 0, 0, 0, 0, 0 };
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = " select * from remaindata where hospName = ? and deptName = ? and"
					+ " drName =? and resDate =?";
			stm = conn.prepareStatement(sql);
			stm.setString(1, drdata.getHospName());
			stm.setString(2, drdata.getDeptName());
			stm.setString(3, drdata.getDrName());
			stm.setDate(4, new java.sql.Date(date.getTime()));
			rs = stm.executeQuery();
			int i = 0;
			while (rs.next()) {
				Time[i] = rs.getInt("remain");
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, rs);
		}
		return Time;
	}

	/**
	 * 查询挂号信息
	 */
	public static List<resdata> getResData(patdata pat) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String sql = null;
		List<resdata> list = new ArrayList<resdata>();
		try {
			conn = DBUtil.getConnection();
			sql = "select * from resdata where patId = ?";
			stm = conn.prepareStatement(sql);
			stm.setInt(1, pat.getId());
			rs = stm.executeQuery();
			while (rs.next()) {
				resdata res = new resdata();
				res.setDeptName(rs.getString("deptName"));
				res.setDrName(rs.getString("drName"));
				res.setHospName(rs.getString("hospName"));
				res.setPatId(rs.getInt("patid"));
				res.setPatName(rs.getString("patName"));
				res.setResDate(rs.getDate("resDate"));
				res.setResFee(rs.getDouble("resFee"));
				res.setResTime(rs.getInt("resTime"));
				res.setStat(rs.getInt("stat"));
				list.add(res);
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, rs);
		}
		return list;
	}

	/**
	 * 更改取消预约挂号 要改的
	 */
	public static int updateRes(resdata resdata) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			// 查询预约挂号记录
			String sql = " select * from resdata where patId = ?";
			stm = conn.prepareStatement(sql);
			stm.setInt(1, resdata.getPatId());
			rs = stm.executeQuery();
			if (rs.next()) {
				// 更新病人预约信息
				sql = " update resdata set stat=1 where patId = ? and hospName =? and deptName =? and drName =? and "
						+ "patName =? and resDate =? and resFee =? and resTime =?";
				stm = conn.prepareStatement(sql);
				stm.setInt(1, resdata.getPatId());
				stm.setString(2, resdata.getHospName());
				stm.setString(3, resdata.getDeptName());
				stm.setString(4, resdata.getDrName());
				stm.setString(5, resdata.getPatName());
				stm.setDate(6, new java.sql.Date(resdata.getResDate().getTime()));
				stm.setDouble(7, resdata.getResFee());
				stm.setInt(8, resdata.getResTime());
				stm.executeUpdate();
				// 查询剩余量表
				sql = " select *  from remaindata where hospName=? and deptName=? and"
						+ " drName =? and resDate =? and time =?";
				stm = conn.prepareStatement(sql);
				stm.setString(1, resdata.getHospName());
				stm.setString(2, resdata.getDeptName());
				stm.setString(3, resdata.getDrName());
				stm.setDate(4, new java.sql.Date(resdata.getResDate().getTime()));
				stm.setInt(5, resdata.getResTime());
				rs = stm.executeQuery();
				int remain;
				if (rs.next()) {
					remain = rs.getInt("remain");
				} else {
					return 0;
				}
				// 更改剩余量+1
				sql = "update remaindata set remain =?  where hospName=? and deptName=? and"
						+ " drName =? and resDate =? and time =?";
				stm = conn.prepareStatement(sql);
				stm.setInt(1, remain + 1);
				stm.setString(2, resdata.getHospName());
				stm.setString(3, resdata.getDeptName());
				stm.setString(4, resdata.getDrName());
				stm.setDate(5, new java.sql.Date(resdata.getResDate().getTime()));
				stm.setInt(6, resdata.getResTime());
				stm.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, rs);
		}
		return 1;
	}


	/**
	 * 挂号信息录入resdata
	 */
	public static void InfroInput(drdata dr, patdata pat, Date resDate, int resTime) {
		Connection conn = null;
		PreparedStatement stm = null;
		PreparedStatement stm1 = null;
		PreparedStatement stm2 = null;
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		ResultSet rs = null;
		int result = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "insert into resdata(patid,hospName,deptName,drName,patName,resDate,resTime,stat,resFee)"
					+ " values(?,?,?,?,?,?,?,?,?)";
			stm = conn.prepareStatement(sql);
			stm.setInt(1, pat.getId());
			stm.setString(2, dr.getHospName());
			stm.setString(3, dr.getDeptName());
			stm.setString(4, dr.getDrName());
			stm.setString(5, pat.getPatName());
			stm.setDate(6, new java.sql.Date(resDate.getTime()));
			stm.setInt(7, resTime);
			stm.setInt(8, 0);
			stm.setDouble(9, dr.getResFee());
			stm.executeUpdate();
			// 在剩余量表中对应医生的号量减一
			sql1 = "select * from remaindata where hospName =? and deptName =? and drName =? and resDate=? and time=?";
			stm1 = conn.prepareStatement(sql1);
			stm1.setString(1, dr.getHospName());
			stm1.setString(2, dr.getDeptName());
			stm1.setString(3, dr.getDrName());
			stm1.setDate(4, new java.sql.Date(resDate.getTime()));
			stm1.setInt(5, resTime);
			rs = stm1.executeQuery();
			if (rs.next()) {
				result = rs.getInt("remain");
				result--;
			}
			sql2 = "update remaindata set remain=? where hospName =? and deptName =? and drName =? and  resDate=? and time=? ";
			stm2 = conn.prepareStatement(sql2);
			stm2.setString(2, dr.getHospName());
			stm2.setString(3, dr.getDeptName());
			stm2.setString(4, dr.getDrName());
			stm2.setDate(5, new java.sql.Date(resDate.getTime()));
			stm2.setInt(6, resTime);
			stm2.setInt(1, result);

			stm2.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBUtil.close(conn, stm, null);
		}

	}

	/**
	 * 得到可选的日期 返回一个日期list
	 */
	public static List<Date> selectDate(Date date) {
		List<Date> list = new ArrayList<Date>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		for (int i = 1; i < 4; i++) {
			Date date1 = new Date();
			calendar.add(calendar.DATE, 1);
			date1 = calendar.getTime();
			list.add(date1);
		}
		return list;
	}

	/**
	 * 根据医院科室医生名查询指定医生
	 * 
	 * @param hospName
	 * @param deptName
	 * @param drName
	 * @return
	 */
	public static drdata getCertainDr(String hospName, String deptName, String drName) {
		drdata dr = new drdata();
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String intro = "";
		try {
			conn = DBUtil.getConnection();
			String sql = " select * from drdata where hospName = ? and deptName = ? and drName =?";
			stm = conn.prepareStatement(sql);
			stm.setString(1, hospName);
			stm.setString(2, deptName);
			stm.setString(3, drName);
			rs = stm.executeQuery();
			if (rs.next()) {
				dr.setDeptName(rs.getString("deptName"));
				dr.setDrName(rs.getString("drName"));
				dr.setHospName(rs.getString("hospName"));
				dr.setId(rs.getInt("id"));
				dr.setIntro(rs.getString("intro"));
				dr.setResFee(rs.getDouble("resFee"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, rs);
		}
		return dr;
	}

	/**
	 * 精确查找病人 传病人ID返回病人信息表
	 */
	public patdata getPatdata1(int patId) {
		String sql = null;
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		patdata pat = null;
		try {
			conn = DBUtil.getConnection();
			sql = "select * from patdata where id =" + patId;
			stm = conn.prepareStatement(sql);
			// stm.setInt(1, patId);
			rs = stm.executeQuery();
			if (rs.next()) {
				pat = new patdata();
				pat.setPatName(rs.getString("patName"));
				pat.setId(rs.getInt("id"));
				pat.setAge(rs.getInt("age"));
				pat.setSex(rs.getString("sex"));
				pat.setAddress(rs.getString("address"));
				pat.setRemarks(rs.getString("remarks"));
				pat.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, rs);
		}
		return pat;
	}

	/**
	 * 更新病人信息 成功返回0 失败返回1
	 */
	public int updatePat(patdata pat) {
		Connection conn = null;
		PreparedStatement stm = null;
		String sql = null;
		int result = 1;
		try {
			conn = DBUtil.getConnection();
			sql = "update patdata set patName= ? , age=? , sex = ? , password = ? , address= ? , remarks =? where id=? ";
			stm = conn.prepareStatement(sql);
			stm.setString(1, pat.getPatName());
			stm.setInt(2, pat.getAge());
			stm.setString(3, pat.getSex());
			stm.setString(4, pat.getPassword());
			stm.setString(5, pat.getAddress());
			stm.setString(6, pat.getRemarks());
			stm.setInt(7, pat.getId());
			result = stm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, null);
		}
		return result;
	}

	/**
	 * 模糊查找病人 传病人名字返回病人信息表
	 */
	public List<patdata> getPatdata(String patName) {
		List<patdata> list = new ArrayList<patdata>();
		String sql = null;
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		patdata pat = null;
		try {
			conn = DBUtil.getConnection();
			sql = "select * from patdata where patName like ?";
			stm = conn.prepareStatement(sql);
			stm.setString(1, "%" + patName + "%");
			rs = stm.executeQuery();
			while (rs.next()) {
				pat = new patdata();
				pat.setId(rs.getInt("id"));
				pat.setPatName(rs.getString("patName"));
				pat.setAge(rs.getInt("age"));
				pat.setSex(rs.getString("sex"));
				pat.setAddress(rs.getString("address"));
				pat.setRemarks(rs.getString("remarks"));
				pat.setPassword(rs.getString("password"));
				list.add(pat);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, rs);
		}
		return list;
	}

	/**
	 * 获取所有的病人列表
	 * 
	 * @return list<patdata>
	 */
	public List<patdata> getAllPat() {
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		List<patdata> userList = new ArrayList<patdata>();
		patdata user = null;
		try {
			conn = DBUtil.getConnection();
			stm = conn.createStatement();
			String sql = "select * from patdata";
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				user = new patdata();
				user.setId(rs.getInt("id"));
				user.setPatName(rs.getString("patName"));
				user.setAge(rs.getInt("age"));
				user.setSex(rs.getString("sex"));
				user.setPassword(rs.getString("password"));
				user.setRemarks(rs.getString("remarks"));
				user.setAddress(rs.getString("address"));
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, rs);
		}
		return userList;
	}

	/**
	 * 获取所有挂号预约信息
	 */
	public static List<resdata> getAllResData() {
		List<resdata> list = new ArrayList<resdata>();
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "select * from resdata";
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				resdata res = new resdata();
				res.setId(rs.getInt("id"));
				res.setDeptName(rs.getString("deptName"));
				res.setDrName(rs.getString("drName"));
				res.setHospName(rs.getString("hospName"));
				res.setPatName(rs.getString("patName"));
				res.setResDate(rs.getDate("resDate"));
				res.setResFee(rs.getDouble("resFee"));
				res.setResTime(rs.getInt("resTime"));
				res.setStat(rs.getInt("stat"));
				list.add(res);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, rs);
		}
		return list;
	}

	/**
	 * 模糊查找预约记录
	 */
	public static List<resdata> getResDataF(String hospName, String deptName, String drName, String patName) {
		List<resdata> list = new ArrayList<resdata>();
		String sql = null;
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			sql = "select * from resdata where hospName like ? and deptName like ? and drName like ? and patName like ?";
			stm = conn.prepareStatement(sql);
			stm.setString(1, "%" + hospName + "%");
			stm.setString(2, "%" + deptName + "%");
			stm.setString(3, "%" + drName + "%");
			stm.setString(4, "%" + patName + "%");
			rs = stm.executeQuery();
			while (rs.next()) {
				resdata res = new resdata();
				res.setId(rs.getInt("id"));
				res.setDeptName(rs.getString("deptName"));
				res.setDrName(rs.getString("drName"));
				res.setHospName(rs.getString("hospName"));

				res.setPatName(rs.getString("patName"));
				res.setResDate(rs.getDate("resDate"));
				res.setResFee(rs.getDouble("resFee"));
				res.setResTime(rs.getInt("resTime"));
				res.setStat(rs.getInt("stat"));
				list.add(res);
			}
			return list;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, rs);
		}
		return list;
	}

	// 根据id查医生
	public static drdata getDoctorById(int id) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		drdata dr = new drdata();
		String sql = "";
		try {
			conn = DBUtil.getConnection();
			sql = "select * from drdata where id = '" + id + "'";
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if (rs.next()) {
				dr = new drdata();
				dr.setId(rs.getInt("id"));
				dr.setHospName(rs.getString("hospName"));
				dr.setDeptName(rs.getString("deptName"));
				dr.setDrName(rs.getString("drName"));
				dr.setIntro(rs.getString("intro"));
				dr.setResFee(rs.getDouble("resFee"));

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			DBUtil.close(conn, stm, rs);
		}

		return dr;
	}

	/**
	 * 修改医生信息
	 * 
	 * @param dept
	 * @param deptName
	 * @param deptIntro
	 * @return
	 */
	public int UpdateDoctors(drdata dr) {
		Connection conn = null;
		PreparedStatement stm = null;
		int result = 0;
		try {
			conn = DBUtil.getConnection();
			String sqlString = "update drdata set hospName =? , deptName =? , drName=? , resFee=? , intro=? where id=?";
			stm = conn.prepareStatement(sqlString);
			stm.setString(1, dr.getHospName());
			stm.setString(2, dr.getDeptName());
			stm.setString(3, dr.getDrName());
			stm.setDouble(4, dr.getResFee());
			stm.setString(5, dr.getIntro());
			stm.setInt(6, dr.getId());

			result = stm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, null);
		}
		return result;

	}

	// 批量删除医生
		public int DeleteDoctors(String id) {
			Connection conn = null;
			PreparedStatement stm = null;
			PreparedStatement stm1 = null;
			ResultSet rs = null;
			int result = 0;
			try {
				conn = DBUtil.getConnection();
				String sql1 = "select * from drdata where id = ?";
				String sql = "delete from drdata where id in (" + id + ")";
				stm1 = conn.prepareStatement(sql1);
				stm1.setInt(1, Integer.parseInt(id));
				rs = stm1.executeQuery();
				if (rs.next()) {
					drdata dr = new drdata();
					dr.setDeptName(rs.getString("deptName"));
					dr.setDrName(rs.getString("drName"));
					dr.setHospName(rs.getString("hospName"));
					dr.setId(rs.getInt("id"));
					dr.setIntro(rs.getString("intro"));
					dr.setResFee(rs.getDouble("resFee"));
					UserDao.deleteDrRemain(dr);
					stm = conn.prepareStatement(sql);
					result = stm.executeUpdate(sql);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				DBUtil.close(conn, stm, null);
			}
			return result;
		}

	/**
	 * 选择医院和科室后显示该科室医生信息 包括模糊查询
	 * 
	 */
	public List<drdata> getAllDoctor(String hospname, String deptname) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		drdata dr = null;
		String sql = "";
		List<drdata> drList = new ArrayList<drdata>();
		try {
			conn = DBUtil.getConnection();
			sql = "select * from drdata where 1=1";
			if (!(hospname == null || "".equals(hospname)))
				sql += " and hospName like '%" + hospname + "%'";
			if (!(deptname == null || "".equals(deptname)))
				sql += " and deptName like '%" + deptname + "%'";
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				dr = new drdata();
				dr.setId(rs.getInt("id"));
				dr.setHospName(rs.getString("hospName"));
				dr.setDeptName(rs.getString("deptName"));
				dr.setDrName(rs.getString("drName"));
				dr.setIntro(rs.getString("intro"));
				dr.setResFee(rs.getDouble("resFee"));
				drList.add(dr);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			DBUtil.close(conn, stm, rs);
		}

		return drList;
	}

	/**
	 * 增加医生
	 */
	public int addDoctor(String hospName, String deptName, String drName, Double resFee, String intro) {
		Connection conn = null;
		PreparedStatement stm = null;
		String sql = "";
		int result = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "insert into drdata (deptName,hospName,drName,resFee,intro) values(?,?,?,?,?)";
			stm = conn.prepareStatement(sql);
			stm.setString(1, hospName);
			stm.setString(2, deptName);
			stm.setString(3, drName);
			stm.setDouble(4, resFee);
			stm.setString(5, intro);
			result = stm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			DBUtil.close(conn, stm, null);
		}
		return result;
	}

	/**
	 * 选择医院和科室后显示该科室医生信息 包括模糊查询
	 * 
	 */
	public static List<drdata> getDoctorByName(String hospname, String deptname,String drName ) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		drdata dr = null;
		String sql = "";
		List<drdata> drList = new ArrayList<drdata>();
		try {
			conn = DBUtil.getConnection();
			sql = "select * from drdata where 1=1";
			if (!(hospname == null || "".equals(hospname)))
				sql += " and hospName like '%" + hospname + "%'";
			if (!(deptname == null || "".equals(deptname)))
				sql += " and deptName like '%" + deptname + "%'";
			if (!(drName == null || "".equals(drName)))
				sql += " and drName like '%" + drName + "%'";
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				dr = new drdata();
				dr.setId(rs.getInt("id"));
				dr.setHospName(rs.getString("hospName"));
				dr.setDeptName(rs.getString("deptName"));
				dr.setDrName(rs.getString("drName"));
				dr.setIntro(rs.getString("intro"));
				dr.setResFee(rs.getDouble("resFee"));
				drList.add(dr);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			DBUtil.close(conn, stm, rs);
		}

		return drList;
	}
	/**
	 * 选择医院和科室后显示该科室医生信息
	 * @param hospname
	 * @param deptname
	 * @author huangzhangyan
	 * @return
	 */
	public static List<drdata> getDoctorByName(String hospname, String deptname) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		drdata dr = null;
		String sql = "";
		List<drdata> drList = new ArrayList<drdata>();
		try {
			conn = DBUtil.getConnection();
			sql = "select * from drdata where 1=1";
			if (!(hospname == null || "".equals(hospname)))
				sql += " and hospName like '%" + hospname + "%'";
			if (!(deptname == null || "".equals(deptname)))
				sql += " and deptName like '%" + deptname + "%'";
//			if (!(drName == null || "".equals(drName)))
//				sql += " and drName like '%" + drName + "%'";
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				dr = new drdata();
				dr.setId(rs.getInt("id"));
				dr.setHospName(rs.getString("hospName"));
				dr.setDeptName(rs.getString("deptName"));
				dr.setDrName(rs.getString("drName"));
				dr.setIntro(rs.getString("intro"));
				dr.setResFee(rs.getDouble("resFee"));
				drList.add(dr);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			DBUtil.close(conn, stm, rs);
		}

		return drList;
	}

	/**
	 * 批量删除科室
	 * 
	 * @param id
	 * @return
	 */
	public int DeleteDepartments(String id) {
		Connection conn = null;
		PreparedStatement stm = null;
		PreparedStatement stm2 = null;
		PreparedStatement stm3 = null;
		PreparedStatement stm4 = null;
		ResultSet rs =null;
		String sql="";
		int result = 0;
		try {
			conn = DBUtil.getConnection();
			String sql4= "select * from deptdata where id ='"+id+"'";
			stm4 = conn.prepareStatement(sql4);
			rs=stm4.executeQuery();
			String deptName=null;
			if(rs.next())
			{
			deptName =rs.getString("deptName");
			}
			sql = "delete from deptdata  where deptName='"+deptName+"'";				
			stm = conn.prepareStatement(sql);
			String sql2 = "delete from drdata where deptName='"+deptName+"'";
			stm2 = conn.prepareStatement(sql2);
			String sql3 = "delete from remaindata where deptName='"+deptName+"'";
			stm3 = conn.prepareStatement(sql3);
			stm3.executeUpdate(sql3);
			stm2.executeUpdate(sql2);
			result = stm.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
		
			DBUtil.close(conn, stm, null);
		}
		return result;
	}

	// 根据id查科室
	public deptdata getDepByID(int id) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		deptdata hos = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from deptdata where id =" + id;
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if (rs.next()) {
				hos = new deptdata();
				hos.setId(rs.getInt("id"));
				hos.setHospName(rs.getString("hospName"));
				hos.setDeptName(rs.getString("deptName"));
				hos.setDeptIntro(rs.getString("deptIntro"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBUtil.close(conn, stm, rs);
		}

		return hos;
	}

	/**
	 * 模糊查询科室
	 * 
	 * @param hospname
	 * @param deptname
	 * @return
	 */
	public List<deptdata> getDepartmentByName1(String hospname, String deptname) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		deptdata dp = null;
		String sql = "";
		List<deptdata> dpList = new ArrayList<deptdata>();
		try {
			conn = DBUtil.getConnection();
			sql = "select * from deptdata where hospName like '%" + hospname + "%'" + "and deptName like '%" + deptname
					+ "%'";
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				dp = new deptdata();
				dp.setId(rs.getInt("id"));
				dp.setHospName(rs.getString("hospName"));
				dp.setDeptName(rs.getString("deptName"));
				dp.setDeptIntro(rs.getString("deptIntro"));
				dpList.add(dp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, rs);
		}
		return dpList;
	}

	/**
	 * 增加医院某科室
	 */
	public int addDepartment(String hospName, String deptName, String deptIntro) {
		Connection conn = null;
		PreparedStatement stm = null;
		String sql = "";
		int result = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "insert into deptdata (hospName,deptName,deptIntro) values('" + hospName + "','" + deptName + "','"
					+ deptIntro + "')";
			stm = conn.prepareStatement(sql);
			result = stm.executeUpdate(sql);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			DBUtil.close(conn, stm, null);
		}
		return result;
	}

	/**
	 * 删除医院某科室
	 */
	public int DeleteDepartment(String deptName) {
		Connection conn = null;
		PreparedStatement stm = null;
		String sql = "";
		int result = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "delete from deptdata  where deptName='" + deptName + "'";
			stm = conn.prepareStatement(sql);
			result = stm.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			DBUtil.close(conn, stm, null);
		}
		return result;
	}

	/**
	 * 更改医院科室信息
	 * @param dept
	 * @param deptName
	 * @param deptIntro
	 * @return
	 */
	public static int UpdateDeptment(deptdata dept,String deptName ,String deptIntro ) {
		Connection conn = null;
		PreparedStatement stm = null;
		PreparedStatement stm1 = null;
		PreparedStatement stm2 = null;
		PreparedStatement stm3 = null;
		PreparedStatement stm4 = null;
		int result = 0;
		try {
			conn = DBUtil.getConnection();
			String sqlString = "update deptdata set deptName =? , deptIntro =? where hospName=? and deptName=?";
			stm=conn.prepareStatement(sqlString);
			stm.setString(1, deptName);
			stm.setString(2, deptIntro);
			stm.setString(3, dept.getHospName());
			stm.setString(4, dept.getDeptName());
			result = stm.executeUpdate();
			String sql1= "update drdata  set deptName = ?  where  deptName=? ";
	        stm1=conn.prepareStatement(sql1);
	        stm1.setString(1, deptName);
	        stm1.setString(2, dept.getDeptName());
	        stm1.executeUpdate();
	        String sql2= "update remaindata  set deptName = ?  where  deptName=? ";
	        stm2=conn.prepareStatement(sql2);
	        stm2.setString(1, deptName);
	        stm2.setString(2, dept.getDeptName());
	        stm2.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, null);
		}
		return result;

 }

	/**
	 * 查医院某个科室
	 * 
	 * @param hospname
	 * @param deptname
	 * @return
	 */

	public static deptdata getDepartmentByName(String hospname, String deptname) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		deptdata dept = null;
		String sql = "";
		try {
			conn = DBUtil.getConnection();
			sql = "select * from deptdata where hospName ='" + hospname + " 'and deptName='" + deptname + "'";
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if (rs.next()) {
				dept = new deptdata();
				dept.setHospName(rs.getString("hospName"));
				dept.setDeptName(rs.getString("deptName"));
				dept.setDeptIntro(rs.getString("deptIntro"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			DBUtil.close(conn, stm, rs);
		}

		return dept;
	}

	// 查询表中所有科室信息
	public List<deptdata> getAllDep() {
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		List<deptdata> userList = new ArrayList<deptdata>();

		deptdata user = null;

		try {
			conn = DBUtil.getConnection();
			stm = conn.createStatement();
			String sql = "select * from deptdata";
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				user = new deptdata();
				user.setId(rs.getInt("id"));
				user.setHospName(rs.getString("hospName"));
				user.setDeptName(rs.getString("deptName"));
				user.setDeptIntro(rs.getString("deptIntro"));
				userList.add(user);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, rs);
		}
		return userList;
	}

	/**
	 *  更改医院信息
	 * @param hos
	 * @param hospName
	 * @param hospIntro
	 * @return
	 */
	public int UpdateHospital(hosps hos,String hospName,String hospIntro) {
		Connection conn = null;
		PreparedStatement stm = null;
		PreparedStatement stm1 = null;
		PreparedStatement stm2 = null;
		PreparedStatement stm3 = null;
		PreparedStatement stm4 = null;
		int result = 0;
		try {
			conn = DBUtil.getConnection();		
			String sql= "update hosps  set hospName = ? , hospIntro=? where  hospName=? ";
	        stm=conn.prepareStatement(sql);
			stm.setString(3, hos.getHospName());
	        stm.setString(1, hospName);
	        stm.setString(2, hospIntro);
			result = stm.executeUpdate();
	        String sql1= "update deptdata  set hospName = ?  where  hospName=? ";
	        stm1=conn.prepareStatement(sql1);
	        stm1.setString(1, hospName);
	        stm1.setString(2, hos.getHospName());
	        stm1.executeUpdate();
	        String sql2= "update drdata  set hospName = ?  where  hospName=? ";
	        stm2=conn.prepareStatement(sql2);
	        stm2.setString(1, hospName);
	        stm2.setString(2, hos.getHospName());
	        stm2.executeUpdate();
	        String sql3= "update remaindata  set hospName = ?  where  hospName=? ";
	        stm3=conn.prepareStatement(sql3);
	        stm3.setString(1, hospName);
	        stm3.setString(2, hos.getHospName());
	        stm3.executeUpdate();
	        String sql4= "update resdata  set hospName = ?  where  hospName=? ";
	        stm4=conn.prepareStatement(sql4);
	        stm4.setString(1, hospName);
	        stm4.setString(2, hos.getHospName());
	        stm4.executeUpdate();
	        
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, null);
		}
		return result;

}

	// 增加医院
	public int addHospital(String hospName, String hospIntro) {
		Connection conn = null;
		PreparedStatement stm = null;
		int result = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into hosps(hospName,hospIntro) values('" + hospName + "','" + hospIntro + "')";

			stm = conn.prepareStatement(sql);
			result = stm.executeUpdate(sql);
			System.out.println(sql);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBUtil.close(conn, stm, null);
		}
		// System.out.println(result);
		return result;
	}

	/**
	 * 批量删除医院
	 */
	public static int DeleteHospitals(String id) {
		Connection conn = null;
		PreparedStatement stm = null;
		PreparedStatement stm1 = null;
		PreparedStatement stm2 = null;
		PreparedStatement stm3 = null;
		PreparedStatement stm4 = null;
		ResultSet rs =null;
		hosps hos=null;
		int result = 0;
		try {
			conn = DBUtil.getConnection();
			String sql4= "select * from hosps where id =?";
			stm4 = conn.prepareStatement(sql4);
			stm4.setString(1, id);
			rs=stm4.executeQuery();
			String hospsName =null;
			if(rs.next())
			{
			hospsName =rs.getString("hospName");
			System.out.println(hospsName);
			}
			String sql = "delete from hosps where hospName= '"+hospsName+"'";
			stm = conn.prepareStatement(sql);
			result = stm.executeUpdate(sql);
			String sql1 = "delete from deptdata where hospName='"+hospsName+"'";
			stm1 = conn.prepareStatement(sql1);
			stm1.executeUpdate(sql1);
			String sql2 = "delete from drdata where hospName= '"+hospsName+"'";
			stm2 = conn.prepareStatement(sql2);
			stm2.executeUpdate(sql2);
			String sql3 = "delete from remaindata where hospName='"+hospsName+"'";
			stm3 = conn.prepareStatement(sql3);
			stm3.executeUpdate(sql3);
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, null);
		}
		return result;
	}

//	// 更改医院信息
//	public int UpdateHospital(hosps hos) {
//		if (getHospitalByName(hos.getHospName()) != null) {
//			Connection conn = null;
//			Statement stm = null;
//			int result = 0;
//			try {
//				conn = DBUtil.getConnection();
//				stm = conn.createStatement();
//				String sqlString = "update hosps set hospName ='" + hos.getHospName() + "',hospIntro ='" + hos.getHospIntro() + "' where id =";
//				result = stm.executeUpdate(sqlString);
//			} catch (Exception e) {
//				// TODO: handle exception
//			} finally {
//				DBUtil.close(conn, stm, null);
//			}
//			return result;
//		} else
//			return 0;
//	}

	// 模糊查询医院和医院简介
	public List<hosps> getHospital1(String hosname) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<hosps> hospList = new ArrayList<hosps>();
		hosps hos = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from hosps ";
			if (!(hosname == null || "".equals(hosname))) {
				sql += "where hospName like'%" + hosname + "%'";
			}
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				hos = new hosps();
				hos.setId(rs.getInt("id"));
				hos.setHospName(rs.getString("hospName"));
				hos.setHospIntro(rs.getString("hospIntro"));
				hospList.add(hos);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBUtil.close(conn, stm, rs);
		}

		return hospList;
	}

	// 根据id查医院
	public hosps getHospitalByID(int id) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		hosps hos = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from hosps where id =" + id;
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if (rs.next()) {
				hos = new hosps();
				hos.setId(rs.getInt("id"));
				hos.setHospName(rs.getString("hospName"));
				hos.setHospIntro(rs.getString("hospIntro"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBUtil.close(conn, stm, rs);
		}

		return hos;
	}

	// 根据医院名查医院
	public hosps getHospitalByName(String hospname) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		hosps hos = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from hosps where hospName =" + hospname;
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if (rs.next()) {
				hos = new hosps();
				hos.setHospName(rs.getString("hospName"));
				hos.setHospIntro(rs.getString("hospIntro"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBUtil.close(conn, stm, rs);
		}

		return hos;
	}

	// 查询表中所有医院信息
	public List<hosps> getAllHos() {
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		List<hosps> userList = new ArrayList<hosps>();

		hosps user = null;

		try {
			conn = DBUtil.getConnection();
			stm = conn.createStatement();
			String sql = "select * from hosps";
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				user = new hosps();
				user.setId(rs.getInt("id"));
				user.setHospName(rs.getString("hospName"));
				user.setHospIntro(rs.getString("hospIntro"));
				userList.add(user);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, rs);
		}
		return userList;
	}

	/**
	 * 查询表中一共有多少条数据
	 * 
	 * @return
	 */
	public int getCount() {
		int result = 0;
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			stm = conn.createStatement();
			String sql = "select count(*) from userinfo";
			rs = stm.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt(1);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, rs);
		}
		return result;
	}
	
	/**
	 * 用于录入挂号信息前判断，防止重复预约
	 * @param dr
	 * @param pat
	 * @param resDate
	 * @param resTime
	 * @return
	 */
	public static  int SelectInput(drdata dr,patdata pat,Date resDate,int resTime)
	{
		Connection conn = null;
		PreparedStatement stm = null;
        String sql="";
        ResultSet rs=null;
        try {
			conn = DBUtil.getConnection();	
			sql = "select *from  resdata where patId=? and hospName=? and "
					+ " deptName=?  and drName=? and  patName=? and resDate=?"
					+ "  and resTime=?  and stat=?";
			stm = conn.prepareStatement(sql);
			stm.setInt(1, pat.getId());
			stm.setString(2, dr.getHospName());
			stm.setString(3, dr.getDeptName());
			stm.setString(4, dr.getDrName());
			stm.setString(5, pat.getPatName());
			stm.setDate(6, new java.sql.Date(resDate.getTime()));
			stm.setInt(7, resTime);
			stm.setInt(8, 0);			
		    rs= stm.executeQuery();
		   if(rs.next())
		   {
			   return 0;//如果挂号信息表中有 则返回0不能让用户重复预约
		   }
        } catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		} finally {
			DBUtil.close(conn, stm, null);
		}
        return 1;
	}
}