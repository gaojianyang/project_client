package com.example.project_client1120;

import android.os.Bundle;


import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class WebActivity extends Activity {
int type;
WebView wv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
	TextView tv=(TextView) findViewById(R.id.tv_back);
	tv.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
		}
	});
		
		wv = (WebView) findViewById(R.id.wv);
		Intent intent=getIntent();
		type=intent.getIntExtra("type", 0);
	
		wv.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return true;
			}
		});
		switch (type) {
		case 1:
			wv.loadUrl("http://www.baidu.com");
			break;
		case 2:
			wv.loadUrl("http://www.ele.me");
			break;
		case 3:
			wv.loadUrl("http://www.taobao.com");
			break;
		case 4:
			wv.loadUrl("http://www.sina.com");
			break;
		case 5:
			wv.loadUrl("http://www.guokr.com");
			break;

		default:
			break;
		}
		
		
		
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {
//			返回上一个打开的页面
			wv.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}
//	@Override
//	public boolean onMenuItemSelected(int featureId, MenuItem item) {
//		// TODO Auto-generated method stub
//		if(item.getItemId()==android.R.id.home){
//			
//			finish();
//			
//		}
//		
//		
//		
//		
//		return super.onMenuItemSelected(featureId, item);
//	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_web, menu);
		return true;
	}

}
