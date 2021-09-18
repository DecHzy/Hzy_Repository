package beans;

import java.util.Date;

public class remaindata {
	private int id;
	private String hospName; //ҽԺ��
	private String deptName; //������
	private String drName;   //ҽ����
	private Date resDate;  //ԤԼ����
	private int time;        //ԤԼʱ��� 1~5
	private int remain;      //ʣ��ԤԼ��
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDrName() {
		return drName;
	}
	public void setDrName(String drName) {
		this.drName = drName;
	}
	public Date getResDate() {
		return resDate;
	}
	public void setResDate(Date resDate) {
		this.resDate = resDate;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getRemain() {
		return remain;
	}
	public void setRemain(int remain) {
		this.remain = remain;
	}
	public String toString() {
		return "remaindata [id=" + id + ", hospName=" + hospName + ", deptName=" + deptName + ", drName=" + drName
				+ ", resDate=" + resDate + ", time=" + time + ", remain=" + remain + "]";
	}
	
}
