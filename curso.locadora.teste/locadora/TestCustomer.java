package locadora;

import static org.junit.Assert.*;

import org.junit.Test;

import curso.locadora.Customer;
import curso.locadora.Movie;
import curso.locadora.Rental;
import junit.framework.TestCase;

public class TestCustomer extends TestCase{
	Customer client;
	
	protected void setUp() throws Exception{
		client = new Customer("John");
	}
	
	public void testNameCreation() {
		String result = client.statement();
		assertContain(result,"Rental Record for John");
	}
	
	public void testOneRegularOneDay(){
		rentMovie("Indiana Jones",Movie.REGULAR,1);
		String result = client.statement();
		assertContain(result,"Amount owed is 2.0");
		assertContain(result,"You earned 1 frequent renter points");
	}
	
	public void testOneRegularThreeDays(){
		rentMovie("Indiana Jones",Movie.REGULAR,3);
		String result = client.statement();
		assertContain(result,"Amount owed is 3.5");
		assertContain(result,"You earned 1 frequent renter points");
	}
	
	public void testOneChildrensOneDay(){
		rentMovie("Procurando Nemo",Movie.CHILDRENS,1);
		String result = client.statement();
		assertContain(result,"Amount owed is 1.5");
		assertContain(result,"You earned 1 frequent renter points");
	}
	
	public void testOneChildrensFiveDays(){
		rentMovie("Procurando Nemo",Movie.CHILDRENS,5);
		String result = client.statement();
		assertContain(result,"Amount owed is 4.5");
		assertContain(result,"You earned 1 frequent renter points");
	}
	
	public void testOneNewReleaseOneDay(){
		rentMovie("Vingadores 2",Movie.NEW_RELEASE,1);
		String result = client.statement();
		assertContain(result,"Amount owed is 3.0");
		assertContain(result,"You earned 1 frequent renter points");
	}
	
	public void testOneNewReleaseThreeDays(){
		rentMovie("Vingadores 2",Movie.NEW_RELEASE,3);
		String result = client.statement();
		assertContain(result,"Amount owed is 9.0");
		assertContain(result,"You earned 2 frequent renter points");
	}
	
	public void testManyRents(){
		rentMovie("Vingadores 2",Movie.NEW_RELEASE,2);
		rentMovie("Star Wars - Episódio VII",Movie.NEW_RELEASE,3);
		rentMovie("Procurando Nemo",Movie.CHILDRENS,3);
		rentMovie("Indiana Jones",Movie.REGULAR,2);
		rentMovie("Divertidamente",Movie.CHILDRENS,4);
		rentMovie("E o vento levou...",Movie.REGULAR,3);
		String result = client.statement();
		assertContain(result,"Amount owed is 25.0");
		assertContain(result,"You earned 8 frequent renter points");
	}
	
	
	private void rentMovie(String title, int type, int days){
		Movie movie = new Movie(title, type);
		Rental rent = new Rental(movie, days);
		client.addRental(rent);
	}
	private void assertContain(String result, String content){
		assertTrue(result.indexOf(content)>=0);
	}

}
