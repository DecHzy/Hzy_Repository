package beans;

public class drdata {
	private int id;
	private String hospName; //医院名
	private String deptName; //科室名
	private String drName;   //医生名
	private double resFee;   //预约费用
	private String intro;    //医生介绍
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
	public double getResFee() {
		return resFee;
	}
	public void setResFee(double resFee) {
		this.resFee = resFee;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String toString() {
		return "drdata [hospName=" + hospName + ", deptName=" + deptName + ", drName=" + drName + ", resFee=" + resFee
				+ ", intro=" + intro + "]";
	}
}
