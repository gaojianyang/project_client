//package com.example.utils;
//
//import com.example.project_client1120.R;
//
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.graphics.drawable.AnimationDrawable;
//import android.view.Gravity;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//public class CustomeProgressDialog extends ProgressDialog{
//	 private Context context = null;
//	    private static CustomeProgressDialog customProgressDialog = null;
//	     
//	    public CustomeProgressDialog(Context context){
//	        super(context);
//	        this.context = context;
//	    }
//	     
//	    public CustomeProgressDialog(Context context, int theme) {
//	        super(context, theme);
//	    }
//	     
//	    public static CustomeProgressDialog createDialog(Context context){
//	        customProgressDialog = new CustomeProgressDialog(context,R.style.CustomProgressDialog);
//	        customProgressDialog.setContentView(R.layout.customprogressdialog);
//	        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
//	         
//	        return customProgressDialog;
//	    }
//	  
//	    public void onWindowFocusChanged(boolean hasFocus){
//	         
//	        if (customProgressDialog == null){
//	            return;
//	        }
//	         
//	        ImageView imageView = (ImageView) customProgressDialog.findViewById(R.id.loadingImageView);
//	        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
//	        animationDrawable.start();
//	    }
//	  
//	    /**
//	     *
//	     * [Summary]
//	     *       setTitile 标题
//	     * @param strTitle
//	     * @return
//	     *
//	     */
//	    public CustomeProgressDialog setTitile(String strTitle){
//	        return customProgressDialog;
//	    }
//	     
//	    /**
//	     *
//	     * [Summary]
//	     *       setMessage 提示内容
//	     * @param strMessage
//	     * @return
//	     *
//	     */
//	    public CustomeProgressDialog setMessage(String strMessage){
//	        TextView tvMsg = (TextView)customProgressDialog.findViewById(R.id.id_tv_loadingmsg);
//	         
//	        if (tvMsg != null){
//	            tvMsg.setText(strMessage);
//	        }
//	         
//	        return customProgressDialog;
//	    }
//}
