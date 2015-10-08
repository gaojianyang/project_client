package com.example.project_client1120;

import java.util.ArrayList;







import java.util.List;

import com.example.UI.PopMenu;
import com.example.utils.Constant;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;


public class TabhostActivity extends FragmentActivity implements OnClickListener{
	
//	用来存放显示的页面
	private List<Fragment> pages  = new ArrayList<Fragment>();
	ImageView[] imgs;
	NotificationManager m_NotificationManager;
	 private ImageView add;
	Notification m_Notification;
	Intent m_Intent;
//int page;
	PendingIntent m_PendingIntent;
	 ViewPager viewPager;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabhost);
        registerBoradcastReceiver();
        MessageActivity f01=new MessageActivity();
        FocusActivity f02=new FocusActivity();
        SchoolActivity f03=new SchoolActivity();
        pages.add(f01);
        pages.add(f02);
        pages.add(f03);
//       TextView textView1=(TextView) findViewById(R.id.tv1);
//       TextView textView2=(TextView) findViewById(R.id.textView2);
//       TextView textView3=(TextView) findViewById(R.id.textView3);
		add = (ImageView) findViewById(R.id.im_ada);
		ImageView uuse = (ImageView) findViewById(R.id.uuse);
	
		ImageView i1 = (ImageView) findViewById(R.id.imageView1);
		ImageView i2 = (ImageView) findViewById(R.id.imageView2);
		ImageView i3 = (ImageView) findViewById(R.id.imageView3);
		
		
       LinearLayout ch1 = (LinearLayout) findViewById(R.id.channel1);
       LinearLayout ch2 = (LinearLayout) findViewById(R.id.channel2);
       LinearLayout ch3 = (LinearLayout) findViewById(R.id.channel3);
       
       ch1.setOnClickListener(this);
       ch2.setOnClickListener(this);
       ch3.setOnClickListener(this);
     
       add.setOnClickListener(this);
       uuse.setOnClickListener(this);
       imgs=new ImageView[]{i1,i2,i3} ;
//       ch1.setBackgroundColor(Color.parseColor("#ffffff"));
//       textView2.setTextColor(Color.parseColor("#bfefff"));
//       textView3.setTextColor(Color.parseColor("#bfefff"));
        viewPager = (ViewPager) findViewById(R.id.viewPager);
       viewPager.setAdapter(new MyListener(this.getSupportFragmentManager()));
      	viewPager.setCurrentItem(1);

        viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				
						switch (arg0) {
						case 0:
							imgs[0].setImageResource(R.drawable.fmb1);
							imgs[1].setImageResource(R.drawable.hhb);
							imgs[2].setImageResource(R.drawable.xb);
							break;
                        case 1:
                        	imgs[0].setImageResource(R.drawable.mmb);
							imgs[1].setImageResource(R.drawable.fhhb);
							imgs[2].setImageResource(R.drawable.xb);

							break;
						case 2:
								imgs[0].setImageResource(R.drawable.mmb);
								imgs[1].setImageResource(R.drawable.hhb);
								imgs[2].setImageResource(R.drawable.fxb);
	
								break;
						default:
							break;
						}
						
					
				
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}
		});
        
      
    }
//    private ServiceConnection conn = new ServiceConnection() {  
//	     @Override 
//		      public void onServiceConnected(ComponentName name, IBinder service) {  
//		       
//	     System.out.println("qidaongle"+"-----------");
//	     
//	     }  
//		        @Override 
//		        public void onServiceDisconnected(ComponentName name) {  
//		         }  
//		    };  
//    
    class MyListener extends FragmentPagerAdapter{

		public MyListener(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return pages.get(arg0);
		}
//@Override
//public Object instantiateItem(ViewGroup container, int position) {
//// TODO Auto-generated method stub
//	 View view = imageViews.get(position);
//	  view.setOnClickListener(new OnClickListener() {
//	   
//	   @Override
//	   public void onClick(View v) {
//	    // TODO Auto-generated method stub
//	    Log.e("xl", "xl:arrive here.");
//	   }
//	  });
//	  ViewPager viewPager = (ViewPager) container;
//	  viewPager.addView(view);
//	  return imageViews.get(position);
//}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pages.size();
		}
//    	
    }
    @Override
    	protected void onDestroy() {
    		// TODO Auto-generated method stub
    		super.onDestroy();
    		unregisterBoradcastReceiver();
    	
    	}
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_tabhost, menu);
		return true;
	}
	
	

	
	
	
	public void registerBoradcastReceiver() {
		IntentFilter myIntentFilter = new IntentFilter();
		myIntentFilter.addAction(Constant.ACTION_NAME);
		// 注册广播
		getApplicationContext().registerReceiver(mBroadcastReceiver, myIntentFilter);

	}
	public void unregisterBoradcastReceiver() {
		IntentFilter myIntentFilter = new IntentFilter();
		myIntentFilter.addAction(Constant.ACTION_NAME);
		// 注册广播
		getApplicationContext().unregisterReceiver(mBroadcastReceiver);

	}
	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(Constant.ACTION_NAME)) {
				showNotification();
			}
		}

	};


	private void showNotification() {
		// 创建一个NotificationManager的引用
		m_NotificationManager = (NotificationManager) getApplicationContext()
				.getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
		m_Intent = new Intent(getApplicationContext(), TabhostActivity.class);
//		m_Intent.putExtra("flag", "ok");
		m_PendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, m_Intent,
				0);
		m_Notification = new Notification();
		m_Notification.icon = R.drawable.tshirt48;
		m_Notification.flags= Notification.FLAG_AUTO_CANCEL;
		// 当我们点击通知时显示的内容
		// 通知时发出的默认声音
		m_Notification.defaults = Notification.DEFAULT_SOUND;
		// 设置通知显示的参数
		m_Notification.setLatestEventInfo(getApplicationContext(), "消息", "你有新消息",
				m_PendingIntent);
		// 这个可以理解为开始执行这个通知
		m_NotificationManager.notify(0, m_Notification);
		//传递参数
		m_Intent.putExtra("flag", "ok");

	}
//	@Override
//		protected void onNewIntent(Intent intent) {
//			// TODO Auto-generated method stub
//			super.onNewIntent(intent);
//			  String flag = intent.getStringExtra("flag");
//			    if (flag != null)
//			    {
//			    	viewPager.setCurrentItem(0);
//			    }
//		}
@Override
	protected void onResume() {
		// TODO Auto-generated method stub
	clearNotification();
		super.onResume();
	}
	private void clearNotification() {
		// 启动后删除之前我们定义的通知
		m_NotificationManager = (NotificationManager) getApplicationContext()
				.getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
		m_NotificationManager.cancelAll();

	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.channel1:
			       viewPager.setCurrentItem(0);
			
			break;
case R.id.channel2:
	viewPager.setCurrentItem(1);
			break;
case R.id.channel3:
	viewPager.setCurrentItem(2);
		
	break;

	
case R.id.im_ada:
	final PopMenu menu=new PopMenu(getApplicationContext());
	menu.addItem("创建帖子");
	menu.addItem("创建活动");
	menu.addItem("创建社团");
	menu.showAsDropDown(add);
	menu.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			// TODO Auto-generated method stub
			menu.dismiss();
		switch (arg2) {
		case 0:
			Intent intent2 = new Intent(getApplicationContext(), CreateInvActivity.class);
			startActivity(intent2);
			break;
		case 1:
			Intent intent1 = new Intent(getApplicationContext(), CreateSpoActivity.class);
			startActivity(intent1);
			
			break;
		case 2:
			Intent intent3 = new Intent(getApplicationContext(), CreateClubActivity.class);
			startActivity(intent3);
			
			
			break;
		default:
			break;
		}
		
		
		}
	});
	break;
case R.id.uuse:
	
	Intent intent1 = new Intent(getApplicationContext(), SheZhiActivity.class);
	startActivity(intent1);
	break;
//case R.id.im_user:
//	PopMenu menu2=new PopMenu(getApplicationContext());
//	menu2.addItem("查找用户");
//	menu2.addItem("更换账号");
//	
//	if(isjieshou==0){	menu2.addItem("接收消息");}else{	menu2.addItem("停止接收");}
//
//
//	menu2.showAsDropDown(add);
//	menu2.setOnItemClickListener(new OnItemClickListener() {
//
//		@Override
//		public void onItemClick(AdapterView<?> arg0, View arg1,
//				int arg2, long arg3) {
//			// TODO Auto-generated method stub
//		
//		switch (arg2) {
//		case 0:
//			Intent intent0=new Intent(getApplicationContext(),SeaUserActivity.class);
//			intent0.putExtra("type", 0);
//			startActivity(intent0);
//			break;
//		case 1:
//			if(isjieshou==1){
//			stopServicess();
//			isjieshou=0;}
//			Intent intent5=new Intent(getApplicationContext(),LoginActivity.class);
//			intent5.putExtra("type", 1);
//			startActivity(intent5);
//			finish();
//			break;
//		case 2:
//			if(isjieshou==0){
//			startServicesss();
//			isjieshou=1;}else{stopServicess();
//			isjieshou=0;}
//			break;
//	
//		default:
//			break;
//		}
//		
//		
//		}
//	});
//		
//	break;


		default:
			break;
		}
	}
}


