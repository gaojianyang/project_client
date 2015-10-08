package com.example.utils;

import java.util.List;


import com.example.pojo.TianQi;
import com.example.project_client1120.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TianQiAdapter extends BaseAdapter{
	  private List<TianQi> list;
		LayoutInflater inflater;
		
		
		
		public TianQiAdapter(Context context,List<TianQi> list){
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
		TextView tv_time;
		TextView tv_temp;
		TextView tv_weather;

	}
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder vHolder=null;
		if(arg1==null){
					vHolder=new ViewHolder();
				   arg1=inflater.inflate(R.layout.tianqiadapter, null);
					vHolder.tv_time=(TextView) arg1.findViewById(R.id.tqrj);
					vHolder.tv_temp=(TextView) arg1.findViewById(R.id.temp);
					vHolder.tv_weather=(TextView) arg1.findViewById(R.id.weather);
					arg1.setTag(vHolder);
				}else{
					vHolder=(ViewHolder) arg1.getTag();
				}
				
		vHolder.tv_time.setText(list.get(arg0).getTime());
		vHolder.tv_weather.setText(list.get(arg0).getWeather());
		vHolder.tv_temp.setText(list.get(arg0).getTemp());
		return arg1;
	}

}
