package com.example.project_client1120;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class AdminActivity extends Activity implements OnClickListener{

	private TextView zinv;
	private TextView zspo;
	private TextView zuser;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		
		zinv = (TextView) findViewById(R.id.zinv);
		zspo = (TextView) findViewById(R.id.zspo);
		zuser = (TextView) findViewById(R.id.zuser);
		zinv.setOnClickListener(this);
		zspo.setOnClickListener(this);
		zuser.setOnClickListener(this);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_admin, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.zinv:
			Intent intent=new Intent(getApplicationContext(),AdinvActivity.class);
			intent.putExtra("type", 1);
			startActivity(intent);
			
			
			break;
case R.id.zspo:
	Intent intent2=new Intent(getApplicationContext(),AdinvActivity.class);
	intent2.putExtra("type", 2);
	startActivity(intent2);
	
			break;
case R.id.zuser:
	Intent intent0=new Intent(this,SeaUserActivity.class);
	intent0.putExtra("type", 1);
	startActivity(intent0);
	
	
	break;

		default:
			break;
		}
		
		
		
	}

}
