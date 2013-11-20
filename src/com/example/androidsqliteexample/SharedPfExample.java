package com.example.androidsqliteexample;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SharedPfExample extends Activity {

	EditText keyText,keyValue,searchKey;
	Button saveData,getData;
	TextView searchValue;
	private static final String USERINFO = "userInfo";
	SharedPreferences mUserInfo;
	Editor mUserInfoEditor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shared_pf_example);
		
		mUserInfo = getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
		mUserInfoEditor = mUserInfo.edit();
		
		keyText = (EditText)findViewById(R.id.keytext);
		keyValue = (EditText)findViewById(R.id.keyvalue);
		searchKey = (EditText)findViewById(R.id.searchkey);
		searchValue = (TextView)findViewById(R.id.search_value);
		
		saveData = (Button)findViewById(R.id.savebutton);
		getData = (Button)findViewById(R.id.getdatabutton);
		saveData.setOnClickListener(saveDataMethod);
		getData.setOnClickListener(getDataMethod);
	}
	
	private OnClickListener saveDataMethod = new OnClickListener() 
	{
		@Override
		public void onClick(View v) {
			if(!keyText.getText().toString().equalsIgnoreCase("") && !keyValue.getText().toString().equalsIgnoreCase(""))
			{
				mUserInfoEditor.putString(keyText.getText().toString(), keyValue.getText().toString()).commit();
			}
			else
			{
				Toast.makeText(getApplicationContext(), "enter value", Toast.LENGTH_SHORT).show();
			}
			keyText.setText("");
			keyValue.setText("");
		}
	};
	
	private OnClickListener getDataMethod = new OnClickListener() 
	{
		@Override
		public void onClick(View v) 
		{
			String value = mUserInfo.getString(searchKey.getText()+"", "key not found");
			searchValue.setText(value);
			searchKey.setText("");
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shared_pf_example, menu);
		return true;
	}

}
