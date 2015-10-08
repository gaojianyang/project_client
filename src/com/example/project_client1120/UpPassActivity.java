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
import com.example.utils.StringUtils;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpPassActivity extends Activity {
EditText etyuanshi;
EditText etxiugai;
EditText etqueren;
private TextView et_jiao;
private TextView et_back;
LoginConfig loginConfig;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_up_pass);
		
		etyuanshi=(EditText) findViewById(R.id.et_yuanshi);
		etxiugai=(EditText) findViewById(R.id.et_xiugai);
		etqueren=(EditText) findViewById(R.id.et_queren);
		loginConfig=AllStatic.loginConfig;

		et_jiao = (TextView) findViewById(R.id.tv_passjiao);
		et_back = (TextView) findViewById(R.id.tv_passback);
		et_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		
		et_jiao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String ypass=etyuanshi.getText().toString();
				final String xpass=etxiugai.getText().toString();
				String qpass=etqueren.getText().toString();
				if(StringUtils.empty(ypass)||StringUtils.empty(ypass)){
					Toast.makeText(getApplicationContext(), R.string.noempty, Toast.LENGTH_SHORT).show();
				}else if(!xpass.equals(qpass)){
					Toast.makeText(getApplicationContext(), R.string.buyiyang, Toast.LENGTH_SHORT).show();
					etxiugai.setText("");
					etqueren.setText("");
				}else{
					FinalHttp finalHttp = new FinalHttp();
					AjaxParams params = new AjaxParams();
				
					params.put("flag", Constant.UPDATEUSER + "");
						params.put("level", loginConfig.getLevel());
						params.put("college", loginConfig.getSchool());
						params.put("head", loginConfig.getHead()+"");
						params.put("uid", loginConfig.getId()+"");
						params.put("pass", xpass);
				
					String url = "http://123.56.95.134:8080/Testlife/servlet/ServletUser";

					finalHttp.post(url, params, new AjaxCallBack<Object>() {
						@Override
						public void onSuccess(Object t) {
							// TODO Auto-generated method stub
							super.onSuccess(t);
							JSONObject responsejson;
							try {
								boolean value;
								responsejson = new JSONObject(t.toString());
								value = responsejson.getBoolean("state");
								System.out.println(value + "-----------vvvvvv");
								if (value == true) {
									supportDAO supportDAO=new supportDAO(getApplicationContext());
									
									loginConfig.setPass(xpass);
									supportDAO.saveLoginConfig(loginConfig);
									AllStatic.loginConfig=loginConfig;
									Toast.makeText(getApplicationContext(), R.string.upok,
											Toast.LENGTH_SHORT).show();
								} else {
									Toast.makeText(getApplicationContext(), R.string.noman,
											Toast.LENGTH_SHORT).show();
								}
							} catch (JSONException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}

						@Override
						public void onFailure(Throwable t, int errorNo, String strMsg) {
							// TODO Auto-generated method stub
							super.onFailure(t, errorNo, strMsg);

							Toast.makeText(getApplicationContext(), R.string.wangluo,
									Toast.LENGTH_SHORT).show();
						}
					});
					
					
					
					
					
				}
				
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_up_pass, menu);
		return true;
	}}

