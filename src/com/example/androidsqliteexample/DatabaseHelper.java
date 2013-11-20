package com.example.androidsqliteexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE " + StudentTable.TABLE_NAME +
				" (" + StudentTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + StudentTable.NAME + " TEXT," +
				StudentTable.STANDARD + " INTEGER," + StudentTable.CITY +
				" TEXT,"+StudentTable.MARKS +
				" INTEGER"+");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " +StudentTable.TABLE_NAME);
		onCreate(db);
	}

}
