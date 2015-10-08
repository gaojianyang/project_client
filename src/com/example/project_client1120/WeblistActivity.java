package com.example.project_client1120;


import java.util.ArrayList;


import java.util.List;

import com.example.pojo.WebUrl;
import com.example.utils.WebAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class WeblistActivity extends Activity {
private ListView list;
List<WebUrl> weblist=new ArrayList<WebUrl>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.activity_weblist);
		WebUrl baidu=new WebUrl(0, "百度", "什么都可以度");
		WebUrl elema=new WebUrl(1, "饿了么", "订外卖的，还有个叫美团的");
		WebUrl taobao=new WebUrl(2, "淘宝", "还有个叫京东的，买卖东西的网站");
		WebUrl sina=new WebUrl(3, "新浪", "看新闻的");
		WebUrl guokr=new WebUrl(4, "果壳网", "当冷知识看就行了");
	weblist.add(baidu);
	weblist.add(elema);
	weblist.add(taobao);
	weblist.add(sina);
	weblist.add(guokr);
		list=(ListView) findViewById(R.id.weblist);
		TextView tvback = (TextView) findViewById(R.id.tv_back);
		tvback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		WebAdapter adapter=new WebAdapter(weblist, this);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(),WebActivity.class);
				intent.putExtra("type", arg2+1);
				startActivity(intent);
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_weblist, menu);
		return true;
	}

}
