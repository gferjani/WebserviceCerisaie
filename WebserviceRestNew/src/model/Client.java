package model;

import java.io.IOException;
import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.collection.internal.PersistentBag;

import java.util.List;


/**
 * The persistent class for the client database table.
 * 
 */
@Entity
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int numCli;

	private String adrRueCli;

	private String cpCli;

	private String nomCli;

	private String numPieceCli;

	private String pieceCli;

	private String villeCli;

	//bi-directional many-to-one association to Sejour
	@OneToMany(mappedBy="client", fetch=FetchType.LAZY)
	@JsonSerialize(using = Sejour.JSonSejour.class)
	private List<Sejour> sejours;

	public Client() {
	}

	public int getNumCli() {
		return this.numCli;
	}

	public String getAdrRueCli() {
		return this.adrRueCli;
	}

	public void setAdrRueCli(String adrRueCli) {
		this.adrRueCli = adrRueCli;
	}

	public String getCpCli() {
		return this.cpCli;
	}

	public void setCpCli(String cpCli) {
		this.cpCli = cpCli;
	}

	public String getNomCli() {
		return this.nomCli;
	}

	public void setNomCli(String nomCli) {
		this.nomCli = nomCli;
	}

	public String getNumPieceCli() {
		return this.numPieceCli;
	}

	public void setNumPieceCli(String numPieceCli) {
		this.numPieceCli = numPieceCli;
	}

	public String getPieceCli() {
		return this.pieceCli;
	}

	public void setPieceCli(String pieceCli) {
		this.pieceCli = pieceCli;
	}

	public String getVilleCli() {
		return this.villeCli;
	}

	public void setVilleCli(String villeCli) {
		this.villeCli = villeCli;
	}

	@JsonSerialize(using = Sejour.JSonSejour.class)
	public List<Sejour> getSejours() {
		return this.sejours;
	}

	public void setSejours(List<Sejour> sejours) {
		this.sejours = sejours;
	}

	public Sejour addSejour(Sejour sejour) {
		getSejours().add(sejour);
		sejour.setClient(this);

		return sejour;
	}

	public Sejour removeSejour(Sejour sejour) {
		getSejours().remove(sejour);
		sejour.setClient(null);

		return sejour;
	}
}