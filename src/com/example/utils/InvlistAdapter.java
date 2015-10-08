package com.example.utils;

import java.util.List;


import com.example.pojo.Invitation;
import com.example.project_client1120.R;
import com.example.support.AllStatic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class InvlistAdapter extends BaseAdapter{
    private List<Invitation> list;
LayoutInflater inflater;
	public InvlistAdapter(List<Invitation> list,Context context) {
		this.list=list;
		this.inflater=LayoutInflater.from(context);	
		
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
		TextView tv_createtime;

	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder vHolder=null;
		if(arg1==null){
			vHolder=new ViewHolder();
		arg1=inflater.inflate(R.layout.adapterinv, null);
			vHolder.imageView=(ImageView) arg1.findViewById(R.id.im_invhead);
			vHolder.tv_name=(TextView) arg1.findViewById(R.id.inv_name);
			vHolder.tv_title=(TextView) arg1.findViewById(R.id.inv_title);
			vHolder.tv_zan=(TextView) arg1.findViewById(R.id.inv_zan);
			vHolder.tv_time=(TextView) arg1.findViewById(R.id.inv_time);
			vHolder.tv_createtime=(TextView) arg1.findViewById(R.id.inv_createtime);
			arg1.setTag(vHolder);
		}else{
			vHolder=(ViewHolder) arg1.getTag();
		}
		if(list.size()>30&&arg0<2){
			vHolder.imageView.setImageResource(AllStatic.images[list.get(arg0).getHead()]);
			vHolder.tv_name.setText(list.get(arg0).getUname());
			vHolder.tv_title.setText(list.get(arg0).getTitle());
			vHolder.tv_zan.setText("ÖÃ¶¥");
			vHolder.tv_time.setText(list.get(arg0).getTime());
			vHolder.tv_createtime.setText(list.get(arg0).getCreatetime());}
		else {
		vHolder.imageView.setImageResource(AllStatic.images[list.get(arg0).getHead()]);
		vHolder.tv_name.setText(list.get(arg0).getUname());
		vHolder.tv_title.setText(list.get(arg0).getTitle());
		vHolder.tv_zan.setText(list.get(arg0).getZan()+"");
		vHolder.tv_time.setText(list.get(arg0).getTime());}
		vHolder.tv_createtime.setText(list.get(arg0).getCreatetime());
		
		return arg1;
	}

}
