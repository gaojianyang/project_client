package com.example.utils;


import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pojo.WebUrl;
import com.example.project_client1120.R;

public class WebAdapter extends BaseAdapter{
    private List<WebUrl> list;
    int[] image=new int[]{R.drawable.baidu,R.drawable.elema,R.drawable.taobao,R.drawable.sina,R.drawable.guoke};
    
LayoutInflater inflater;
	public WebAdapter(List<WebUrl> list,Context context) {
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
			arg1.setTag(vHolder);
		}else{
			vHolder=(ViewHolder) arg1.getTag();
		}
		vHolder.imageView.setImageResource(image[list.get(arg0).getHead()]);
		vHolder.tv_name.setText(list.get(arg0).getName());
		vHolder.tv_title.setText(list.get(arg0).getContent());
		vHolder.tv_time.setText("½øÈë>>");
		vHolder.tv_zan.setVisibility(View.INVISIBLE);
		return arg1;
	}


}
