package com.example.project_client1120;

import java.util.List;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.Http.HttpJson;
import com.example.pojo.User;
import com.example.support.AllStatic;
import com.example.utils.ClubUserAdapter;
import com.example.utils.Constant;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ClubUserActivity extends Activity {
	ListView lv_user;
	List<User> list;
	ClubUserAdapter adapter;
	int invid, iuid,type;
	ProgressBar pb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_club_user);
	
		lv_user = (ListView) findViewById(R.id.lv_clubuser);
		pb=(ProgressBar) findViewById(R.id.inv_pro);
		pb.setVisibility(View.VISIBLE);
		TextView  tvback= (TextView) findViewById(R.id.tv_back);	
	     tvback.setOnClickListener(new OnClickListener() {
	 		
	 		@Override
	 		public void onClick(View arg0) {
	 			// TODO Auto-generated method stub
	 			
	 			finish();
	 			
	 			
	 		}
	 	});
		Intent intent = getIntent();
		invid = intent.getIntExtra("invid", 0);
		iuid = intent.getIntExtra("iuid", 0);
		type = intent.getIntExtra("type", 0);
		

		lv_user.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						UserInfoActivity.class);
				if(type==1){
				intent.putExtra("uid", list.get(arg2).getUid());
				if (iuid == AllStatic.loginConfig.getId()) {
                     intent.putExtra("invid", invid);
					intent.putExtra("type", 1);
				} else {
					intent.putExtra("type", 0);
				}
				startActivity(intent);}
				else if(type==2){
					
					intent.putExtra("uid", list.get(arg2).getUid());

					if (iuid == AllStatic.loginConfig.getId()) {
	                     intent.putExtra("invid", invid);
						intent.putExtra("type", 1);
					} else {
						intent.putExtra("type", 0);
					}
					startActivity(intent);
				}
				
				
			}
		});

		FinalHttp finalHttp = new FinalHttp();
		AjaxParams params = new AjaxParams();
if(type==1){
		params.put("invid", invid + "");
		params.put("flag", Constant.INVUSER + "");}else if(type==2){
			params.put("sid", invid + "");
			params.put("flag", Constant.SPORTUSER + "");
		}
		String url = "http://10.2.16.19:8080/Testlife/servlet/ServletUser";

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
						JSONArray jsonArray = new JSONArray();
						jsonArray = responsejson.getJSONArray("user");
						HttpJson httpJson = HttpJson.getinstance();
						list = httpJson.geteasyUserList(jsonArray);
						adapter = new ClubUserAdapter(list,
								getApplicationContext());
						adapter.notifyDataSetChanged();
						lv_user.setAdapter(adapter);
					} else {
						Toast.makeText(getApplicationContext(), R.string.noman,
								Toast.LENGTH_SHORT).show();
					}
					pb.setVisibility(View.GONE);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);
				pb.setVisibility(View.GONE);
				Toast.makeText(getApplicationContext(), R.string.wangluo,
						Toast.LENGTH_SHORT).show();
			}
		});

	}
//	@Override
//	public boolean onMenuItemSelected(int featureId, MenuItem item) {
//		// TODO Auto-generated method stub
//		if(item.getItemId()==android.R.id.home){
//			
//			finish();
//			
//		}
//		return super.onMenuItemSelected(featureId, item);
//	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_club_user, menu);
		return true;
	}	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if ((keyCode == KeyEvent.KEYCODE_BACK) ) {
//			返回上一个打开的页面
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}

}
