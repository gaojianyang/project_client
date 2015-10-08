package com.example.project_client1120;

import java.util.ArrayList;
import java.util.Random;







import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import com.example.Http.HttpJson;
import com.example.UI.CustomListView;
import com.example.UI.CustomListView.OnRefreshListener;
import com.example.pojo.ChatMessage;
import com.example.pojo.LoginConfig;
import com.example.pojo.Message;
import com.example.support.AllStatic;
import com.example.utils.Constant;
import com.example.utils.MessageAdapter;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;

public class MessageActivity extends Fragment {
//	NotificationManager m_NotificationManager;
//
//	Notification m_Notification;

	Intent m_Intent;

	PendingIntent m_PendingIntent;

	LoginConfig loginConfig;
	List<Message> list = new ArrayList<Message>();
	private View mMainView;
//	 String url = "http://10.2.16.25:8080/Testlife/servlet/ServletMessage";
	String url = "http://123.56.95.134:8080/Testlife/servlet/ServletMessage";
	MessageAdapter adapter;
	FinalDb finalDb;
	CustomListView lv_message;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup p = (ViewGroup) mMainView.getParent();
		if (p != null) {
			p.removeAllViewsInLayout();
		}
		return mMainView;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getActivity().getLayoutInflater();

		mMainView = inflater.inflate(R.layout.activity_message,
				(ViewGroup) getActivity().findViewById(R.id.viewPager), false);
	lv_message = (CustomListView) mMainView
				.findViewById(R.id.lv_message);
	EditText et_sea = (EditText) mMainView.findViewById(R.id.sea);
	et_sea.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(getActivity(),SeaUserActivity.class);
		startActivity(intent);}
	});
		registerBoradcastReceiver();
		loginConfig = AllStatic.loginConfig;
		
		finalDb = FinalDb.create(getActivity());

//		LinearLayout tv_name = (LinearLayout) mMainView.findViewById(R.id.tv_mename);
//		TextView tv_person = (TextView) mMainView
//				.findViewById(R.id.te_meperson);
//		TextView tv_mmm = (TextView) mMainView
//				.findViewById(R.id.tv_memmm);
//		ImageView imhead = (ImageView) mMainView.findViewById(R.id.im_mehead);
//		tv_name.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent(getActivity(),
//						UpdateUserActivity.class);
//				startActivity(intent);
//			}
//		});
//		tv_mmm.setText(loginConfig.getName());
//		tv_person.setText(loginConfig.getPersonal());
//		imhead.setImageResource(AllStatic.images[loginConfig.getHead()]);
//		  DBHelper db= new DBHelper(getActivity());
//		 SQLiteDatabase readableDatabase = db.getReadableDatabase();
//		 Cursor rawQuery = readableDatabase.rawQuery("select * from com_example_pojo_Message",null);
//		while(rawQuery.moveToNext()){
//		 short ty= (short) rawQuery.getShort(7); 
//		 System.out.println(ty+"--sqldfsfdsfdsfds");}
//		rawQuery.close();
//		readableDatabase.close();
		list.clear();
		Message _Message=new Message(1, "你好啊", 2, (int)Math.random()*30, 1, 0, "3月5号", "小明", 1,AllStatic.loginConfig.getId());
		list.add(_Message);
		adapter = new MessageAdapter(list, getActivity());
		lv_message.setAdapter(adapter);
		lv_message.setonRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				int head=(int)Math.random()*30;
				Message _Message=new Message(1, "你好啊", 2, head
						, 1, 0, "3月5号", "小明", 1,AllStatic.loginConfig.getId());
				list.add(_Message);
				adapter=new MessageAdapter(list, getActivity());
				lv_message.setAdapter(adapter);
				// TODO Auto-generated method stub
//				FinalHttp finalHttp = new FinalHttp();
//				AjaxParams params = new AjaxParams();
//				params.put("uid", loginConfig.getId() + "");
//				params.put("flag", "44");
//
//				finalHttp.post(url, params, new AjaxCallBack<Object>() {
//					@Override
//					public void onSuccess(Object t) {
//						// TODO Auto-generated method stub
//						super.onSuccess(t);
//						JSONObject responsejson;
//						try {
//						
//							responsejson = new JSONObject(t.toString());
//							System.out.println("json----"+responsejson.toString() + "--------js---dsfsdfdsv");
//
//							JSONObject jsonObjecta=new JSONObject();
//							JSONObject jsonObjectc=new JSONObject();
//							jsonObjecta=responsejson.getJSONObject("allmessage");
//						boolean	valuea = jsonObjecta.getBoolean("stateme");
//							System.out.println(valuea + "-----------aaavv");
//							if (valuea == true) {
//								JSONArray jsonArray = new JSONArray();
//								jsonArray = jsonObjecta
//										.getJSONArray("messagea");
//								System.out.println("arraya"+ jsonArray.toString()+"3232");
//
//								HttpJson httpJson = HttpJson.getinstance();
//								
//						List<Message>	list2 = httpJson.getMessage(jsonArray);
//								for (Message message : list2) {
//									System.out.println(message.getType() + "----ytyytyty-----aaavv");
//									finalDb.save(message);
//								}
//								
////								Intent intent = new Intent();
////								intent.setAction(Constant.ACTION_NAME);
////								lv_message.onRefreshComplete();
//							} else {
////								lv_message.onRefreshComplete();
//							}
//							jsonObjectc=responsejson.getJSONObject("chatmessage");
//							boolean	valuec = jsonObjectc.getBoolean("statech");
//							System.out.println(valuec + "-----------ccccvv");
//
//							if (valuec == true) {
//								JSONArray jsonArray = new JSONArray();
//								jsonArray = jsonObjectc
//										.getJSONArray("messagec");
//								System.out.println("arrayc"+ jsonArray.toString()+"3232");
//								HttpJson httpJson = HttpJson.getinstance();
//								List<ChatMessage> chatlist = httpJson.getChatMessage(jsonArray);
//								for (ChatMessage message : chatlist) {
//									finalDb.save(message);
//								}
//								
//
//							} else {
//							}
//list.clear();
//list=finalDb.findAllByWhere(Message.class, "userid="
//									+ AllStatic.loginConfig.getId(), "time desc");
// adapter=new MessageAdapter(list, getActivity());
//	lv_message.setAdapter(adapter);
//							lv_message.onRefreshComplete();
//
//						} catch (JSONException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}

//					}
//					@Override
//					public void onFailure(Throwable t, int errorNo,
//							String strMsg) {
//						// TODO Auto-generated method stub
//						super.onFailure(t, errorNo, strMsg);
//
						lv_message.onRefreshComplete();
//					}
//
//				});

			}
		});
		lv_message.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				
				System.out.println(arg2-1+"arg22----------");
				System.out.println(list.size());
Message message=list.get(arg2-1);
				int type = list.get(arg2-1).getType();
System.out.println(list.get(arg2-1).getType()+"-------------type");
System.out.println(list.get(arg2-1).getContent()+"-------------ontetnt");
System.out.println(list.get(arg2-1).getTime()+"-------------contetn");
				switch (type) {
				case 0:
					message.setIsread(2);
					finalDb.update(message);
					list.clear();
					list=finalDb.findAllByWhere(Message.class, "userid="+ AllStatic.loginConfig.getId(), "time desc");
					 adapter=new MessageAdapter(list, getActivity());
				lv_message.setAdapter(adapter);
					Intent intent0 = new Intent(getActivity(),
							ChatActivity.class);
					intent0.putExtra("message", message);
					intent0.putExtra("type", 1);
					startActivity(intent0);
					
					break;
//				case 1:
//					Intent intent1 = new Intent(getActivity(),
//							InvReplyActivity.class);
//					intent1.putExtra("invid", list.get(arg2).getInvid());
//					startActivity(intent1);
//					finalDb.delete(list.get(arg2));
//					list.clear();
//					adapter=null;	list=finalDb.findAllByWhere(Message.class, "userid="+ AllStatic.loginConfig.getId(), "time desc");
//					 adapter.notifyDataSetChanged();
//					break;
//				case 2:
//					Intent intent2 = new Intent(getActivity(),
//							ClublistActivity.class);
//					intent2.putExtra("invid", list.get(arg2).getInvid());
//
//					finalDb.delete(list.get(arg2));
//					startActivity(intent2);
//					list.clear();
//					list=finalDb.findAllByWhere(Message.class, "userid="+ AllStatic.loginConfig.getId(), "time desc");
//					adapter=new MessageAdapter(list,getActivity()) ;
//					lv_message.setAdapter(adapter);
//
//					break;
//				case 3:
//					Intent intent3 = new Intent(getActivity(),
//							SpolistActivity.class);
//					intent3.putExtra("invid", list.get(arg2).getInvid());
//					startActivity(intent3);
//					finalDb.delete(list.get(arg2));
//					list.clear();
//					list=finalDb.findAllByWhere(Message.class, "userid="+ AllStatic.loginConfig.getId(), "time desc");
//					adapter=null;	adapter=new MessageAdapter(list,getActivity()) ;
//					lv_message.setAdapter(adapter);
//
//					break;
				case 4:
					
	              finalDb.delete(message);
					list.clear();
					list=finalDb.findAllByWhere(Message.class, "userid="+ AllStatic.loginConfig.getId(), "time desc");
					adapter=null;
					adapter=new MessageAdapter(list,getActivity()) ;
					lv_message.setAdapter(adapter);
					Intent intent4 = new Intent(getActivity(),
							SpoReplyActivity.class);
					intent4.putExtra("spoid", message.getInvid());
					intent4.putExtra("suid", message.getFromid());
					startActivity(intent4);
				
					
				

					break;
				case 5:
				
					finalDb.delete(message);
					list.clear();
					list=finalDb.findAllByWhere(Message.class, "userid="+ AllStatic.loginConfig.getId(), "time desc");
					adapter=null;adapter=new MessageAdapter(list,getActivity()) ;
					lv_message.setAdapter(adapter);
					Intent intent5 = new Intent(getActivity(),
							InvReplyActivity.class);
					intent5.putExtra("invid", message.getInvid());
					startActivity(intent5);
					

					break;
				case 6:

					finalDb.delete(message);
					list.clear();
					list=finalDb.findAllByWhere(Message.class, "userid="+ AllStatic.loginConfig.getId(), "time desc");
					adapter=null;adapter=new MessageAdapter(list,getActivity()) ;
					lv_message.setAdapter(adapter);
					Intent intent6 = new Intent(getActivity(),
							ClublistActivity.class);
					intent6.putExtra("invid", message.getInvid());
					intent6.putExtra("iuid", message.getFromid());
					startActivity(intent6);
					


					break;
				case 7:
//					list.clear();
//					list=finalDb.findAllByWhere(Message.class, "userid="+ AllStatic.loginConfig.getId(), "isread asc,time desc");
//					adapter=null;
//					adapter=new MessageAdapter(list,getActivity()) ;
//					lv_message.setAdapter(adapter);
					Intent intent7 = new Intent(getActivity(),
							InvReplyActivity.class);
					intent7.putExtra("invid", message.getInvid());
					startActivity(intent7);
					break;

				default:
					break;
				}

			}
		});

	}

	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(Constant.ACTION_NAME)) {
				list.clear();
				list=finalDb.findAllByWhere(Message.class, "userid="+ AllStatic.loginConfig.getId(), "time desc");
				adapter=null;
				adapter=new MessageAdapter(list,getActivity()) ;
				lv_message.setAdapter(adapter);

			}
		}

	};

	public void registerBoradcastReceiver() {
		IntentFilter myIntentFilter = new IntentFilter();
		myIntentFilter.addAction(Constant.ACTION_NAME);
		// 注册广播
		getActivity().registerReceiver(mBroadcastReceiver, myIntentFilter);

	}
	public void unregisterBoradcastReceiver() {
		IntentFilter myIntentFilter = new IntentFilter();
		myIntentFilter.addAction(Constant.ACTION_NAME);
		// 注册广播
		getActivity().unregisterReceiver(mBroadcastReceiver);

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterBoradcastReceiver();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub


		super.onResume();
		list.clear();
		list=finalDb.findAllByWhere(Message.class, "userid="+ AllStatic.loginConfig.getId(), "time desc");
		adapter=null;
		adapter=new MessageAdapter(list,getActivity()) ;
		lv_message.setAdapter(adapter);
//		    lv_message.setAdapter(adapter);
	}


}
