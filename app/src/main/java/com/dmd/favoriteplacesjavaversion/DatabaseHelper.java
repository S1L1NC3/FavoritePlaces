package com.dmd.favoriteplacesjavaversion;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

public class DatabaseHelper {
    private Context contextLocal;
    private static SQLiteDatabase database;
    //Table name MyLocations
    public DatabaseHelper(Context context) {
        contextLocal = context;
    }

    //Check database isOpen
    public boolean isOpen(){
        return database.isOpen();
    }

    //Open Database connection
    public void openConnection(){
        database = contextLocal.openOrCreateDatabase(contextLocal.getResources().getString(R.string.database_name),MODE_PRIVATE,null);
    }

    //Close Database connection
    public void closeConnection(){
        if (database != null){
            database.close();
        }
    }

    //Create table if not Exists
    public void createTableIfNotExists(){
        try {
            database.execSQL("CREATE TABLE IF NOT EXISTS MyLocations (Id INTEGER PRIMARY KEY,locationName VARCHAR, locationLatitude double, locationLongitude double)");
        } catch (Exception ignored){

        }
    }

    //Insert Into Database
    public boolean insertIntoLocations(String locationName, Double locationLatitude, Double locationLongitude){
        try{
            SQLiteStatement sqLiteStatement = database.compileStatement("INSERT INTO MyLocations (locationName, locationLatitude, locationLongitude) values (?,?,?)");

            sqLiteStatement.bindString(1, locationName);
            sqLiteStatement.bindDouble(2, locationLatitude);
            sqLiteStatement.bindDouble(3, locationLongitude);
            sqLiteStatement.executeInsert();
            return true;
        } catch (SQLException exception){
            return false;
        }
    }

    //Update data in Database
    public boolean updateDataInMyLocations(String locationName, Double locationLatitude, Double locationLongitude, int idForDelete){
        try{
            SQLiteStatement sqLiteStatement = database.compileStatement("UPDATE MyLocations SET locationName = ?, locationLongitude = ?, locationLatitude = ? WHERE _id = ?");

            sqLiteStatement.bindString(1, locationName);
            sqLiteStatement.bindDouble(2, locationLatitude);
            sqLiteStatement.bindDouble(3, locationLongitude);
            sqLiteStatement.bindLong(4, idForDelete);

            sqLiteStatement.execute();
            return true;
        } catch (Exception ignored){
            return false;
        }
    }

    //Remove based on Id
    public boolean removeFromLocations(int id){
        try {
            SQLiteStatement sqLiteStatement = database.compileStatement("DELETE FROM MyLocations WHERE Id= ?");
            sqLiteStatement.bindLong(1, id);
            sqLiteStatement.execute();
            return true;
        } catch (Exception ignored){
            return false;
        }
    }

    //Delete Database
    public boolean dropDb(){
        try {
            contextLocal.deleteDatabase(contextLocal.getResources().getString(R.string.database_name));
            return true;
        } catch (Exception ignored){
            return false;
        }

    }

    //Log Database
    public void trackAllData(){
        Cursor cursor = null;
        try{
            cursor = database.rawQuery("SELECT * FROM MyLocations", null);
        } catch (Exception ignored){

        }

        if (cursor != null){
            int idIndex = cursor.getColumnIndex("Id");
            int locatioNameIndex = cursor.getColumnIndex("locationName");
            int locationLatitudeIndex = cursor.getColumnIndex("locationLatitude");
            int locationLongitudeIndex = cursor.getColumnIndex("locationLongitude");

            while (cursor.moveToNext()){
                Log.i("DbTrackLog", "idIndex: " + cursor.getString(idIndex));
                Log.i("DbTrackLog", "locatioNameIndex: " + cursor.getString(locatioNameIndex));
                Log.i("DbTrackLog", "locationLatitudeIndex: " + cursor.getDouble(locationLatitudeIndex));
                Log.i("DbTrackLog", "locationLongitudeIndex: " + cursor.getDouble(locationLongitudeIndex));
            }
        }


    }

}
