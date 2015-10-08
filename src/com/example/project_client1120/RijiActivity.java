package com.example.project_client1120;

import java.util.ArrayList;

import java.util.List;

import com.example.pojo.Riji;
import com.example.support.AllStatic;
import com.example.utils.RijiAdapter;

import net.tsz.afinal.FinalDb;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class RijiActivity extends Activity implements OnClickListener{
List<Riji> list=new ArrayList<Riji>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_riji);
		TextView back = (TextView) findViewById(R.id.tv_back);
		TextView add = (TextView) findViewById(R.id.add);
		ListView lv = (ListView) findViewById(R.id.lv_riji);
		back.setOnClickListener(this);
		add.setOnClickListener(this);
		FinalDb finalDb=FinalDb.create(this);
		
	list=finalDb.findAllByWhere(Riji.class, "userid="+AllStatic.loginConfig.getId(),"riqi desc");
	RijiAdapter adapter=new RijiAdapter(getApplicationContext(), list);
	lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
			Intent intent=new Intent(getApplicationContext(),XieRijiActivity.class);
			intent.putExtra("type", 1);
			intent.putExtra("riqi", list.get(arg2).getRiqi());
			intent.putExtra("content", list.get(arg2).getContent());
			startActivity(intent);
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_riji, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.tv_back:
			finish();
			break;
		case R.id.add:
			Intent intent=new Intent(getApplicationContext(),XieRijiActivity.class);
			intent.putExtra("type", 0);
			startActivity(intent);
			
			break;
		default:
			break;
		}
	}

}
