package com.example.androidsqliteexample;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 String[] values = new String[] { "Shared Pref", "External Memory", "Internal Memory",
                 "Sqlite DB"};
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
       setListAdapter(adapter); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	   @Override
       protected void onListItemClick(ListView l, View v, int position, long id) {
             
             super.onListItemClick(l, v, position, id);
             
             switch (position) {
			case 0:
				startActivity(new Intent(getApplicationContext(), SharedPfExample.class));
				break;
			case 1:
				startActivity(new Intent(getApplicationContext(), ExternalMemExample.class));
				break;
			case 2:
				startActivity(new Intent(getApplicationContext(), InternalMemExample.class));
				break;
			case 3:
				startActivity(new Intent(getApplicationContext(), SqliteDBExample.class));
				break;
	
			default:
				break;
			}
       }

}
