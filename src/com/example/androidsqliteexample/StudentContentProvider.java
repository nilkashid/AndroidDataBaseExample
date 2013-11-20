package com.example.androidsqliteexample;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class StudentContentProvider extends ContentProvider
{
	private static final String DATABASE_NAME = "student.db";
	private static final int DATABASE_VERSION = 1;
	public static final String AUTHORITY = "com.example.androidsqliteexample.studentcontentprovider";
	private static final UriMatcher sUriMatcher;
	private static HashMap<String, String> projectionMap;
	private static final int STUDENTS = 1;
	private static final int SSID = 2;

	
	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count;

		switch (sUriMatcher.match(uri)) 
		{
			case STUDENTS:
				count = db.delete(StudentTable.TABLE_NAME, where,whereArgs);
			break;
			case SSID:
				String ssid = uri.getPathSegments().get(StudentTable.SSID_PATH_POSITION);
				String finalWhere = StudentTable.ID+"="+ssid;
				if (where != null) 
				{
					finalWhere = finalWhere + " AND " + where;
				}
				count = db.delete(StudentTable.TABLE_NAME,finalWhere, whereArgs);
			break;
			default:
				throw new IllegalArgumentException
				("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public String getType(Uri uri) 
	{
		switch (sUriMatcher.match(uri)) 
		{
			case STUDENTS:
				return StudentTable.CONTENT_TYPE;
			case SSID:
				return StudentTable.CONTENT_ITEM_TYPE;
			default:
				throw new IllegalArgumentException("Unknown URI "+ uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues initialValues) 
	{
		if (sUriMatcher.match(uri) != STUDENTS) 
		{ 
			throw new IllegalArgumentException("Unknown URI " + uri); 
		}
		
		ContentValues values;
		if (initialValues != null) 
		{
			values = new ContentValues(initialValues);
		} 
		else 
		{
			values = new ContentValues();
		}
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long rowId = db.insert(StudentTable.TABLE_NAME,StudentTable.NAME, values);
		if (rowId > 0) 
		{
			Uri citizenUri = ContentUris.withAppendedId(StudentTable.CONTENT_URI,rowId);
			getContext().getContentResolver().notifyChange(citizenUri,null);
				return citizenUri;
		}
		throw new SQLException("Failed to insert row into " + uri);
	}

	private DatabaseHelper dbHelper;
	@Override
	public boolean onCreate() {
		// HELPER DATABASE IS INITIALIZED
		dbHelper = new DatabaseHelper(getContext(),DATABASE_NAME,null,DATABASE_VERSION);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,String sortOrder)
	{
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(StudentTable.TABLE_NAME);

		switch (sUriMatcher.match(uri)) 
		{
			case STUDENTS:
				qb.setProjectionMap(projectionMap);
			break;
			case SSID:
				String ssid = uri.getPathSegments().get(StudentTable.SSID_PATH_POSITION);
				qb.setProjectionMap(projectionMap);
				// 	FOR QUERYING BY SPECIFIC SSID
				qb.appendWhere(StudentTable.ID + "=" + ssid);
			break;
			default:
				throw new IllegalArgumentException
				("Unknown URI " + uri);
		}
		
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
		// REGISTERS NOTIFICATION LISTENER WITH GIVEN CURSOR
		// CURSOR KNOWS WHEN UNDERLYING DATA HAS CHANGED
		cursor.setNotificationUri(getContext().getContentResolver(),uri);
		return cursor;
	}
	
	static 
	{
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(AUTHORITY, "student", STUDENTS);
		sUriMatcher.addURI(AUTHORITY, "student/#", SSID);
		
		projectionMap = new HashMap<String, String>();
		projectionMap.put(StudentTable.ID, StudentTable.ID);
		projectionMap.put(StudentTable.NAME, StudentTable.NAME);
		projectionMap.put(StudentTable.STANDARD, StudentTable.STANDARD);
		projectionMap.put(StudentTable.CITY, StudentTable.CITY);
		projectionMap.put(StudentTable.MARKS, StudentTable.MARKS);
	}

	@Override
	public int update(Uri uri, ContentValues values, String where,String[] whereArgs) 
	{
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count;

		switch (sUriMatcher.match(uri)) 
		{
			case STUDENTS:
				count = db.update(StudentTable.TABLE_NAME, values, where, whereArgs);
			break;
			case SSID:
				String ssid = uri.getPathSegments().get(StudentTable.SSID_PATH_POSITION);
				String finalWhere = StudentTable.ID+"="+ssid;
				if (where != null) 
				{
					finalWhere = finalWhere + " AND " + where;
				}
				count = db.update(StudentTable.TABLE_NAME, values, finalWhere, whereArgs);
			break;
			default:
			throw new IllegalArgumentException
			("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;

	}

}
