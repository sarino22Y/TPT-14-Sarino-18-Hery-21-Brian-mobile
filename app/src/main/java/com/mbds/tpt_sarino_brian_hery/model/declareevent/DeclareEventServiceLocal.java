package com.mbds.tpt_sarino_brian_hery.model.declareevent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.List;

public class DeclareEventServiceLocal extends SQLiteOpenHelper {
    private static final String CREATE_TABLE = "CREATE TABLE declareevents (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "title TEXT, " +
            "description TEXT, " +
            "department TEXT, " +
            "citizenid TEXT, " +
            "eventownerid TEXT, " +
            "dateevent TEXT)";

    private SQLiteDatabase dbLocal;
    public DeclareEventServiceLocal(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS declareevents");
    }

    /**
     * Ajouter un evenement.
     * @param event
     * @return
     */
    public DeclareEvent addDeclareEvent(DeclareEvent event) {
        dbLocal = this.getWritableDatabase();
        long id = 0;
        ContentValues values = new ContentValues();
        values.put("title", event.getTitle());
        values.put("description", event.getDescription());
        values.put("department", event.getDepartment());
        values.put("citizenid", event.getCitizenId());
        values.put("eventownerid", event.getEventOwnerId());
        values.put("dateevent", event.getDateEvent());
        try {
            id = dbLocal.insert("declareevents", null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (dbLocal != null) {
            dbLocal.close();
        }
        if (id > 0) {
            return event;
        } else {
            return null;
        }
    }

    /**
     * Obtenir la liste des evenements.
     */
    public List<DeclareEvent> findAll() {
        dbLocal = this.getReadableDatabase();
        String query = "SELECT * FROM declareevents";

        DeclareEvent event = null;

        Cursor cursor = dbLocal.rawQuery(query, null);
        List<DeclareEvent> events = null;
        if (cursor.moveToFirst()) {
            event = new DeclareEvent();
            do {
                event = new DeclareEvent();
                event.setIdDeclareEvent(cursor.getInt(0));
                event.setTitle(cursor.getString(1));
                event.setDescription(cursor.getString(2));
                event.setDepartment(cursor.getString(3));
                event.setCitizenId(cursor.getString(4));
                event.setEventOwnerId(cursor.getString(5));
                event.setDateEvent(cursor.getString(6));
                events.add(event);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return events;
    }

    /**
     * Obtenir un evenement à partir de son id.
     */
    public DeclareEvent findById(int id) {
        dbLocal = this.getReadableDatabase();
        String query = "SELECT * FROM declareevents WHERE id = " + id;
        DeclareEvent event = null;
        Cursor cursor = dbLocal.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            event = new DeclareEvent();
            do {
                event.setIdDeclareEvent(cursor.getInt(0));
                event.setTitle(cursor.getString(1));
                event.setDescription(cursor.getString(2));
                event.setDepartment(cursor.getString(3));
                event.setCitizenId(cursor.getString(4));
                event.setEventOwnerId(cursor.getString(5));
                event.setDateEvent(cursor.getString(6));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return event;
    }
}
