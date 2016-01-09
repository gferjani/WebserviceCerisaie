package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonSerialize;


/**
 * The persistent class for the activite database table.
 * 
 */
@Entity
@NamedQuery(name="Activite.findAll", query="SELECT a FROM Activite a")
public class Activite implements Serializable {
	private static final long serialVersionUID = 1L;

	public static class JSonListActivite extends JsonSerializer<List<Activite>>
	{
		@Override
		public void serialize(List<Activite> elements, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException
		{
			jgen.writeStartArray();
			for(Activite es : elements)
				jgen.writeObject(getID(es));
			jgen.writeEndArray();
		}
		
		protected int getID(Activite element)
		{
			return element.getId();
		}
	}

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int idActivite;

	private int nbloc;

	//bi-directional many-to-one association to Sport
	@ManyToOne
	@JoinColumn(name="CodeSport")
	@JsonSerialize(using = Sport.JSonSport.class)
	private Sport sport;

	//bi-directional many-to-one association to Sejour
	@ManyToOne
	@JoinColumn(name="NumSej")
	@JsonSerialize(using = Sejour.JSonSejour.class)
	private Sejour sejour;
	
	@Temporal(TemporalType.DATE)
	private Date DateJour;

	public Activite() {
	}

	public int getId() {
		return this.idActivite;
	}

	public void setId(int id) {
		this.idActivite = id;
	}
	
	public Date getDateJour() {
		return this.DateJour;
	}

	public void setDateJour(Date DateJour) {
		this.DateJour = DateJour;
	}

	public int getNbloc() {
		return this.nbloc;
	}

	public void setNbloc(int nbloc) {
		this.nbloc = nbloc;
	}

	public Sport getSport() {
		return this.sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	@JsonSerialize(using = Sejour.JSonSejour.class)
	public Sejour getSejour() {
		return this.sejour;
	}

	public void setSejour(Sejour sejour) {
		this.sejour = sejour;
	}

}