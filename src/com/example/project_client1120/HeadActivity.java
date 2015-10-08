package com.example.project_client1120;

import com.example.UI.HorizontalScrollViewAdapter;



import com.example.UI.MyHorizontalScrollView;
import com.example.UI.MyHorizontalScrollView.CurrentImageChangeListener;
import com.example.UI.MyHorizontalScrollView.OnItemClickListener;

import java.util.ArrayList;  
import java.util.Arrays;  
import java.util.List;  
  
import android.app.Activity;  
import android.content.Intent;
import android.graphics.Color;  
import android.os.Bundle;  
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;  
import android.view.View.OnClickListener;
import android.widget.ImageView;  
import android.widget.TextView;

public class HeadActivity extends Activity{
	private MyHorizontalScrollView mHorizontalScrollView;  
    private HorizontalScrollViewAdapter mAdapter;  
    private ImageView mImg;  
    int head;
	private TextView tvsend;

    private List<Integer> mDatas = new ArrayList<Integer>(Arrays.asList(  
    		R.drawable.a072,R.drawable.a172,R.drawable.a272,R.drawable.a372,R.drawable.a572,R.drawable.b1,R.drawable.b2,R.drawable.b3,R.drawable.b4,R.drawable.b5,R.drawable.b6,R.drawable.b7,R.drawable.b8,R.drawable.b9,R.drawable.b10,R.drawable.b11,R.drawable.b12,R.drawable.b13,R.drawable.b14,R.drawable.b15,R.drawable.b16,R.drawable.b17,R.drawable.b18,R.drawable.b19,R.drawable.b20,R.drawable.b21,R.drawable.b22,R.drawable.d1,R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5,R.drawable.d6,R.drawable.d7,R.drawable.d8,R.drawable.d9,R.drawable.d10,R.drawable.d11,R.drawable.d12,R.drawable.d13,R.drawable.d14,R.drawable.e1,R.drawable.e2,R.drawable.e3,R.drawable.e4,R.drawable.e5,R.drawable.e6,R.drawable.e7,R.drawable.g1,R.drawable.g2,R.drawable.g3,R.drawable.g4,R.drawable.g5,R.drawable.g6,R.drawable.g7,R.drawable.g8,R.drawable.g9,R.drawable.g10,
    		R.drawable.g11,R.drawable.g12,R.drawable.g13,R.drawable.g14,R.drawable.g15,R.drawable.g16,R.drawable.g17,R.drawable.g18,R.drawable.g19,R.drawable.g20,R.drawable.g21,R.drawable.h1,R.drawable.h2,R.drawable.h3,R.drawable.h4,R.drawable.h5,R.drawable.h6,R.drawable.h7,R.drawable.h8,R.drawable.h9,R.drawable.h20,R.drawable.h21,R.drawable.h22,R.drawable.h23,R.drawable.h24,R.drawable.h25,R.drawable.h26,R.drawable.h27,R.drawable.h28,R.drawable.h29,R.drawable.h30,R.drawable.h31,R.drawable.q1,R.drawable.q2,R.drawable.q3,R.drawable.q4,R.drawable.y1,R.drawable.y2,R.drawable.y3,R.drawable.y4,R.drawable.y5,R.drawable.y6,R.drawable.y7,R.drawable.y8,R.drawable.y9,R.drawable.y10,R.drawable.y11,R.drawable.y12,R.drawable.y13,R.drawable.y14,R.drawable.y15,R.drawable.y16,R.drawable.z1,R.drawable.z2,R.drawable.z3,R.drawable.z4));  
    public static final String KEY_USER_HEAD="KEY_USER_HEAD";  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_head);
		
		tvsend=(TextView) findViewById(R.id.tv_back);
		 mImg = (ImageView) findViewById(R.id.id_content);  
		  TextView sure = (TextView) findViewById(R.id.tv_surehead);
		  tvsend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		  sure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.putExtra(KEY_USER_HEAD, head);
				setResult(RESULT_OK, intent); 
				finish();
			}
		});
		  
		  
	        mHorizontalScrollView = (MyHorizontalScrollView) findViewById(R.id.id_horizontalScrollView);  
	        mAdapter = new HorizontalScrollViewAdapter(this, mDatas);  
	        //添加滚动回调  
	        mHorizontalScrollView  
	                .setCurrentImageChangeListener(new CurrentImageChangeListener()  
	                {  
	                    @Override  
	                    public void onCurrentImgChanged(int position,  
	                            View viewIndicator)  
	                    {  head=position;
	                        mImg.setImageResource(mDatas.get(position));  
	                        viewIndicator.setBackgroundColor(Color  
	                                .parseColor("#AA024DA4"));  
	                    }  
	                });  
	        //添加点击回调  
	        mHorizontalScrollView.setOnItemClickListener(new OnItemClickListener()  
	        {  
	  
	            @Override  
	            public void onClick(View view, int position)  
	            {  head=position;
	                mImg.setImageResource(mDatas.get(position));  
	                view.setBackgroundColor(Color.parseColor("#AA024DA4"));  
	            }  
	        });  
	        //设置适配器  
	        mHorizontalScrollView.initDatas(mAdapter);  
	    }  
	    
	  
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_head, menu);
		return true;
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
//		
//		return super.onMenuItemSelected(featureId, item);}
//	

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

	
}
