package com.example.project_client1120;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.UI.HorizontalScrollViewAdapter;
import com.example.UI.MyHorizontalScrollView;
import com.example.UI.MyHorizontalScrollView.CurrentImageChangeListener;
import com.example.UI.MyHorizontalScrollView.OnItemClickListener;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class HeadCActivity extends Activity {
	private MyHorizontalScrollView mHorizontalScrollView;  
    private HorizontalScrollViewAdapter mAdapter;  
    private ImageView mImg;  
    int head;
	private TextView tvsend;

    private List<Integer> mDatas = new ArrayList<Integer>(Arrays.asList(R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c5,R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,R.drawable.c10,R.drawable.c11,R.drawable.c12,R.drawable.c13,R.drawable.c14 )); 
   public static final String KEY_USER_HEAD="KEY_USER_HEAD";  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_head_c);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		 mImg = (ImageView) findViewById(R.id.id_content);  
		  TextView sure = (TextView) findViewById(R.id.tv_surehead);
			tvsend=(TextView) findViewById(R.id.tv_back);
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
		getMenuInflater().inflate(R.menu.activity_head_c, menu);
		return true;
	}

}
