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
								Toast.makeText(getApplicationContext(), "�ϴ��ɹ�",
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
	// * ���������һ���ж���Ҫ��Ϊ�˵����������ѡ�񣬱��磺ʹ�õ��������ļ��������Ļ�����ѡ����ļ��Ͳ�һ����ͼƬ�ˣ�
	// * �����Ļ��������ж��ļ��ĺ�׺�� �����ͼƬ��ʽ�Ļ�����ô�ſ���
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
		// ����ϵͳͼ�⣬ѡ��һ��ͼƬ
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/*");
		// ����һ�����з���ֵ��Activity��������ΪPHOTO_REQUEST_GALLERY
		startActivityForResult(intent, 2);
	}

	public static boolean hasSdcard() {
		String STATE = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED.equals(STATE);
	}

	public void camera() {
		// �������
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		// �жϴ洢���Ƿ�����ã����ý��д洢
		if (hasSdcard()) {
			tempFile = new File(Environment.getExternalStorageDirectory(),
					"textphoto.jpg");
			// ���ļ��д���uri
			Uri uri = Uri.fromFile(tempFile);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		}
		// ����һ�����з���ֵ��Activity��������ΪPHOTO_REQUEST_CAREMA
		startActivityForResult(intent, 1);
	}

	// �ڶ�����ͨ��startActivityForResult()�������ǿ�����onActivityResult()������ȡ����Ӧ�ķ���ֵ
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 2 && resultCode == RESULT_OK) {
			// ����᷵�ص�����
			if (data != null) {
				// �õ�ͼƬ��ȫ·��
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
					 * ���������һ���ж���Ҫ��Ϊ�˵����������ѡ�񣬱��磺ʹ�õ��������ļ��������Ļ�����ѡ����ļ��Ͳ�һ����ͼƬ�ˣ�
					 * �����Ļ��������ж��ļ��ĺ�׺�� �����ͼƬ��ʽ�Ļ�����ô�ſ���
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

					Toast.makeText(getApplicationContext(), "��֪��Ϊʲô��ͼƬ��ʽ����",
							Toast.LENGTH_SHORT).show();

				}
			}

		} else if (requestCode == 1 && resultCode == RESULT_OK) {
			// ��������ص�����
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
					 * ���������һ���ж���Ҫ��Ϊ�˵����������ѡ�񣬱��磺ʹ�õ��������ļ��������Ļ�����ѡ����ļ��Ͳ�һ����ͼƬ�ˣ�
					 * �����Ļ��������ж��ļ��ĺ�׺�� �����ͼƬ��ʽ�Ļ�����ô�ſ���
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

					Toast.makeText(getApplicationContext(), "��֪��Ϊʲô��ͼƬ��ʽ����",
							Toast.LENGTH_SHORT).show();

				}

			} else {
				Toast.makeText(getApplicationContext(), "δ�ҵ��洢�����޷��洢��Ƭ",
						Toast.LENGTH_SHORT).show();
			}

			// } else if (requestCode ==3&& resultCode==RESULT_OK) {
			// // �Ӽ���ͼƬ���ص�����
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
			// * ���������һ���ж���Ҫ��Ϊ�˵����������ѡ�񣬱��磺ʹ�õ��������ļ��������Ļ�����ѡ����ļ��Ͳ�һ����ͼƬ�ˣ�
			// * �����Ļ��������ж��ļ��ĺ�׺�� �����ͼƬ��ʽ�Ļ�����ô�ſ���
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
			// Toast.makeText(getApplicationContext(), "��֪��Ϊʲô��ͼƬ��ʽ����",
			// Toast.LENGTH_SHORT).show();
			//
			// }
			//
			// }
			try {
				// ����ʱ�ļ�ɾ��
				tempFile.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	// ��������ע�⵽�����һ��crop()����������˼�壬�������ڼ���ͼƬ��
	/*
	 * ����ͼƬ
	 */
	// private void crop(Uri uri) {
	// // �ü�ͼƬ��ͼ
	// Intent intent = new Intent("com.android.camera.action.CROP");
	// intent.setDataAndType(uri, "image/*");
	// intent.putExtra("crop", "true");
	// // �ü���ı�����1��1
	// intent.putExtra("aspectX", 1);
	// intent.putExtra("aspectY", 1);
	// intent.putExtra("outputX", 250);
	// intent.putExtra("outputY", 250);
	// intent.putExtra("outputFormat", "JPEG");// ͼƬ��ʽ
	// intent.putExtra("noFaceDetection", true);// ȡ������ʶ��
	// intent.putExtra("return-data", true);
	// // ����һ�����з���ֵ��Activity��������ΪPHOTO_REQUEST_CUT
	// startActivityForResult(intent, 3);
	// }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_png, menu);
		return true;
	}

}
