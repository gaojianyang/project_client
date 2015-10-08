package com.example.project_client1120;




import java.util.List;


import org.json.JSONException;

import org.json.JSONObject;

import com.example.UI.PopMenu;
import com.example.pojo.ChatMessage;
import com.example.pojo.LoginConfig;
import com.example.pojo.Message;
import com.example.pojo.User;

import com.example.support.AllStatic;
import com.example.utils.ChatAdapter;
import com.example.utils.Constant;

import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ChatActivity extends Activity {
Message message=new Message();
int type;
private EditText et_content;
private ListView lv_chatm;
List<ChatMessage> list;
TextView button_send;
TextView button1;
ChatAdapter adapter;
LoginConfig loginConfig;
private ImageView add;
	TextView tv_back;
	int isgz=0;
User user;
String url="http://123.56.95.134:8080/Testlife/servlet/ServletUser";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		Intent intent=getIntent();
		type=intent.getIntExtra("type", 0);
		lv_chatm = (ListView) findViewById(R.id.lv_chatmessage);
		button1=(TextView) findViewById(R.id.userinfo);
		add = (ImageView) findViewById(R.id.im_ada);
		 tv_back = (TextView) findViewById(R.id.tv_back);
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
					final PopMenu menu=new PopMenu(getApplicationContext());
					menu.addItem("用户信息");
					if(isgz==0){
					menu.addItem("关注Ta");}else{menu.addItem("取消关注");}
					menu.showAsDropDown(add);
					menu.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							// TODO Auto-generated method stub
						menu.dismiss();
						switch (arg2) {
						case 0:
							Intent intent=new Intent(ChatActivity.this,UserInfoActivity.class);
							if(type==1){
							intent.putExtra("uid", message.getFromid());}
							else{
								intent.putExtra("uid", user.getUid());
							}
							startActivity(intent);
							break;	
						case 1:
							if(isgz==0){
								if(type==0&&AllStatic.loginConfig.getId()==user.getUid()||type==1&&AllStatic.loginConfig.getId()==message.getFromid()||type==2&&AllStatic.loginConfig.getId()==user.getUid()){
									Toast.makeText(getApplicationContext(), R.string.zilian,
											Toast.LENGTH_SHORT).show();
								}else{
								FinalHttp finalHttp = new FinalHttp();
								AjaxParams params = new AjaxParams();
								params.put("uid",AllStatic.loginConfig.getId()+"");
								
								if(type==1){
								params.put("fid",message.getFromid()+"");}else{
									
									params.put("fid",user.getUid()+"");
								}

								params.put("flag", Constant.GUANZHUUSER+"");
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
											System.out.println(responsejson.toString()+"lllllllllsssssss");
											
											if (value == true) {
												Toast.makeText(getApplicationContext(), "关注成功",
														Toast.LENGTH_SHORT).show();
												isgz=1;
												

											} else {
												
												isgz=1;
												Toast.makeText(getApplicationContext(), R.string.shibaile,
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
								}
								
							}else{if(type==0&&AllStatic.loginConfig.getId()==user.getUid()||type==1&&AllStatic.loginConfig.getId()==message.getFromid()||type==2&&AllStatic.loginConfig.getId()==user.getUid()){
								Toast.makeText(getApplicationContext(), R.string.zilian,
										Toast.LENGTH_SHORT).show();
							}else{
							FinalHttp finalHttp = new FinalHttp();
							AjaxParams params = new AjaxParams();
							params.put("uid",AllStatic.loginConfig.getId()+"");
							
							if(type==1){
							params.put("fid",message.getFromid()+"");}else{
								
								params.put("fid",user.getUid()+"");
							}

							params.put("flag", Constant.QUXIAOGUANZHU+"");
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
										System.out.println(responsejson.toString()+"lllllllllsssssss");
										
										if (value == true) {
											Toast.makeText(getApplicationContext(), R.string.tiuser,
													Toast.LENGTH_SHORT).show();
										isgz=0;
										} else {
											isgz=0;
											Toast.makeText(getApplicationContext(), R.string.shibailee,
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
								
						}	}
							break;
						default:
							break;
						}
						
						
						}
					});
					
					
					
				}
			});
	    loginConfig=AllStatic.loginConfig;
	    button_send=(TextView) findViewById(R.id.send);
		et_content = (EditText) findViewById(R.id.content);
		if(type==1){
			message=(Message) intent.getSerializableExtra("message");
			System.out.println(message.getFromhead()+"========hr");
		button1.setText(message.getFromname());
		FinalDb finalDb=FinalDb.create(this);
		 list=finalDb.findAllByWhere(ChatMessage.class,"fromid="+message.getFromid()+" or toid="+message.getFromid(),"time");
		}else if(type==0){
			user=(User) intent.getSerializableExtra("user");
			button1.setText(user.getName());
			FinalDb finalDb=FinalDb.create(this);
			 list=finalDb.findAllByWhere(ChatMessage.class,"fromid="+user.getUid()+" or toid="+user.getUid(),"time");
			isgz=1;}else if(type==2){
			user=(User) intent.getSerializableExtra("user");
			button1.setText(user.getName());
			FinalDb finalDb=FinalDb.create(this);
			 list=finalDb.findAllByWhere(ChatMessage.class,"fromid="+user.getUid()+" or toid="+user.getUid(),"time");
		}
//	 "title=\"" + invid + "\""
	 adapter=new ChatAdapter(list, getApplicationContext());
	lv_chatm.setAdapter(adapter);
	lv_chatm.setSelection(list.size()-1);
   button_send.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);   
				imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
				
				button_send.setClickable(false);
				
			String content = et_content.getText().toString();
			
				FinalHttp finalHttp=new FinalHttp();
				AjaxParams params=new AjaxParams();
				params.put("content",content);
				params.put("fromid", AllStatic.loginConfig.getId()+"");
				if(type==1){params.put("toid", message.getFromid()+"");}
				else{
					params.put("toid", user.getUid()+"");
				}
				params.put("type", 0+"");
				params.put("invid", 0+"");
				params.put("flag",Constant.MESSAGE+"");
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
								FinalDb finalDb=FinalDb.create(getApplicationContext(), AllStatic.loginConfig.getId()+"user.db");
							    String time= responsejson.getString("time");
								ChatMessage chatmessage=new ChatMessage(et_content.getText().toString(), AllStatic.loginConfig.getId(),AllStatic.loginConfig.getHead() , time,message.getFromid(), AllStatic.loginConfig.getName(),AllStatic.loginConfig.getId());
								finalDb.save(chatmessage);
								list.clear();
								list=finalDb.findAllByWhere(ChatMessage.class,"fromid="+message.getFromid()+" or toid="+message.getFromid(),"time");
                                adapter=new ChatAdapter(list, getApplicationContext());
                                lv_chatm.setAdapter(adapter);
                                lv_chatm.setSelection(list.size()-1);
                                et_content.setText("");
                                button_send.setClickable(true);
							}else  {
								Toast.makeText(ChatActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
								 button_send.setClickable(true);    
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
						Toast.makeText(ChatActivity.this, R.string.wangluo, Toast.LENGTH_SHORT).show();
						 button_send.setClickable(true);
					}
					
				});
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_chat, menu);
		return true;
	}


}
