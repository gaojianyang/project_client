package com.example.Http;

import java.util.ArrayList;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.pojo.ChatMessage;
import com.example.pojo.Club;
import com.example.pojo.Images;
import com.example.pojo.Message;
import com.example.pojo.Reply;
import com.example.pojo.Sport;
import com.example.pojo.Sreply;
import com.example.pojo.User;
import com.example.pojo.Invitation;
import com.example.support.AllStatic;


public class HttpJson {
	
	private static HttpJson httpJson=null;
	
	
    private HttpJson(){
}
	
	public static HttpJson getinstance(){
		
		if(httpJson==null){
			
			httpJson=new HttpJson();
			
		}
		return httpJson;
		
		
	}
	
	
	//get user
	public User getUser(JSONObject jsonObject){
		try {
			int id=jsonObject.getInt("id");
			int head=jsonObject.getInt("head");
			String college=jsonObject.getString("college");
			String name=jsonObject.getString("name");
			String gender=jsonObject.getString("gender");
			String personal=jsonObject.getString("personal");
			String level=jsonObject.getString("level");
			long guanzhucount=jsonObject.getLong("guanzhucount");
			User user=new User(id, name, gender, head, college, personal,level,guanzhucount,AllStatic.loginConfig.getId());
			return user;
			
		} catch (JSONException e) {
          return null;		
		}
	
	}

	
	//get listuser
//	for (int i = 0; i < responsearray.length(); i++) {
//		System.out.println(responsearray.length()+"--rjlll");
//		JSONObject jsonObject2=(JSONObject) responsearray.get(i);
//		
//		News news=new News();
//		news.setId(jsonObject2.getInt("id"));
//		news.setTittle(jsonObject2.getString("title"));
//		news.setContent(jsonObject2.getString("content"));
//		news.setUrl(jsonObject2.getString("img"));
//		list.add(news);
	public  List<User> getUserList(JSONArray jsonArray){
		List<User> list=new ArrayList<User>();
		
			try {
				for(int i=0;i<jsonArray.length();i++){
					JSONObject jsonObject=(JSONObject) jsonArray.get(i);
				int id = jsonObject.getInt("id");
				int head=jsonObject.getInt("head");
				String college=jsonObject.getString("college");
				String name=jsonObject.getString("name");
				String gender=jsonObject.getString("gender");
				String personal=jsonObject.getString("personal");
				String level=jsonObject.getString("level");
				long guanzhucount=jsonObject.getLong("guanzhucount");
				User user=new User(id, name, gender, head, college, personal,level,guanzhucount,AllStatic.loginConfig.getId());
				list.add(user);
				}
				return list;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
            return null;			
		  }
	
	}
	public  List<User> geteasyUserList(JSONArray jsonArray){
		List<User> list=new ArrayList<User>();
		
			try {
				for(int i=0;i<jsonArray.length();i++){
					JSONObject jsonObject=(JSONObject) jsonArray.get(i);
				
				int id = jsonObject.getInt("id");
				int head=jsonObject.getInt("head");
				System.out.println(head+"-----------------------------");
				String college=jsonObject.getString("college");
				String name=jsonObject.getString("name");
				String gender=jsonObject.getString("gender");
				String personal=jsonObject.getString("personal");
				String level=jsonObject.getString("level");

				User user=new User(id, name, gender, head, college, personal,level,AllStatic.loginConfig.getId());
				list.add(user);
			}
				return list;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
            return null;			
		  }
	
	}
	
	//get list club
	public  List<Club> getUserClub(JSONArray jsonArray){
		List<Club> list=new ArrayList<Club>();
		
			try {
				for(int i=0;i<jsonArray.length();i++){
				JSONObject jsonObject;
				jsonObject = (JSONObject) jsonArray.get(i);
				int id=jsonObject.getInt("id");
				int uid=jsonObject.getInt("uid");
				int number=jsonObject.getInt("number");
				int invid=jsonObject.getInt("invid");
				int head=jsonObject.getInt("head");
				String introduce=jsonObject.getString("content");
				String name=jsonObject.getString("name");
				String time=jsonObject.getString("time");
				Club club=new Club(id, uid, name, number, introduce, head, time, invid,AllStatic.loginConfig.getId());
			list.add(club);
			}
			return list;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
			return null;
			}
		
	}
	//get list inv
	
	public List<Invitation> getUserInv(JSONArray jsonArray){
		
		System.out.println(jsonArray+"==================");
		List<Invitation> list=new ArrayList<Invitation>();
		
		try {
			for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObject;
			jsonObject = (JSONObject) jsonArray.get(i);
			int id=jsonObject.getInt("id");
			int iuid=jsonObject.getInt("iuid");
			long zan=jsonObject.getLong("zan");
			int head=jsonObject.getInt("head");
			short type=(short) jsonObject.getInt("type");
			String title=jsonObject.getString("title");
			String uname=jsonObject.getString("uname");
			String createtime=jsonObject.getString("createtime");
			String time=jsonObject.getString("time");
			Invitation inv=new Invitation(id,iuid, head,uname,title,zan,time,createtime,type,AllStatic.loginConfig.getId());
		list.add(inv);
		}
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
	System.out.println("++++=yichang lkkdskfskdfndjkdfjfskfskld;");
		}return list;
	
}
	
	public List<Sport> getUserSport(JSONArray jsonArray){
		
		List<Sport> list=new ArrayList<Sport>();
		
		try {
			for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObject;
			jsonObject = (JSONObject) jsonArray.get(i);
			int id=jsonObject.getInt("id");
			
			int uid=jsonObject.getInt("uid");


			int head=jsonObject.getInt("head");

			int needperson=jsonObject.getInt("needperson");
			String title=jsonObject.getString("title");
			System.out.println(title+"title_______-----");
			String content=jsonObject.getString("content");
			System.out.println(content+"c_______-----");
			String uname=jsonObject.getString("uname");
			System.out.println(uname+"uname_______-----");

			String createtime=jsonObject.getString("createtime");
			System.out.println(createtime+"came_______-----");
			String time=jsonObject.getString("time");
			System.out.println(time+"time_______-----");

            Sport sport=new Sport(id,head, uid,uname, title, content, time, createtime, needperson,AllStatic.loginConfig.getId());
            list.add(sport);
		}
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		return null;
		}
		return list;
	}
	
public List<Reply> getInvReply(JSONArray jsonArray){
	

	List<Reply> list=new ArrayList<Reply>();
	
	try {
		for(int i=0;i<jsonArray.length();i++){
		JSONObject jsonObject;
		jsonObject = (JSONObject) jsonArray.get(i);
		int id=jsonObject.getInt("id");
		int uid=jsonObject.getInt("uid");
		int head=jsonObject.getInt("head");
		int zan=jsonObject.getInt("zan");
		String uname=jsonObject.getString("uname");
		String content=jsonObject.getString("content");
		String time=jsonObject.getString("time");
        Reply reply=new Reply(id, uname,uid, content, zan, time, head);
        list.add(reply);
	}
	
	} catch (JSONException e) {
		// TODO Auto-generated catch block
	return null;
	}return list;
}
public List<Sreply> getSpoReply(JSONArray jsonArray){
	
List<Sreply> list=new ArrayList<Sreply>();
	
	try {
		for(int i=0;i<jsonArray.length();i++){
		JSONObject jsonObject;
		jsonObject = (JSONObject) jsonArray.get(i);
		int id=jsonObject.getInt("id");
		int uid=jsonObject.getInt("uid");
		int head=jsonObject.getInt("head");
		String uname=jsonObject.getString("uname");
		String content=jsonObject.getString("content");
		String time=jsonObject.getString("time");
     Sreply reply=new Sreply(id, uid, head,uname, content, time);
        list.add(reply);
	}
	return list;
	} catch (JSONException e) {
		// TODO Auto-generated catch block
	return null;
	}
	
	
	
	
}
public  List<Message> getMessage(JSONArray jsonArray){
	List<Message> list=new ArrayList<Message>();
	
	try {
		for(int i=0;i<jsonArray.length();i++){
		JSONObject jsonObject;
		jsonObject = (JSONObject) jsonArray.get(i);
		int id=jsonObject.getInt("id");
		int fromid=jsonObject.getInt("fromid");
		int fromhead=jsonObject.getInt("fromhead");
		int invid=jsonObject.getInt("invid");
		int type= jsonObject.getInt("type");
		String fromname=jsonObject.getString("fromname");
		String content=jsonObject.getString("content");
		String time=jsonObject.getString("time");
		Message message=new Message(id, content, fromid, fromhead, type, invid, time, fromname,1,AllStatic.loginConfig.getId());
        list.add(message);
	}
	return list;
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

//	return null;
	}
	return list;

}
public  List<ChatMessage> getChatMessage(JSONArray jsonArray){
	List<ChatMessage> list=new ArrayList<ChatMessage>();
	
	try {
		for(int i=0;i<jsonArray.length();i++){
		JSONObject jsonObject;
		jsonObject = (JSONObject) jsonArray.get(i);
		int fromid=jsonObject.getInt("fromid");
		int fromhead=jsonObject.getInt("fromhead");
		int toid=jsonObject.getInt("toid");
		String fromname=jsonObject.getString("fromname");
		String content=jsonObject.getString("content");
		String time=jsonObject.getString("time");
		ChatMessage message=new ChatMessage( content, fromid, fromhead, time, toid, fromname,AllStatic.loginConfig.getId());
        list.add(message);
	}
	return list;
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

//	return null;
	}
	return list;

}
  public List<Images> invImage(JSONArray jsonArray){
	List<Images> list=new ArrayList<Images>();
	

	try {
		for(int i=0;i<jsonArray.length();i++){
		JSONObject jsonObject;
		jsonObject = (JSONObject) jsonArray.get(i);
		int uid=jsonObject.getInt("uid");
		String title=jsonObject.getString("title");
		String name=jsonObject.getString("name");
		String uri=jsonObject.getString("uri");
		String time=jsonObject.getString("time");
		Images images=new Images(uid, name, title, uri, time);
        list.add(images);
	}
	return list;
	} catch (JSONException e) {
		// TODO Auto-generated catch block
	return null;
	}
	
} 





}
