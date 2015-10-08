package com.example.project_client1120;



import android.os.Bundle;


import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {
//	private CustomeProgressDialog progressDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//      startProgressDialog();
	new Handler().postDelayed(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MainActivity.this,LoginActivity.class);
			startActivity(intent);
			finish();
		
		}
	}, 1000);
	
	
	}
//	 private void startProgressDialog(){
//	        if (progressDialog == null){
//	            progressDialog = CustomeProgressDialog.createDialog(this);
//	            progressDialog.setMessage("正在加载中...");
//	        }
//	         
//	        progressDialog.show();
//	    }
//	     
//	    private void stopProgressDialog(){
//	        if (progressDialog != null){
//	            progressDialog.dismiss();
//	            progressDialog = null;
//	        }
//	    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
