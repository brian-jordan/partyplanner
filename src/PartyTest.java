import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
/**
 * Freestyle Fellowship 2018 Engineering Application
 * Brian Jordan
 * November 3, 2017
 * 
 * Tests for Party.java Class
 */
public class PartyTest {
	// Test Objects
	Map<String,Integer> correctDrinkPricesMap;
	Map<String,Integer> correctFoodPricesMap;
	Map<String,Integer> correctDrinkPreferencesMap;
	Map<String,Integer> correctFoodPreferencesMap;
	Party tester;
	// Initializing Test Objects
	@Before
	public void setUp(){
		tester = new Party(0,"people","drinks","food");
		correctDrinkPricesMap = new HashMap<String,Integer>();
		correctFoodPricesMap = new HashMap<String,Integer>();
		correctDrinkPreferencesMap = new HashMap<String,Integer>();
		correctFoodPreferencesMap = new HashMap<String,Integer>();
		// Initializing Correct Drink Prices Map
		correctDrinkPricesMap.put("fanta", 1);
		correctDrinkPricesMap.put("sunkist", 3);
		correctDrinkPricesMap.put("pepsi", 2);
		correctDrinkPricesMap.put("coke", 4);
		correctDrinkPricesMap.put("sprite", 1);
		correctDrinkPricesMap.put("lemonade", 6);
		correctDrinkPricesMap.put("tea", 8);
		correctDrinkPricesMap.put("milk", 2);
		correctDrinkPricesMap.put("coffee", 5);
		correctDrinkPricesMap.put("milkshake", 8);
		// Initializing Correct Food Prices Map
		correctFoodPricesMap.put("chips", 3);
		correctFoodPricesMap.put("guacamole", 4);
		correctFoodPricesMap.put("salsa", 3);
		correctFoodPricesMap.put("pretzels", 5);
		correctFoodPricesMap.put("cupcakes", 6);
		correctFoodPricesMap.put("brownies", 5);
		correctFoodPricesMap.put("chili", 8);
		correctFoodPricesMap.put("pizza", 10);
		correctFoodPricesMap.put("ribs", 10);
		correctFoodPricesMap.put("cookies", 4);
		correctFoodPricesMap.put("queso", 6);
		correctFoodPricesMap.put("taquitos", 12);
		// Initializing Correct Drink Preferences Map
		correctDrinkPreferencesMap.put("fanta", 7);
		correctDrinkPreferencesMap.put("sunkist", 9);
		correctDrinkPreferencesMap.put("pepsi", 15);
		correctDrinkPreferencesMap.put("coke", 18);
		correctDrinkPreferencesMap.put("sprite", 12);
		correctDrinkPreferencesMap.put("lemonade", 8);
		correctDrinkPreferencesMap.put("tea", 4);
		correctDrinkPreferencesMap.put("milk", 6);
		correctDrinkPreferencesMap.put("coffee", 12);
		correctDrinkPreferencesMap.put("milkshake", 15);
		// Initializing Correct Food Preferences Map
		correctFoodPreferencesMap.put("chips", 15);
		correctFoodPreferencesMap.put("guacamole", 8);
		correctFoodPreferencesMap.put("salsa", 12);
		correctFoodPreferencesMap.put("pretzels", 14);
		correctFoodPreferencesMap.put("cupcakes", 9);
		correctFoodPreferencesMap.put("brownies", 12);
		correctFoodPreferencesMap.put("chili", 5);
		correctFoodPreferencesMap.put("pizza", 18);
		correctFoodPreferencesMap.put("ribs", 2);
		correctFoodPreferencesMap.put("cookies", 20);
		correctFoodPreferencesMap.put("queso", 9);
		correctFoodPreferencesMap.put("taquitos", 1);
	}
	
	// Method Tests
	// Tests setDrinkPrices method in Party.java Class
	@Test(timeout = 10000)
	public void testSetDrinkPrices() {
		assertTrue("This test checks if Party.java correctly reads from people.txt"
				+ "to create the drinkPricesMap", tester.drinkPricesMap.equals(correctDrinkPricesMap));
	}
	// Tests setFoodPrices method in Party.java Class
	@Test(timeout = 10000)
	public void testSetFoodPrices() {
		assertTrue("This test checks if Party.java correctly reads from people.txt"
				+ "to create the foodPricesMap", tester.foodPricesMap.equals(correctFoodPricesMap));
	}
	// Tests setDrinkFoodPreferences method in Party.java Class
	@Test(timeout = 10000)
	public void testSetDrinkPreferences() {
		assertTrue("This test checks if Party.java correctly reads from people.txt"
				+ "to create the drinkPreferencesMap", tester.drinkPreferencesMap.equals(correctDrinkPreferencesMap));
	}
	// Tests setDrinkFoodPreferences method in Party.java Class
	@Test(timeout = 10000)
	public void testSetFoodPreferences() {
		assertTrue("This test checks if Party.java correctly reads from people.txt"
				+ "to create the foodPreferencesMap", tester.foodPreferencesMap.equals(correctFoodPreferencesMap));
	}
	
	// Case testing for different budget values
	// Budget is $1
	@Test(timeout = 10000)
	public void testParty1() {
		Party test1 = new Party(1,"people","drinks","food");
		String correctDrinks = "Drinks: sprite";
		String correctFoods = "Food: ";
		assertTrue("This test checks if Party.java correctly selects the optimal drinks"
				+ "using the setMenu method", test1.chosenDrinks.equals(correctDrinks));
		assertTrue("This test checks if Party.java correctly selects the optimal food"
				+ "using the setMenu method", test1.chosenFoods.equals(correctFoods));
	}
	// Budget is $10
	@Test(timeout = 10000)
	public void testParty2() {
		Party test2 = new Party(10,"people","drinks","food");
		String correctDrinks = "Drinks: coke, pepsi";
		String correctFoods = "Food: cookies";
		assertTrue("This test checks if Party.java correctly selects the optimal drinks"
				+ "using the setMenu method", test2.chosenDrinks.equals(correctDrinks));
		assertTrue("This test checks if Party.java correctly selects the optimal food"
				+ "using the setMenu method", test2.chosenFoods.equals(correctFoods));
	}
	// Budget is $50
	@Test(timeout = 10000)
	public void testParty3() {
		Party test3 = new Party(50,"people","drinks","food");
		String correctDrinks = "Drinks: coke, pepsi, milkshake, sprite, fanta";
		String correctFoods = "Food: cookies, pizza, chips, pretzels, salsa, brownies, guacamole";
		assertTrue("This test checks if Party.java correctly selects the optimal drinks"
				+ "using the setMenu method", test3.chosenDrinks.equals(correctDrinks));
		assertTrue("This test checks if Party.java correctly selects the optimal food"
				+ "using the setMenu method", test3.chosenFoods.equals(correctFoods));
	}
	// Budget is $150
	@Test(timeout = 10000)
	public void testParty4() {
		Party test4 = new Party(150,"people","drinks","food");
		String correctDrinks = "Drinks: coke, pepsi, milkshake, sprite, coffee, sunkist, lemonade, fanta, milk, tea";
		String correctFoods = "Food: cookies, pizza, chips, pretzels, salsa, brownies, queso, cupcakes, guacamole, chili, ribs, taquitos";
		assertTrue("This test checks if Party.java correctly selects the optimal drinks"
				+ "using the setMenu method", test4.chosenDrinks.equals(correctDrinks));
		assertTrue("This test checks if Party.java correctly selects the optimal food"
				+ "using the setMenu method", test4.chosenFoods.equals(correctFoods));
	}

}
