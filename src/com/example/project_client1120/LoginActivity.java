package com.example.project_client1120;


import net.tsz.afinal.FinalHttp;





import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.pojo.LoginConfig;
import com.example.support.AllStatic;
import com.example.support.supportDAO;
import com.example.utils.Constant;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends Activity {
	int type=0;
	private EditText et_name;
	private EditText et_pass;
	private TextView login;
	private TextView register;
	String name, pass, url;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		et_name = (EditText) findViewById(R.id.b);
		et_pass = (EditText) findViewById(R.id.c);
		login = (TextView) findViewById(R.id.d);
		register = (TextView) findViewById(R.id.e);
	
//		LoginConfig config=new LoginConfig(1, 1, "aa", "nan", "22", "hah", "shadjlas", "sda", false);
//		AllStatic.loginConfig=config;
//		Intent intent3 = new Intent(this, TabhostActivity.class);
//		startActivity(intent3);
		Intent intent2=getIntent();
		type=intent2.getIntExtra("type", 0);
		if(type==0){
		supportDAO supportDAO = new supportDAO(this);
		if (!supportDAO.getLoginConfig().isIsfirst()) {
			AllStatic.loginConfig=supportDAO.getLoginConfig();
			Intent intent = new Intent(this, TabhostActivity.class);
			startActivity(intent);
			finish();
		}}
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
//
//				name = et_name.getText().toString();
//				pass = et_pass.getText().toString();
//				url = "http://123.56.95.134:8080/Testlife/servlet/ServletUser";
//				FinalHttp finalHttp = new FinalHttp();
//				AjaxParams params = new AjaxParams();
//				params.put("name", name);
//				params.put("pass", pass);
//				params.put("flag", Constant.LOGIN + "");
				// params.put("gender","nv");
				// params.put("college","ah");
				// params.put("head","1");
				// params.put("personal","tssssdfg");
//				finalHttp.post(url, params, new AjaxCallBack<Object>() {
//					@Override
//					public void onSuccess(Object t) {
//						// TODO Auto-generated method stub
//						super.onSuccess(t);
//						JSONObject responsejson;
//						try {
//							boolean value;
//							responsejson = new JSONObject(t.toString());
//							value = responsejson.getBoolean("state");
//							if (value == true) {
//								int id=responsejson.getInt("id");
//								int head=responsejson.getInt("head");
//								String college=responsejson.getString("college");
//								String name=responsejson.getString("name");
//								String gender=responsejson.getString("gender");
//								String personal=responsejson.getString("personal");
//								String level=responsejson.getString("level");
							  supportDAO supportDAO2 = new supportDAO(
										getApplicationContext());
								LoginConfig loginConfig = new LoginConfig(
										1,1,"aa","男", "aa", 
												"天津工业大学",
												"老师好","扬州", false);
								supportDAO2.saveLoginConfig(loginConfig);
								AllStatic.loginConfig=loginConfig;
								Toast.makeText(LoginActivity.this, "登入成功",
										Toast.LENGTH_SHORT).show();
								Intent intent = new Intent(LoginActivity.this,
										TabhostActivity.class);
								startActivity(intent);
								finish();
//							} else {
//								Toast.makeText(LoginActivity.this, "shibaile",
//										Toast.LENGTH_SHORT).show();
//							}
//						} catch (JSONException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//					}

//					@Override
//					public void onFailure(Throwable t, int errorNo,
//							String strMsg) {
//						// TODO Auto-generated method stub
//						super.onFailure(t, errorNo, strMsg);
//						Toast.makeText(LoginActivity.this, "shissss",
//								Toast.LENGTH_SHORT).show();
//
//					}
//
//				});
			}

		});

		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				Intent intent = new Intent(LoginActivity.this,
						RegActivity.class);
				startActivity(intent);
				System.out.println("------------s");
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

}
