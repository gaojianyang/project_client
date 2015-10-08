package com.example.project_client1120;



import org.json.JSONException;



import org.json.JSONObject;

import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import com.example.pojo.Invitation;
import com.example.pojo.LoginConfig;
import com.example.support.supportDAO;
import com.example.utils.Constant;
import com.example.utils.StringUtils;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CreateInvActivity extends Activity {

	private TextView et_title;
	private TextView et_content;
	private Spinner sp_type;
	private TextView sendinv;
	
	short type;
	LoginConfig loginConfig;
	ProgressBar pb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_inv);
		pb=(ProgressBar) findViewById(R.id.inv_pro);

	
//		actionBar.setDisplayHomeAsUpEnabled(true);
		et_title = (TextView) findViewById(R.id.et_invtitle);
		et_content = (TextView) findViewById(R.id.et_invcontent);
		sp_type = (Spinner) findViewById(R.id.sp_type);
		sendinv = (TextView) findViewById(R.id.tv_sendinv);
		TextView tvback = (TextView) findViewById(R.id.tv_back);

		tvback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		String [] schoolarray=getResources().getStringArray(R.array.type);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(CreateInvActivity.this,R.layout.spinner, schoolarray);
		sp_type.setAdapter(adapter);
		supportDAO supportDAO=new supportDAO(this);
		loginConfig=supportDAO.getLoginConfig();
//		tvback.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				finish();
//			}
//		});
//		
		
		sp_type.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				type=(short) arg2;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				type=0;
			}
		});
	sendinv.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String title=et_title.getText().toString();
			String content=et_content.getText().toString();
			if(StringUtils.empty(title)||StringUtils.empty(content)){
				Toast.makeText(getApplicationContext(), "标题或内容不能为空", Toast.LENGTH_SHORT).show();
				
		}else{
			pb.setVisibility(View.VISIBLE);
sendinv.setClickable(false);
			
			FinalHttp finalHttp=new FinalHttp();
			AjaxParams params=new AjaxParams();
			params.put("title",title);
			params.put("content",content);
			params.put("uid",loginConfig.getId()+"");
			params.put("type",type+"");
			params.put("flag", Constant.CREATEINVITATION+"");
			String url="http://123.56.95.134:8080/Testlife/servlet/ServletInv";
			
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
					int id=responsejson.getInt("id");
					int iuid=responsejson.getInt("iuid");
					int zan=responsejson.getInt("zan");
					int head=responsejson.getInt("head");
					short type=(short) responsejson.getInt("type");
					String title=responsejson.getString("title");
					String uname=responsejson.getString("uname");
					String time=responsejson.getString("time");
					String createtime=responsejson.getString("createtime");
					FinalDb finalDb=FinalDb.create(getApplicationContext());
					System.out.println(loginConfig.getId()+"-----------vvvvvv");
					pb.setVisibility(View.INVISIBLE);
					sendinv.setClickable(true);
					Invitation inv=new Invitation(id,iuid, head, uname, title, zan, time, createtime, type,loginConfig.getId());
					finalDb.save(inv);
//					AllStatic.inv=inv;
					Intent intent=new Intent(getApplicationContext(),InvReplyActivity.class);
					intent.putExtra("invid", inv.getInvid());	
					intent.putExtra("iuid", inv.getIuid());	
					startActivity(intent);
					}else  {
						pb.setVisibility(View.INVISIBLE);
					sendinv.setClickable(true);
				Toast.makeText(getApplicationContext(), R.string.wangluo, Toast.LENGTH_SHORT).show();

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
			pb.setVisibility(View.INVISIBLE);
			sendinv.setClickable(true);
	Toast.makeText(getApplicationContext(), R.string.wangluo, Toast.LENGTH_SHORT).show();
		}
		
	});
			
			
			
		}
			
			
			
		}
	});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_create_inv, menu);
		return true;
	}
//	@Override
//	public boolean onMenuItemSelected(int featureId, MenuItem item) {
//		// TODO Auto-generated method stub
//		switch (item.getItemId()) {
//		case android.R.id.home:
//			finish();
//			break;
//
//		}
//		
//		return super.onMenuItemSelected(featureId, item);
//		
//		
//		
//	}
	
}
