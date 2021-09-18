package beans;

import java.util.Date;

public class resdata {
	private int id;
	private String hospName; //ҽԺ��
	private String deptName; //������
	private String drName;   //ҽ����
	private String patName;  //������
	private Date resDate;  //ԤԼ����
	private int resTime;     //ԤԼʱ��� 1~5
	private int stat;        //ԤԼ״̬ 0Ϊ����ԤԼ 1Ϊ��ȡ��
	private int patId;
	private double resFee;   //ԤԼ����
	
	public int getPatId() {
		return patId;
	}
	public void setPatId(int patId) {
		this.patId = patId;
	}
	public double getResFee() {
		return resFee;
	}
	public void setResFee(double resFee) {
		this.resFee = resFee;
	}
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
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	
	public Date getResDate() {
		return resDate;
	}
	public void setResDate(Date resDate) {
		this.resDate = resDate;
	}
	public int getResTime() {
		return resTime;
	}
	public void setResTime(int resTime) {
		this.resTime = resTime;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
	@Override
	public String toString() {
		return "resdata [id=" + id + ", hospName=" + hospName + ", deptName=" + deptName + ", drName=" + drName
				+ ", patName=" + patName + ", resDate=" + resDate + ", resTime=" + resTime + ", stat=" + stat
				+ ", patId=" + patId + ", resFee=" + resFee + "]";
	}
	
}
