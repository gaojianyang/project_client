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
import com.example.utils.Constant;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UserInfoActivity extends Activity implements OnClickListener{

	private TextView tvname;
	private ImageView tvgender;
	private TextView tvschool;
	private TextView tvlevel;
	private TextView tvfans;
	private TextView tvseeclub;
	private TextView tvseeinv;
	private TextView tvsend;
	private TextView tvguanzhu;
	private TextView tvperson;
	private TextView tvout;
	private TextView tvback;
	private TextView tvseespo;
	
	User user;
   int uid;
String url="http://123.56.95.134:8080/Testlife/servlet/ServletUser";


int type,invid;
private ImageView im_mehead;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
		Intent intent=getIntent();
		uid=intent.getIntExtra("uid", 0);
		System.out.println(uid+"uid_--------");
		type=intent.getIntExtra("type", 0);
		
		tvback=(TextView) findViewById(R.id.tv_back);

		im_mehead = (ImageView) findViewById(R.id.im_mehead);
		tvname = (TextView) findViewById(R.id.tv_name3);
		tvgender=(ImageView) findViewById(R.id.tv_gender);
		tvperson=(TextView) findViewById(R.id.tv_person);
		tvschool=(TextView) findViewById(R.id.tv_school);
		tvlevel=(TextView) findViewById(R.id.tv_level);
		tvfans=(TextView) findViewById(R.id.tv_fans);
		tvseeclub=(TextView) findViewById(R.id.tv_seeclub);
		tvseeinv=(TextView) findViewById(R.id.tv_seeinv);
		tvseespo=(TextView) findViewById(R.id.tv_seespo);
		tvsend=(TextView) findViewById(R.id.tv_send);
		tvguanzhu= (TextView) findViewById(R.id.tv_guanzhu);
		tvout= (TextView) findViewById(R.id.tv_out);
		if(type==1||type==2){
			invid=intent.getIntExtra("invid", 0);
			tvout.setVisibility(View.VISIBLE);
			tvout.setClickable(true);
		}else{}
		tvschool.setText("");
		tvname.setText("");
		
		tvfans.setText("");
		tvperson.setText("");
		tvlevel.setText("");
		tvseeclub.setOnClickListener(this);
		tvseeinv.setOnClickListener(this);
		tvseespo.setOnClickListener(this);
		tvsend.setOnClickListener(this);
		tvfans.setOnClickListener(this);
		tvguanzhu.setOnClickListener(this);
		tvback.setOnClickListener(this);

		FinalHttp finalHttp = new FinalHttp();
		AjaxParams params = new AjaxParams();
		params.put("uid",uid+"");
System.out.println(uid+"---------------");
		params.put("flag", Constant.QUERYUSERID+"");

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
					System.out.println(responsejson.toString()+"lllllllllsssssss");
					
					if (value == true) {
						HttpJson httpJson = HttpJson.getinstance();
						 user = httpJson.getUser(responsejson);
						 tvname.setText(user.getName());
							tvschool.setText(user.getCollege());
							System.out.println(tvschool + "-----------vvvvvv");
							
							if(user.getGender().equals("男")){
								tvgender.setImageResource(R.drawable.boy);
							}else{tvgender.setImageResource(R.drawable.girl);}
							
						
							tvfans.setText(user.getGuanzhucount()+"");
							System.out.println(user.getGuanzhucount()+"fanscount");
							tvperson.setText(user.getPersonal());
							tvlevel.setText(user.getLevel());
							im_mehead.setImageResource(AllStatic.images[user.getHead()]);
					} else {
						


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
				finish();
			}

		});
		tvout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				FinalHttp finalHttp = new FinalHttp();
				AjaxParams params = new AjaxParams();
				params.put("uid",uid+"");
				
				if(type==1){
				params.put("invid",invid+"");

				params.put("flag", Constant.QUXIAOINV+"");}
				else if(type==2){
					params.put("invid",invid+"");
					params.put("flag", Constant.QUITSPORT+"");
				}
				

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
							System.out.println(responsejson.toString()+"lllllllllsssssss");
							
							if (value == true) {
								Toast.makeText(getApplicationContext(), R.string.tiuser,
										Toast.LENGTH_SHORT).show();
								tvout.setVisibility(View.INVISIBLE);
								tvout.setClickable(false);

								
							} else {
								tvout.setVisibility(View.INVISIBLE);
								tvout.setClickable(false);

								Toast.makeText(getApplicationContext(), R.string.noin,
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
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_user_info, menu);
		return true;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if ((keyCode == KeyEvent.KEYCODE_BACK) ) {
//			返回上一个打开的页面
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}
//@Override
//public boolean onMenuItemSelected(int featureId, MenuItem item) {
//	// TODO Auto-generated method stub
//	if(item.getItemId()==android.R.id.home){
//		
//		finish();
//	
//	}
//	
//	return super.onMenuItemSelected(featureId, item);
//}

@Override
public void onClick(View arg0) {
	// TODO Auto-generated method stub
	switch (arg0.getId()) {
	case R.id.tv_seeclub:
		
		Intent intent4=new Intent(getApplicationContext(),ListInvCActivity.class);
		intent4.putExtra("uid", uid);
		intent4.putExtra("type", 2);
		startActivity(intent4);
			
			break;
	case R.id.tv_seeinv:
			Intent intent3=new Intent(getApplicationContext(),ListInvCActivity.class);
			intent3.putExtra("uid", uid);

			intent3.putExtra("type", 1);
			startActivity(intent3);
		
		
			break;
	case R.id.tv_seespo:
		Intent intent8=new Intent(getApplicationContext(),ListSpoActivity.class);
		intent8.putExtra("uid", uid);

		startActivity(intent8);
	
	
		break;
case R.id.tv_send:
	
	if(AllStatic.loginConfig.getId()==user.getUid()){
		Toast.makeText(getApplicationContext(), R.string.zilian,
				Toast.LENGTH_SHORT).show();
	}else{
	
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
		
		
		params.put("fid",uid+"");

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
					System.out.println(value + "-----------vvvvvv");
					System.out.println(responsejson.toString()+"lllllllllsssssss");
					
					if (value == true) {
						Toast.makeText(getApplicationContext(),"关注成功",
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

case R.id.tv_fans:
	Intent intent=new Intent(getApplicationContext(),ListUserActivity.class);
	intent.putExtra("type", 1);
	intent.putExtra("uid", uid);
	startActivity(intent);
	System.out.println("jieshule ");
	finish();
	
	break;
case R.id.tv_back:
	finish();
	break;


	default:
		break;
	}
}

}
