package com.example.hwangseho.ohmydays_user.Diary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SQLiteHelper {
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public SQLiteHelper(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
    }

    public long INSERT(String time, String weather, byte[] url, byte[] all_url, String write, String userID){
        ContentValues values = new ContentValues();

        values.put(DAO.Entry.TIME, time);
        values.put(DAO.Entry.WEATHER, weather);
        values.put(DAO.Entry.URL, url);
        values.put(DAO.Entry.ALL_URL, all_url);
        values.put(DAO.Entry.WRITE, write);
        values.put(DAO.Entry.userID, userID);
        //values.put(Database.Entry.userID, userID);

        return db.insert(DAO.Entry.TABLE_NAME, null, values);
    }

    public Cursor SELECTALL() {
        String[] colume = {
                DAO.Entry._ID,
                DAO.Entry.TIME,
                DAO.Entry.WEATHER,
                DAO.Entry.URL,
                DAO.Entry.ALL_URL,
                DAO.Entry.WRITE,
                DAO.Entry.userID
        };

        String sortOrder = DAO.Entry._ID + " DESC"; // 입력된 순서의 내림차순으로 정렬
        String selection = DAO.Entry.userID + " LIKE ?";
        Cursor c = db.query(
                DAO.Entry.TABLE_NAME,
                colume,
                null,
                null,
                null,
                null,
                sortOrder
        );
        return c;
    }

    public void DELETE(String id){
        String selection = DAO.Entry._ID + " LIKE ?";
        String[] selctionArgs = { id };

        db.delete(DAO.Entry.TABLE_NAME, selection, selctionArgs);
    }

    public void DELETE_ALL(){
        db.delete(DAO.Entry.TABLE_NAME, null, null);
    }

    public int UPDATE(String id, String time, String weather, byte[] url, byte[] all_url, String write) {
        ContentValues values = new ContentValues();
        values.put(DAO.Entry.TIME, time);
        values.put(DAO.Entry.WEATHER, weather);
        values.put(DAO.Entry.URL, url);
        values.put(DAO.Entry.ALL_URL, all_url);
        values.put(DAO.Entry.WRITE, write);

        String selection = DAO.Entry._ID + " LIKE ?";
        String[] selectionArgs = { id };

        int count = db.update(DAO.Entry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }
}
