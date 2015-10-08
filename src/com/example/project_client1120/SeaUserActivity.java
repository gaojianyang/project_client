package com.example.project_client1120;

import net.tsz.afinal.FinalDb;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.Http.HttpJson;
import com.example.pojo.User;
import com.example.support.AllStatic;
import com.example.support.supportDAO;
import com.example.utils.Constant;
import com.example.utils.StringUtils;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SeaUserActivity extends Activity implements OnClickListener{
	private TextView tvname;
	private ImageView tvgender;
	private TextView tvschool;
	private TextView tvlevel;
	private TextView tvfans;
	private TextView tvseeclub;
	private TextView tvseeinv;
	private TextView tvsend;
	private TextView tvadmin;
	private TextView tvguanzhu;
	private TextView tvseauser;
	private TextView tvperson;
	private ImageView im_mehead;
	private LinearLayout lluser;
	private TextView tvback;

	
String url="http://123.56.95.134:8080/Testlife/servlet/ServletUser";
	User user;
	private EditText et_username;
	supportDAO sd;
	ProgressDialog pd;
	int type;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sea_user);
	
		
		Intent intent=getIntent();
		type=intent.getIntExtra("type", 0);
		
		tvback=(TextView) findViewById(R.id.tv_back);

		et_username = (EditText) findViewById(R.id.et_username);
		im_mehead = (ImageView) findViewById(R.id.im_mehead);
		tvname = (TextView) findViewById(R.id.tv_name3);
		tvgender=(ImageView) findViewById(R.id.tv_gender);
		tvseauser=(TextView) findViewById(R.id.tv_seauser);
		tvadmin=(TextView) findViewById(R.id.tv_admin);
		tvperson=(TextView) findViewById(R.id.tv_person);
		tvschool=(TextView) findViewById(R.id.tv_school);
		tvlevel=(TextView) findViewById(R.id.tv_level);
		tvfans=(TextView) findViewById(R.id.tv_fans);
		tvseeclub=(TextView) findViewById(R.id.tv_seeclub);
		tvseeinv=(TextView) findViewById(R.id.tv_seeinv);
		tvsend=(TextView) findViewById(R.id.tv_send);
		tvguanzhu= (TextView) findViewById(R.id.tv_guanzhu);
		
		lluser=(LinearLayout) findViewById(R.id.ll_user);
		tvseeclub.setOnClickListener(this);
		tvseeinv.setOnClickListener(this);
		tvsend.setOnClickListener(this);
		tvfans.setOnClickListener(this);
		tvguanzhu.setOnClickListener(this);
		tvback.setOnClickListener(this);

		sd=new supportDAO(this);
		pd=sd.myProgressD();
		if(type==1){
			
			tvadmin.setVisibility(View.VISIBLE);
			tvadmin.setClickable(true);
			tvadmin.setOnClickListener(this);
			
		}
	
	tvseauser.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			pd.show();
			FinalHttp finalHttp = new FinalHttp();
			AjaxParams params = new AjaxParams();
			
			
			String name=et_username.getText().toString();
			if(StringUtils.empty(name)){
				Toast.makeText(getApplicationContext(), R.string.noempty, Toast.LENGTH_SHORT).show();
				
				
			}else{
			params.put("name",name+"");

			params.put("flag", Constant.QUERYUSER+"");

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
						
						if (value == true) {
							lluser.setVisibility(View.VISIBLE);
							HttpJson httpJson = HttpJson.getinstance();
							    user = httpJson.getUser(responsejson);
							    tvname.setText(user.getName());
								tvschool.setText(user.getCollege());
								if(user.getGender().equals("男")){
								tvgender.setImageResource(R.drawable.boy);}else{
									tvgender.setImageResource(R.drawable.girl);
								}
								tvfans.setText(user.getGuanzhucount()+"");
								tvperson.setText(user.getPersonal());
								tvlevel.setText(user.getLevel());
								im_mehead.setImageResource(AllStatic.images[user.getHead()]);
						} else {
							Toast.makeText(getApplicationContext(), R.string.notitle, Toast.LENGTH_SHORT).show();
						}
						pd.dismiss();
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	
					
				}

				@Override
				public void onFailure(Throwable t, int errorNo, String strMsg) {
					// TODO Auto-generated method stub
					super.onFailure(t, errorNo, strMsg);
					pd.dismiss();
					Toast.makeText(getApplicationContext(), R.string.wangluo,
							Toast.LENGTH_SHORT).show();
					finish();
				}

			});}
			
			
		}
	});
	
	
	
	
	}
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_sea_user, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()==android.R.id.home){
			
			finish();
			
		}
		
		
		
		
		return super.onMenuItemSelected(featureId, item);
	}




	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch (arg0.getId()) {
		case R.id.tv_fans:
			
			Intent intent2=new Intent(getApplicationContext(),ListUserActivity.class);
			intent2.putExtra("type", 1);
			intent2.putExtra("uid", user.getUid());
			
			break;
		
		
	case R.id.tv_seeclub:
			
		Intent intent4=new Intent(getApplicationContext(),ListInvCActivity.class);
		intent4.putExtra("type", 2);
		intent4.putExtra("uid", user.getUid());

		startActivity(intent4);
			
			break;
	case R.id.tv_seeinv:
			Intent intent3=new Intent(getApplicationContext(),ListInvCActivity.class);
			intent3.putExtra("type", 1);
			intent3.putExtra("uid", user.getUid());
			startActivity(intent3);
		
			break;
		
		
	case R.id.tv_send:
		if(AllStatic.loginConfig.getId()==user.getUid()){
			Toast.makeText(getApplicationContext(), R.string.zilian,
					Toast.LENGTH_SHORT).show();}else{
		Intent intent=new Intent(getApplicationContext(),ChatActivity.class);
		intent.putExtra("type", 0);
		intent.putExtra("user", user);
		startActivity(intent);}
		break;
	case R.id.tv_guanzhu:
		if(AllStatic.loginConfig.getId()==user.getUid()){
			Toast.makeText(getApplicationContext(), R.string.zilian,
					Toast.LENGTH_SHORT).show();
		}else{

			FinalHttp finalHttp = new FinalHttp();
			AjaxParams params = new AjaxParams();
			params.put("uid",AllStatic.loginConfig.getId()+"");
			params.put("fid",user.getUid()+"");

			params.put("flag", Constant.GUANZHUUSER+"");
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
						if (value == true) {
							Toast.makeText(getApplicationContext(), R.string.tiuser,
									Toast.LENGTH_SHORT).show();
							FinalDb db=FinalDb.create(getApplicationContext());
							db.save(user);
							tvguanzhu.setVisibility(View.INVISIBLE);
							
						} else {
							Toast.makeText(getApplicationContext(), R.string.shibaile,
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
		
		break;	
		
	case R.id.tv_admin:
		FinalHttp finalHttp = new FinalHttp();
		AjaxParams params = new AjaxParams();
		params.put("uid",AllStatic.loginConfig.getId()+"");
		params.put("fid",user.getUid()+"");

		params.put("flag", Constant.ADMINUSER+"");
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
					if (value == true) {
						Toast.makeText(getApplicationContext(),"他现在是管理员了",
								Toast.LENGTH_SHORT).show();
						tvadmin.setVisibility(View.INVISIBLE);
					} else {
						Toast.makeText(getApplicationContext(),"他已经是了",
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
	case R.id.tv_back:
		finish();
		break;
		
	}}

}
