package com.example.utils;

import java.util.ArrayList;



import java.util.List;

import com.example.pojo.Club;
import com.example.pojo.Invitation;
import com.example.pojo.Sport;
import com.example.pojo.User;
import com.example.project_client1120.R;
import com.example.support.AllStatic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpandAdapter extends BaseExpandableListAdapter {
	Context context;
	List<User> userlist=new ArrayList<User>();
	List<Invitation> invlist=new ArrayList<Invitation>();
	List<Sport> spolist=new ArrayList<Sport>();
	List<Club> clublist=new ArrayList<Club>();
	String[] focus=new String[]{"我的关注","我的社团","我的活动","我的帖子"};
//	Handler handler;
	public ExpandAdapter(Context context, List<User> userlist,
			List<Invitation> invlist, List<Sport> spolist, List<Club> clublist) {
		this.context=context;
		this.invlist=invlist;
		this.clublist=clublist;
		this.spolist=spolist;
		this.userlist=userlist;
//		handler = new Handler(){  
//            @Override  
//            public void handleMessage(Message msg) {  
//              notifyDataSetChanged();  
//              super.handleMessage(msg);  
//            }  
//        }; 
//        
//	}
//	 public void refresh() {  
//	        handler.sendMessage(new Message());  
	    } 
	 
//@Override
//public void onGroupCollapsed(int groupPosition) {
//	// TODO Auto-generated method stub
//	
//	super.onGroupCollapsed(groupPosition);
//	notifyDataSetChanged();
//}
//@Override
//public void onGroupExpanded(int groupPosition) {
//	// TODO Auto-generated method stub
//	super.onGroupExpanded(groupPosition);
//	notifyDataSetChanged();
//}
	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	class GroupHolder {

		TextView textView;
ImageView mgroupimage;
	}


	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		// TODO Auto-generated method stub
		GroupHolder groupHolder = null;

		if (arg2 == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			groupHolder = new GroupHolder();
			arg2 = inflater.inflate(R.layout.exadaptergroup, null);
			groupHolder.textView = (TextView) arg2.findViewById(R.id.tv_focus);
			groupHolder.mgroupimage =(ImageView)arg2.findViewById(R.id.shangxia);

			arg2.setTag(groupHolder);

		} else {
			groupHolder = (GroupHolder) arg2.getTag();
		}
		groupHolder.mgroupimage.setImageResource(R.drawable.xxx);
          if(!arg1){
        	  groupHolder. mgroupimage.setImageResource(R.drawable.yyy);
           }
		groupHolder.textView.setText(focus[arg0]);
		return arg2;
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public Object getGroup(int arg0) {
		// TODO Auto-generated method stub
		return focus[arg0];
	}

	@Override
	public int getChildrenCount(int arg0) {
		switch (arg0) {
		case 0:
			return userlist.size(); 
		case 1:
			return clublist.size();
		case 2:
			return spolist.size();
		case 3:
			return invlist.size();
		default:
			break;
		}
		return 0;
	}

	class ChildGrouper {
		ImageView imageview;
		TextView tv_name;
		TextView tv_personal;
		TextView tv_time;

	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		ChildGrouper childGrouper = null;
		if (convertView == null) {
			childGrouper = new ChildGrouper();
			  LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
			//获取二级列表对应的布局文件, 并将其各元素设置相应的属性 
//			  LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.child, null); 
			convertView = layoutInflater.inflate(R.layout.exadapterchild, null);
//					.from(context);
//			convertView = inflater1.inflate(R.layout.exadapterchild, null);
			childGrouper.imageview = (ImageView)convertView.findViewById(R.id.im_childhead);
			childGrouper.tv_name = (TextView) convertView.findViewById(R.id.tv_childname);
			childGrouper.tv_personal = (TextView)convertView. findViewById(R.id.tv_childpersonal);
			childGrouper.tv_time = (TextView)convertView. findViewById(R.id.times);
//			View findViewById = convertView.findViewById(R.id.im_childhead);
			convertView.setTag(childGrouper);
		
		} else {
		childGrouper=(ChildGrouper) convertView.getTag();
		}
		switch (groupPosition) {
		
		case 0:
			childGrouper.imageview.setImageResource(AllStatic.images[userlist.get(childPosition).getHead()]);
			childGrouper.tv_name.setText(userlist.get(childPosition).getName());
			childGrouper.tv_personal.setText(userlist.get(childPosition).getPersonal());
			childGrouper.tv_time.setVisibility(View.INVISIBLE);
			break;
		case 1:
			childGrouper.imageview.setImageResource(AllStatic.imageclub[clublist.get(childPosition).getHead()]);
			childGrouper.tv_name.setText(clublist.get(childPosition).getName());
			childGrouper.tv_personal.setText(clublist.get(childPosition).getIntroduce());
			childGrouper.tv_time.setText(clublist.get(childPosition).getTime());
			break;
		case 2:
			childGrouper.imageview.setImageResource(AllStatic.images[spolist.get(childPosition).getHead()]);
			childGrouper.tv_name.setText(spolist.get(childPosition).getTitle());
			childGrouper.tv_personal.setText(spolist.get(childPosition).getContent());
			childGrouper.tv_time.setText(spolist.get(childPosition).getTime());
			
			break;
		case 3:
			childGrouper.imageview.setImageResource(AllStatic.images[invlist.get(childPosition).getHead()]);
			childGrouper.tv_name.setText(invlist.get(childPosition).getUname());
			childGrouper.tv_personal.setText(invlist.get(childPosition).getTitle());
			childGrouper.tv_time.setText(invlist.get(childPosition).getCreatetime());
			break;
		}
		return convertView;
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return arg1;
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		switch (arg0) {	
	case 0:
		return userlist.get(arg1); 
	case 1:
		return clublist.get(arg1);
	case 2:
		return spolist.get(arg1);
	case 3:
		return invlist.get(arg1);
	default:
		break;}
		
		
		return null;
	}

}
