package com.example.utils;

public class Constant {
	//login config
	public static final String LOGIN_SET = "eim_login_set";// ��¼����
	public static final String USERNAME = "username";// �˻�
	public static final String PASSWORD = "password";// ����
	public static final String SCHOOL = "school";// ����
	public static final String PERSONAL = "personal";// ����
	public static final String LEVEL = "level";// ����
	public static final String URL = "hh";// ����
	public static final String IS_FIRSTSTART = "isfirststart";// ����
	public static final String ID = "id";// ����
	public static final String HEAD = "head";// ����
	public static final String GENDER = "gender";// ����
	
	public final static String  ACTION_NAME="haha";
	
	public final static int REGISTER=0;        //ע��1        name pass gender head college personal            uid  
	public final static int LOGIN=1;           //����1        name pass                                  uid college personal 
	public final static int UPDATEUSER=2;      //�޸��û���Ϣ1  pass gender head college personal            true false
	public final static int CREATEINVITATION=3;//����2        uid title content type                      invid title createtime 
	public final static int DELETEINVITATION=4;//ɾ��2        invid                                         true false
	public final static int ALLINVITATION=5;   //��ѯ��������2   flag=5  page                                   list<invitation>    
	public final static int ZONGZANINVITATION=6;   //��������2  flag=6   page                                  list<invitation>
	public final static int TYPEINVITATION=7;  //�����Ͳ�ѯ2    flag=7    page                                 list<invitation>
	public final static int TITLEINVITATION=8;  //�������ѯ2    title  page
	public final static int CREATEREPLY=9;     //����ظ�3      invid uid content                            true false   time
	public final static int DELETEREPLY=10;     //ɾ���ظ�3x     
	public final static int ALLREPLY=11;       //���лظ�3      flag=11  page                                    list<reply>
	public final static int ZANREPLY=12;      //�ظ���������3    ...
	public final static int PAGEREPLY=13;      //�ظ���ҳ������3x
	public final static int CREATECLUB=14;     //��������4      uid title introduce head                      invid
	public final static int DELETECLUB=15;     //ɾ������4      cid   uid                                     true
	public final static int ALLCLUB=16;        //��ѯ��������4   
	public final static int JOINCLUB=17;       //ÿ���û��μӵ�����5 uid                                         list<club>
	public final static int DELETEUSERCLUB=18; //�˳�����5         uid cid                                        true
	public final static int MESSAGE=19;        //���͵���Ϣ6      content  fromid toid                          true              
	public final static int INVITATIONZAN=20;            //���ӵ���7  invid
	public final static int REPLYZAN=21;            //�؏͵���7      invid replyid
	public final static int QUERYMESSAGE=22;   //��ѯ��û������Ϣ8     uid                                     list<message>  fromname
	public final static int QUERYUSER=23;   //�����ֲ�ѯ�û�                       name                                        user
	public final static int GUANZHUUSER=24;   //��ע�û�                            uid  fid                                          true
	public final static int QUERYGUANZHUUSER=44;   //���ҹ�ע�û�                                                                                                                     list<user>
	public final static int QUXIAOGUANZHU=25;   //ȡ����ע                     uid   fid
	public final static int ZHOUZANINVITATION=26;   //һ���޵�����   
	public final static int YUEZANINVITATION=27;   //һ�µ�����
	public final static int CREATESPORT=28;   //�����
	public final static int DELETESPORT=29;   //ɾ���
	public final static int JOINSPORT=30;   //�μӻ
	public final static int QUITSPORT=31;   //QUXIAO�
	public final static int REPLYSPORT=32;   //HUIFU�
	public final static int USERCLUB=33;   //HUIFU�
	public final static int USERINVITATION=34;   //HUIFU�
	public final static int USERGUANZHU=36;   //HUIFU�
	public final static int SPORTREPLY=37;
	public final static int USERSPORT=35;   //HUIFU�
	public final static int SPORTUSER=40;   //HUIFU�
	public final static int ALLSPORT=38;   //HUIFU�
	public final static int TITLESPORT=39;   //HUIFU�
	public final static int TITLECLUB=46;   //HUIFU�
	public final static int GUANZHUINV=40;   //HUIFU�
	public final static int LOUZHUREPLY=41;   //HUIFU�
	public final static int QUERYUSERID=42;   //HUIFU�
	public final static int DAOREPLY=43;   //HUIFU�
	public final static int RECMESSAGE=44;   //HUIFU�
	public final static int QUXIAOINV=47;   //HUIFU�
	public final static int INVUSER=45;   //HUIFU�
	public final static int TOPUSER=49;   //HUIFU�
	public final static int TOPINV=48; 
	public final static int ALLINFO=50;//HUIFU�
	public final static int USERFANS=51;
	public final static int DAOSREPLY=52;   //HUIFU�
	public final static int LOUZHUSREPLY=53;
	public final static int UPSPO=54;
	public final static int ADMINUSER=55;
	public final static int ZHIDINGINV=56;
	public final static int ZHIDINGSPO=57;
	public final static int INVIMAGE=58;

}
