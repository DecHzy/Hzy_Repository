package beans;

public class deptdata {
	private int id;
	private String hospName;  //ҽԺ��
	private String deptName;  //������
	private String deptIntro; //���ҽ���
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
	public String getDeptIntro() {
		return deptIntro;
	}
	public void setDeptIntro(String deptIntro) {
		this.deptIntro = deptIntro;
	}
	public String toString() {
		return "deptdata [hospName=" + hospName + ", deptName=" + deptName + ", deptIntro=" + deptIntro + "]";
	}
}
