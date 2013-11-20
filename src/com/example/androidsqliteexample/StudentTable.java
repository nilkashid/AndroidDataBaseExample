package com.example.androidsqliteexample;

import android.net.Uri;

public class StudentTable 
{
	public static final String TABLE_NAME = "student_table";
	
	public static final Uri CONTENT_URI = Uri.parse("content://" +StudentContentProvider.AUTHORITY + "/student");
	
	public static final String CONTENT_TYPE = "com.example.androidsqliteexample.dir/citizen";
	
	public static final String CONTENT_ITEM_TYPE = "com.example.androidsqliteexample.item/citizen";
	
	public static final int SSID_PATH_POSITION = 1;


	public static final String ID = "_id";
	public static final String NAME = "name";
	public static final String STANDARD = "standard";
	public static final String CITY = "city";
	public static final String MARKS = "marks";
}
