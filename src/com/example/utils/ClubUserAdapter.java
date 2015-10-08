package com.example.utils;

import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pojo.User;
import com.example.project_client1120.R;
import com.example.support.AllStatic;

public class ClubUserAdapter extends BaseAdapter{

	List<User> list;
	LayoutInflater inflater;
	Context context;
	int arg3;
	
	public ClubUserAdapter(List<User> list,Context context){
		this.context=context;
		this.list=list;
		inflater=LayoutInflater.from(context);
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	class ViewHolder{
		ImageView imageView;
		TextView tv_content;
		TextView paiming;

	}
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder vHolder=null;
		this.arg3=arg0;
		if(arg1==null){
			vHolder=new ViewHolder();
		arg1=inflater.inflate(R.layout.chatadapter, null);
			vHolder.imageView=(ImageView) arg1.findViewById(R.id.im_chathead);
			vHolder.tv_content=(TextView) arg1.findViewById(R.id.chat_content);
			vHolder.paiming=(TextView) arg1.findViewById(R.id.chat_time);
			arg1.setTag(vHolder);
		}else{
			vHolder=(ViewHolder) arg1.getTag();
		}
		vHolder.imageView.setImageResource(AllStatic.images[list.get(arg0).getHead()]);
		vHolder.tv_content.setText(list.get(arg0).getName());
		vHolder.paiming.setText(arg0+1+"");
		
//    vHolder.tv_name.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//			Intent intent=new Intent(context,UserInfoActivity.class);
//			intent.putExtra("uid",list.get(arg3).getUid());
//			
//			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			context.startActivity(intent);
//			}
//		});
//		
		
		return arg1;
	}
}
