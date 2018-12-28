package beans;

public class Eleve {
	private int id_elv;
	private String nom;
	private String prenom;
	private String date_nais;
	private String annee;
	private String filiere;
	private String classe;
	private String mdp;
	private boolean valide;
	
	public boolean isValide() {
		return valide;
	}
	public void setValide(boolean valide) {
		this.valide = valide;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public int getId_elv() {
		return id_elv;
	}
	public void setId_elv(int id_elv) {
		this.id_elv = id_elv;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getDate_nais() {
		return date_nais;
	}
	public void setDate_nais(String date_nais) {
		this.date_nais = date_nais;
	}
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee;
	}
	public String getFiliere() {
		return filiere;
	}
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}

}
