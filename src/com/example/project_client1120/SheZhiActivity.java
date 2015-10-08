package com.example.project_client1120;

import com.example.Service.MessageService;
import com.example.pojo.LoginConfig;
import com.example.support.AllStatic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SheZhiActivity extends Activity implements OnClickListener{
	 LoginConfig loginConfig;
		ImageView im_xiao ;
	 int isjieshou=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shezhi);
		LinearLayout tv_xiugai = (LinearLayout) findViewById(R.id.xiugai);
		LinearLayout tv_change = (LinearLayout) findViewById(R.id.change);
		LinearLayout hah = (LinearLayout) findViewById(R.id.im_xiaoxi);
		ImageView imhead = (ImageView) findViewById(R.id.im_mehead);
		im_xiao = (ImageView) findViewById(R.id.editapp);
		   loginConfig=AllStatic.loginConfig;
		imhead.setImageResource(AllStatic.images[loginConfig.getHead()]);
		 TextView tv_person = (TextView)findViewById(R.id.te_meperson);
		 TextView tv_back = (TextView)findViewById(R.id.tv_back);
			TextView tv_mmm = (TextView) findViewById(R.id.tv_memmm);
			tv_mmm.setText(loginConfig.getName());
			tv_person.setText(loginConfig.getPersonal());
			

			ImageView imsex = (ImageView) findViewById(R.id.im_sex);
			if(loginConfig.getGender().equals("男")){
				imsex.setImageResource(R.drawable.boy);
			}else{	imsex.setImageResource(R.drawable.girl);
	}
			  tv_xiugai.setOnClickListener(this);
		       tv_change.setOnClickListener(this);
		       im_xiao.setOnClickListener(this);
		       hah.setOnClickListener(this);
//		      tv_shengming.setOnClickListener(this);
		      tv_back.setOnClickListener(this);
	}
	private void startServicesss(){
		Intent intent=new Intent();
		intent.setClass(getApplicationContext(), MessageService.class);
		getApplicationContext().startService(intent);
		
	}
	private void stopServicess(){
		Intent intent=new Intent();
		intent.setClass(getApplicationContext(), MessageService.class);
		getApplicationContext().stopService(intent);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		
		
	case R.id.xiugai:
		Intent intent=new Intent(getApplicationContext(),UpdateUserActivity.class);
		startActivity(intent);
		
		break;
//	case R.id.shengming:
//		Intent intent2=new Intent(getApplicationContext(),ShuoMingActivity.class);
//		startActivity(intent2);
//		
//		break;
	case R.id.change:
		if(isjieshou==1){
			stopServicess();}
			Intent intent5=new Intent(getApplicationContext(),LoginActivity.class);
			intent5.putExtra("type", 1);
			startActivity(intent5);
			finish();
		break;
	case R.id.editapp:
		if(isjieshou==1){
			im_xiao.setImageResource(R.drawable.guan);
			stopServicess();
			isjieshou=0;
			Toast.makeText(getApplicationContext(), "你停止了消息接收", Toast.LENGTH_SHORT).show();
		}else{
	im_xiao.setImageResource(R.drawable.kai);
	startServicesss();
	isjieshou=1;
	Toast.makeText(getApplicationContext(), "你开启了消息接收(80秒接收一次)", Toast.LENGTH_SHORT).show();
			}	
		break;
	case R.id.tv_back:
			finish();
		break;
	case R.id.im_xiaoxi:
		Intent intent6=new Intent(getApplicationContext(),XiaoXiActivity.class);
		startActivity(intent6);
		break;}
	}

}
