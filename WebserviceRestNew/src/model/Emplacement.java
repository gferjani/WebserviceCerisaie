package model;

import java.io.Serializable;

import javax.persistence.*;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;


/**
 * The persistent class for the emplacement database table.
 * 
 */
@Entity
@NamedQuery(name="Emplacement.findAll", query="SELECT e FROM Emplacement e")
public class Emplacement implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static class JSonEmplacement extends JSonConverter<Emplacement>
	{
		@Override
		protected int getID(Emplacement element)
		{
			return element.getNumEmpl();
		}
	}
	public static class JSonListEmplacement extends JSonListConverter<Emplacement>
	{
		@Override
		protected int getID(Emplacement element)
		{
			return element.getNumEmpl();
		}
	}

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int numEmpl;

	private int nbPersMaxEmpl;

	private float surfaceEmpl;

	//bi-directional many-to-one association to TypeEmplacement
	@ManyToOne
	@JoinColumn(name="CodeTypeE")
	@JsonSerialize(using = TypeEmplacement.JSonTypeEmplacement.class)
	private TypeEmplacement typeEmplacement;

	//bi-directional many-to-one association to Sejour
	@OneToMany(mappedBy="emplacement", fetch=FetchType.LAZY)
	@JsonSerialize(using = Sejour.JSonListSejour.class)
	private List<Sejour> sejours;

	public Emplacement() {
	}

	public int getNumEmpl() {
		return this.numEmpl;
	}

	public int getNbPersMaxEmpl() {
		return this.nbPersMaxEmpl;
	}

	public void setNbPersMaxEmpl(int nbPersMaxEmpl) {
		this.nbPersMaxEmpl = nbPersMaxEmpl;
	}

	public float getSurfaceEmpl() {
		return this.surfaceEmpl;
	}

	public void setSurfaceEmpl(float surfaceEmpl) {
		this.surfaceEmpl = surfaceEmpl;
	}

	public TypeEmplacement getTypeEmplacement() {
		return this.typeEmplacement;
	}

	public void setTypeEmplacement(TypeEmplacement typeEmplacement) {
		this.typeEmplacement = typeEmplacement;
	}

	public List<Sejour> getSejours() {
		return this.sejours;
	}

	public void setSejours(List<Sejour> sejours) {
		this.sejours = sejours;
	}

	public Sejour addSejour(Sejour sejour) {
		getSejours().add(sejour);
		sejour.setEmplacement(this);

		return sejour;
	}

	public Sejour removeSejour(Sejour sejour) {
		getSejours().remove(sejour);
		sejour.setEmplacement(null);

		return sejour;
	}

}