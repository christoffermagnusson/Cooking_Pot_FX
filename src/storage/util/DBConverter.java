package storage.util;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.models.*;
import domain.handlers.*;
import log.Log;

public class DBConverter {

	private static DBConverter instance;

	private DBConverter(){

	}

	public static DBConverter getInstance(){
		if(instance==null){
			return new DBConverter();
		}
		return instance;
	}

	public static Chef toChef(ResultSet res) throws StorageException{
		Chef chef = null;
		try{
			while(res.next()){
				chef = new Chef(res.getString(1),res.getString(2));
				chef.setId(res.getInt(3));
			}
			res.close();
		}catch(SQLException se){
			throw new StorageException(se);
		}
		finally{
			return chef;
		}
	}
	public static ArrayList<Chef> toChefList(ResultSet res) throws StorageException{
		ArrayList<Chef> array = new ArrayList<Chef>();
		Chef chef = null;
		try{
			while(res.next()){
				chef = new Chef(res.getString(1),res.getString(2));
				array.add(chef);
				chef.setId(res.getInt(3));
			}
			res.close();
		}
		catch(SQLException se){
			throw new StorageException(se);
		}
		finally{

			return array;
		}
	}
	public static IngredientType toIngredientType(ResultSet res)throws StorageException{
		IngredientType type = null;
		try{
			while(res.next()){
				type = new IngredientType(res.getString(1),res.getString(2));
			}
			res.close();
		}catch(SQLException se){
			throw new StorageException(se);
		}finally{
			return type;
		}
	}
	public static ArrayList<IngredientType> toTypeList(ResultSet res) throws StorageException{
		ArrayList<IngredientType> array = new ArrayList<IngredientType>();
		IngredientType type = null;
		try{
			while(res.next()){
				type = new IngredientType(res.getString(1),res.getString(2));
				array.add(type);
			}
			res.close();
		}
		catch(SQLException se){
			throw new StorageException(se);
		}
		finally{
			return array;
		}
	}
	public static int getId(ResultSet res) throws StorageException{
		int id = 0;
		try{
			while(res.next()){
			 id = res.getInt(1);
			}
			res.close();
			}
			catch(SQLException se){
				throw new StorageException(se);
			}
			finally{

			}
			return id;
	}
	public static ArrayList<Ingredient> toIngredientList(ResultSet res)throws StorageException{
		ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
		try{
			while(res.next()){
				String typeQuery = String.format("SELECT * FROM ingredienttype WHERE name ='%s'"
					,res.getString(1));
				ResultSet typeRes = DBConnection.getInstance().execQuery(typeQuery);
				IngredientType type = toIngredientType(typeRes);

				ingredientList.add(new Ingredient(type,res.getInt(2)));

			}
			res.close();
		}
		catch(SQLException se){
			throw new StorageException(se);
		}
		return ingredientList;
	}
	public static Recipe toRecipe(ResultSet res)throws StorageException{
		Recipe recipe = null;
		try{

			while(res.next()){
				String typeName = res.getString(2);
			ResultSet typeSet = DBConnection.getInstance().execQuery(String.format("SELECT * FROM ingredienttype WHERE name = '%s'"
				,typeName));
			IngredientType type = toIngredientType(typeSet);

			int chefId = res.getInt(4);
			ResultSet chefSet = DBConnection.getInstance().execQuery(String.format("SELECT * FROM chef WHERE id = %d"
				,chefId));
			Chef chef = toChef(chefSet);

			IngredientListHandler handler = new IngredientListHandler();
			handler.setId(res.getInt(5));

			int cookingTime = res.getInt(6);
			String timeUnitType = res.getString(7);
			TimeUnit timeUnit = new TimeUnit(cookingTime,timeUnitType);

			recipe = new Recipe(res.getString(1)
													,chef
													,type
													,handler
													,res.getString(3)
													,timeUnit);
			}
			res.close();
		}
		catch(SQLException se){
			throw new StorageException(se);
		}
		return recipe;
	}

	public static ArrayList<Recipe> toRecipeList(ResultSet res)throws StorageException{
		ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
		try{

			while(res.next()){
				String typeName = res.getString(2);
				ResultSet typeSet = DBConnection.getInstance().execQuery(String.format("SELECT * FROM ingredienttype WHERE name = '%s'"
					,typeName));
				IngredientType type = toIngredientType(typeSet);

				int chefId = res.getInt(4);
				ResultSet chefSet = DBConnection.getInstance().execQuery(String.format("SELECT * FROM chef WHERE id = %d"
					,chefId));
				Chef chef = toChef(chefSet);

				IngredientListHandler handler = new IngredientListHandler();
				handler.setId(res.getInt(5));

				int cookingTime = res.getInt(6);
				String timeUnitType = res.getString(7);
				TimeUnit timeUnit = new TimeUnit(cookingTime,timeUnitType);

				recipeList.add(new Recipe(res.getString(1)
																	,chef
																	,type
																	,handler
																	,res.getString(3)
																	,timeUnit));
			}
			res.close();
		}
		catch(SQLException se){
			throw new StorageException(se);
		}
		return recipeList;
	}

	public static ArrayList<String> toTimeUnitTypeList(ResultSet res)throws StorageException{
		ArrayList<String> typeArray = new ArrayList<String>();
		try{
			while(res.next()){
				String typeName = res.getString(2);
				typeArray.add(typeName);
			}
			res.close();

		}catch(SQLException se){
			throw new StorageException(se);
		}
		return typeArray;
	}

	public static User toUser(ResultSet res)throws StorageException{
		User user = null;
		try{
			while(res.next()){
				user = new User(res.getString(2)
						,res.getString(3));
			}
		}
		catch(SQLException se){
			throw new StorageException(se);
		}
		return user;
	}

}
