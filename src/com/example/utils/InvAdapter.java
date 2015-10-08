package com.example.utils;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;


import com.example.pojo.Reply;
import com.example.project_client1120.R;
import com.example.project_client1120.UserInfoActivity;
import com.example.support.AllStatic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class InvAdapter extends BaseAdapter{
	int invid;
      private	Context context;
        private List<Reply> list;
	LayoutInflater inflater;
	
	public InvAdapter(Context context,List<Reply> list,int invid){
		this.invid=invid;
		this.context=context;
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
	TextView tv_lou;

}
	@Override
	public View getView( final int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder vHolder=null;
		if(arg1==null){
			vHolder=new ViewHolder();
		arg1=inflater.inflate(R.layout.reolyadapter, null);
			vHolder.imageView=(ImageView) arg1.findViewById(R.id.im_invhead);
			vHolder.tv_name=(TextView) arg1.findViewById(R.id.inv_name);
			vHolder.tv_lou=(TextView) arg1.findViewById(R.id.inv_loushu);
			vHolder.tv_title=(TextView) arg1.findViewById(R.id.inv_title);
			vHolder.tv_zan=(TextView) arg1.findViewById(R.id.inv_zan);
			vHolder.tv_time=(TextView) arg1.findViewById(R.id.inv_time);
			arg1.setTag(vHolder);
		}else{
			vHolder=(ViewHolder) arg1.getTag();
		}
		vHolder.imageView.setImageResource(AllStatic.images[list.get(arg0).getHead()]);
		vHolder.tv_name.setText(list.get(arg0).getUname());
		vHolder.tv_title.setText(list.get(arg0).getContent());
		vHolder.tv_zan.setText(list.get(arg0).getZan()+"");
		vHolder.tv_time.setText(list.get(arg0).getTime());
		vHolder.tv_lou.setText(list.get(arg0).getId()+"¥");
		
		
		vHolder.tv_name.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg1) {
				// TODO Auto-generated method stub
			Intent intent=new Intent(context,UserInfoActivity.class);
			intent.putExtra("uid",list.get(arg0).getUid());
			System.out.println(list.get(arg0).getUid()+"---------");
			
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra("type", 0);
			
			context.startActivity(intent);
			
			}
		});
		vHolder.tv_zan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg1) {
				if(list.get(arg0).getUid()==AllStatic.loginConfig.getId()){
					Toast.makeText(context, R.string.zilian, Toast.LENGTH_SHORT).show();
					System.out.println(list.get(arg0).getUid()+"youyong--lis---------------------------");
				}else{
				System.out.println("youyong-----------------------------");
				// TODO Auto-generated method stub
				String	 url="http://123.56.95.134:8080/Testlife/servlet/ServletInv";
				FinalHttp finalHttp=new FinalHttp();
				AjaxParams params=new AjaxParams();
				params.put("invid",invid+ "");
				params.put("reid",list.get(arg0).getId()+ "");
				params.put("flag", Constant.REPLYZAN+"");
				
		finalHttp.post(url, params, new AjaxCallBack<Object>() {		
			@Override
			public void onSuccess(Object t) {
				// TODO Auto-generated method stub
				super.onSuccess(t);
				JSONObject responsejson;
				try {
					boolean value;
					responsejson = new JSONObject(t.toString());
					value = responsejson.getBoolean("state");
					System.out.println(value+"-----------vvvvvv");
					if(value==true){
						Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
						}else  {
							Toast.makeText(context, "mang", Toast.LENGTH_SHORT).show();
					}
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			@Override
			public void onFailure(Throwable t, int errorNo,
					String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);
		Toast.makeText(context, "shibaile", Toast.LENGTH_SHORT).show();
			}
		});}
			}
		});
		
		
		return arg1;
	}

}
