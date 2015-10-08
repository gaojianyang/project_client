package com.example.project_client1120;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class XinShengActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xin_sheng);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_xin_sheng, menu);
		return true;
	}

}
