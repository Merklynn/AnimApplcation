package com.osmobile.capita.animapplcation;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by JKidd on 17/02/2017.
 */

public class AnimDBManager extends  SQLiteOpenHelper{
    private static String PRIMARY_KEY = "PRIMARY KEY";
    private static String INTEGERTYPE = "INTEGER";
    private static String AUTOINCREMENT = "AUTOINCREMENT";
    private static String TEXTTYPE = "TEXT";

    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME= "AnimApplication.db";

    private static final String[] TABLENAMES = new String[]{"Products"};

    private  static final String[] TABLEFIELDS = new String[]{"_id "+ INTEGERTYPE +" " + PRIMARY_KEY +" "  +  AUTOINCREMENT,
            "_name " + TEXTTYPE};


    public AnimDBManager(Context context,  SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        CreateTables(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        DeleteTables(db);

        CreateTables(db);
    }

    private void CreateTables(SQLiteDatabase db) {
        for (String item:TABLENAMES) {
            String query = "CREATE TABLE " + item + " (";

            for (String col:TABLEFIELDS) {
                query +=col;
                query+=", ";
            }
            if(query.endsWith(", "))
               query = query.substring(0, query.length()-2);

            query += ");";

            db.execSQL(query);
        }
    }

    private void DeleteTables(SQLiteDatabase db) {
        for (String item:TABLENAMES) {
            String query = "DROP TABLE IF EXISTS " + item;
            db.execSQL(query);
        }
    }

    public void addProduct(Product prod)
    {
        ContentValues values = new ContentValues();
        for (String item:TABLEFIELDS) {
            if(!item.contains(AUTOINCREMENT))
            {
                values.put(item.replace(PRIMARY_KEY, "").replace(INTEGERTYPE, "").replace(TEXTTYPE, ""), prod.get_name());
            }
        }

        dbInsert("Products", values);
    }

    public  ArrayList<String> SelectFromProducts() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM Products;";
        Cursor cur = db.rawQuery(query, null);
        cur.moveToFirst();
        ArrayList<String> str = new ArrayList<String>();
        while (!cur.isAfterLast()) {
            if (cur.getString(cur.getColumnIndex("_name")) != null) {
                str.add(cur.getString(cur.getColumnIndex("_name")));
                cur.moveToNext();
            }
        }
        db.close();
        return str;
    }

    public void deleteProduct(String prodName)
    {
        dbDelete("Products", "_name = ?", new String[]{ prodName});

    }

    private int  dbDelete(String name, String where, String[] whereArgs) {
        SQLiteDatabase db = getWritableDatabase();
        int res = db.delete(name, where, whereArgs);
        db.close();
        return res;
    }
    private long dbInsert(String name, ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        long res = db.insert(name, null, values);
        db.close();
        return res;
    }


}
