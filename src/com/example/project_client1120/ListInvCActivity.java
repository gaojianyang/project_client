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
import com.example.utils.ClubAdapter;
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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ListInvCActivity extends Activity {
int type,uid;//1 inv,2 club
List<Invitation> listi=new ArrayList<Invitation>();
List<Club> listc=new ArrayList<Club>();
private ListView lv_ic;
InvlistAdapter adapteri;
ClubAdapter adapterc;
ProgressBar pb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_inv_c);
		lv_ic = (ListView) findViewById(R.id.lv_userinvc);
	
		TextView  tvback= (TextView) findViewById(R.id.tv_back);	
	     tvback.setOnClickListener(new OnClickListener() {
	 		
	 		@Override
	 		public void onClick(View arg0) {
	 			// TODO Auto-generated method stub
	 			
	 			finish();
	 			
	 			
	 		}
	 	});
		pb=(ProgressBar) findViewById(R.id.inv_pro);

		Intent intent=getIntent();
		type=intent.getIntExtra("type", 0);
		if(type==1||type==2){
			uid=intent.getIntExtra("uid", 0);	
		}else{}
		pb.setVisibility(View.VISIBLE);

		FinalHttp finalHttp=new FinalHttp();
		AjaxParams params=new AjaxParams();
		if(type==1){
		params.put("uid",uid+"");
		params.put("flag", Constant.USERINVITATION+"");}else if(type==2){
			params.put("uid",uid+"");
			params.put("flag", Constant.USERCLUB+"");
		}else if(type==3){
			params.put("flag", Constant.TOPINV+"");
		}
		
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
				jsonArray=responsejson.getJSONArray("userinv");
				System.out.println(jsonArray.toString()+"---");
				HttpJson httpJson=HttpJson.getinstance();
				if(type==1||type==3){
				listi.clear();
				  listi=httpJson.getUserInv(jsonArray);
					System.out.println(listi.get(0).getTime()+"------------------");
				    adapteri=new InvlistAdapter(listi, getApplicationContext());
					adapteri.notifyDataSetChanged();
					lv_ic.setAdapter(adapteri);
					}else if(type==2){
						listc.clear();
						listc=httpJson.getUserClub(jsonArray);
						adapterc=new ClubAdapter(listc, getApplicationContext());
						adapterc.notifyDataSetChanged();
						lv_ic.setAdapter(adapterc);
					}

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
         lv_ic.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(type==1||type==3){Intent intent=new Intent(getApplicationContext(),InvReplyActivity.class);
				intent.putExtra("invid", listi.get(arg2).getInvid());
				intent.putExtra("iuid", listi.get(arg2).getIuid());
				intent.putExtra("title", listi.get(arg2).getTitle());
				startActivity(intent);
//				AllStatic.inv=listi.get(arg2);
				}else if(type==2){
					
					Intent intent2=new Intent(getApplicationContext(),ClublistActivity.class);
					intent2.putExtra("invid", listc.get(arg2).getInvid());
					intent2.putExtra("iuid", listc.get(arg2).getUid());
//					AllStatic.club=listc.get(arg2);
					startActivity(intent2);

				}
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
		getMenuInflater().inflate(R.menu.activity_list_inv_c, menu);
		return true;
	}

}
