package storage.util;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.models.*;
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
}
