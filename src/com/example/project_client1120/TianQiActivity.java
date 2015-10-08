package com.example.project_client1120;

import java.util.ArrayList;

import java.util.List;


import com.example.pojo.TianQi;


import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class TianQiActivity extends Activity {
List<TianQi> list=new ArrayList<TianQi>();
WebView wv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tian_qi);
		
		TextView back = (TextView) findViewById(R.id.tv_back);
//		getWeatherInfo2();
		wv = (WebView) findViewById(R.id.lv_tianqi);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		wv.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return true;
			}
		});
		wv.loadUrl("http://m.weather.com.cn/mweather/101030100.shtml");

		
//		String url="http://m.weather.com.cn/data/101030100.html";
//		
//		FinalHttp finalHttp=new FinalHttp();
//		finalHttp.post(url, new AjaxCallBack<Object>() {
//			
//			@Override
//			public void onSuccess(Object t) {
//				// TODO Auto-generated method stub
//				super.onSuccess(t);
//				  JSONObject responsejson;
//System.out.println(t.toString()+"-dstianqi");
//					try {
//						responsejson = new JSONObject(t.toString());
//					JSONObject tianqi=	responsejson.getJSONObject("weatherinfo");
//					String time1=tianqi.getString("date_y");
//					String temp1=tianqi.getString("temp1");
//					String weather1=tianqi.getString("weather1");
//					TianQi tianQi=new TianQi(time1, temp1, weather1);
//					list.add(tianQi);
//					String time2="明天";
//					String temp2=tianqi.getString("temp2");
//					String weather2=tianqi.getString("weather2");
//					TianQi tianQi2=new TianQi(time2, temp2, weather2);
//					list.add(tianQi2);
//					String time3="后天";
//					String temp3=tianqi.getString("temp3");
//					String weather3=tianqi.getString("weather3");
//					TianQi tianQi3=new TianQi(time3, temp3, weather3);
//					list.add(tianQi3);
//				TianQiAdapter adapter=new TianQiAdapter(getApplicationContext(), list);
//				lv.setAdapter(adapter);
//					} catch (JSONException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//
//			
//			}
//			
//			@Override
//			public void onFailure(Throwable t, int errorNo, String strMsg) {
//				// TODO Auto-generated method stub
//				super.onFailure(t, errorNo, strMsg);
//			Toast.makeText(getApplicationContext(), R.string.wangluo, Toast.LENGTH_SHORT).show();
//	
//			}
//			
//			
//			
//		});
//		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {
//			返回上一个打开的页面
			wv.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}
//	 private String getWeatherInfo2(){
//	        StringBuilder info = new StringBuilder();
//	        try {
//	            DefaultHttpClient httpclient = new DefaultHttpClient();
//	            HttpGet httget = new HttpGet("http://m.weather.com.cn/data/101050101.html");
//	            ResponseHandler<String> responseHandler = new BasicResponseHandler();
//	            String responseBody = httpclient.execute(httget, responseHandler);
//	            System.out.println(responseBody);
//	           
//	            JSONObject jse = new JSONObject(responseBody);
//	            JSONObject jso =  jse.getJSONObject("weatherinfo");
////	          String updTime = jso.get("fchh").getAsString();
////	          if(updTime != null){
////	              //温度
////	              String j = jso.get("temp1").getAsString();//今天
////	              String m = jso.get("temp2").getAsString();//明天
////	              //天气情况
////	              String j_weather = jso.get("weather1").getAsString();//今天
////	              String m_weather = jso.get("weather2").getAsString();//明天
////	              //风向风力
////	              String j_wind = jso.get("wind1").getAsString();//今天
////	              String m_wind = jso.get("wind2").getAsString();//明天
////	              info.append("今天：").append(j).append(" ").append(j_weather).append(" ").append(j_wind).append("\n");
////	              info.append("明天：").append(m).append(" ").append(m_weather).append(" ").append(m_wind).append("\n");
////	          }
//	            String updTime = jso.get("fchh").toString();
//	            if(updTime != null){
//	                if(!updTime.trim().equals("18")){
//	                    //温度
//	                    String j = jso.get("temp1").toString();//今天
//	                    String m = jso.get("temp2").toString();//明天
//	                    //天气情况
//	                    String j_weather = jso.get("weather1").toString();//今天
//	                    String m_weather = jso.get("weather2").toString();//明天
//	                    //风向风力
//	                    String j_wind = jso.get("wind1").toString();//今天
//	                    String m_wind = jso.get("wind2").toString();//明天
//	                    info.append("今天：").append(j).append(" ").append(j_weather).append(" ").append(j_wind).append("\n");
//	                    info.append("明天：").append(m).append(" ").append(m_weather).append(" ").append(m_wind).append("\n");
//	                }else{
//	                    //18
//	                    //温度
//	                    String temp1 = jso.get("temp1").toString();//今天
//	                    String temp2 = jso.get("temp2").toString();//今天
//	                    String temp3 = jso.get("temp3").toString();//今天
//	                    String j = temp1.split("~")[1] + "~" +  temp2.split("~")[0];
//	                    String m = temp2.split("~")[1] + "~" + temp3.split("~")[0];//明天
//	                    //天气情况
//	                    String weather1 = jso.get("weather1").toString();
//	                    String weather2 = jso.get("weather2").toString();
//	                    String weather3 = jso.get("weather3").toString();
//	                    String j_weather = "";
//	                    String j_weather_part1 = "";
//	                    String j_weather_part2 = "";
//	                    //判断是否有转
//	                    if(weather1.indexOf("转") > 0){
//	                        //有
//	                        j_weather_part1 = weather1.split("转")[1];
//	                    }else{
//	                        j_weather_part1 = weather1;
//	                    }
//	                    if(weather2.indexOf("转") > 0){
//	                        //有
//	                        j_weather_part2 = weather2.split("转")[0];
//	                    }else{
//	                        j_weather_part2 = weather2;
//	                    }
//	                    if(j_weather_part1.equalsIgnoreCase(j_weather_part2)){
//	                        j_weather = j_weather_part1;//今天
//	                    }else{
//	                        j_weather = j_weather_part1 + "转" + j_weather_part2;//今天
//	                    }
//	                    String m_weather = "";
//	                    String m_weather_part1 = "";
//	                    String m_weather_part2 = "";
//	                    //判断是否有转
//	                    if(weather2.indexOf("转") > 0){
//	                        //有
//	                        m_weather_part1 = weather2.split("转")[1];
//	                    }else{
//	                        m_weather_part1 = weather2;
//	                    }
//	                    if(weather3.indexOf("转") > 0){
//	                        //有
//	                        m_weather_part2 = weather3.split("转")[0];
//	                    }else{
//	                        m_weather_part2 = weather3;
//	                    }
//	                    if(m_weather_part1.equalsIgnoreCase(m_weather_part2)){
//	                        m_weather = m_weather_part1;//今天
//	                    }else{
//	                        m_weather = m_weather_part1 + "转" + m_weather_part2;//明天
//	                    }
//	                    //风向风力
//	                    String j_wind = jso.get("wind2").toString();//今天
//	                    String m_wind = jso.get("wind3").toString();//明天
//	                    info.append("今天：").append(j).append(" ").append(j_weather).append(" ").append(j_wind).append("\n");
//	                    info.append("明天：").append(m).append(" ").append(m_weather).append(" ").append(m_wind).append("\n");
//	                }
//	            }
//	        } catch (Exception e) {
//	        }
//	        return info.toString();
//	    }
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_tian_qi, menu);
//		return true;
//	}

}
