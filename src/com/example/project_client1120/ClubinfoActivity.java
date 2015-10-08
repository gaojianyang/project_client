package com.example.project_client1120;

import org.json.JSONException;



import org.json.JSONObject;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import com.example.pojo.Club;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ClubinfoActivity extends Activity {
Club club;
String url="http://123.56.95.134:8080/Testlife/servlet/ServletInv";
ProgressBar pb;
TextView joinc;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clubinfo);
		ImageView head = (ImageView) findViewById(R.id.im_clubhead);
		TextView name = (TextView) findViewById(R.id.tv_clubname);
		TextView intro = (TextView) findViewById(R.id.tv_clubintro);
		TextView ctime = (TextView) findViewById(R.id.club_time);
		TextView number = (TextView) findViewById(R.id.club_number);
		TextView tvback = (TextView) findViewById(R.id.tv_back);
		
	 joinc= (TextView) findViewById(R.id.tv_joinclub);
		pb=(ProgressBar) findViewById(R.id.clubinfo_pro);
tvback.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
	finish();	
	}
});
		Intent intent=getIntent();
		club=(Club) intent.getSerializableExtra("club");
		head.setImageResource(AllStatic.imageclub[club.getHead()]);
		ctime.setText(club.getTime());
		name.setText(club.getName());
		intro.setText(club.getIntroduce());
		number.setText(club.getNumber()+"");
		joinc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				pb.setVisibility(View.VISIBLE);
				joinc.setClickable(false);
				FinalHttp finalHttp = new FinalHttp();
				AjaxParams params = new AjaxParams();
				params.put("uid",AllStatic.loginConfig.getId()+"");
                params.put("invid",club.getInvid()+"");
				params.put("flag", Constant.GUANZHUINV+"");
				finalHttp.post(url, params, new AjaxCallBack<Object>() {
					@Override
					public void onSuccess(Object t) {
						// TODO Auto-generated method stub
						super.onSuccess(t);
						joinc.setClickable(false);

						JSONObject responsejson;
						try {
							boolean value;
							responsejson = new JSONObject(t.toString());
							value = responsejson.getBoolean("state");
							System.out.println(value + "-----------vvvvvv");
							
							if (value == true) {
								pb.setVisibility(View.GONE);
								Toast.makeText(getApplicationContext(),"加入成功",
										Toast.LENGTH_SHORT).show();
//								FinalDb finalDb=FinalDb.create(getApplicationContext());
//								if(AllStatic.club!=null){
//		                             finalDb.save(AllStatic.club);}else{}
	                          	joinc.setClickable(false);
								Intent intent=new Intent(getApplicationContext(),ClublistActivity.class);
								intent.putExtra("invid", club.getInvid());
								intent.putExtra("iuid", club.getUid());
								startActivity(intent);
								
							} else {
								Toast.makeText(getApplicationContext(),"你已经加入过了",
										Toast.LENGTH_SHORT).show();
								joinc.setVisibility(View.GONE);
								pb.setVisibility(View.GONE);
								Intent intent=new Intent(getApplicationContext(),ClublistActivity.class);
								intent.putExtra("invid", club.getInvid());
								intent.putExtra("iuid", club.getUid());
								startActivity(intent);
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
						pb.setVisibility(View.GONE);
						joinc.setClickable(true);

						Toast.makeText(getApplicationContext(), R.string.wangluo,
								Toast.LENGTH_SHORT).show();
						finish();
					}

				});

				
			}
		});
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if ((keyCode == KeyEvent.KEYCODE_BACK) ) {
//			返回上一个打开的页面
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_clubinfo, menu);
		return true;
	}

}
