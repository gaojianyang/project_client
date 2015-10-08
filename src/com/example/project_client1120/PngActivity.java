package com.example.project_client1120;

import java.util.ArrayList;


import java.util.List;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.Http.HttpJson;
import com.example.pojo.Images;
import com.example.utils.Constant;
import com.example.utils.ImageAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class PngActivity extends Activity {

	private ListView lv_im;
	ProgressBar pb;
	int invid;
	List<Images> list=new ArrayList<Images>();
ImageAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_png);
		TextView tvback=(TextView) findViewById(R.id.tv_back);
		tvback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		pb=(ProgressBar) findViewById(R.id.inv_pro);
		lv_im = (ListView) findViewById(R.id.lv_image);
		Intent intent=getIntent();
		invid=intent.getIntExtra("invid", 0);
		pb.setVisibility(View.VISIBLE);


		FinalHttp finalHttp = new FinalHttp();
		AjaxParams params = new AjaxParams();

		params.put("invid", invid + "");
		params.put("flag", Constant.INVIMAGE + "");
		String url = "http://123.56.95.134:8080/Testlife/servlet/ServletInv";

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
						JSONArray jsonArray = new JSONArray();
						jsonArray = responsejson.getJSONArray("image");
						HttpJson httpJson = HttpJson.getinstance();
						list = httpJson.invImage(jsonArray);
						adapter = new ImageAdapter(list,
								getApplicationContext());
						adapter.notifyDataSetChanged();
						lv_im.setAdapter(adapter);
					} else {
						Toast.makeText(getApplicationContext(), R.string.noman,
								Toast.LENGTH_SHORT).show();
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
		});

	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()==android.R.id.home){
			finish();
		}
		
		
		return super.onMenuItemSelected(featureId, item);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if ((keyCode == KeyEvent.KEYCODE_BACK) ) {
//			返回上一个打开的页面
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_png, menu);
		return true;
	}

}
