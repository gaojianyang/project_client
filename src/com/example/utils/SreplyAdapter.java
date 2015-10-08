package com.example.utils;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pojo.Sreply;
import com.example.project_client1120.R;
import com.example.project_client1120.UserInfoActivity;
import com.example.support.AllStatic;

public class SreplyAdapter extends BaseAdapter{
    private List<Sreply> list;
    private	Context context;
LayoutInflater inflater;
int arg3;

	public SreplyAdapter(List<Sreply> list,Context context) {
		this.list=list;
		this.inflater=LayoutInflater.from(context);	
		this.context=context;
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
		TextView tv_name;
		TextView tv_title;
		TextView tv_zan;
		TextView tv_time;
		TextView tv_lou;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder vHolder=null;
		this.arg3=arg0;
		if(arg1==null){
			vHolder=new ViewHolder();
		arg1=inflater.inflate(R.layout.reolyadapter, null);
			vHolder.imageView=(ImageView) arg1.findViewById(R.id.im_invhead);
			vHolder.tv_name=(TextView) arg1.findViewById(R.id.inv_name);
			vHolder.tv_title=(TextView) arg1.findViewById(R.id.inv_title);
			vHolder.tv_zan=(TextView) arg1.findViewById(R.id.inv_zan);
			vHolder.tv_time=(TextView) arg1.findViewById(R.id.inv_time);
			vHolder.tv_lou=(TextView) arg1.findViewById(R.id.inv_loushu);
			arg1.setTag(vHolder);
		}else{
			vHolder=(ViewHolder) arg1.getTag();
		}
		vHolder.imageView.setImageResource(AllStatic.images[list.get(arg0).getHead()]);
		vHolder.tv_name.setText(list.get(arg0).getUname());
		vHolder.tv_title.setText(list.get(arg0).getContent());
		vHolder.tv_time.setText(list.get(arg0).getTime());
		vHolder.tv_zan.setVisibility(View.GONE);
		vHolder.tv_lou.setText(list.get(arg0).getId()+"¥");

	vHolder.tv_name.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent intent=new Intent(context,UserInfoActivity.class);
			intent.putExtra("uid",list.get(arg3).getUid());
			intent.putExtra("type", 0);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
			}
		});
		
		
		return arg1;
	}


}