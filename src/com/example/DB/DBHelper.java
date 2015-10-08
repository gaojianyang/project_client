package com.example.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
	private static String DBNAME="afinal.db";
	private static int DBVERSION=1;
//	private static String createuser="create table user(uid integer primary key ,name varchar(40) unique,gender varchar(10),head integer,school varchar(40),personal varchar(60));";
//	private static String createinv="create table inv(invid integer primary key,uid integer,head integer,uname varchar(40) ,tittle varchar(50));";
//	private static String createclub="create table club(cid integer primary key,uid integer,head integer,tittle varchar(50),introduce varchar(60),number integer,invid integer);";
//	
public DBHelper(Context context){
	this(context,DBNAME,null,DBVERSION);
	
}
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
//		arg0.execSQL(createuser);
//		arg0.execSQL(createinv);
//		arg0.execSQL(createclub);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	

}
