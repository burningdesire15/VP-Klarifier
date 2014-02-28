package com.example.homescreen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import com.example.draweractivity.R;;

public class HomeScreen extends Activity
{
	Button bt,bt2,bt3;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homescreen);
		bt=(Button) findViewById(R.id.bLogin);
		bt2=(Button) findViewById(R.id.button2);
		bt3=(Button) findViewById(R.id.bContinue);
		Typeface tf=Typeface.createFromAsset(this.getAssets(),"fonts/Imprima-Regular.ttf");
		bt.setTypeface(tf);
		bt2.setTypeface(tf);
		bt3.setTypeface(tf);
		
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(),com.example.draweractivity.HomeActivity.class);
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

}
