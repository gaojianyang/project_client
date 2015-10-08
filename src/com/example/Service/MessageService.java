package com.example.Service;


import java.util.ArrayList;


import java.util.List;

import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.Http.HttpJson;
import com.example.pojo.ChatMessage;
import com.example.pojo.LoginConfig;
import com.example.pojo.Message;
import com.example.support.AllStatic;
import com.example.utils.Constant;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
public class MessageService extends Service{
	List<Message> list = new ArrayList<Message>();
	LoginConfig loginConfig	 = AllStatic.loginConfig;
	String url = "http://10.2.16.19:8080/Testlife/servlet/ServletMessage";
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(handler!=null){
			switch (msg.what) {
			case 1:
				final FinalDb finalDb=FinalDb.create(getApplicationContext());
				FinalHttp finalHttp = new FinalHttp();
				AjaxParams params = new AjaxParams();
				params.put("uid", loginConfig.getId() + "");
				params.put("flag", "44");
				System.out.println(loginConfig.getId()+"kao+222222222222222");
				finalHttp.post(url, params, new AjaxCallBack<Object>() {
					@Override
					public void onSuccess(Object t) {
						// TODO Auto-generated method stub
						super.onSuccess(t);
						JSONObject responsejson;
						try {
							responsejson = new JSONObject(t.toString());
							System.out.println("json----"+responsejson.toString() + "--------js---dsfsdfdsv");
							JSONObject jsonObjecta=new JSONObject();
							JSONObject jsonObjectc=new JSONObject();
							jsonObjecta=responsejson.getJSONObject("allmessage");
						boolean	valuea = jsonObjecta.getBoolean("stateme");
							if (valuea == true) {
								JSONArray jsonArray = new JSONArray();
								jsonArray = jsonObjecta
										.getJSONArray("messagea");
								System.out.println("arraya"+ jsonArray.toString()+"3232");

								HttpJson httpJson = HttpJson.getinstance();
								list.clear();
								list = httpJson.getMessage(jsonArray);
								for (Message message : list) {
									finalDb.save(message);
								}
						
							} else {
							}
							jsonObjectc=responsejson.getJSONObject("chatmessage");
							boolean	valuec = jsonObjectc.getBoolean("statech");
							System.out.println(valuec + "-----------ccccvv");

							if (valuec == true) {
								JSONArray jsonArray = new JSONArray();
								jsonArray = jsonObjectc
										.getJSONArray("messagec");
								System.out.println("arrayc"+ jsonArray.toString()+"3232");
								HttpJson httpJson = HttpJson.getinstance();
								List<ChatMessage> chatlist = httpJson.getChatMessage(jsonArray);
								for (ChatMessage message : chatlist) {
									finalDb.save(message);
								}
								
//								Intent intent = new Intent();
//								intent.setAction(Constant.ACTION_NAME);
//								lv_message.onRefreshComplete();
							} else {
//								lv_message.onRefreshComplete();
							}

							Intent intent = new Intent();
							intent.setAction(Constant.ACTION_NAME);
							sendBroadcast(intent);
							if(handler!=null){	handler.sendEmptyMessageDelayed(1,1000*100);}else{}
							
						

						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							System.out.println("yicahng+222222222222222");
						}

					}

					@Override
					public void onFailure(Throwable t, int errorNo, String strMsg) {
						// TODO Auto-generated method stub
						super.onFailure(t, errorNo, strMsg);
						if(handler!=null){	handler.sendEmptyMessageDelayed(1,1000*10);}else{}
					}

				});
				break;
			default:
				break;
			}
			}else{
				System.out.println("wuyukong+222222222222222");

				
			}
			
			
		};
		
		
		
	};

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		System.out.println("qidaongle+222ds");
		handler.sendEmptyMessageDelayed(1, 1000*10);
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
//		handler.
		handler.removeCallbacksAndMessages(null);
		handler=null;
	}
	
	

}
