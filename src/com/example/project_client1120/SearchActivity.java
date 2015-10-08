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
import com.example.pojo.Club;
import com.example.pojo.Invitation;
import com.example.pojo.Sport;
import com.example.utils.ClubAdapter;
import com.example.utils.Constant;
import com.example.utils.InvlistAdapter;
import com.example.utils.SpoAdapter;
import com.example.utils.StringUtils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SearchActivity extends Activity {
int type;
long totalcount;
int count;
int page=1;
  ListView lv_inv ;
  String url;
//0,liaotian 1,inv 2,spo,3 club

List<Invitation> listi=new ArrayList<Invitation>();
List<Sport> lists=new ArrayList<Sport>();
List<Club> listc=new ArrayList<Club>();
InvlistAdapter adapteri;
SpoAdapter adapters;
ClubAdapter adapterc;
ProgressBar pb;
private TextView tvback;


TextView but_chazhao;
String title;
private EditText ettitle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
	
		pb=(ProgressBar) findViewById(R.id.inv_pro);
		tvback=(TextView) findViewById(R.id.tv_back);

		lv_inv = (ListView) findViewById(R.id.lv_all);
		TextView but_pre = (TextView) findViewById(R.id.but_invpre);
		TextView but_next = (TextView) findViewById(R.id.but_invnext);
		 but_chazhao = (TextView) findViewById(R.id.et_chazhao);
		ImageView but_refresh = (ImageView) findViewById(R.id.but_refresh);
		ettitle = (EditText) findViewById(R.id.et_title);
		
		
		Intent intent=getIntent();
		type=intent.getIntExtra("type", 0);
		tvback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		lv_inv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				if(type==1){
					Intent intent=new Intent(getApplicationContext(),InvReplyActivity.class);
				intent.putExtra("invid", listi.get(arg2).getInvid());
				intent.putExtra("iuid", listi.get(arg2).getIuid());
				intent.putExtra("title", listi.get(arg2).getTitle());

//				AllStatic.inv=listi.get(arg2);
				startActivity(intent);}else if(type==2){

					Intent intent=new Intent(getApplicationContext(),SpoReplyActivity.class);
				intent.putExtra("spoid", lists.get(arg2).getSpoid());
				intent.putExtra("suid", lists.get(arg2).getUid());
//				AllStatic.sport=lists.get(arg2);
				startActivity(intent);
					
					
					
				}else if(type==3){
					
					Intent intent=new Intent(getApplicationContext(),ClubinfoActivity.class);
					intent.putExtra("club", listc.get(arg2));
//					AllStatic.club=listc.get(arg2);
					startActivity(intent);
					
				}
				
			}
		});
		but_chazhao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			title=ettitle.getText().toString();
			boolean empty = StringUtils.empty(title);
				if(empty||title.length()<3){
				Toast.makeText(getApplicationContext(), R.string.leasttwo, Toast.LENGTH_SHORT).show();	
				}
				else{
					
					returnList(page);
					
				}
				
				
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
		but_chazhao.setClickable(false);	

		pb.setVisibility(View.VISIBLE);

			lists.clear();
			listi.clear();
			listc.clear();
			FinalHttp finalHttp=new FinalHttp();
			AjaxParams params=new AjaxParams();
			if(type==1){
			params.put("page",page+"");
			params.put("title",title+"");
			params.put("flag", Constant.TITLEINVITATION+"");
			url="http://123.56.95.134:8080/Testlife/servlet/ServletInv";
			}else if(type==2){
				params.put("title",title+"");
				params.put("page",page+"");
				params.put("flag", Constant.TITLESPORT+"");
				url="http://123.56.95.134:8080/Testlife/servlet/ServletSpo";

			}else if(type==3){
				params.put("title",title+"");
				params.put("page",page+"");
				params.put("flag", Constant.TITLECLUB+"");
				url="http://123.56.95.134:8080/Testlife/servlet/ServletClub";

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
					
					if(type==1){
					jsonArray=responsejson.getJSONArray("inv");
					System.out.println(jsonArray.toString()+"---");
					HttpJson httpJson=HttpJson.getinstance();
					  listi=httpJson.getUserInv(jsonArray);

					    adapteri=new InvlistAdapter(listi, getApplicationContext());
						adapteri.notifyDataSetChanged();
						lv_inv.setAdapter(adapteri);
						pb.setVisibility(View.GONE);
						but_chazhao.setClickable(true);	
}
					
					else if(type==2){
						
						jsonArray=responsejson.getJSONArray("allspo");
						System.out.println(jsonArray.toString()+"---");
						HttpJson httpJson=HttpJson.getinstance();
						  lists=httpJson.getUserSport(jsonArray);

							System.out.println(lists.get(0).getTime()+"------------------");
						    adapters=new SpoAdapter(lists, getApplicationContext());
							adapters.notifyDataSetChanged();
							lv_inv.setAdapter(adapters);
							pb.setVisibility(View.GONE);
							but_chazhao.setClickable(true);	

						
					}else if(type==3){
						jsonArray=responsejson.getJSONArray("allclub");
						System.out.println(jsonArray.toString()+"---");
						HttpJson httpJson=HttpJson.getinstance();
						  listc=httpJson.getUserClub(jsonArray);
							System.out.println(lists.get(0).getTime()+"------------------");
						    adapterc=new ClubAdapter(listc, getApplicationContext());
							adapterc.notifyDataSetChanged();
							lv_inv.setAdapter(adapterc);
							pb.setVisibility(View.GONE);
							but_chazhao.setClickable(true);	

					}
					}else  {	
						Toast.makeText(getApplicationContext(), R.string.notitle, Toast.LENGTH_SHORT).show();
						
						pb.setVisibility(View.GONE);
						but_chazhao.setClickable(true);	

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
	but_chazhao.setClickable(true);	

	}
		
	});}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_search, menu);
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
