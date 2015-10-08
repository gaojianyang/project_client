package com.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

public class AsyncImgLoader {
  
  private HashMap<String, SoftReference<Drawable>> imageCache;
  
public AsyncImgLoader(){
	imageCache=new HashMap<String, SoftReference<Drawable>>();
	
}
public Drawable loadDrawable(final String imageUrl,final ImageCallback imageCallback){
	if(imageCache.containsKey(imageUrl)){
		SoftReference<Drawable> softReference=imageCache.get(imageUrl);
	     Drawable drawable=softReference.get();
	     if(drawable!=null){
	    	 return drawable;
	     }
	}
	final Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
imageCallback.imageLoaded((Drawable)msg.obj, imageUrl);		}
	};
	
	new Thread(){
		@Override
		public void run() {
		Drawable drawable=loadImageFromUrl(imageUrl);
		imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
		Message message=handler.obtainMessage(0, drawable);
		handler.sendMessage(message);
		}

		
	}.start();
	return null;
}

public interface ImageCallback{
	public void imageLoaded(Drawable imageDrawble,String imageUrl);
}
public static Drawable loadImageFromUrl(String imageUrl) {
	// TODO Auto-generated method stub
	URL m;
	InputStream i=null;
	try {
		m=new URL(imageUrl);
		i=(InputStream) m.getContent();
	}catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
} Drawable d=Drawable.createFromStream(i, "srcname");
return d;
};
}
