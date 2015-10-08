package com.example.project_client1120;

import java.util.Calendar;




import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.support.AllStatic;
import com.example.utils.Constant;
import com.example.utils.StringUtils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class UpSpoActivity extends Activity {
	private EditText et_title;
	private EditText et_content;
	private EditText et_person;
	private EditText et_shi;
	private EditText et_fen;
	private Spinner sp_yue;
	private Spinner sp_ri;
	private Spinner sp_nian;
	String [] yuearray;
	String[] riarray;
	String[] nianarray;
	private TextView tv_cre;
	private TextView tv_back;

	String yue;
	String nian;
	String ri;
String time;
ProgressBar pb;
int spoid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_up_spo);
		
		et_title = (EditText) findViewById(R.id.et_spotitle);
		et_content = (EditText) findViewById(R.id.et_spocontent);
		et_person = (EditText) findViewById(R.id.et_spoperson);
		et_shi = (EditText) findViewById(R.id.et_shi);
		et_fen = (EditText) findViewById(R.id.et_fen);
		sp_yue = (Spinner) findViewById(R.id.sp_yue);
		sp_nian = (Spinner) findViewById(R.id.sp_nian);
		sp_ri = (Spinner) findViewById(R.id.sp_ri);
		tv_cre = (TextView) findViewById(R.id.crespo);
		Intent intent=getIntent();
		spoid=intent.getIntExtra("spoid", 0);
		yuearray=getResources().getStringArray(R.array.yue);
		pb=(ProgressBar) findViewById(R.id.inv_pro);
		tv_back = (TextView) findViewById(R.id.tv_back);

//        sport=AllStatic.sport;
    
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
sp_ri.setSelection(calendar.get(Calendar.DAY_OF_MONTH-1));
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
			
final String title=et_title.getText().toString();
final String content=et_content.getText().toString();
final String person=et_person.getText().toString();
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
		pb.setVisibility(View.VISIBLE);

		time=nian+"-"+yue+"-"+ri+" "+shi+":"+fen;  
		FinalHttp finalHttp=new FinalHttp();
		AjaxParams params=new AjaxParams();
		params.put("title",title);
		params.put("content",content);
		params.put("uid",AllStatic.loginConfig.getId()+"");
		params.put("sid",spoid+"");
		params.put("time",time);
		params.put("needperson",person);
		params.put("flag", Constant.UPSPO+"");
		String url="http://123.56.95.134:8080/Testlife/servlet/ServletInv";
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
				System.out.println(AllStatic.loginConfig.getId()+"-----------vvvvvv");
				
				Intent intent=new Intent(getApplicationContext(),SpoReplyActivity.class);
				intent.putExtra("sid", spoid);	
				startActivity(intent);
				}else  {
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
		getMenuInflater().inflate(R.menu.activity_up_spo, menu);
		return true;
	}

}
