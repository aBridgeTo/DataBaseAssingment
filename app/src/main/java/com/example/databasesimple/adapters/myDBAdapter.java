package com.example.databasesimple.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;





public class myDBAdapter {

    myDbHelper myhelper;

    public myDBAdapter(Context context) {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String name, String lastname, String phone) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.LastName, lastname);
        contentValues.put(myDbHelper.PHONE, phone);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getData() {

        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.NAME, myDbHelper.LastName, myDbHelper.PHONE};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name = cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            String lastName = cursor.getString(cursor.getColumnIndex(myDbHelper.LastName));
            String phone = cursor.getString(cursor.getColumnIndex(myDbHelper.PHONE));
            buffer.append(cid + "   " + name + "   " + lastName + "   " + phone + " \n");
        }
        return buffer.toString();
    }

    public void delete() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        db.delete(myDbHelper.TABLE_NAME, null  , null );
        db.close();
    }

    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "localDatabase";    // Database Name
        private static final String TABLE_NAME = "myTable";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID = "_id";     // Column I (Primary Key)
        private static final String NAME = "Name";    //Column II
        private static final String LastName = "Password";    // Column III
        private static final String PHONE = "Phone";    // Column III
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) ," + LastName + " VARCHAR(225) ," + PHONE + " VARCHAR(225));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context, "OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }

    }
}
