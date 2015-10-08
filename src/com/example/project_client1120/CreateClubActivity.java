package com.example.project_client1120;





import org.json.JSONException;



import org.json.JSONObject;

import com.example.pojo.Club;
import com.example.pojo.LoginConfig;
import com.example.support.AllStatic;
import com.example.support.supportDAO;
import com.example.utils.Constant;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class CreateClubActivity extends Activity {
LoginConfig loginConfig=null;
EditText et_intro ;
EditText et_clubname;
int head=10;
public static final int REQUSET = 2;
private ImageView chead;
ProgressBar pb;
TextView but_creclub ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_club);
		
		
		
		supportDAO supportDAO=new supportDAO(this);
		loginConfig=supportDAO.getLoginConfig();
		
		init();
		
		
	}

	private void init() {
		
		chead = (ImageView) findViewById(R.id.im_chead);
		pb=(ProgressBar) findViewById(R.id.inv_pro);

		but_creclub = (TextView) findViewById(R.id.but_creclub);	
     TextView  but_uhead= (TextView) findViewById(R.id.tv_uphead);	
     TextView  tvback= (TextView) findViewById(R.id.tv_back);	
     tvback.setOnClickListener(new OnClickListener() {
 		
 		@Override
 		public void onClick(View arg0) {
 			// TODO Auto-generated method stub
 			
 			finish();
 			
 			
 		}
 	});
     
     but_uhead.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			Intent intent= new Intent(getApplicationContext(),HeadCActivity.class);
			startActivityForResult(intent, REQUSET);
			
			
		}
	});
     
     
     
     et_intro = (EditText) findViewById(R.id.et_introduce);
    et_clubname = (EditText) findViewById(R.id.et_clubname);
      but_creclub.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		if(StringUtils.empty(et_clubname.getText().toString())||StringUtils.empty(et_intro.getText().toString())){
			Toast.makeText(getApplicationContext(),R.string.noempty, Toast.LENGTH_SHORT).show();
		}else{
			creClub();}
		
		}
	});
      
	}
	
	public void creClub() {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		dialogBuilder
				.setTitle("校园")
				.setMessage("你确定要成立该社团吗")
				.setPositiveButton("确实",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								
								but_creclub.setClickable(false);
								pb.setVisibility(View.VISIBLE);
                                 
							FinalHttp finalHttp=new FinalHttp();
							AjaxParams params=new AjaxParams();
							params.put("uid",loginConfig.getId()+"");
							params.put("head",head+"");
							params.put("name",et_clubname.getText().toString() );
							params.put("introduce",et_intro.getText().toString() );
							params.put("flag", Constant.CREATECLUB+"");
							String	url="http://123.56.95.134:8080/Testlife/servlet/ServletClub";
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
									if(value==true){
										int id=responsejson.getInt("id");
										int uid=responsejson.getInt("uid");
										int number=responsejson.getInt("number");
										int invid=responsejson.getInt("invid");
										int head=responsejson.getInt("head");
										String introduce=responsejson.getString("content");
										String name=responsejson.getString("name");
										String time=responsejson.getString("time");
										Club club=new Club(id, uid, name, number, introduce, head, time, invid,AllStatic.loginConfig.getId());
									
									FinalDb finalDb=FinalDb.create(getApplicationContext());
									finalDb.save(club);
//									AllStatic.club=club;
									Intent intent=new Intent(CreateClubActivity.this,ClublistActivity.class);
									intent.putExtra("invid", club.getInvid());
									startActivity(intent);
									pb.setVisibility(View.GONE);
										}else  {
											pb.setVisibility(View.GONE);
	Toast.makeText(getApplicationContext(), "名字重复了", Toast.LENGTH_SHORT).show();
										}
									but_creclub.setClickable(true);

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
						but_creclub.setClickable(true);

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


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_create_club, menu);
		return true;
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		 if (requestCode == UpdateUserActivity.REQUSET && resultCode == RESULT_OK) {  
             head=data.getIntExtra(HeadActivity.KEY_USER_HEAD,1);
             System.out.println(head+"_----------------------------");
              chead.setImageResource(AllStatic.imageclub[head]);    
   }  }
//	@Override
//	public boolean onMenuItemSelected(int featureId, MenuItem item) {
//		// TODO Auto-generated method stub
//		switch (item.getItemId()) {
//		case android.R.id.home:
//			finish();
//			break;
//
//}	
//		
//		return super.onMenuItemSelected(featureId, item);}
//	
//
//
}
