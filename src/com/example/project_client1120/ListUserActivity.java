package com.example.project_client1120;

import java.util.ArrayList;

import java.util.List;


import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.Http.HttpJson;
import com.example.pojo.User;
import com.example.utils.ClubUserAdapter;
import com.example.utils.Constant;

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
import android.widget.TextView;
import android.widget.Toast;

public class ListUserActivity extends Activity {

	private ListView lv_user;
	int type;
	int uid;
	List<User> list=new ArrayList<User>();
	ClubUserAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.activity_list_user);
		TextView  tvback= (TextView) findViewById(R.id.tv_back);	
	     tvback.setOnClickListener(new OnClickListener() {
	 		
	 		@Override
	 		public void onClick(View arg0) {
	 			// TODO Auto-generated method stub
	 			
	 			finish();
	 			
	 			
	 		}
	 	});
		lv_user = (ListView) findViewById(R.id.lv_guanzhuuser);
		Intent intent=getIntent();
		type=intent.getIntExtra("type", 0);
		System.out.println(type+"-------");
	if(type==1){
		uid=intent.getIntExtra("uid", 0);
	}else{}
	FinalHttp finalHttp = new FinalHttp();
	AjaxParams params = new AjaxParams();
if(type==1){
	params.put("fid", uid + "");
	params.put("flag", Constant.USERFANS + "");}else if(type==2){
		params.put("flag", Constant.TOPUSER + "");
	}
	String url = "http://123.56.95.134:8080/Testlife/servlet/ServletUser";
	
	
	
	
	

	finalHttp.post(url, params, new AjaxCallBack<Object>() {
		@Override
		public void onSuccess(Object t) {
			// TODO Auto-generated method stub
			super.onSuccess(t);
			System.out.println(t.toString() + "-----------hahahha");
			JSONObject responsejson;
			try {
				boolean value;
				responsejson = new JSONObject(t.toString());
				value = responsejson.getBoolean("state");
			
				if (value == true) {
				JSONArray jsonArray = new JSONArray();
					jsonArray = responsejson.getJSONArray("userfans");
					HttpJson httpJson = HttpJson.getinstance();
					list.clear();
					list = httpJson.geteasyUserList(jsonArray);
					adapter = new ClubUserAdapter(list,
							getApplicationContext());
					adapter.notifyDataSetChanged();
					lv_user.setAdapter(adapter);
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

		
	lv_user.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(getApplicationContext(),UserInfoActivity.class);
			intent.putExtra("type", 0);
			intent.putExtra("uid", list.get(arg2).getUid());
			
			System.out.println(list.get(arg2).getUid());
			startActivity(intent);
			
			
		}
	});
	
		
		
		
		
		
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
//	@Override
//	public boolean onMenuItemSelected(int featureId, MenuItem item) {
//		// TODO Auto-generated method stub
//		if(item.getItemId()==android.R.id.home){
//			
//			finish();
//			
//		}
//		
//		
//		return super.onMenuItemSelected(featureId, item);
//	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list_user, menu);
		return true;
	}

}
