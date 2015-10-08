package com.example.project_client1120;

import net.tsz.afinal.FinalDb;


import com.example.pojo.ChatMessage;
import com.example.pojo.Message;
import com.example.support.AllStatic;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class XiaoXiActivity extends Activity implements OnClickListener{
	 int isjieshou=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xiao_xi);
		TextView back = (TextView) findViewById(R.id.tv_back);
		TextView zhu = (TextView) findViewById(R.id.clearzhu);
//		TextView kaiguan = (TextView) findViewById(R.id.kaiguan);
		TextView liao = (TextView) findViewById(R.id.clearliao);
		
		
		back.setOnClickListener(this);
		zhu.setOnClickListener(this);
		liao.setOnClickListener(this);
//		kaiguan.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_xiao_xi, menu);
		return true;
	}
//	private void startServicesss(){
//		Intent intent=new Intent();
//		intent.setClass(getApplicationContext(), MessageService.class);
//		getApplicationContext().startService(intent);
//		
//	}
//	private void stopServicess(){
//		Intent intent=new Intent();
//		intent.setClass(getApplicationContext(), MessageService.class);
//		getApplicationContext().stopService(intent);
//	}


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.tv_back:
			finish();
			break;
	
case R.id.clearzhu:
			FinalDb finalDb=FinalDb.create(this);
			finalDb.deleteByWhere(Message.class, "userid="+AllStatic.loginConfig.getId());
			Toast.makeText(getApplicationContext(), "清除成功", Toast.LENGTH_SHORT).show();

			break;
case R.id.clearliao:
	FinalDb finalDb2=FinalDb.create(this);
	finalDb2.deleteByWhere(ChatMessage.class, "userid="+AllStatic.loginConfig.getId());
	Toast.makeText(getApplicationContext(), "清除成功", Toast.LENGTH_SHORT).show();

	break;
			
			

		default:
			break;
		}
	}

}
