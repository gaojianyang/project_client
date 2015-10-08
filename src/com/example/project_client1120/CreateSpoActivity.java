package com.example.project_client1120;

import java.util.Calendar;




import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.pojo.Sport;
import com.example.support.AllStatic;
import com.example.utils.Constant;
import com.example.utils.StringUtils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CreateSpoActivity extends Activity {

	private EditText et_title;
	private EditText et_content;
	private EditText et_person;
	private EditText et_shi;
	private EditText et_fen;
	private Spinner sp_yue;
	private Spinner sp_ri;
	private Spinner sp_nian;
	String [] yuearray;
	String[] nianarray;
	String[] riarray;
	private TextView tv_cre;
	private TextView tv_back;
	String yue;
	String nian;
	String ri;
	
String time;
ProgressBar pb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_spo);
	
		et_title = (EditText) findViewById(R.id.et_spotitle);
		et_content = (EditText) findViewById(R.id.et_spocontent);
		et_person = (EditText) findViewById(R.id.et_spoperson);
		et_shi = (EditText) findViewById(R.id.et_shi);
		et_fen = (EditText) findViewById(R.id.et_fen);
		sp_yue = (Spinner) findViewById(R.id.sp_yue);
		sp_nian = (Spinner) findViewById(R.id.sp_nian);
		sp_ri = (Spinner) findViewById(R.id.sp_ri);
		tv_cre = (TextView) findViewById(R.id.crespo);
		tv_back = (TextView) findViewById(R.id.tv_back);
		yuearray=getResources().getStringArray(R.array.yue);
		pb=(ProgressBar) findViewById(R.id.inv_pro);
tv_back.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		finish();
	}
});
		nianarray=getResources().getStringArray(R.array.nian);
		final Calendar calendar=Calendar.getInstance();
//		calendar.getActualMaximum(Calendar.DATE);
		
		ArrayAdapter<String> yueadapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner, yuearray);
		ArrayAdapter<String> nianadapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner, nianarray);

		sp_yue.setAdapter(yueadapter);
		sp_nian.setAdapter(nianadapter);
		if(calendar.get(Calendar.YEAR)==2015){
 sp_nian.setSelection(1);} else{
	 sp_nian.setSelection(0);
 }   
sp_yue.setSelection(calendar.get(Calendar.MONTH));

     sp_yue.setOnItemSelectedListener(new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
if(arg2==0||arg2==2||arg2==4||arg2==6||arg2==7||arg2==9||arg2==11){
	riarray=new String[]{"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	ArrayAdapter<String> riadapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner, riarray);
	 sp_ri.setAdapter(riadapter);
	 sp_ri.setSelection(calendar.get(Calendar.DAY_OF_MONTH)-1);
}else if(arg2==1){
	 riarray=new String[]{"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"};
	ArrayAdapter<String> riadapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner, riarray);
	 sp_ri.setAdapter(riadapter);
	 sp_ri.setSelection(calendar.get(Calendar.DAY_OF_MONTH)-1);
}else if(arg2==3||arg2==5||arg2==8||arg2==10){
riarray=new String[]{"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
	ArrayAdapter<String> riadapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner, riarray);
	 sp_ri.setAdapter(riadapter);
	 sp_ri.setSelection(calendar.get(Calendar.DAY_OF_MONTH)-1);
}
yue=yuearray[arg2];
	}
		
		
		
		

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	});
     
     
     sp_ri.setOnItemSelectedListener(new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
			ri=riarray[arg2];
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	});
     sp_nian.setOnItemSelectedListener(new OnItemSelectedListener() {

 		@Override
 		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
 				long arg3) {
 			nian=nianarray[arg2];
 		}

 		@Override
 		public void onNothingSelected(AdapterView<?> arg0) {
 			// TODO Auto-generated method stub
 			
 		}
 	});
     tv_cre.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
			
String title=et_title.getText().toString();
String content=et_content.getText().toString();
String person=et_person.getText().toString();
String shi=et_shi.getText().toString();
String fen=et_fen.getText().toString();
if(StringUtils.empty(title)==true||StringUtils.empty(content)==true||StringUtils.empty(person)==true||StringUtils.empty(shi)==true||StringUtils.empty(fen)==true||Integer.parseInt(person)<1){
	Toast.makeText(getApplicationContext(), R.string.biaoticuowu,Toast.LENGTH_SHORT).show();
}else if(Integer.parseInt(shi)<0||Integer.parseInt(shi)>23||Integer.parseInt(fen)<0||Integer.parseInt(fen)>59)
{
		Toast.makeText(getApplicationContext(), R.string.truetime,Toast.LENGTH_SHORT).show();
	}else if(shi.length()<2||fen.length()<2){
		Toast.makeText(getApplicationContext(), R.string.addzero,Toast.LENGTH_SHORT).show();
	}else{
		tv_cre.setClickable(false);
		pb.setVisibility(View.VISIBLE);

		time=nian+"-"+yue+"-"+ri+" "+shi+":"+fen;  
		FinalHttp finalHttp=new FinalHttp();
		AjaxParams params=new AjaxParams();
		params.put("title",title);
		params.put("content",content);
		params.put("needperson",person);
		params.put("time",time);
		params.put("uid",AllStatic.loginConfig.getId()+"");
		params.put("flag", Constant.CREATESPORT+"");
		String url="http://123.56.95.134:8080/Testlife/servlet/ServletSpo";
		
         finalHttp.post(url, params, new AjaxCallBack<Object>() {		
	     @Override
	     public void onSuccess(Object t) {
	  	// TODO Auto-generated method stub
		  super.onSuccess(t);
		  pb.setVisibility(View.INVISIBLE);
		  JSONObject responsejson;
		 try {
			boolean value;
			responsejson = new JSONObject(t.toString());
			value = responsejson.getBoolean("state");
			System.out.println(value+"-----------vvvvvv");
			if(value==true){
				int id=responsejson.getInt("id");
				int head=responsejson.getInt("head");
				int needperson=responsejson.getInt("needperson");
			    String title=responsejson.getString("title");
			    String content=responsejson.getString("content");
				String uname=responsejson.getString("uname");
				String time=responsejson.getString("time");
				System.out.println(time+"ttttttime");
				String createtime=responsejson.getString("createtime");
				FinalDb finalDb=FinalDb.create(getApplicationContext());
				System.out.println(AllStatic.loginConfig.getId()+"-----------vvvvvv");
                Sport sport=new Sport(id, head, AllStatic.loginConfig.getId(), uname, title, content, time, createtime, needperson, AllStatic.loginConfig.getId());
				finalDb.save(sport);
//				AllStatic.sport=sport;
				Intent intent=new Intent(getApplicationContext(),SpoReplyActivity.class);
				intent.putExtra("spoid", sport.getSpoid());	
				intent.putExtra("suid", sport.getUid());	
				startActivity(intent);
				finish();
				}else  {
			Toast.makeText(getApplicationContext(), "时间不对或者其他什么原因创建不了...", Toast.LENGTH_SHORT).show();

			}		tv_cre.setClickable(true);

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
		  tv_cre.setClickable(true);
Toast.makeText(getApplicationContext(), R.string.wangluo, Toast.LENGTH_SHORT).show();
	}
	
});
		
		
	}
}
		
	});
	}
//	@Override
//	public boolean onMenuItemSelected(int featureId, MenuItem item) {
//		// TODO Auto-generated method stub
//		switch (item.getItemId()) {
//		case android.R.id.home:
//			finish();
//			break;
//
//}	
		
//		return super.onMenuItemSelected(featureId, item);}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_create_spo, menu);
		return true;
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

}
