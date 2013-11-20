package com.example.androidsqliteexample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InternalMemExample extends Activity {

	Button createFileButton,readFileButton,deleteFileButton;
	EditText DataText;
	TextView appData;
	String fileName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal_mem_example);
		DataText = (EditText) findViewById(R.id.DataText);
		fileName = "MyappData.txt";
		createFileButton = (Button) findViewById(R.id.createFile);
		readFileButton = (Button) findViewById(R.id.ReadFile);
		deleteFileButton = (Button) findViewById(R.id.deleteFile);
		appData = (TextView) findViewById(R.id.appData);
		
		createFileButton.setOnClickListener(createFileClickListener);
		readFileButton.setOnClickListener(readFileClickListener);
		deleteFileButton.setOnClickListener(deleteFileClickListener);
	}

	private OnClickListener createFileClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) 
		{
			try {
				FileOutputStream fileOPStream = openFileOutput(fileName, getApplicationContext().MODE_PRIVATE);
				fileOPStream.write(DataText.getText().toString().getBytes());
				fileOPStream.close();
				DataText.setText("");
				appData.setText("File created in applications internal memory with name MyappData.txt");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	private OnClickListener readFileClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) 
		{
			try {
				String readString = "no data";
				FileInputStream fileIPStream = openFileInput(fileName);
				InputStreamReader reader = new InputStreamReader(fileIPStream);
				StringBuilder outputString = new StringBuilder();
				char[] ipBuffer = new char[2048];
				int l;
				try {
					while ((l = reader.read(ipBuffer)) != -1)
					{
						outputString.append(ipBuffer,0,l);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				readString = outputString.toString();
				appData.setText(readString);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	private OnClickListener deleteFileClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) 
		{
			deleteFile(fileName);
			appData.setText("file deleted");
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.internal_mem_example, menu);
		return true;
	}

}
