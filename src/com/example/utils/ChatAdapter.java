package com.example.utils;

import java.util.List;

import com.example.pojo.ChatMessage;
import com.example.project_client1120.R;
import com.example.support.AllStatic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatAdapter extends BaseAdapter{

	List<ChatMessage> list;
	LayoutInflater inflater;
	Context context;
	
	
	public ChatAdapter(List<ChatMessage> list,Context context){
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
		TextView tv_time;

	}
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder vHolder=null;
		if(arg1==null){
			vHolder=new ViewHolder();
		arg1=inflater.inflate(R.layout.chatadapter, null);
			vHolder.imageView=(ImageView) arg1.findViewById(R.id.im_chathead);
			vHolder.tv_content=(TextView) arg1.findViewById(R.id.chat_content);
			vHolder.tv_time=(TextView) arg1.findViewById(R.id.chat_time);
			arg1.setTag(vHolder);
		}else{
			vHolder=(ViewHolder) arg1.getTag();
		}
		vHolder.imageView.setImageResource(AllStatic.images[list.get(arg0).getFromhead()]);
		vHolder.tv_content.setText(list.get(arg0).getContent());
		vHolder.tv_time.setText(list.get(arg0).getTime());
		return arg1;
	}

}
