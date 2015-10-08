package com.example.project_client1120;

import java.util.ArrayList;







import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.Http.HttpJson;
import com.example.UI.PopMenu;
import com.example.pojo.Club;
import com.example.pojo.LoginConfig;
import com.example.pojo.Reply;
import com.example.support.AllStatic;
import com.example.utils.Constant;
import com.example.utils.InvAdapter;
import com.example.utils.StringUtils;

import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
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

public class ClublistActivity extends Activity implements OnClickListener {
	String url = "http://123.56.95.134:8080/Testlife/servlet/ServletInv";
	long totalcount;
int type=1;//0 all,1dao,2louzhu
	int count;
	int page = 1;
	int invid;
	int uid;
	int huif=0;
	 InvAdapter reaAdapter;
	 LoginConfig loginConfig;
	 ListView lv_club;
	 TextView but_next;
	 TextView but_pre;
	 TextView tvback;
		TextView but_send;
		TextView but_img;
		ImageView but_refresh;
		LinearLayout ly;
List<Reply> list=new ArrayList<Reply>();
private EditText et_reply;
ProgressBar pb;
private ImageView add;
private ImageView huifu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clublist);
	
		
		pb=(ProgressBar) findViewById(R.id.inv_pro);
		Intent intent=getIntent();
		invid = intent.getIntExtra("invid", 0);
		uid = intent.getIntExtra("iuid", 0);
	    loginConfig=AllStatic.loginConfig;
		returnlist(page);
		add = (ImageView) findViewById(R.id.im_ada);
		huifu = (ImageView) findViewById(R.id.huifu);
		 ly = (LinearLayout) findViewById(R.id.b);
		 lv_club = (ListView) findViewById(R.id.lv_clubre);
		et_reply = (EditText) findViewById(R.id.et_reply);
	   lv_club.setAdapter(reaAdapter);
		but_next = (TextView) findViewById(R.id.but_invnext);
		 but_refresh = (ImageView) findViewById(R.id.but_refresh);
		but_pre = (TextView) findViewById(R.id.but_invpre);
		 but_next = (TextView) findViewById(R.id.but_invnext);
		 but_send = (TextView) findViewById(R.id.but_send);
		 but_img = (TextView) findViewById(R.id.but_addpng);
		 tvback = (TextView) findViewById(R.id.tv_back);
		but_next.setOnClickListener(this);
		but_pre.setOnClickListener(this);
		but_refresh.setOnClickListener(this);
		but_send.setOnClickListener(this);
		but_img.setOnClickListener(this);
		tvback.setOnClickListener(this);
		huifu.setOnClickListener(this);
	add.setOnClickListener(this);
	}
	public void returnlist(int page){
		pb.setVisibility(View.VISIBLE);
		FinalHttp finalHttp=new FinalHttp();
		AjaxParams params=new AjaxParams();
		params.put("invid", invid+"");
		params.put("page", page+"");
		if(type==0){
		params.put("flag", Constant.ALLREPLY+"");}else if(type==1){
			
			params.put("flag", Constant.DAOREPLY+"");
		}else if(type==2){params.put("flag", Constant.LOUZHUREPLY+"");}else if(type==3){
			
			params.put("flag", Constant.ZANREPLY+"");
			
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
				System.out.println(t.toString());
				value = responsejson.getBoolean("state");
				System.out.println(value+"-----------vvvvvv");
				if(value==true){
					totalcount = responsejson.getInt("count");
					if(totalcount<21){count=1;}else{
						if(totalcount%20>0){
							count=(int) (totalcount/20+1);
							System.out.println(count+"ccccccccccccccccccc");
						}else{count=(int) (totalcount/20);}}
					
					JSONArray jsonArray=new JSONArray();
					jsonArray=responsejson.getJSONArray("inv");
					HttpJson httpJson=HttpJson.getinstance();
					list.clear();
					 list=httpJson.getInvReply(jsonArray);
					 reaAdapter = new InvAdapter(getApplicationContext(), list, invid);
						lv_club.setAdapter(reaAdapter);
						reaAdapter.notifyDataSetChanged();
					}else  {
						

				}
				pb.setVisibility(View.GONE);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_clublist, menu);
		return true;
	}
	


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
	case R.id.but_invnext:
		but_next.setClickable(false);
		
		if (page < count) {
			page = page+1;
		    returnlist(page);
			
		} else {
			Toast.makeText(getApplicationContext(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
		}
		
		break;
	case R.id.but_invpre:
		but_pre.setClickable(false);

		if (page > 1) {
			page = page-1;
		
				returnlist(page);
			
		} else {
			Toast.makeText(getApplicationContext(),"这是第一页", Toast.LENGTH_SHORT).show();
		}

		break;
	case R.id.but_refresh:
		but_refresh.setClickable(false);

		
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
		params.put("invid", invid+"");
		params.put("rid", loginConfig.getId()+"");
		params.put("content", content);
		params.put("type",6+"");
		params.put("flag", Constant.CREATEREPLY+"");

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
	                     Toast.makeText(getApplicationContext(), R.string.replyok,
									Toast.LENGTH_SHORT).show();
	                     but_send.setClickable(true);
	                 	huif=0;
	                    ly.setVisibility(View.GONE);	
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
	
			Toast.makeText(getApplicationContext(), R.string.noempty,
					Toast.LENGTH_SHORT).show();
		}
		break;
	case R.id.but_addpng:
		Intent intent=new Intent(getApplicationContext(),AddPngActivity.class);
		intent.putExtra("invid", invid);
		startActivity(intent);
		break;
	case R.id.im_ada:
		final PopMenu menu2=new PopMenu(getApplicationContext());
		if(type==0){
		    menu2.addItem("只看团主");menu2.addItem("按赞排列");menu2.addItem("倒序排列");
		}else if(type==1){
			menu2.addItem("只看团主");menu2.addItem("按赞排序");menu2.addItem("正序排列");
		}else if(type==2){
			menu2.addItem("正序排列");menu2.addItem("按赞排列");menu2.addItem("倒序排列");
		}else if(type==3){
			menu2.addItem("只看团主");menu2.addItem("正序排列");menu2.addItem("倒序排列");
		}
		menu2.addItem("查看图片");
		if(uid==loginConfig.getId()){
		menu2.addItem("解散社团");}else{
		menu2.addItem("退出社团");
		}
		;
		
		
		
		menu2.showAsDropDown(add);
		menu2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
			menu2.dismiss();
			switch (arg2) {
			case 0:
				if(type!=2){page=1;
				type=2;
			returnlist(page);}else{    page=1;
	        type=0;
		returnlist(page);}
			
				break;
			case 1:
				if(type!=3){page=1;
				type=3;
			returnlist(page);}else{    page=1;
	        type=0;
		returnlist(page);}
				
				break;
			case 2:
				if(type!=1){page=1;
				type=1;
			returnlist(page);}else{    page=1;
	        type=0;
		returnlist(page);}
			
				break;
			case 3:
				
				Intent intent=new Intent(getApplicationContext(),PngActivity.class);
				intent.putExtra("invid", invid);
				startActivity(intent);
				break;
			case 4:
				creClub();
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
	case R.id.huifu:
		if(huif==0){
			huif=1;
		ly.setVisibility(View.VISIBLE);}else{
			
			huif=0;
       ly.setVisibility(View.GONE);	
		}
		
		break;
	default:
		break;
	
		
	}
	}
	public void creClub() {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		dialogBuilder
				.setTitle("校园")
				.setMessage("你确定要退出/解散该社团吗")
				.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								
								
								pb.setVisibility(View.VISIBLE);

							FinalHttp finalHttp=new FinalHttp();
							AjaxParams params=new AjaxParams();
							
							if(AllStatic.loginConfig.getId()==uid){
								params.put("invid",invid+"");
								params.put("flag", Constant.DELETECLUB+"");
							}else{	params.put("uid",loginConfig.getId()+"");
							params.put("invid",AllStatic.loginConfig.getId()+"");
							params.put("flag", Constant.QUXIAOINV+"");
							}
							String	url="http://123.56.95.134:8080/Testlife/servlet/ServletInv";
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
									if(AllStatic.loginConfig.getId()==uid){
										FinalDb db=FinalDb.create(getApplicationContext());
										db.deleteByWhere(Club.class, "invid="+invid+" and userid="+AllStatic.loginConfig.getId());
									}
									pb.setVisibility(View.GONE);
									Toast.makeText(getApplicationContext(), "退出/解散成功", Toast.LENGTH_SHORT).show();
									finish();
										}else  {
											pb.setVisibility(View.GONE);
											Toast.makeText(getApplicationContext(), "你不在里面或者社团已经解散了", Toast.LENGTH_SHORT).show();
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
