package test.service;

import javax.persistence.EntityManager;

import model.Client;
import model.Sejour;

import org.junit.Before;

import service.ClientEntityService;

public class TestFactureEntityService
{
	private Sejour sejour;
	private EntityManager entitymanager;
	private ClientEntityService service;
	@Before
	public void initialize()
	{
		service = new ClientEntityService();
	}
}
