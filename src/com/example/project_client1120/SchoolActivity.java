package com.example.project_client1120;

import android.content.Intent;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SchoolActivity extends Fragment implements OnClickListener{
	 private View mMainView;
	private LinearLayout tv1;
	private LinearLayout tv2;
	private LinearLayout tv4;
	private LinearLayout tv5;
	private LinearLayout tv6;
	private LinearLayout inv;
	private LinearLayout spo;
	private LinearLayout club;
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    		Bundle savedInstanceState) {
	    	// TODO Auto-generated method stub
	    	 ViewGroup p=(ViewGroup) mMainView.getParent();    
	    	 if(p!=null){
	    	 	p.removeAllViewsInLayout();
	    	 }
	    	 
	    	 return mMainView;
	    	
	    }
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater=getActivity().getLayoutInflater();

		mMainView=inflater.inflate(R.layout.activity_school,(ViewGroup) getActivity().findViewById(R.id.viewPager),false);
           tv1 = (LinearLayout) mMainView.findViewById(R.id.tv_school1);
           tv2 = (LinearLayout) mMainView.findViewById(R.id.tv_school2);
           tv4 = (LinearLayout) mMainView.findViewById(R.id.tv_school4);
           tv5 = (LinearLayout) mMainView.findViewById(R.id.tv_school5);
           tv6 = (LinearLayout) mMainView.findViewById(R.id.tv_school6);
           inv = (LinearLayout) mMainView.findViewById(R.id.inv);
           spo = (LinearLayout) mMainView.findViewById(R.id.spo);
           club = (LinearLayout) mMainView.findViewById(R.id.club);
           tv1.setOnClickListener(this);
           tv2.setOnClickListener(this);
           tv4.setOnClickListener(this);
           tv5.setOnClickListener(this);
           tv6.setOnClickListener(this);
           inv.setOnClickListener(this);
           spo.setOnClickListener(this);
           club.setOnClickListener(this);
           
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.tv_school1:
			Intent intent1=new Intent(getActivity(),ListUserActivity.class);
			intent1.putExtra("type", 2);
			startActivity(intent1);
			break;
	   case R.id.tv_school2:
		Intent intent2=new Intent(getActivity(),ListInvCActivity.class);
		intent2.putExtra("type", 3);
		startActivity(intent2);
			
			break;
	
	  case R.id.tv_school4:
		Intent intent4=new Intent(getActivity(),XinShengActivity.class);
		startActivity(intent4);
		
		break;
	  case R.id.tv_school5:
		Intent intent5=new Intent(getActivity(),WeblistActivity.class);
		startActivity(intent5);
		break;
	  case R.id.tv_school6:
		Intent intent6=new Intent(getActivity(),ShuoMingActivity.class);
		startActivity(intent6);
		break;
	  case R.id.inv:
			Intent intent7=new Intent(getActivity(),RijiActivity.class);
			startActivity(intent7);
			break;
	  case R.id.spo:
			Intent intent8=new Intent(getActivity(),TianQiActivity.class);
			startActivity(intent8);
			break;
	  case R.id.club:
			Intent intent9=new Intent(getActivity(),InvReplyActivity.class);
			startActivity(intent9);
			break;
			

		default:
			break;
		}
		
		
	}
}

	

