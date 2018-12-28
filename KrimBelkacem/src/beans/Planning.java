package beans;

public class Planning {
	private String id_planning;
	private beans.Classe classe;
	private String trimestre1;
	private String trimestre2;
	private String trimestre3;
	private String annuel;
	
	public Planning() {
		this.classe = new Classe();
	}

	public String getId_planning() {
		return id_planning;
	}

	public void setId_planning(String id_planning) {
		this.id_planning = id_planning;
	}

	public beans.Classe getClasse() {
		return classe;
	}

	public void setClasse(beans.Classe classe) {
		this.classe = classe;
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

	public String getAnnuel() {
		return annuel;
	}

	public void setAnnuel(String annuel) {
		this.annuel = annuel;
	}
}
