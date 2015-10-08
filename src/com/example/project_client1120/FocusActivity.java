package com.example.project_client1120;

import java.util.ArrayList;





import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import com.example.Http.HttpJson;
import com.example.UI.MainExpandableListView;
import com.example.UI.MainExpandableListView.OnRefreshListener;
import com.example.pojo.Club;
import com.example.pojo.Invitation;
import com.example.pojo.LoginConfig;
import com.example.pojo.Sport;
import com.example.pojo.User;
import com.example.support.AllStatic;

import com.example.utils.Constant;
import com.example.utils.ExpandAdapter;

import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;

public class FocusActivity extends Fragment implements OnClickListener
	{
	
	
	MainExpandableListView lv_user;
	List<User> userlist = new ArrayList<User>();
	List<Invitation> invlist = new ArrayList<Invitation>();
	List<Sport> spolist = new ArrayList<Sport>();
	List<Club> clublist = new ArrayList<Club>();
	private View mMainView;
	LoginConfig loginConfig = AllStatic.loginConfig;
	ExpandAdapter adapter;
//Handler handler=new Handler(){
//	
//	public void handleMessage(android.os.Message msg) {
//		
//		switch (msg.what) {
//		case 4:
//			adapter.notifyDataSetChanged();
//			break;
//
//		default:
//			break;
//		}
//		
//		
//	};
//	
//};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup p = (ViewGroup) mMainView.getParent();
		if (p != null) {
			p.removeAllViewsInLayout();
		}
		return mMainView;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getActivity().getLayoutInflater();

		mMainView = inflater.inflate(R.layout.activity_focus,
				(ViewGroup) getActivity().findViewById(R.id.viewPager), false);

		LinearLayout but_club = (LinearLayout) mMainView.findViewById(R.id.club);
		LinearLayout but_inv = (LinearLayout) mMainView.findViewById(R.id.inv);
		LinearLayout but_spo = (LinearLayout) mMainView.findViewById(R.id.spo);
		lv_user = (MainExpandableListView) mMainView.findViewById(R.id.lvuser);
		EditText etsea = (EditText) mMainView.findViewById(R.id.seaspo);
	etsea.setOnClickListener(this);
		
	
		but_club.setOnClickListener(this);
		but_inv.setOnClickListener(this);
		but_spo.setOnClickListener(this);
		FinalDb finalDb = FinalDb.create(getActivity());
		userlist = finalDb.findAllByWhere(User.class,
				"userid=" + loginConfig.getId());
		spolist = finalDb.findAllByWhere(Sport.class,
				"userid=" + loginConfig.getId(), "time desc");
		invlist = finalDb.findAllByWhere(Invitation.class, "userid="
				+ loginConfig.getId(), "time desc");
		System.out.println();
		clublist = finalDb.findAllByWhere(Club.class,
				"userid=" + loginConfig.getId(), "time desc");
		adapter = new ExpandAdapter(getActivity(), userlist, invlist, spolist,
				clublist);
		lv_user.setAdapter(adapter);
lv_user.setonRefreshListener(new OnRefreshListener() {
	
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		FinalHttp finalHttp = new FinalHttp();
		AjaxParams params = new AjaxParams();
		params.put("flag", Constant.ALLINFO + "");
		params.put("uid", loginConfig.getId() + "");
		String url = "http://123.56.95.134:8080/Testlife/servlet/ServletUser";

		finalHttp.post(url, params, new AjaxCallBack<Object>() {
			@Override
			public void onSuccess(Object t) {
				// TODO Auto-generated method stub
				super.onSuccess(t);
				lv_user.onRefreshComplete();
				Toast.makeText(getActivity(), R.string.win, Toast.LENGTH_SHORT)
						.show();
				JSONObject responsejson;
				try {
					HttpJson httpJson = HttpJson.getinstance();
					responsejson = new JSONObject(t.toString());
					JSONObject jsonObjectu = responsejson.getJSONObject("user");
					JSONObject jsonObjects = responsejson
							.getJSONObject("sport");
					JSONObject jsonObjecti = responsejson.getJSONObject("inv");
					JSONObject jsonObjectc = responsejson.getJSONObject("club");
					JSONArray jsonArrayu = new JSONArray();
					JSONArray jsonArrays = new JSONArray();
					JSONArray jsonArrayc = new JSONArray();
					JSONArray jsonArrayi = new JSONArray();
					boolean value = jsonObjectu.getBoolean("stateu");
					if (value == true) {
					
						jsonArrayu = jsonObjectu.getJSONArray("userguanzhu");
				
						userlist.clear();

						userlist = httpJson.getUserList(jsonArrayu);
//						System.out.println(userlist.get(0).getName()
//								+ "--------name---vvvvvv");
						FinalDb finalDb = FinalDb.create(getActivity());
						finalDb.deleteByWhere(User.class, "userid="
								+ loginConfig.getId());
						for (User temp : userlist) {
							finalDb.save(temp);
						}
						
					}else{}

					boolean value2 = jsonObjects.getBoolean("states");
//					System.out.println(value + "-----------vvvvvv");
					if (value2 == true) {
						jsonArrays = jsonObjects.getJSONArray("allspo");
//						HttpJson httpJson = HttpJson.getinstance();
						spolist.clear();
//						adapter.notifyDataSetChanged();

						spolist = httpJson.getUserSport(jsonArrays);

//						System.out.println(spolist.size()+"------sposize--------------");
						FinalDb finalDb = FinalDb.create(getActivity());
						finalDb.deleteByWhere(Sport.class, "userid="+ loginConfig.getId());
						for (Sport temp : spolist) {
							finalDb.save(temp);
						}
						
						
					}else{}

					boolean value3 = jsonObjectc.getBoolean("statec");
					System.out.println(value + "-----------vvvvvv");
					if (value3 == true) {
					
						jsonArrayc = jsonObjectc.getJSONArray("userclub");
						System.out.println(jsonArrayc.toString() + "-userclubv----------");
					
						clublist.clear();
//						adapter.notifyDataSetChanged();
	
					
						clublist = httpJson.getUserClub(jsonArrayc);
						System.out.println(clublist.size()+"------clubsize--------------");
						FinalDb finalDb = FinalDb.create(getActivity());
						finalDb.deleteByWhere(Club.class, "userid="+ loginConfig.getId());
						for (Club temp : clublist) {
							finalDb.save(temp);
						}
					}else{}
					boolean value4 = jsonObjecti.getBoolean("statei");
					System.out.println(value + "-----------vvvvvv");
					if (value4 == true) {
						jsonArrayi = jsonObjecti.getJSONArray("userinv");
//						System.out.println(jsonArrayi.toString() + "-userinv----------");
//						HttpJson httpJson = HttpJson.getinstance();
						invlist.clear();
//						adapter.notifyDataSetChanged();

						invlist = httpJson.getUserInv(jsonArrayi);

//						System.out.println(invlist.size()+"------invsize--------------");
						FinalDb finalDb = FinalDb.create(getActivity());
						finalDb.deleteByWhere(Invitation.class, "userid="+ loginConfig.getId());
						for (Invitation temp : invlist) {
							finalDb.save(temp);
						}
					}else{}
					
//				if(jsonArrayc!=null&&jsonArrayi!=null&&jsonArrayi!=null)

				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			   adapter=null;
			   adapter=new ExpandAdapter(getActivity(), userlist, invlist, spolist, clublist);
               lv_user.setAdapter(adapter);				
				
			} 
		
			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);
				lv_user.onRefreshComplete();
				Toast.makeText(getActivity(), R.string.wangluo,
						Toast.LENGTH_SHORT).show();
			}
		});	
	}
});
		lv_user.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				
			
				
				switch (groupPosition) {
				case 0:
					Intent intentu = new Intent(getActivity(),
							ChatActivity.class);
					intentu.putExtra("type", 2);
					intentu.putExtra("user", userlist.get(childPosition));
					startActivity(intentu);
					break;
				case 1:
					Intent intentc = new Intent(getActivity(),
							ClublistActivity.class);
					intentc.putExtra("invid", clublist.get(childPosition)
							.getInvid());
//					AllStatic.club=clublist.get(childPosition);
					startActivity(intentc);
					break;
				case 2:
					Intent intents = new Intent(getActivity(),
							SpoReplyActivity.class);
					intents.putExtra("spoid", spolist.get(childPosition)
							.getSpoid());
					intents.putExtra("suid", spolist.get(childPosition)
							.getUid());
//					AllStatic.sport=spolist.get(childPosition);

					startActivity(intents);

					break;
				case 3:
					Intent intenti = new Intent(getActivity(),
							InvReplyActivity.class);
					intenti.putExtra("invid", invlist.get(childPosition)
							.getInvid());
					intenti.putExtra("iuid", invlist.get(childPosition)
							.getIuid());
//					AllStatic.inv=invlist.get(childPosition);

					startActivity(intenti);

					break;

				default:
					break;
				}

				return false;
			}
		});

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.club:
			Intent intent2 = new Intent(getActivity(), ClubActivity.class);
			startActivity(intent2);

			break;
		case R.id.inv:
			Intent intent = new Intent(getActivity(), InvlistActivity.class);
			startActivity(intent);
			break;
		case R.id.spo:
			Intent intent3 = new Intent(getActivity(), SpolistActivity.class);
			startActivity(intent3);

			break;
		case R.id.seaspo:
			Intent intent4= new Intent(getActivity(), SearchActivity.class);
			intent4.putExtra("type", 2);
			startActivity(intent4);
			break;

		default:
			break;
		}

	}


}
