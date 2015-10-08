package com.example.support;

import com.example.pojo.LoginConfig;
import com.example.project_client1120.R;
import com.example.utils.Constant;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;

public class supportDAO {
	protected SharedPreferences preferences;
	private Context context;
	public supportDAO(Context context){
		this.context=context;
		this.preferences=context.getSharedPreferences(Constant.LOGIN_SET,0);
		
	}
	
	public ProgressDialog  myProgressD(){
		
		ProgressDialog mypDialog=new ProgressDialog(context);
		  //ʵ����
		  mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		  //���ý�������񣬷��ΪԲ�Σ���ת��
		  mypDialog.setTitle("У԰");
		  mypDialog.setMessage("������...");
		  mypDialog.setIcon(R.drawable.free48);
		  //����ProgressDialog ����
		  //����ProgressDialog ��ʾ��Ϣ
		  //����ProgressDialog ����ͼ��
		  //����ProgressDialog ��һ��Button
		  //����ProgressDialog �Ľ������Ƿ���ȷ
		  mypDialog.setCancelable(true);
		
		return mypDialog;
	}
	
	public void saveLoginConfig(LoginConfig loginConfig) {
		
		
		
		preferences.edit()
				.putString(Constant.URL, loginConfig.getName())
				.commit();
		preferences.edit()
				.putString(Constant.USERNAME, loginConfig.getName())
				.commit();
		preferences.edit()
		.putInt(Constant.ID, loginConfig.getId())
		.commit();
		preferences.edit()
		.putInt(Constant.HEAD, loginConfig.getHead())
		.commit();
		preferences.edit()
		.putString(Constant.SCHOOL, loginConfig.getSchool())
		.commit();
		preferences.edit()
		.putString(Constant.PERSONAL, loginConfig.getPersonal())
		.commit();
		preferences.edit()
		.putString(Constant.LEVEL, loginConfig.getLevel())
		.commit();
		preferences.edit()
				.putString(Constant.PASSWORD, loginConfig.getPass())
				.commit();
		preferences.edit()
		.putString(Constant.GENDER, loginConfig.getGender())
		.commit();
	
		preferences.edit()
				.putBoolean(Constant.IS_FIRSTSTART, loginConfig.isIsfirst())
				.commit();
	}
	
	public LoginConfig getLoginConfig() {
		LoginConfig loginConfig = new LoginConfig();
		loginConfig.setName(preferences.getString(Constant.USERNAME, null));
		loginConfig.setId(preferences.getInt(Constant.ID, 0));
		loginConfig.setPass(preferences.getString(Constant.PASSWORD, null));
		loginConfig.setSchool(preferences.getString(Constant.SCHOOL, null));
		loginConfig.setHead(preferences.getInt(Constant.HEAD,0));
		loginConfig.setPersonal(preferences.getString(Constant.PERSONAL, null));
		loginConfig.setLevel(preferences.getString(Constant.LEVEL, null));
		loginConfig.setGender(preferences.getString(Constant.GENDER, null));
		loginConfig.setIsfirst(preferences.getBoolean(Constant.IS_FIRSTSTART, true));
		return loginConfig;
	}


	
	

}
