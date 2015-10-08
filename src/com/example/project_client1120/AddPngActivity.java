package com.example.project_client1120;

import java.io.File;

import java.io.FileNotFoundException;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.support.AllStatic;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddPngActivity extends Activity {

	private TextView bendi;
	private TextView paizhao;
	private TextView sure;
	private EditText title;
	String url, picPath;
	Uri uri;
	private ImageView image;
	int invid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_png);
		bendi = (TextView) findViewById(R.id.add_bendi);
		paizhao = (TextView) findViewById(R.id.add_paizhao);
		sure = (TextView) findViewById(R.id.add_sure);
		title = (EditText) findViewById(R.id.add_title);
		image = (ImageView) findViewById(R.id.image);
		Intent intent = getIntent();
		invid=intent.getIntExtra("invid", 0);
		bendi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				camera();

			}
		});

		paizhao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				gallery();

			}
		});

		sure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				sure.setClickable(false);
				// TODO Auto-generated method stub
				String name = title.getText().toString();
				FinalHttp finalHttp = new FinalHttp();
				AjaxParams params = new AjaxParams();
				params.put("invid", invid + "");
				params.put("uid", AllStatic.loginConfig.getId() + "");
				params.put("name", name);
				try {
					params.put("file", new File(picPath));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				url = "http://123.56.95.134:8080/Testlife/servlet/ServletTest";

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

							if (value == true) {
								Toast.makeText(getApplicationContext(), "上传成功",
										Toast.LENGTH_SHORT).show();
sure.setClickable(true);
								finish();
							} else {
								Toast.makeText(getApplicationContext(),
										R.string.notitle, Toast.LENGTH_SHORT)
										.show();
								sure.setClickable(true);
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
						Toast.makeText(getApplicationContext(),
								R.string.wangluo, Toast.LENGTH_SHORT).show();
						sure.setClickable(true);
					}

				});

			}
		});

	}

	// protected void opentuku() {
	// // TODO Auto-generated method stub
	// Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
	// intent.addCategory(intent.CATEGORY_OPENABLE);
	// intent.setType("image/*");
	// startActivityForResult(intent, 1);
	//
	// }
	// @Override
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// // TODO Auto-generated method stub
	// super.onActivityResult(requestCode, resultCode, data);
	// if(requestCode==1 && resultCode==RESULT_OK){
	// uri=data.getData();
	//
	// String[] pojo = { MediaStore.Images.Media.DATA };
	//
	// Cursor cursor = managedQuery(uri, pojo, null, null, null);
	// if (cursor != null) {
	// ContentResolver cr = this.getContentResolver();
	// int colunm_index = cursor
	// .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	// cursor.moveToFirst();
	// String path = cursor.getString(colunm_index);
	// /***
	// * 这里加这样一个判断主要是为了第三方的软件选择，比如：使用第三方的文件管理器的话，你选择的文件就不一定是图片了，
	// * 这样的话，我们判断文件的后缀名 如果是图片格式的话，那么才可以
	// */
	// if (path.endsWith("jpg") || path.endsWith("png")|| path.endsWith("JPEG"))
	// {
	// picPath = path;
	// try {
	// Bitmap bitmap = BitmapFactory.decodeStream(cr
	// .openInputStream(uri));
	//
	// image.setImageBitmap(bitmap);
	//
	// } catch (FileNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }}}
	//
	//
	// }
	File tempFile;
	

	public void gallery() {
		// 激活系统图库，选择一张图片
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/*");
		// 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
		startActivityForResult(intent, 2);
	}

	public static boolean hasSdcard() {
		String STATE = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED.equals(STATE);
	}

	public void camera() {
		// 激活相机
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		// 判断存储卡是否可以用，可用进行存储
		if (hasSdcard()) {
			tempFile = new File(Environment.getExternalStorageDirectory(),
					"textphoto.jpg");
			// 从文件中创建uri
			Uri uri = Uri.fromFile(tempFile);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		}
		// 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
		startActivityForResult(intent, 1);
	}

	// 第二步，通过startActivityForResult()方法我们可以在onActivityResult()中来获取到相应的返回值
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 2 && resultCode == RESULT_OK) {
			// 从相册返回的数据
			if (data != null) {
				// 得到图片的全路径
				Uri uri = data.getData();
				image.setImageURI(uri);
				String[] pojo = { MediaStore.Images.Media.DATA };

				Cursor cursor = managedQuery(uri, pojo, null, null, null);
				if (cursor != null) {
					// ContentResolver cr = this.getContentResolver();
					int colunm_index = cursor
							.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
					cursor.moveToFirst();
					String path = cursor.getString(colunm_index);
					/***
					 * 这里加这样一个判断主要是为了第三方的软件选择，比如：使用第三方的文件管理器的话，你选择的文件就不一定是图片了，
					 * 这样的话，我们判断文件的后缀名 如果是图片格式的话，那么才可以
					 */
					if (path.endsWith("jpg") || path.endsWith("png")
							|| path.endsWith("JPEG")) {
						picPath = path;

						if (picPath != null) {
							title.setVisibility(View.VISIBLE);
							sure.setVisibility(View.VISIBLE);
							sure.setClickable(true);

						}

					}
				} else {

					Toast.makeText(getApplicationContext(), "不知道为什么，图片格式不对",
							Toast.LENGTH_SHORT).show();

				}
			}

		} else if (requestCode == 1 && resultCode == RESULT_OK) {
			// 从相机返回的数据
			if (hasSdcard()) {
				Uri uri = data.getData();
				image.setImageURI(uri);
				String[] pojo = { MediaStore.Images.Media.DATA };

				Cursor cursor = managedQuery(uri, pojo, null, null, null);
				if (cursor != null) {
					// ContentResolver cr = this.getContentResolver();
					int colunm_index = cursor
							.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
					cursor.moveToFirst();
					String path = cursor.getString(colunm_index);
					/***
					 * 这里加这样一个判断主要是为了第三方的软件选择，比如：使用第三方的文件管理器的话，你选择的文件就不一定是图片了，
					 * 这样的话，我们判断文件的后缀名 如果是图片格式的话，那么才可以
					 */
					if (path.endsWith("jpg") || path.endsWith("png")
							|| path.endsWith("JPEG")) {
						picPath = path;

						if (picPath != null) {
							title.setVisibility(View.VISIBLE);
							sure.setVisibility(View.VISIBLE);
							sure.setClickable(true);

						}

					}
				} else {

					Toast.makeText(getApplicationContext(), "不知道为什么，图片格式不对",
							Toast.LENGTH_SHORT).show();

				}

			} else {
				Toast.makeText(getApplicationContext(), "未找到存储卡，无法存储照片",
						Toast.LENGTH_SHORT).show();
			}

			// } else if (requestCode ==3&& resultCode==RESULT_OK) {
			// // 从剪切图片返回的数据
			// if (data != null) {
			// Bitmap bitmap = data.getParcelableExtra("data");
			// image.setImageBitmap(bitmap);
			// Uri uri=data.getData();
			//
			// String[] pojo = { MediaStore.Images.Media.DATA };
			//
			// Cursor cursor = managedQuery(uri, pojo, null, null, null);
			// if (cursor != null) {
			// // ContentResolver cr = this.getContentResolver();
			// int colunm_index = cursor
			// .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			// cursor.moveToFirst();
			// String path = cursor.getString(colunm_index);
			// /***
			// * 这里加这样一个判断主要是为了第三方的软件选择，比如：使用第三方的文件管理器的话，你选择的文件就不一定是图片了，
			// * 这样的话，我们判断文件的后缀名 如果是图片格式的话，那么才可以
			// */
			// if (path.endsWith("jpg") || path.endsWith("png")||
			// path.endsWith("JPEG")) {
			// picPath = path;
			//
			// if(picPath!=null){
			// title.setVisibility(View.VISIBLE);
			// sure.setVisibility(View.VISIBLE);
			// sure.setClickable(true);
			//
			// }
			//
			// }}else{
			//
			// Toast.makeText(getApplicationContext(), "不知道为什么，图片格式不对",
			// Toast.LENGTH_SHORT).show();
			//
			// }
			//
			// }
			try {
				// 将临时文件删除
				tempFile.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	// 第三步，注意到里边有一个crop()方法，顾名思义，就是用于剪切图片的
	/*
	 * 剪切图片
	 */
	// private void crop(Uri uri) {
	// // 裁剪图片意图
	// Intent intent = new Intent("com.android.camera.action.CROP");
	// intent.setDataAndType(uri, "image/*");
	// intent.putExtra("crop", "true");
	// // 裁剪框的比例，1：1
	// intent.putExtra("aspectX", 1);
	// intent.putExtra("aspectY", 1);
	// intent.putExtra("outputX", 250);
	// intent.putExtra("outputY", 250);
	// intent.putExtra("outputFormat", "JPEG");// 图片格式
	// intent.putExtra("noFaceDetection", true);// 取消人脸识别
	// intent.putExtra("return-data", true);
	// // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
	// startActivityForResult(intent, 3);
	// }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_png, menu);
		return true;
	}

}
