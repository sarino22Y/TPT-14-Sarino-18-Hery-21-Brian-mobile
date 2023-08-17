package com.mbds.tpt_sarino_brian_hery.model.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.mbds.tpt_sarino_brian_hery.model.user.User;

public class AccesLocal {

    private String nomBase = "tpt_sarino_brian_hery";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesDb;
    private SQLiteDatabase database;

    /**
     * Constructeur de la classe.
     * @param context
     */
    public AccesLocal(Context context){
        accesDb = new MySQLiteOpenHelper(context, nomBase, null, versionBase);
    }

    /**
     * Ajouter un utilisateur a la base de données.
     * @param user
     */
    public void ajouterUser(User user){
        database = accesDb.getWritableDatabase();
        String sql = "INSERT INTO users (lastName, firstName, mail, password) VALUES ('"+user.getLastName()+"', '"+user.getFirstName()+"', '"+user.getEmail()+"', '"+user.getPassword()+"')";
        database.execSQL(sql);
    }

    /**
     * Récupérer le dernier utilisateur ajouté.
     */
    public User getLastUser(){
        database = accesDb.getReadableDatabase();
        String sql = "SELECT * FROM users";
        User user = null;
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToLast();
        if(!cursor.isAfterLast()){
            String lastName = cursor.getString(1);
            String firstName = cursor.getString(2);
            String email = cursor.getString(3);
            String password = cursor.getString(4);
            user = new User(lastName, firstName, email, password);
        }
        cursor.close();
        return user;
    }

    /**
     *  Récupérer un utilisateur à partir de son mail
     * @param email
     * @return
     */
    public User getUserByEmail(String email){
        database = accesDb.getReadableDatabase();
        String sql = "SELECT * FROM users WHERE mail = '"+email+"'";
        User user = new User();
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            user.setLastName(cursor.getString(1));
            user.setFirstName(cursor.getString(2));
            user.setEmail(cursor.getString(3));
            user.setPassword(cursor.getString(4));
        }
        return user;
    }


}
