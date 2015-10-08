package com.example.project_client1120;

import java.util.Calendar;

import net.tsz.afinal.FinalDb;

import com.example.pojo.Riji;
import com.example.support.AllStatic;
import com.example.utils.StringUtils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class XieRijiActivity extends Activity {
int type;
String riqi;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xie_riji);
		TextView tvback = (TextView) findViewById(R.id.tv_back);
		TextView tvriqi = (TextView) findViewById(R.id.riqi);
		TextView tvwancheng = (TextView) findViewById(R.id.tv_wancheng);
		final EditText etcontent = (EditText) findViewById(R.id.tv_con);
		Intent intent=getIntent();
		type=intent.getIntExtra("type", 0);
		
		if(type==1){
		riqi=intent.getStringExtra("riqi");
		String content=	intent.getStringExtra("content");
             tvriqi.setText(riqi);
			etcontent.setText(content);
		}else{Calendar calendar=Calendar.getInstance();
		int nian=calendar.get(Calendar.YEAR);
        int yue=calendar.get(Calendar.MONTH)+1;
        int ri=calendar.get(Calendar.DAY_OF_MONTH);
        riqi=nian+"-"+yue+"-"+ri;}
		
		tvriqi.setText(riqi);
		tvback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	tvwancheng.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String contetn = etcontent.getText().toString();
				if(StringUtils.empty(contetn)){
					Toast.makeText(getApplicationContext(), "输入不能为空", Toast.LENGTH_SHORT).show();
				}else{
					Riji riji=new Riji();
					riji.setContent(contetn);
					riji.setRiqi(riqi);
					riji.setUserid(AllStatic.loginConfig.getId());
					FinalDb finalDb=FinalDb.create(getApplicationContext());
					finalDb.save(riji);
					Toast.makeText(getApplicationContext(), "输入成功", Toast.LENGTH_SHORT).show();
					finish();
				}
				
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_xie_riji, menu);
		return true;
	}

}
