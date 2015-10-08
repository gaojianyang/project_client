package com.example.utils;

import java.util.List;

import net.tsz.afinal.FinalBitmap;

import com.example.pojo.Images;
import com.example.project_client1120.R;
import com.example.project_client1120.UserInfoActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter{
  
	private List<Images> list;
	private Context context;
	private LayoutInflater inflater;
	int arg3;
	public ImageAdapter(List<Images> list, Context context){
		this.list=list;
		this.context=context;
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
    class viewHolder{
     	ImageView iv;
    	TextView name;
    	TextView time;
    	TextView title;
}
	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		this.arg3=arg0;
		viewHolder viewholder=null;
		
		FinalBitmap fb=FinalBitmap.create(context);
		
		   if(convertView == null){
			   viewholder=new viewHolder();
			   
		        convertView = inflater.inflate(R.layout.imageadapter, null);
		        viewholder.name=(TextView) convertView.findViewById(R.id.im_name);
		        viewholder.time=(TextView) convertView.findViewById(R.id.im_time);
		        viewholder.title=(TextView) convertView.findViewById(R.id.im_title);
		       viewholder.iv = (ImageView) convertView.findViewById(R.id.im_png);
		       viewholder.iv.setScaleType(ScaleType.CENTER_CROP);
		        convertView.setTag(viewholder);
		    }else{
		    viewholder=(viewHolder) convertView.getTag();
		    }
		    //bitmap加载就这一行代码，display还有其他重载，详情查看源码
		   viewholder.title.setText(list.get(arg0).getTitle());
		   viewholder.name.setText(list.get(arg0).getName());
		   viewholder.time.setText(list.get(arg0).getTime());
		   fb.display(viewholder.iv,list.get(arg0).getUri());
		  viewholder.name.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg1) {
					// TODO Auto-generated method stub
				Intent intent=new Intent(context,UserInfoActivity.class);
				intent.putExtra("uid",list.get(arg3).getUid());
				System.out.println(list.get(arg3).getUid()+"---------");
				
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("type", 0);
				
				context.startActivity(intent);
				
				}
			});
		   
		return convertView;
	}

}
