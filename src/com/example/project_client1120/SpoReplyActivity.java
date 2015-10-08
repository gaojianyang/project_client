package com.example.project_client1120;

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
import com.example.UI.PopMenu;
import com.example.pojo.LoginConfig;
import com.example.pojo.Sport;
import com.example.pojo.Sreply;
import com.example.support.AllStatic;
import com.example.utils.Constant;
import com.example.utils.SreplyAdapter;
import com.example.utils.StringUtils;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SpoReplyActivity extends Activity implements OnClickListener {
int spoid;
String url = "http://123.56.95.134:8080/Testlife/servlet/ServletSpo";
long totalcount;
int type=0;//0 all,1dao,2louzhu
int huif;
int count;
int page = 1;
int suid;
List<Sreply> list = new ArrayList<Sreply>();
TextView but_pre;
TextView but_next;
TextView but_send;
ImageView but_refresh;
LoginConfig loginConfig;
 SreplyAdapter reaAdapter;
 ListView lv_club;
private EditText et_reply;
ProgressBar pb;
private ImageView add;
private ImageView user;
	TextView tv_back;
	private LinearLayout llth;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spo_reply);
		add = (ImageView) findViewById(R.id.im_ada);
		user = (ImageView) findViewById(R.id.im_user);
		 tv_back = (TextView) findViewById(R.id.tv_back);
		Intent intent=getIntent();
		spoid=intent.getIntExtra("spoid", 0);
		suid=intent.getIntExtra("suid", 0);
		pb=(ProgressBar) findViewById(R.id.inv_pro);

	    loginConfig=AllStatic.loginConfig;
		
		returnlist(page);
		llth = (LinearLayout) findViewById(R.id.llth);
		et_reply = (EditText) findViewById(R.id.et_reply);
		lv_club= (ListView) findViewById(R.id.lv_invreply);
		but_pre = (TextView) findViewById(R.id.but_invpre);
		 but_next = (TextView) findViewById(R.id.but_invnext);
		 but_send = (TextView) findViewById(R.id.but_send);
		 but_refresh = (ImageView) findViewById(R.id.but_refresh);
		but_next.setOnClickListener(this);
		but_send.setOnClickListener(this);
		et_reply.setOnClickListener(this);
		but_pre.setOnClickListener(this);
		but_refresh.setOnClickListener(this);
		tv_back.setOnClickListener(this);
		add.setOnClickListener(this);
		user.setOnClickListener(this);
		
		
	}
	public void returnlist(int page) {
		pb.setVisibility(View.VISIBLE);

		FinalHttp finalHttp = new FinalHttp();
		AjaxParams params = new AjaxParams();
		params.put("sid", spoid + "");
		params.put("page", page + "");

		if(type==0){
			params.put("flag", Constant.SPORTREPLY+"");}else if(type==1){
				
				params.put("flag", Constant.DAOSREPLY+"");

			}else if(type==2){params.put("flag", Constant.LOUZHUSREPLY+"");
			params.put("suid",suid+"");}
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
					if (value == true) {
						totalcount = responsejson.getInt("count");
						if(totalcount<21){count=1;}else{
							if(totalcount%20>0){
								count=(int) (totalcount/20+1);
							}else{count=(int) (totalcount/20);
							System.out.println(count+"cccchhhhhccccccc");}	}
						JSONArray jsonArray = new JSONArray();
						jsonArray = responsejson.getJSONArray("allreply");
						HttpJson httpJson = HttpJson.getinstance();
						list.clear();
						list = httpJson.getSpoReply(jsonArray);
					    reaAdapter=new SreplyAdapter(list, getApplicationContext());
						lv_club.setAdapter(reaAdapter);
						reaAdapter.notifyDataSetChanged();
						
					}else {
					
					}
					pb.setVisibility(View.GONE);
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
				Toast.makeText(getApplicationContext(), R.string.wangluo,
						Toast.LENGTH_SHORT).show();
			}

		});}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_spo_reply, menu);
		return true;
	}

		
		
		
	

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch (arg0.getId()) {
		case R.id.but_invnext:
			
			if (page < count) {
				page = page+1;
				returnlist(page );
			} else {
				Toast.makeText(getApplicationContext(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
			}
			
			break;
		case R.id.but_invpre:

			if (page > 1) {
				page = page-1;
			
				returnlist(page);
			} else {
				Toast.makeText(getApplicationContext(),"这是第一页", Toast.LENGTH_SHORT).show();
			}

			break;
		case R.id.but_refresh:
				returnlist(page);
			break;
		case R.id.but_send:
			InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);   
			imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
			
			String content=et_reply.getText().toString();
			boolean empty = StringUtils.empty(content);
			if(!empty){
				but_send.setClickable(false);
			FinalHttp finalHttp=new FinalHttp();
			AjaxParams params=new AjaxParams();
			params.put("spoid", spoid+"");
			params.put("uid", AllStatic.loginConfig.getId()+"");
			params.put("content", content);
			params.put("flag", Constant.REPLYSPORT+"");

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
						if (value == true) {
							et_reply.setText("");
		                     Toast.makeText(getApplicationContext(), "回复成功",
										Toast.LENGTH_SHORT).show();
		                  
		                     but_send.setClickable(true);
		                        huif=0;
								llth.setVisibility(View.GONE); 
						} else {
							Toast.makeText(getApplicationContext(), R.string.shibaile,
									Toast.LENGTH_SHORT).show();
		                    but_send.setClickable(true);

						}
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						but_send.setClickable(true);			}
				}

				@Override
				public void onFailure(Throwable t, int errorNo, String strMsg) {
					// TODO Auto-generated method stub
					super.onFailure(t, errorNo, strMsg);
					Toast.makeText(getApplicationContext(), R.string.wangluo,
							Toast.LENGTH_SHORT).show();
		            but_send.setClickable(true);

				}

			});}else{
				Toast.makeText(getApplicationContext(), "回复不能为空",
						Toast.LENGTH_SHORT).show();
				
			}
			
			break;
		
		case R.id.im_ada:
			final PopMenu menu=new PopMenu(getApplicationContext());
			menu.addItem("加入活动");
			menu.addItem("回复活动");
			menu.addItem("查看成员");
		
			if(suid==loginConfig.getId()){
			menu.addItem("解散活动");
			menu.addItem("修改活动");
			}else{menu.addItem("退出活动");}
		
			menu.showAsDropDown(add);
			menu.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
				menu.dismiss();
				switch (arg2) {
				case 0:
					if(loginConfig.getId()==suid){
						Toast.makeText(getApplicationContext(), R.string.zilian, Toast.LENGTH_SHORT).show();
						
					}else{
					
					FinalHttp finalHttp=new FinalHttp();
					AjaxParams params=new AjaxParams();
					params.put("sid", spoid+"");
					params.put("uid", loginConfig.getId()+"");
					params.put("suid", suid+"");
					params.put("flag", Constant.JOINSPORT+"");
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
								if (value == true) {
//									FinalDb finalDb=FinalDb.create(getApplicationContext());
//									if(AllStatic.sport!=null){
//			                             finalDb.save(AllStatic.sport);}else{}
		                             Toast.makeText(getApplicationContext(), "加入成功",
		 									Toast.LENGTH_SHORT).show();
								} else {
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
					
					
					break;
				case 1:
					if(huif==0){
						huif=1;
					llth.setVisibility(View.VISIBLE);}else{
						
						huif=0;
						llth.setVisibility(View.GONE);
					}
					break;

				case 2:
					
					Intent intent=new Intent(getApplicationContext(),ClubUserActivity.class);
					intent.putExtra("invid", spoid);
					intent.putExtra("type", 2);
					intent.putExtra("iuid", suid);
					startActivity(intent);
					

					break;
					
				case 3:
					
					creClub();
					
					break;
					
				case 4:
					Intent intent3=new Intent(getApplicationContext(),UpSpoActivity.class);
					intent3.putExtra("spoid", spoid);
					startActivity(intent3);
					break;
					
				
				default:
					break;
				}
				
				
				}
			});
			break;
		case R.id.im_user:
			
			
			PopMenu menu2=new PopMenu(getApplicationContext());
			if(type==0){
			    menu2.addItem("只看楼主");menu2.addItem("倒序排列");
			}else if(type==1){
				menu2.addItem("只看楼主");menu2.addItem("正序排列");
			}else if(type==2){
				menu2.addItem("正序排列");menu2.addItem("倒序排列");
			}
		
			menu2.showAsDropDown(add);
			menu2.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
				
				switch (arg2) {
				case 0:
					if(type!=2){page=1;
					type=2;
				returnlist(page);}else{    page=1;
		        type=0;
			returnlist(page);}
				
					break;
				case 1:
					if(type!=1){page=1;
					type=1;
				returnlist(page);}else{    page=1;
		        type=0;
			returnlist(page);}
					
					break;
		
					
				default:
					break;
				}
				
				
				}
			});
			
			
			break;
			
		case R.id.tv_back:
			finish();
			break;
		case R.id.et_reply:
			et_reply.setFocusable(true);
			break;
		default:
			break;
		}		
			}
	public void creClub() {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		dialogBuilder
				.setTitle("校园")
				.setMessage("你确定要退出/解散该活动吗")
				.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								
								
								pb.setVisibility(View.VISIBLE);

							FinalHttp finalHttp=new FinalHttp();
							AjaxParams params=new AjaxParams();
							
							if(AllStatic.loginConfig.getId()==suid){
								params.put("sid",spoid+"");
								params.put("flag", Constant.DELETESPORT+"");
							}else{	params.put("uid",loginConfig.getId()+"");
							params.put("sid",spoid+"");
							params.put("flag", Constant.QUITSPORT+"");
							}
							String	url="http://123.56.95.134:8080/Testlife/servlet/ServletSpo";
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
									if(AllStatic.loginConfig.getId()==suid){
										FinalDb db=FinalDb.create(getApplicationContext());
										db.deleteByWhere(Sport.class, "spoid="+spoid+" and userid="+AllStatic.loginConfig.getId());
									}
									pb.setVisibility(View.GONE);
									Toast.makeText(getApplicationContext(), "退出/解散成功", Toast.LENGTH_SHORT).show();

									finish();
										}else  {
											pb.setVisibility(View.GONE);

											Toast.makeText(getApplicationContext(), "你不在里面或者活动已经解散了", Toast.LENGTH_SHORT).show();
                                       finish(); 
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
						})
				.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int whichButton) {
								dialog.cancel();
							}
						});
		dialogBuilder.show();
	}

}
