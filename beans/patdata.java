package beans;

public class patdata {
	private int id;			//�ڱ�����һ��10000��ʼ������ ����id���˺ŵ�¼
	private String patName; //������
	private int age;		//����
	private String sex;		//�Ա�
	private String password;//����
	private String address; //סַ
	private String remarks; //��ע
	private String phoneNum; //�绰����
	
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String toString() {
		return "patdata [id=" + id + ", patName=" + patName + ", age=" + age + ", sex=" + sex + ", password=" + password
				+ ", address=" + address + ", remarks=" + remarks + ", phoneNum=" + phoneNum + "]";
	}
	
}
