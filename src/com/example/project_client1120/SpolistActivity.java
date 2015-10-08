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
import com.example.pojo.Sport;
import com.example.utils.Constant;
import com.example.utils.SpoAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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

public class SpolistActivity extends Activity {
	long totalcount;
	int count;
	int page=1;
	 ListView lv_inv ;
		ProgressBar pb; 
	
	List<Sport> list=new ArrayList<Sport>();
	SpoAdapter adapter;
	private TextView tv_back;
	 private ImageView add;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spolist);
		
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
				menu.addItem("创建活动");
				menu.addItem("查找活动");
				menu.showAsDropDown(add);
				menu.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
					
					switch (arg2) {
					case 0:
						Intent intent=new Intent(getApplicationContext(),CreateSpoActivity.class);
						startActivity(intent);
						break;	
					case 1:
						Intent intent2=new Intent(getApplicationContext(),SearchActivity.class);
						intent2.putExtra("type", 2);
						startActivity(intent2);
						break;
					default:
						break;
					}
					
					
					}
				});
				
				
				
			}
		});

		pb=(ProgressBar) findViewById(R.id.inv_pro);

		lv_inv = (ListView) findViewById(R.id.lv_inv);
		TextView but_pre = (TextView) findViewById(R.id.but_invpre);
		TextView but_next = (TextView) findViewById(R.id.but_invnext);
		ImageView but_refresh = (ImageView) findViewById(R.id.but_refresh);
		returnList(page);
		
		lv_inv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(),SpoReplyActivity.class);
				intent.putExtra("spoid", list.get(arg2).getSpoid());
				intent.putExtra("suid", list.get(arg2).getUid());
//				AllStatic.sport=list.get(arg2);
				startActivity(intent);
					
					
			
			}
		});
		
		but_pre.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(page>1){
					page=page-1;
					returnList(page);
		
				}else{
					Toast.makeText(getApplicationContext(),R.string.firstpage, Toast.LENGTH_SHORT).show();

				}
		     }
		});
        but_next .setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if(page<count){
				page=page+1;
				System.out.println(page+"pppppppp");
					returnList(page);
				}else{
					Toast.makeText(getApplicationContext(), R.string.lastpage, Toast.LENGTH_SHORT).show();
			
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
		
	}
	public void returnList(int page){
		pb.setVisibility(View.VISIBLE);

			list.clear();
			FinalHttp finalHttp=new FinalHttp();
			AjaxParams params=new AjaxParams();
			params.put("page",page+"");
			params.put("flag", Constant.ALLSPORT+"");
				
			
			
			String	url="http://123.56.95.134:8080/Testlife/servlet/ServletSpo";
			
	         finalHttp.post(url, params, new AjaxCallBack<Object>() {		
		     @Override
		     public void onSuccess(Object t) {
		  	// TODO Auto-generated method stub
			  super.onSuccess(t);
			  System.out.println(t.toString()+"------------------");
			  JSONObject responsejson;
			 try {
				boolean value;
				responsejson = new JSONObject(t.toString());
				value = responsejson.getBoolean("state");
				if(value==true){
					pb.setVisibility(View.GONE);
					System.out.println(value+"-----------vvvvvv");
					totalcount=responsejson.getLong("count");
					if(totalcount<31){count=1;}else{
						if(totalcount%30>0){
							count=(int) (totalcount/30+1);
						}else{count=(int) (totalcount/30);
						}	}
					
					JSONArray jsonArray=new JSONArray();
					HttpJson httpJson=HttpJson.getinstance();
					jsonArray=responsejson.getJSONArray("allspo");
					
					 list=httpJson.getUserSport(jsonArray);
			   adapter=new SpoAdapter(list, getApplicationContext());
						lv_inv.setAdapter(adapter);
					
//
					System.out.println(list.size()+"time------------------");
					}else  {
						Toast.makeText(getApplicationContext(), R.string.notitle, Toast.LENGTH_SHORT).show();

						pb.setVisibility(View.GONE);
					}	

			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				  pb.setVisibility(View.GONE);
				  System.out.println("xxxxxxx");
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
		
	});}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_spolist, menu);
		return true;
	}
	

}
