package com.example.project_client1120;

import java.util.ArrayList;









import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import com.example.Http.HttpJson;
import com.example.UI.PopMenu;
import com.example.pojo.Invitation;

import com.example.support.AllStatic;
import com.example.utils.Constant;
import com.example.utils.InvlistAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class InvlistActivity extends Activity {
	long totalcount;
	int count;
	int page=1;
	  ListView lv_inv ;
int type=-1;//0,liaotian 1,tuijian 2,liuyan,3 jianyi
String	 url="http://123.56.95.134:8080/Testlife/servlet/ServletInv";
//http://123.56.95.134:8080/Testlife/servlet/ServletInv?page=1&flag=5
	List<Invitation> list=new ArrayList<Invitation>();
	
	InvlistAdapter adapter;
	ProgressBar pb;
	private TextView tv_back;
	 private ImageView add;
	 private ImageView user;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invlist);
		
		
		add = (ImageView) findViewById(R.id.im_ada);
		user = (ImageView) findViewById(R.id.im_user);
		tv_back = (TextView) findViewById(R.id.tv_back);
		tv_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		lv_inv = (ListView) findViewById(R.id.lv_inv);
		TextView but_pre = (TextView) findViewById(R.id.but_invpre);
		TextView but_next = (TextView) findViewById(R.id.but_invnext);
	
		pb=(ProgressBar) findViewById(R.id.inv_pro);
	
		ImageView but_refresh = (ImageView) findViewById(R.id.but_refresh);
		returnList(page);
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final PopMenu menu=new PopMenu(getApplicationContext());
				menu.addItem("创建帖子");
				menu.addItem("查找帖子");
				menu.showAsDropDown(add);
				menu.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
					
					switch (arg2) {
					case 0:
						menu.dismiss();
						Intent intent2 = new Intent(getApplicationContext(), CreateInvActivity.class);
						startActivity(intent2);
						break;
					case 1:
						menu.dismiss();

						Intent intent1=new Intent(getApplicationContext(),SearchActivity.class);
						intent1.putExtra("type", 1);
						startActivity(intent1);
						break;
					
					default:
						break;
					}
					}
				});
			
			}
		});
user.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final PopMenu menu=new PopMenu(getApplicationContext());
				
				
				
				menu.addItem("所有类型");
				menu.addItem("聊天类型");
				menu.addItem("留言板型");
				menu.addItem("周边推荐");
				menu.addItem("校园建议");
				menu.showAsDropDown(add);
				menu.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
					menu.dismiss();
					switch (arg2) {
					case 0:
					

						type=-1;
						page=1;
						returnList(page);
						break;
					case 1:
					

						type=0;
						page=1;
						returnList(page);
						break;
					case 2:
					

						type=2;
						page=1;
						returnList(page);
						break;
					case 3:
				
						page=1;
						type=1;
						returnList(page);
						break;
					case 4:
						type=3;
						page=1;
						returnList(page);
						break;
					default:
						break;
					}
					
					
					}
				});
				
				
				
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
		
	lv_inv.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(getApplicationContext(),InvReplyActivity.class);
			intent.putExtra("invid", list.get(arg2).getInvid());
			intent.putExtra("iuid", list.get(arg2).getIuid());
			intent.putExtra("title", list.get(arg2).getTitle());
//			AllStatic.inv=list.get(arg2);
			startActivity(intent);
			
		}
	});
		
		
		}
		
	
	
	public void returnList(int page){
	
		list.clear();
		Invitation inv=new Invitation(1,2, (int)Math.random()*30,"谷恒","1+1到底等于几啊",1,"19:15","2015.3.6",(short) 2,AllStatic.loginConfig.getId());
		list.add(inv);
//		FinalHttp finalHttp=new FinalHttp();
//		AjaxParams params=new AjaxParams();
//		if(type<0){
//		params.put("page",page+"");
//		params.put("flag", Constant.ALLINVITATION+"");}else{
//			params.put("page",page+"");
//			params.put("flag", Constant.TYPEINVITATION+"");
//			params.put("type",type+"");
//		}
//		
//		
//         finalHttp.post(url, params, new AjaxCallBack<Object>() {		
//	     @Override
//	     public void onSuccess(Object t) {
//	  	// TODO Auto-generated method stub
//		  super.onSuccess(t);
//		  JSONObject responsejson;
//		 try {
//			boolean value;
//			responsejson = new JSONObject(t.toString());
//			value = responsejson.getBoolean("state");
//			System.out.println(value+"-----------vvvvvv");
//			if(value==true){
//				totalcount=responsejson.getLong("count");
//				if(totalcount<31){count=1;}else{
//					if(totalcount%30>0){
//						count=(int) (totalcount/30+1);
//					}else{count=(int) (totalcount/30);
//					}	}
//				JSONArray jsonArray=new JSONArray();
//				jsonArray=responsejson.getJSONArray("inv");
//				System.out.println(jsonArray.toString()+"---");
//				HttpJson httpJson=HttpJson.getinstance();
//				  list=httpJson.getUserInv(jsonArray);
//					System.out.println(list.get(0).getTime()+"------------------");
				    adapter=new InvlistAdapter(list, getApplicationContext());
					adapter.notifyDataSetChanged();
					lv_inv.setAdapter(adapter);
//					pb.setVisibility(View.GONE);
//
//				System.out.println(list.get(0).getTime()+"------------------");
//				}else  {	pb.setVisibility(View.GONE);
//				Toast.makeText(getApplicationContext(), R.string.notitle, Toast.LENGTH_SHORT).show();
//
//
//				}
//		} catch (JSONException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	
//	}
//	@Override
//	public void onFailure(Throwable t, int errorNo,
//			String strMsg) {
//		// TODO Auto-generated method stub
//		super.onFailure(t, errorNo, strMsg);
//Toast.makeText(getApplicationContext(), R.string.wangluo, Toast.LENGTH_SHORT).show();
//pb.setVisibility(View.GONE);
//}
//	
//});
//		
	
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_invlist, menu);
//	    SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
//searchView.setOnQueryTextListener(this);
		return true;
	}
	
	





}
