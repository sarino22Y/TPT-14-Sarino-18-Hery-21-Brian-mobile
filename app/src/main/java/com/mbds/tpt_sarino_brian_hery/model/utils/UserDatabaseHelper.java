package com.mbds.tpt_sarino_brian_hery.model.utils;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mbds.tpt_sarino_brian_hery.model.user.User;

public class UserDatabaseHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tpt_sarino_brian_hery";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NOM = "lastName";
    private static final String COLUMN_PRENOM = "firstName";
    private static final String COLUMN_MAIL = "mail";
    private static final String COLUMN_PASSWORD = "password";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NOM + " TEXT,"
            + COLUMN_PRENOM + " TEXT,"
            + COLUMN_MAIL + " TEXT,"
            + COLUMN_PASSWORD + " TEXT" + ")";

    public UserDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
    }

    public boolean insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NOM, user.getLastName());
        values.put(COLUMN_PRENOM, user.getFirstName());
        values.put(COLUMN_MAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());

        long userId = -1;

        try {
            userId = db.insert(TABLE_USERS, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        if (userId == -1) {
            return false;
        } else {
            return true;
        }
    }

    // Vérifie si l'utilisateur est enregistré
    public boolean isUserRegistered(String email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(
                UserDatabaseHelper.TABLE_USERS, // Nom de la table
                null, // Colonnes (null signifie toutes les colonnes)
                UserDatabaseHelper.COLUMN_MAIL + "=?", // Clause WHERE
                new String[]{email}, // Arguments de la clause WHERE
                null, // GROUP BY
                null, // HAVING
                null // ORDER BY
        );

        boolean isRegistered = cursor.moveToFirst(); // Déplace le curseur sur le premier enregistrement s'il existe

        cursor.close();
        return isRegistered;
    }

    /**
     * Retourne un utilisateur dans la base de données par son e-mail.
     * @param email
     * @return
     */
    public User getUserByEmail(String email) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                COLUMN_ID,
                COLUMN_PRENOM,
                COLUMN_NOM,
                COLUMN_MAIL,
                COLUMN_PASSWORD
        };

        String selection = COLUMN_MAIL + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(
                TABLE_USERS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)));
            user.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRENOM)));
            user.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOM)));
            user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MAIL)));
            user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD)));
            cursor.close();
        }

        return user;
    }
}
