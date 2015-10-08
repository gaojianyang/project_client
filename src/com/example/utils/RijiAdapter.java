package com.example.utils;

import java.util.List;

import com.example.pojo.Riji;
import com.example.project_client1120.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RijiAdapter extends BaseAdapter{
      private List<Riji> list;
	LayoutInflater inflater;
	
	
	
	public RijiAdapter(Context context,List<Riji> list){
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
		TextView tv_title;
		TextView tv_time;

	}
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder vHolder=null;
if(arg1==null){
			vHolder=new ViewHolder();
		   arg1=inflater.inflate(R.layout.rijiadapter, null);
			vHolder.tv_title=(TextView) arg1.findViewById(R.id.tv_content);
			vHolder.tv_time=(TextView) arg1.findViewById(R.id.tv_riqi);
			arg1.setTag(vHolder);
		}else{
			vHolder=(ViewHolder) arg1.getTag();
		}
		
vHolder.tv_time.setText(list.get(arg0).getRiqi());
vHolder.tv_title.setText(list.get(arg0).getContent());
		
		return arg1;
	}

}
