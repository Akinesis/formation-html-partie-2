package dev.pizzeria.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.pizzeria.domain.Client;
import dev.pizzeria.domain.Pizza;
import dev.pizzeria.utils.JpaConnector;

public class PizzeriaDbService implements IPizzeriaService {

	private JpaConnector jpaDb;
	private static final Logger LOGGER = LoggerFactory.getLogger(PizzeriaDbService.class);

	public PizzeriaDbService(){
		jpaDb = new JpaConnector();
	}

	public void saveClient(Client client){

		if(!clientExists(client.getId())){
			jpaDb.insertIntoDb(client);

		}else{
			LOGGER.warn("Pizza found and not inserted");
		}

	}

	public boolean clientExists(int idClient) {

		return jpaDb.entryExist(Client.class, "id='"+idClient+"'");

	}
	
	public List<Client> findAllClients(){
		return (ArrayList<Client>) jpaDb.selectFromDb(Client.class,null);
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		if(!pizzaExists(pizza.getId())){
			jpaDb.insertIntoDb(pizza);

		}else{
			LOGGER.warn("Pizza found and not inserted");
		}
		
	}
	
	public boolean pizzaExists(int idPizza) {

		return jpaDb.entryExist(Pizza.class, "id='"+idPizza+"'");

	}


}
