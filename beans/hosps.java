package beans;

public class hosps {
	private int id;
	private String hospName;  //医院名
	private String hospIntro; //医院介绍
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
	public String getHospIntro() {
		return hospIntro;
	}
	public void setHospIntro(String hospIntro) {
		this.hospIntro = hospIntro;
	}
	public String toString() {
		return "hosps [id=" + id + ", hospName=" + hospName + ", hospIntro=" + hospIntro + "]";
	}
}
