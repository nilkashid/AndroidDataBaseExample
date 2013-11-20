package com.example.androidsqliteexample;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SqliteDBExample extends Activity {

	EditText name, standard, city, marks, idValue, nameValue;
	TextView retriveValue;
	Button getValueButton, createRecordButton, deleteRecordButton;
	ContentResolver contentResolver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite_dbexample);
		
		contentResolver = getContentResolver();
		name = (EditText)findViewById(R.id.name);
		standard = (EditText)findViewById(R.id.standard);
		city = (EditText)findViewById(R.id.city);
		marks = (EditText)findViewById(R.id.marks);
		idValue = (EditText)findViewById(R.id.idValue);
		nameValue = (EditText)findViewById(R.id.nameValue);
		
		retriveValue = (TextView) findViewById(R.id.retriveValue);
		
		getValueButton = (Button) findViewById(R.id.getValueButton);
		getValueButton.setOnClickListener(getValueListener);
		createRecordButton = (Button) findViewById(R.id.createRecordButton);
		createRecordButton.setOnClickListener(createRecordListener);
		deleteRecordButton = (Button) findViewById(R.id.deleteValueButton);
		deleteRecordButton.setOnClickListener(deleteRecordListener);
	}

	private OnClickListener getValueListener = new OnClickListener() 
	{
		@Override
		public void onClick(View v) 
		{
			Uri myC ;
			Cursor cursor = null;
			if(!idValue.getText().toString().equalsIgnoreCase(""))
			{
				Toast.makeText(getApplicationContext(), "text :"+idValue.getText().toString(), Toast.LENGTH_SHORT).show();
				myC = Uri.withAppendedPath(StudentTable.CONTENT_URI,idValue.getText().toString());
				cursor = contentResolver.query(myC, null, null, null, null);
				Toast.makeText(getApplicationContext(), "inside count : "+cursor.getCount(), Toast.LENGTH_SHORT).show();
			}
			else if(!nameValue.getText().toString().equalsIgnoreCase(""))
			{
				String[] selectionArgs = new String[1];
				selectionArgs[0] = nameValue.getText().toString();
				cursor = contentResolver.query(StudentTable.CONTENT_URI, null, StudentTable.NAME+" = ?", selectionArgs, null);
			}
			else
			{
				cursor = contentResolver.query(StudentTable.CONTENT_URI, null, null, null, null);
			}
			Toast.makeText(getApplicationContext(), "count : "+cursor.getCount(), Toast.LENGTH_SHORT).show();
			
			startManagingCursor(cursor);
			while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex(StudentTable.ID));
			String name = cursor.getString(cursor.getColumnIndex(StudentTable.NAME));
			int standard = cursor.getInt(cursor.getColumnIndex(StudentTable.STANDARD));
			int marks = cursor.getInt(cursor.getColumnIndex(StudentTable.MARKS));
			String city = cursor.getString(cursor.getColumnIndex(StudentTable.CITY));
			retriveValue.setText(name +"   "+ standard +"  "+ marks +"  "+city);
			idValue.setText("");
			nameValue.setText("");
			}

		}
	};
	
	
	
	private OnClickListener createRecordListener = new OnClickListener() 
	{
		@Override
		public void onClick(View v) 
		{
			ContentValues contentValue = new ContentValues();
			contentValue.put(StudentTable.NAME, name.getText().toString());
			contentValue.put(StudentTable.STANDARD, Integer.parseInt(standard.getText().toString()));
			contentValue.put(StudentTable.CITY, city.getText().toString());
			contentValue.put(StudentTable.MARKS, Integer.parseInt(marks.getText().toString()));
			contentResolver.insert(StudentTable.CONTENT_URI, contentValue);
			Cursor cursor = contentResolver.query(StudentTable.CONTENT_URI, null, null,null, null);
			Toast.makeText(getApplicationContext(), "count : "+cursor.getCount(), Toast.LENGTH_SHORT).show();
		}
	};
	
	private OnClickListener deleteRecordListener = new OnClickListener() 
	{
		@Override
		public void onClick(View v) 
		{
			//contentResolver.delete(url, where, selectionArgs)
			Uri myC ;
			Cursor cursor = null;
			if(!idValue.getText().toString().equalsIgnoreCase(""))
			{
				myC = Uri.withAppendedPath(StudentTable.CONTENT_URI,idValue.getText().toString());
				contentResolver.delete(myC, null, null);
				idValue.setText("");
			}
			else if(!nameValue.getText().toString().equalsIgnoreCase(""))
			{
				String[] selectionArgs = new String[1];
				selectionArgs[0] = nameValue.getText().toString();
				contentResolver.delete(StudentTable.CONTENT_URI, StudentTable.NAME+" = ?", selectionArgs);
				nameValue.setText("");
			}
			else
			{
				contentResolver.delete(StudentTable.CONTENT_URI, null, null);
				Toast.makeText(getApplicationContext(), "all data deleted", Toast.LENGTH_SHORT).show();
			}
			
			
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sqlite_dbexample, menu);
		return true;
	}

}
