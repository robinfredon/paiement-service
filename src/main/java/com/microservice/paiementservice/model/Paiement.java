package com.microservice.paiementservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Paiement {

	public Paiement(int id, int idCommande, int montant, MoyenPayementEnum moyen, Date date)  {
		super();
		this.id = id;
		this.idCommande = idCommande;
		this.montant = montant;
		this.moyen = moyen;
		this.date = date;
	}
	public Paiement() {

	}

	public enum MoyenPayementEnum{
	  VIREMENT,
    CB,
    CHEQUE,
    PAYPAL,
    ESPECE
  }

	@Override
	public String toString() {
		return "Paiement :" +
				"\n\tid : " + this.id +
				"\n\tCommande : " + this.idCommande +
				"\n\tmontant : " + this.montant +
        "\n\tmoyen : " + this.moyen +
        "\n\tdate : " + this.date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getidCommande() {
		return idCommande;
	}
	public void setidCommande(int idCommande) {
		this.idCommande = idCommande;
	}
	public int getMontant() {
		return montant;
	}
	public void setMontant(int montant) {
		this.montant = montant;
	}
  public MoyenPayementEnum getMoyen() { return moyen;}
  public void setMoyen(MoyenPayementEnum moyen) { this.moyen = moyen; }
  public Date getDate() { return date;}
  public void setDate(Date date) { this.date = date; }
	@Id
	//@GeneratedValue
	private int id;
	private int idCommande;
	private int montant;
  private MoyenPayementEnum moyen;
  private Date date;

}
