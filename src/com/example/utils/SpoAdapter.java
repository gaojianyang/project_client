package com.example.utils;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pojo.Sport;
import com.example.project_client1120.R;
import com.example.support.AllStatic;

public class SpoAdapter extends BaseAdapter{
    private List<Sport> list;
LayoutInflater inflater;
	public SpoAdapter(List<Sport> list,Context context) {
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
		TextView tv_content;
		TextView tv_zhiding;

	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder vHolder=null;
		if(arg1==null){
			vHolder=new ViewHolder();
		arg1=inflater.inflate(R.layout.spoadapter, null);
			vHolder.imageView=(ImageView) arg1.findViewById(R.id.im_spohead);
			vHolder.tv_name=(TextView) arg1.findViewById(R.id.spo_name);
			vHolder.tv_zhiding=(TextView) arg1.findViewById(R.id.zhiding);
			vHolder.tv_title=(TextView) arg1.findViewById(R.id.spo_title);
			vHolder.tv_content=(TextView) arg1.findViewById(R.id.spo_content);
			vHolder.tv_zan=(TextView) arg1.findViewById(R.id.spo_person);
			vHolder.tv_time=(TextView) arg1.findViewById(R.id.spo_time);
			arg1.setTag(vHolder);
		}else{
			vHolder=(ViewHolder) arg1.getTag();
		}
		
if(list.size()>30&&arg0<2){
			
			vHolder.imageView.setImageResource(AllStatic.images[list.get(arg0).getHead()]);
			vHolder.tv_name.setText(list.get(arg0).getUname());
			vHolder.tv_title.setText(list.get(arg0).getTitle());
			vHolder.tv_zan.setText(list.get(arg0).getNeedperson());
			vHolder.tv_content.setText(list.get(arg0).getContent());
			vHolder.tv_time.setText(list.get(arg0).getTime());
			vHolder.tv_zhiding.setText("ÖÃ¶¥");
		}else{
	
		vHolder.imageView.setImageResource(AllStatic.images[list.get(arg0).getHead()]);
		vHolder.tv_name.setText(list.get(arg0).getUname());
		vHolder.tv_title.setText(list.get(arg0).getTitle());
		vHolder.tv_zan.setText(list.get(arg0).getNeedperson()+"");
		vHolder.tv_time.setText(list.get(arg0).getTime());
		vHolder.tv_content.setText(list.get(arg0).getContent());
}
		
		return arg1;
	}

}