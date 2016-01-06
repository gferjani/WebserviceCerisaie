package model;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the sejour database table.
 * 
 */
@Entity
@NamedQuery(name="Sejour.findAll", query="SELECT s FROM Sejour s")
public class Sejour implements Serializable {
	private static final long serialVersionUID = 1L;

	public static class JSonSejour extends JSonConverter<Sejour>
	{
		@Override
		protected int getID(Sejour element)
		{
			return element.getNumSej();
		}
	}
	public static class JSonListSejour extends JSonListConverter<Sejour>
	{
		@Override
		protected int getID(Sejour element)
		{
			return element.getNumSej();
		}
	}

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int numSej;

	@Temporal(TemporalType.DATE)
	private Date datedebSej;

	@Temporal(TemporalType.DATE)
	private Date dateFinSej;

	private int nbPersonnes;

	//bi-directional many-to-one association to Activite
	@OneToMany(mappedBy="sejour", fetch=FetchType.LAZY)
	@JsonSerialize(using = Activite.JSonListActivite.class)
	private List<Activite> activites;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="NumCli")
	@JsonSerialize(using = Client.JSonClient.class)
	private Client client;

	//bi-directional many-to-one association to Emplacement
	@ManyToOne
	@JoinColumn(name="NumEmpl")
	@JsonSerialize(using = Emplacement.JSonEmplacement.class)
	private Emplacement emplacement;

	public Sejour() {
	}

	public int getNumSej() {
		return this.numSej;
	}

	public Date getDatedebSej() {
		return this.datedebSej;
	}

	public void setDatedebSej(Date datedebSej) {
		this.datedebSej = datedebSej;
	}

	public Date getDateFinSej() {
		return this.dateFinSej;
	}

	public void setDateFinSej(Date dateFinSej) {
		this.dateFinSej = dateFinSej;
	}

	public int getNbPersonnes() {
		return this.nbPersonnes;
	}

	public void setNbPersonnes(int nbPersonnes) {
		this.nbPersonnes = nbPersonnes;
	}

	@JsonSerialize(using = Activite.JSonListActivite.class)
	public List<Activite> getActivites() {
		return this.activites;
	}

	public void setActivites(List<Activite> activites) {
		this.activites = activites;
	}

	public Activite addActivite(Activite activite) {
		getActivites().add(activite);
		activite.setSejour(this);

		return activite;
	}

	public Activite removeActivite(Activite activite) {
		getActivites().remove(activite);
		activite.setSejour(null);

		return activite;
	}

	@JsonSerialize(using = Client.JSonClient.class)
	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Emplacement getEmplacement() {
		return this.emplacement;
	}

	public void setEmplacement(Emplacement emplacement) {
		this.emplacement = emplacement;
	}

}