package beans;

public class Prof {
	private String nom,prenom,pseudo;
	private String id_prf;
	private String mdp;
	private Classe[] classes;
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private boolean valide = false;
	public boolean isValide() {
		return valide;
	}
	public void setValide(boolean valide) {
		this.valide = valide;
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
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getId_prf() {
		return id_prf;
	}
	public void setId_prf(String id_prof) {
		this.id_prf = id_prof;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public Classe[] getClasses() {
		return classes;
	}
	public void setClasses(Classe[] classes) {
		this.classes = classes;
	}
}
