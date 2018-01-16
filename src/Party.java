import java.util.*;
import java.io.*;
/**
 * Freestyle Fellowship 2018 Engineering Application
 * Brian Jordan
 * November 3, 2017
 * 
 * Algorithm: This class uses HashMaps to determine the most optimal drink and food list
 * for a party based on guest preferences, drink prices, and food prices.  Data is read 
 * in from people.txt, food.txt, and drinks.txt and 4 different HashMaps are created.  
 * drinkPricesMap and foodPricesMap store drinks and food as keys respectively and prices
 * as values.  drinkPreferencesMap and foodPreferencesMap store drinks and food as keys
 * respectively and the amount of people that preferred them as the values.  The program
 * works by first selecting the most preferred drink and adding it to the chosenDrinks
 * String.  Then for every two foods that are selected by their preference and added to the
 * chosenFoods String, another drink is added.  At any point, if the next
 * most preferred food or drink goes over budget the program will select the next most preferred.
 * If no more food or drinks are within budget, the program will select the next most 
 * preferred item of the other type.  If the budget is great enough, the program will select all
 * of the items.
 * 
 * Assumptions: I have created the files being tested, but the program will work for any
 * variation of the files as long as they are correctly formatted and have data.  I have 
 * also assumed that all of the food and drinks preferred in the peoples.txt file have
 * a matching price value in their respective price files.  Along with this, I have
 * come up with the idea that the best food to drink ratio is 2:1 and have created my 
 * algorithm accordingly.
 */
public class Party {
	// Instance Variables
	int budget;
	int moneyToSpend;
	Map<String,Integer> drinkPricesMap;
	Map<String,Integer> foodPricesMap;
	Map<String,Integer> drinkPreferencesMap;
	Map<String,Integer> foodPreferencesMap;
	String chosenDrinks = "Drinks: ";
	String chosenFoods = "Food: ";
	
	// Constructor
	public Party(int spendingMoney, String people, String drinks, String food){
		drinkPricesMap = new HashMap<String,Integer>();
		foodPricesMap = new HashMap<String,Integer>();
		drinkPreferencesMap = new HashMap<String,Integer>();
		foodPreferencesMap = new HashMap<String,Integer>();
		budget = spendingMoney;
		moneyToSpend = budget;
		String fileNamePeople = "Files/" + people + ".txt";
		File fPeople = new File(fileNamePeople);
		String peopleText = textFromFile(fPeople);
		setDrinkFoodPreferences(peopleText);
		String fileNameDrinks = "Files/" + drinks + ".txt";
		File fDrinks = new File(fileNameDrinks);
		String drinksText = textFromFile(fDrinks);
		setDrinkPrices(drinksText);
		String fileNameFood = "Files/" + food + ".txt";
		File fFood = new File(fileNameFood);
		String foodText = textFromFile(fFood);
		setFoodPrices(foodText);
		setMenu();
	}
	
	// Methods
	// Returns initialized HashMaps for Drink and Food Preferences
	public void setDrinkFoodPreferences(String preferences){
		Scanner input = new Scanner(preferences);
		while (input.hasNext()){
			input.next();									// Skips over guest name
			String guestDrinkPref = input.next();
			String guestFoodPref = input.next();
			for (String s : guestDrinkPref.split(",")){		// Adds drink preferences to map or iterates existing ones
				if (! drinkPreferencesMap.containsKey(s)){
					drinkPreferencesMap.put(s,0);
				}
				drinkPreferencesMap.put(s, drinkPreferencesMap.get(s) + 1);
			}
			for (String s : guestFoodPref.split(",")){		// Adds food preferences to map or iterates existing ones
				if (! foodPreferencesMap.containsKey(s)){
					foodPreferencesMap.put(s, 0);
				}
				foodPreferencesMap.put(s, foodPreferencesMap.get(s) + 1);
			}
		}
		input.close();
	}
	// Returns initialized Hashmap for Drink Prices
	public void setDrinkPrices(String drinks){
		Scanner input = new Scanner(drinks);
		while (input.hasNext()){
			String option = input.next();
			String[] drinkNPrice = option.split(":");
			String drink = drinkNPrice[0];
			int price = Integer.parseInt(drinkNPrice[1]);
			drinkPricesMap.put(drink, price);
		}
		input.close();
	}
	// Returns initialized Hashmap for Food Prices
	public void setFoodPrices(String foods){
		Scanner input = new Scanner(foods);
		while (input.hasNext()){
			String option = input.next();
			String[] foodNPrice = option.split(":");
			String food = foodNPrice[0];
			int price = Integer.parseInt(foodNPrice[1]);
			foodPricesMap.put(food,price);
		}
		input.close();
	}
	// Creates strings for optimal drinks and food for the party
	// After the first drink, 2 foods are added for every drink until the budget is reached
	public void setMenu(){
		int drinkCount = 0;
		int foodCount = 0;
		while (moneyToSpend > 0 && (drinkPreferencesMap != null || foodPreferencesMap != null)){
			if (foodCount == (2 * drinkCount) || foodPreferencesMap == null){
				String nextDrink = getNextDrink();
				if (nextDrink.equals("")){
					drinkPreferencesMap = null;
					continue;
				}
				if (chosenDrinks.equals("Drinks: ")){
					chosenDrinks += nextDrink;
				}
				else {
					chosenDrinks = chosenDrinks + ", " + nextDrink;
				}
				drinkCount++;
				moneyToSpend -= drinkPricesMap.get(nextDrink);
				if (drinkPreferencesMap.size() == 1){
					drinkPreferencesMap = null;
				}
				else{
					drinkPreferencesMap.remove(nextDrink);
				}
			}
			else {
				String nextFood = getNextFood();
				if (nextFood.equals("")){
					foodPreferencesMap = null;
					continue;
				}
				if (chosenFoods.equals("Food: ")){
					chosenFoods += nextFood;
				}
				else {
					chosenFoods = chosenFoods + ", " + nextFood;
				}
				foodCount++;
				moneyToSpend -= foodPricesMap.get(nextFood);
				if (foodPreferencesMap.size() == 1){
					foodPreferencesMap = null;
				}
				else{
					foodPreferencesMap.remove(nextFood);
				}
			}
		}
	}
	
	// Helper Methods
	// Returns the text from the file that is inputed
	public String textFromFile(File file){
		BufferedInputStream source;
		Scanner scan = null;
		try {
			source = new BufferedInputStream(new FileInputStream(file));
			source.mark(Integer.MAX_VALUE);
			scan = new Scanner(source);
		} catch (IOException e) {
			e.printStackTrace();
		}
		scan.useDelimiter("\\Z");
		return scan.next();
	}
	// Returns the name of the next most preferred drink
	public String getNextDrink(){
		int desiredDrink = 0;
		String nextDrink = "";
		for (String drink : drinkPreferencesMap.keySet()){
			if (drinkPricesMap.get(drink) > moneyToSpend){	// Skips over drinks outside the current budget range
				 continue;
			}
			else {
				if (drinkPreferencesMap.get(drink) >= desiredDrink){
					desiredDrink = drinkPreferencesMap.get(drink);
					nextDrink = drink;
				}
			}
		}
		return nextDrink;									// returns empty string when there are no more drink options
	}
	// Returns the name of the next most preferred food
	public String getNextFood(){
		int desiredFood = 0;
		String nextFood = "";
		for (String food : foodPreferencesMap.keySet()){
			if (foodPricesMap.get(food) > moneyToSpend){	// Skips over food outside the current budget range
				continue;
			}
			else {
				if (foodPreferencesMap.get(food) >= desiredFood){
					desiredFood = foodPreferencesMap.get(food);
					nextFood = food;
				}
			}
		}
		return nextFood;									// returns empty string when there are no more drink options
	}
}
