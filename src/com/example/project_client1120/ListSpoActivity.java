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
import com.example.pojo.Sport;
import com.example.utils.Constant;
import com.example.utils.SpoAdapter;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ListSpoActivity extends Activity {
	ProgressBar pb;
	int uid;
	List<Sport> list=new ArrayList<Sport>();
	SpoAdapter adapter;
	ListView lv_ic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_spo);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		lv_ic = (ListView) findViewById(R.id.lv_userspo);

		pb=(ProgressBar) findViewById(R.id.inv_pro);
		TextView  tvback= (TextView) findViewById(R.id.tv_back);
		Intent intent=getIntent();
		uid=intent.getIntExtra("uid", 0);
	     tvback.setOnClickListener(new OnClickListener() {
	 		
	 		@Override
	 		public void onClick(View arg0) {
	 			// TODO Auto-generated method stub
	 			
	 			finish();
	 			
	 			
	 		}
	 	});
			pb.setVisibility(View.VISIBLE);

	     FinalHttp finalHttp=new FinalHttp();
			AjaxParams params=new AjaxParams();
			
			params.put("uid",uid+"");
			params.put("flag", Constant.USERSPORT+"");
			
			String	 url="http://123.56.95.134:8080/Testlife/servlet/ServletUser";
			
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

					System.out.println(value+"-----------vvvvvv");

					
					JSONArray jsonArray=new JSONArray();
					jsonArray=responsejson.getJSONArray("allspo");
					System.out.println(jsonArray.toString()+"---");
					HttpJson httpJson=HttpJson.getinstance();
					
					list.clear();
					  list=httpJson.getUserSport(jsonArray);
						System.out.println(list.get(0).getTime()+"------------------");
					    adapter=new SpoAdapter(list, getApplicationContext());
						adapter.notifyDataSetChanged();
						lv_ic.setAdapter(adapter);
					}else  {	
						pb.setVisibility(View.GONE);
						Toast.makeText(getApplicationContext(), R.string.notitle, Toast.LENGTH_SHORT).show();


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
			pb.setVisibility(View.GONE);
			super.onFailure(t, errorNo, strMsg);
	Toast.makeText(getApplicationContext(), R.string.wangluo, Toast.LENGTH_SHORT).show();
	}
		
	});
	     
	     
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list_spo, menu);
		return true;
	}

}
