package com.example.project_client1120;



import org.json.JSONException;




import org.json.JSONObject;

import com.example.support.AllStatic;
import com.example.utils.Constant;
import com.example.utils.StringUtils;


import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class RegActivity extends Activity{

	private EditText et_pass;
	private EditText et_name;
	private EditText et_person;
	private EditText et_level;
	private Spinner sp_school;
	
	private RadioGroup sex_group;
	private TextView tv_reg;
	private TextView tv_head;
	private TextView tv_back;
	int head=1;
	ProgressBar pb;
	String gender="男";
	String school;
	String url="http://123.56.95.134:8080/Testlife/servlet/ServletUser";
	 public static final int REQUSET = 1;
	private ImageView im_head; 
	 
	 
	 @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		 if (requestCode == RegActivity.REQUSET && resultCode == RESULT_OK) {  
	                  head=data.getIntExtra(HeadActivity.KEY_USER_HEAD,1);
	                  im_head.setImageResource(AllStatic.images[head]);
	        }  
		
		
	}
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
	
		im_head = (ImageView) findViewById(R.id.imageView1);
		et_pass = (EditText) findViewById(R.id.c);
		et_name = (EditText) findViewById(R.id.b);
		et_person = (EditText) findViewById(R.id.h);
		et_level = (EditText) findViewById(R.id.hhome);
		sp_school =  (Spinner) findViewById(R.id.f);
		pb=(ProgressBar) findViewById(R.id.reg_pro);
		sex_group = (RadioGroup) findViewById(R.id.aaa);
		tv_reg = (TextView) findViewById(R.id.g);
		tv_back = (TextView) findViewById(R.id.j);
		tv_head = (TextView) findViewById(R.id.tv_choicehead);
       tv_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			finish();
			}
		});
		
		tv_head.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(),HeadActivity.class);
			startActivityForResult(intent,REQUSET);
			
			}
		});
		
		
		final String [] schoolarray=getResources().getStringArray(R.array.schoolname);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(RegActivity.this,R.layout.spinner, schoolarray);
		
		sp_school.setAdapter(adapter);
		sex_group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
			
				if(arg1==R.id.d){
					 gender="男";
				}else{
					gender="女";
							}
			}
		});
		sp_school.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				school=schoolarray[arg2];
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				school=schoolarray[0];					}
		});
		tv_reg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			 
				String name=et_name.getText().toString();
				String pass=et_pass.getText().toString();
				String personal=et_person.getText().toString();
				String level=et_level.getText().toString();
				if(StringUtils.empty(name)||StringUtils.empty(pass)){
					Toast.makeText(getApplicationContext(), "姓名或密码不能为空", Toast.LENGTH_SHORT).show();
					
			}else if(pass.length()<6){
				Toast.makeText(getApplicationContext(), "密码不能少于6位数", Toast.LENGTH_SHORT).show();

			}else {  pb.setVisibility(View.VISIBLE);
				tv_reg.setClickable(false);
				FinalHttp finalHttp=new FinalHttp();
				AjaxParams params=new AjaxParams();
				params.put("name", name);
				params.put("pass", pass);
				params.put("gender", gender);
				params.put("college", school);
				System.out.println(school+"========================");
				System.out.println(gender+"========================");
				params.put("personal", personal);
				params.put("level", level);
				params.put("flag", Constant.REGISTER+"");
				params.put("head", head+"");
				finalHttp.configCharset("utf-8");
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
							System.out.println(value+"-----------vvvvvv");
							if(value==true){
								pb.setVisibility(View.GONE);
								Toast.makeText(RegActivity.this, "注册成功了", Toast.LENGTH_SHORT).show();
								
								tv_reg.setClickable(true);
								finish();
								}else  {
									pb.setVisibility(View.GONE);
									tv_reg.setClickable(true);
								Toast.makeText(RegActivity.this, "注册失败，用户名或已存在", Toast.LENGTH_SHORT).show();
							}
						

						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					
					@Override
					public void onFailure(Throwable t, int errorNo,
							String strMsg) {
						// TODO Auto-generated method stub
						super.onFailure(t, errorNo, strMsg);
						Toast.makeText(RegActivity.this, R.string.wangluo, Toast.LENGTH_SHORT).show();
					pb.setVisibility(View.GONE);
					tv_reg.setClickable(true);

					}
				});}
			}
		});
	
		
		
	}

}
