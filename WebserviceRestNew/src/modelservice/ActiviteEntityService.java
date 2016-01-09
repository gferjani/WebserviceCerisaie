package modelservice;

import java.util.Date;
import java.util.List;

import model.Activite;
import service.DataEntityService;

public class ActiviteEntityService extends DataEntityService<Activite>
{
	public ActiviteEntityService()
	{
		super("Activite", Activite.class);
	}

	@Override
	protected Integer getId(Activite entity)
	{
		return entity.getId();
	}
	
	public List<Activite> findBySejour(int numSejour)
	{
		startTransaction().begin();
		List<Activite> activites = entitymanager
					.createQuery("SELECT a FROM Activite a WHERE a.sejour.numSej = :num", Activite.class)
					.setParameter("num", numSejour)
					.getResultList();
		return activites;
	}
	
	public Activite findBySportSejDate(int nSport, int nSej, Date date)
	{
		startTransaction().begin();
		String sql = "SELECT a FROM Activite a WHERE "
					+ "a.sejour.numSej = :numSej "
					+ "and a.DateJour = :mydate "
					+ "and a.sport.codeSport = :numSport";
		Activite activite = entitymanager
					.createQuery(sql, Activite.class)
					.setParameter("numSport", nSport)
					.setParameter("numSej", nSej)
					.setParameter("mydate", date)
					.getSingleResult();					
		return activite;
	}
}
