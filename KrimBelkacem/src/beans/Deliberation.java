package beans;

public class Deliberation {
	private String id_del;
	private Classe classe;
	private String trimestre1;
	private String trimestre2;
	private String trimestre3;
	
	public Deliberation() {
		this.classe = new Classe();
	}
	
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	public String getId_del() {
		return id_del;
	}
	public void setId_del(String id_del) {
		this.id_del = id_del;
	}

	public String getTrimestre1() {
		return trimestre1;
	}

	public void setTrimestre1(String trimestre1) {
		this.trimestre1 = trimestre1;
	}

	public String getTrimestre2() {
		return trimestre2;
	}

	public void setTrimestre2(String trimestre2) {
		this.trimestre2 = trimestre2;
	}

	public String getTrimestre3() {
		return trimestre3;
	}

	public void setTrimestre3(String trimestre3) {
		this.trimestre3 = trimestre3;
	}
}
