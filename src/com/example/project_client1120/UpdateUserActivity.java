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
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateUserActivity extends Activity implements OnClickListener{
	private TextView tvname;
	private EditText tvschool;
	private EditText tvlevel;
	private TextView tvfans;
	private TextView tvseeinv;
	private TextView tvsend;
	private TextView tvgender;
	private TextView tvguanzhu;
	private EditText tvperson;
	String url="http://123.56.95.134:8080/Testlife/servlet/ServletUser";
	 public static final int REQUSET = 2;
int head;
	
	int type,invid;
	private ImageView im_mehead;
	String person,level,school;
	LoginConfig loginConfig;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_user);
		
		loginConfig=AllStatic.loginConfig;
		im_mehead = (ImageView) findViewById(R.id.im_mehead);
		tvname = (TextView) findViewById(R.id.tv_name3);
		tvperson=(EditText) findViewById(R.id.tv_person);
		tvschool=(EditText) findViewById(R.id.tv_school);
		tvlevel=(EditText) findViewById(R.id.tv_level);
		tvgender=(TextView) findViewById(R.id.tv_gender);
		tvfans=(TextView) findViewById(R.id.tv_fans);
		tvseeinv=(TextView) findViewById(R.id.tv_uppass);
		tvsend=(TextView) findViewById(R.id.tv_back);
		tvguanzhu= (TextView) findViewById(R.id.tv_tijiao);
        head=loginConfig.getHead();
        im_mehead.setImageResource(AllStatic.images[AllStatic.loginConfig.getHead()]);
		tvname.setText(loginConfig.getName());
		tvschool.setText(loginConfig.getSchool());
		tvgender.setText(loginConfig.getGender());
		tvfans.setText("点击查看");
		tvperson.setText(loginConfig.getPersonal());
		tvlevel.setText(loginConfig.getLevel());
		tvseeinv.setOnClickListener(this);
		tvsend.setOnClickListener(this);
		tvfans.setOnClickListener(this);
		tvguanzhu.setOnClickListener(this);
im_mehead.setOnClickListener(this);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_update_user, menu);
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		

		 if (requestCode == UpdateUserActivity.REQUSET && resultCode == RESULT_OK) {  
	                  head=data.getIntExtra(HeadActivity.KEY_USER_HEAD,1);
	                  System.out.println(head+"_----------------------------");
	                  im_mehead.setImageResource(AllStatic.images[head]);    
	        }  
		
	}
	

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.tv_fans:
			
			Intent intent2=new Intent(getApplicationContext(),ListUserActivity.class);
			intent2.putExtra("type", 1);
			intent2.putExtra("uid",loginConfig.getId());
			startActivity(intent2);
			
			break;
	case R.id.tv_uppass:
		Intent intent=new Intent(getApplicationContext(),UpPassActivity.class);
		startActivity(intent);
		
		
			break;
	case R.id.tv_back:
		finish();
		
		break;
	case R.id.tv_tijiao:
		
		person=tvperson.getText().toString();
		level=tvlevel.getText().toString();
		school=tvschool.getText().toString();
		if(!StringUtils.empty(person)&&!StringUtils.empty(level)&&!StringUtils.empty(school)){

			FinalHttp finalHttp = new FinalHttp();
			AjaxParams params = new AjaxParams();
		
			params.put("flag", Constant.UPDATEUSER + "");
				params.put("level", level);
				params.put("head", head+"");
				params.put("college", school+"");
				params.put("uid", loginConfig.getId()+"");
				params.put("pass", loginConfig.getPass());
		
			String url = "http://10.2.16.9:8080/Testlife/servlet/ServletUser";

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
							loginConfig.setHead(head);
							loginConfig.setLevel(level);
							loginConfig.setSchool(school);
							loginConfig.setPersonal(person);
							supportDAO supportDAO=new supportDAO(getApplicationContext());
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
		}else{
			Toast.makeText(getApplicationContext(), R.string.noemptyh,
					Toast.LENGTH_SHORT).show();
		}
		break;
	case R.id.im_mehead:
		Intent intent3=new Intent(getApplicationContext(),HeadActivity.class);
		startActivityForResult(intent3, REQUSET);
		break;
	default:
			break;
		}
	}
		
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.guanli:
			

			FinalHttp finalHttp = new FinalHttp();
			AjaxParams params = new AjaxParams();
		
			params.put("flag", Constant.ADMINUSER + "");
				params.put("uid", loginConfig.getId()+"");
		
			String url = "http://10.2.16.34:8080/Testlife/servlet/ServletUser";

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
							Intent intent =new Intent(getApplicationContext(),AdminActivity.class);
							startActivity(intent);
							
							
						} else {
							Toast.makeText(getApplicationContext(),"你不是管理员，别点了",
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
			
			
			break;

}	
		
		return super.onMenuItemSelected(featureId, item);}
		
		
	

}
