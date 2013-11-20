package com.example.androidsqliteexample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ExternalMemExample extends Activity {

	Button createFileButton,readFileButton,deleteFileButton;
	EditText DataText;
	TextView appData;
	String fileName;
	boolean externalAvailable = false, externalWriteable = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_external_mem_example);
		
		DataText = (EditText) findViewById(R.id.DataText);
		fileName = "MyappData.txt";
		createFileButton = (Button) findViewById(R.id.createFile);
		readFileButton = (Button) findViewById(R.id.ReadFile);
		deleteFileButton = (Button) findViewById(R.id.deleteFile);
		appData = (TextView) findViewById(R.id.appData);
		
		createFileButton.setOnClickListener(createFileClickListener);
		readFileButton.setOnClickListener(readFileClickListener);
		deleteFileButton.setOnClickListener(deleteFileClickListener);
		
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) 
		{
			// HERE MEDIA IS BOTH AVAILABLE AND WRITEABLE
			externalAvailable = true;
			externalWriteable = true;
		} else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			// HERE SD CARD IS AVAILABLE BUT NOT WRITEABLE
			externalAvailable = true;
		} else {
			// HERE FAILURE COULD BE RESULT OF MANY SITUATIONS
			// NO OP
		}
		
	}

	private OnClickListener createFileClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) 
		{
			if (externalAvailable && externalWriteable) {
				// FOR API LEVEL 7 AND BELOW
				// RETRIEVE SD CARD DIRECTORY
				File directory = Environment.getExternalStorageDirectory();
				File file = new File(directory, fileName);
				try
				{
					FileWriter fileWriter = new FileWriter(file);
					BufferedWriter out = new BufferedWriter(fileWriter);
					out.write(DataText.getText().toString());
					out.close();
					
					appData.setText("File created in external memory card with name MyappData.txt"+"\n"+
					"file directory : "+directory);
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			else 
			{
				appData.setText("SD CARD UNAVAILABLE");
			}
		}
	};
	
	private OnClickListener readFileClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) 
		{
			if (externalAvailable) 
			{
				File directory = Environment.getExternalStorageDirectory();
				File file = new File(directory, fileName);
				String fileText;
				BufferedReader br = null;
				try {
					FileReader fileReader = new FileReader(file);
					br = new BufferedReader(fileReader);
					while ((fileText = br.readLine()) != null) {
						appData.setText(fileText);
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					try {
						if (br != null)br.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
			else 
			{
				appData.setText("SD CARD UNAVAILABLE");
			}
		}
	};
	
	private OnClickListener deleteFileClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) 
		{
			File directory = Environment.getExternalStorageDirectory();
			File file = new File(directory, fileName);
			boolean deleted = file.delete();
			if(deleted)
			{
				Toast.makeText(getApplicationContext(), "file deleted", Toast.LENGTH_SHORT).show();
				appData.setText("");
			}
		}
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.external_mem_example, menu);
		return true;
	}

}
