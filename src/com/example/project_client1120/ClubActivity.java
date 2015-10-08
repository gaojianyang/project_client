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
import com.example.UI.PopMenu;
import com.example.pojo.Club;
import com.example.utils.ClubAdapter;
import com.example.utils.Constant;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ClubActivity extends Activity  {
	long totalcount;
	int count;
	int page=1;
	  ListView lv_inv ;
	List<Club> list=new ArrayList<Club>();
	ClubAdapter adapter;
	ProgressBar pb;
	private TextView tv_back;
	 private ImageView add;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_club);
		add = (ImageView) findViewById(R.id.im_ada);
		tv_back = (TextView) findViewById(R.id.tv_back);
		tv_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				PopMenu menu=new PopMenu(getApplicationContext());
				menu.addItem("创建社团");
				menu.addItem("查找社团");
				menu.showAsDropDown(add);
				menu.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
					
					switch (arg2) {
					case 0:
						Intent intent=new Intent(getApplicationContext(),CreateClubActivity.class);
						startActivity(intent);
						break;
					case 1:
						
						Intent intent2=new Intent(getApplicationContext(),SearchActivity.class);
						intent2.putExtra("type", 3);
						startActivity(intent2);
						break;
					default:
						break;
					}
					
					
					}
				});
				
				
				
			}
		});
		pb=(ProgressBar) findViewById(R.id.club_pro);

		lv_inv = (ListView) findViewById(R.id.lv_clubl);
		TextView but_pre = (TextView) findViewById(R.id.but_invpre);
		TextView but_next = (TextView) findViewById(R.id.but_invnext);
		ImageView but_refresh = (ImageView) findViewById(R.id.but_refresh);
	
	returnList(page);
	
		but_pre.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(page>1){
					page=page-1;
					returnList(page);
		
				}else{
					Toast.makeText(getApplicationContext(),"这是第一页", Toast.LENGTH_SHORT).show();

				}
		     }
		});
        but_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if(page<count){
				page=page+1;
				System.out.println(page+"pppppppp");
					returnList(page);
				}else{
					Toast.makeText(getApplicationContext(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
			
				}
		}
		});
       but_refresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				returnList(page);
			}
		});
		
	lv_inv.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(getApplicationContext(),ClubinfoActivity.class);
			intent.putExtra("club", list.get(arg2));
//			AllStatic.club=list.get(arg2);
			startActivity(intent);
			
		}
	});
		
		
		}
		
	
	
	public void returnList(int page){
		pb.setVisibility(View.VISIBLE);

		list.clear();
		FinalHttp finalHttp=new FinalHttp();
		AjaxParams params=new AjaxParams();
		params.put("page",page+"");
		params.put("flag", Constant.ALLCLUB+"");
	  String url="http://123.56.95.134:8080/Testlife/servlet/ServletClub";
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
				System.out.println(value+"-----------vvvvvv");

				totalcount=responsejson.getLong("count");
				
				if(totalcount<21){count=1;}else{
					if(totalcount%20>0){
						count=(int) (totalcount/20+1);
					}else{count=(int) (totalcount/20);
					}	}
				
				JSONArray jsonArray=new JSONArray();
				jsonArray=responsejson.getJSONArray("allclub");
				HttpJson httpJson=HttpJson.getinstance();
			
				  list=httpJson.getUserClub(jsonArray);
				  adapter=new ClubAdapter(list, getApplicationContext());
					adapter.notifyDataSetChanged();
					lv_inv.setAdapter(adapter);
					pb.setVisibility(View.GONE);

				}else  {	
					Toast.makeText(getApplicationContext(), R.string.notitle, Toast.LENGTH_SHORT).show();

					pb.setVisibility(View.GONE);
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
Toast.makeText(getApplicationContext(), R.string.wangluo, Toast.LENGTH_SHORT).show();
pb.setVisibility(View.GONE);

	
	}
	
});
		
	
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_club, menu);

		return true;
	}
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if ((keyCode == KeyEvent.KEYCODE_BACK) ) {
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}





}
