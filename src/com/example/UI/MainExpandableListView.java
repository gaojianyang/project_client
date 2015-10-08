package com.example.UI;




import com.example.project_client1120.R;

import android.content.Context; 
import android.util.AttributeSet; 
import android.view.LayoutInflater; 
import android.view.MotionEvent; 
import android.view.View; 
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator; 
import android.view.animation.RotateAnimation; 
import android.widget.AbsListView; 
import android.widget.AbsListView.OnScrollListener; 
import android.widget.ExpandableListView; 
import android.widget.ImageView; 
import android.widget.LinearLayout; 
import android.widget.ListAdapter; 
import android.widget.ProgressBar; 
import android.widget.TextView; 


public class MainExpandableListView extends ExpandableListView implements 
        OnScrollListener { 
	  private final static int RELEASE_TO_REFRESH = 0;
	    private final static int PULL_TO_REFRESH = 1;
	    //正在刷新
	    private final static int REFRESHING = 2;
	    //刷新完成
	    private final static int DONE = 3;
	    private final static int LOADING = 4;
	    
	    private final static int RADIO = 3;
	    
	    private LayoutInflater mInflater;
	    private LinearLayout mHeadView;
	    private TextView mTipsTextView;
	    private ImageView mArrowImageView;
	    private ProgressBar mProgressBar;
	    
	    private RotateAnimation mAnimation;
	    private RotateAnimation mReverseAnimation;
	    private boolean mIsRecored;
	    private int mHeadContentWidth;
	    private int mHeadContentHeight;
	    private int mStartY;
	    private int mFirstItemIndex;
	    private int mState;
	    private boolean mIsBack;
	    private boolean mISRefreshable;
	    private OnRefreshListener mRefreshListener;
	    
	    public MainExpandableListView(Context context, AttributeSet attrs) {
	        super(context, attrs);
	        init(context);
	    }

	    private void init(Context context) {
//	        setCacheColorHint(android.R.color.black);
	        mInflater = LayoutInflater.from(context);
	        mHeadView = (LinearLayout) mInflater.inflate(R.layout.headview, null);
	        mArrowImageView = (ImageView) mHeadView.findViewById(R.id.head_arrowImageView);
//	        mArrowImageView.setMinimumWidth(70);
//	        mArrowImageView.setMinimumHeight(50);
	        mProgressBar = (ProgressBar) mHeadView.findViewById(R.id.head_progressBar);
	        mTipsTextView = (TextView) mHeadView.findViewById(R.id.head_tipsTextView);
	        
	        measureView(mHeadView);
	        mHeadContentHeight = mHeadView.getMeasuredHeight();
	        mHeadContentWidth = mHeadView.getMeasuredWidth();
	        System.out.println("mHeadContentWidth = " + mHeadContentWidth);
	        mHeadView.setPadding(0, -1 * mHeadContentHeight, 0, 0);
	        mHeadView.invalidate();
	        addHeaderView(mHeadView, null, false);
	        setOnScrollListener(this);
	        
	        mAnimation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
	        mAnimation.setInterpolator(new LinearInterpolator());
	        mAnimation.setDuration(250);
	        mAnimation.setFillAfter(true);
	        
	        mReverseAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
	        mReverseAnimation.setInterpolator(new LinearInterpolator());
	        mReverseAnimation.setDuration(250);
	        mReverseAnimation.setFillAfter(true);
	        
	        mState = DONE;
	        mISRefreshable = false;
	    }
	    
	    private void measureView(View child) {
	        android.view.ViewGroup.LayoutParams params = child.getLayoutParams();
	        System.out.println("params = " + params);
	        if(params == null) {
	            params = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	        }
	        System.out.println("lpWidth = " + params.width);
	        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0+0, params.width);
	        System.out.println("childWidthSpec = " + childWidthSpec);
	        int lpHeight = params.height;
	        System.out.println("lpHeight = " + lpHeight);
	        int childHeightSpec;
	        if(lpHeight > 0) {
	            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
	        } else {
	            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.UNSPECIFIED);
	        }
	        System.out.println("childHeightSpec = " + childHeightSpec);
	        child.measure(childWidthSpec, childHeightSpec);
	    }

	    @Override
	    public void onScrollStateChanged(AbsListView view, int scrollState) {
	        
	    }

	    @Override
	    public void onScroll(AbsListView view, int firstVisibleItem,
	            int visibleItemCount, int totalItemCount) {
	        mFirstItemIndex = firstVisibleItem;
	    }

	    public interface OnRefreshListener {
	        public void onRefresh();
	    }
	    
	    private void onRefresh() {
	        if(mRefreshListener != null) {
	            mRefreshListener.onRefresh();
	        }
	    }
	    
	    public void onRefreshComplete() {
	        mState = DONE;
	        changeHeaderViewByState();
	    }
	    
	    public void setonRefreshListener(OnRefreshListener onRefreshListener) {
	        this.mRefreshListener = onRefreshListener;
	        mISRefreshable = true;
	    }
	    
	    @Override
	    public boolean onTouchEvent(MotionEvent ev) {
	        if(mISRefreshable) {
	            switch (ev.getAction()) {
	            case MotionEvent.ACTION_DOWN:
	                if(mFirstItemIndex == 0 && !mIsRecored) {
	                    mIsRecored = true;
	                    mStartY = (int) ev.getY();
	                }
	                break;

	            case MotionEvent.ACTION_UP:
	                if(mState != REFRESHING && mState != LOADING) {
	                    if(mState == DONE) {
	                        
	                    }
	                    if(mState == PULL_TO_REFRESH) {
	                        mState = DONE;
	                        changeHeaderViewByState();
	                    }
	                    if(mState == RELEASE_TO_REFRESH) {
	                        mState = REFRESHING;
	                        changeHeaderViewByState();
	                        onRefresh();
	                    }
	                }
	                mIsBack = false;
	                mIsRecored = false;
	                break;
	                
	            case MotionEvent.ACTION_MOVE:
	                int tempY = (int) ev.getY();
	                if(!mIsRecored && mFirstItemIndex == 0) {
	                    mIsRecored = true;
	                    mStartY = tempY;
	                }
	                if(mState != REFRESHING && mIsRecored && mState != LOADING) {
	                    if(mState == RELEASE_TO_REFRESH) {
	                        setSelection(0);
	                        if((tempY - mStartY)/RADIO < mHeadContentHeight && (tempY - mStartY) > 0) {
	                            mState = PULL_TO_REFRESH;
	                            changeHeaderViewByState();
	                        } else if(tempY - mStartY <= 0) {
	                            mState = DONE;
	                            changeHeaderViewByState();
	                        }
	                    }
	                    
	                    if(mState == PULL_TO_REFRESH) {
	                        setSelection(0);
	                        if((tempY - mStartY)/RADIO >= mHeadContentHeight) {
	                            mState = RELEASE_TO_REFRESH;
	                            mIsBack = true;
	                            changeHeaderViewByState();
	                        }
	                    } else if(tempY - mStartY <= 0) {
	                        mState = DONE;
	                        changeHeaderViewByState();
	                    }
	                    
	                    if(mState == DONE) {
	                        if(tempY - mStartY > 0) {
	                            mState = PULL_TO_REFRESH;
	                            changeHeaderViewByState();
	                        }
	                    }
	                    
	                    if(mState == PULL_TO_REFRESH) {
	                        mHeadView.setPadding(0, -1 * mHeadContentHeight + (tempY - mStartY)/RADIO, 0, 0);
	                    }
	                    
	                    if(mState == RELEASE_TO_REFRESH) {
	                        mHeadView.setPadding(0, (tempY - mStartY)/RADIO - mHeadContentHeight, 0, 0);
	                    }
	                }
	                break;
	                
	            default:
	                break;
	            }
	        }
	        return super.onTouchEvent(ev);
	    }

	    private void changeHeaderViewByState() {
	        switch (mState) {
	        case PULL_TO_REFRESH:
	            mProgressBar.setVisibility(GONE);
	            mTipsTextView.setVisibility(VISIBLE);
	            mArrowImageView.clearAnimation();
	            mArrowImageView.setVisibility(VISIBLE);
	            if(mIsBack) {
	                mIsBack = false;
	                mArrowImageView.clearAnimation();
	                mArrowImageView.startAnimation(mReverseAnimation);
	                mTipsTextView.setText("");
	            } else {
	                mTipsTextView.setText("再往下一点才可以哦");
	            }
	            break;

	        case DONE:
	            mHeadView.setPadding(0, -1 * mHeadContentHeight, 0, 0);
	            mProgressBar.setVisibility(GONE);
	            mArrowImageView.clearAnimation();
	            mArrowImageView.setImageResource(R.drawable.arrow);
	            mTipsTextView.setText("已经加载完毕");
	            break;
	            
	        case REFRESHING:
	            mHeadView.setPadding(0, 0, 0, 0);
	            mProgressBar.setVisibility(VISIBLE);
	            mArrowImageView.clearAnimation();
	            mArrowImageView.setVisibility(GONE);
	            mTipsTextView.setText("正在加载中……");
	            break;
	            
	        case RELEASE_TO_REFRESH:
	            mArrowImageView.setVisibility(VISIBLE);
	            mProgressBar.setVisibility(GONE);
	            mTipsTextView.setVisibility(VISIBLE);
	            mArrowImageView.clearAnimation();
	            mArrowImageView.startAnimation(mAnimation);
	            mTipsTextView.setText("请释放刷新");
	            break;
	        default:
	            break;
	        }
	    }
	    
	    @Override
	    public void setAdapter(ListAdapter adapter) {
	        super.setAdapter(adapter);
	    }
	
	    
//
//    @Override 
//    public void setAdapter(ListAdapter adapter) { 
//        // TODO Auto-generated method stub 
//        super.setAdapter(adapter); 
//    } 
//
//    //松开刷新 
//    private final static int RELEASE_TO_REFRESH=0;// 
//    private final static int PULL_TO_REFRESH=1;//下拉刷新 
//    
//    private final static int REFRESHING =2;//正在刷新 
//    
//    private final static int DONE = 3;    
//    private final static int RATIO=3;//实际的padding的距离与界面 上偏移距离 的比例 
//    private LayoutInflater inflater; 
////    private LinearLayout headLayout;//头linearlayout 
//    private TextView tipsTextview; 
//    private TextView lastUpdatedTextView; 
//    private ImageView arrowImageView;//箭头的图标 
//    
//    private ProgressBar progressBar; 
//
//    private RotateAnimation animation; 
//    // 反转动画 
//    private RotateAnimation reverseAnimation; 
//
//    private int headContentWidth;//头部的宽度 
//    private OnRefreshListener mRefreshListener;
//
//    private LinearLayout headView; 
//    private int headContentHeight; 
//     
//    /** 手势按下的起点位置 */ 
//    private int startY; 
//    private int firstItemIndex; 
//
//    private int state; 
//    private int mState;
//    private boolean mIsBack;
//    private boolean mISRefreshable;
//
//
//    private boolean isBack; 
//
//    //private OnRefreshListener refreshListener; 
//
//    private boolean isRefreshable; 
//
//    public MainExpandableListView(Context context) { 
//        super(context); 
//        // TODO Auto-generated constructor stub 
//        init(context); 
//    } 
//    public MainExpandableListView(Context context, AttributeSet attrs) { 
//        super(context, attrs); 
//        // TODO Auto-generated constructor stub 
//        init(context); 
//    } 
//
//    public void onClick(DialogInterface dialog, int which) { 
//        // TODO Auto-generated method stub 
//
//    } 
//    public void init(Context context) 
//    { 
//        inflater =LayoutInflater.from(context); 
//        headView =(LinearLayout) inflater.inflate(R.layout.headview, null); 
//        arrowImageView = (ImageView) headView.findViewById(R.id.head_arrowImageView);//箭头 
//        arrowImageView.setMinimumWidth(70); 
//        arrowImageView.setMinimumHeight(50); 
//        progressBar = (ProgressBar) headView.findViewById(R.id.head_progressBar); 
//        tipsTextview = (TextView) headView.findViewById(R.id.head_tipsTextView); 
//        lastUpdatedTextView = (TextView) headView.findViewById(R.id.head_lastUpdatedTextView); 
//        
//        headView.measure(0, 0); 
//        headContentHeight=headView.getMeasuredHeight(); 
//        headContentWidth=headView.getMeasuredWidth(); 
//        
//        headView.setPadding(0, -headContentHeight, 0, 0);//把headview隐藏到顶部 
//        headView.invalidate();//刷新界面 
//        
//        addHeaderView(headView,null,false); 
//        setOnScrollListener(this);//滚动监听 
//        animation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f); 
//        animation.setInterpolator(new LinearInterpolator()); 
//        animation.setDuration(250); 
//        animation.setFillAfter(true); 
//        
//        reverseAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f); 
//        reverseAnimation.setInterpolator(new LinearInterpolator()); 
//        reverseAnimation.setDuration(200); 
//        reverseAnimation.setFillAfter(true); 
//        
//        state = DONE; 
//        isRefreshable = false; 
//    } 
//    public void onScroll(AbsListView view, int firstVisibleItem, 
//            int visibleItemCount, int totalItemCount) { 
//        // TODO Auto-generated method stub 
//        firstItemIndex=firstVisibleItem; 
//        
//    } 
//    public void onScrollStateChanged(AbsListView view, int scrollState) { 
//        // TODO Auto-generated method stub 
//        
//    } 
//    public interface OnRefreshListener {
//        public void onRefresh();
//    }
//    
//    private void onRefresh() {
//        if(mRefreshListener != null) {
//            mRefreshListener.onRefresh();
//        }
//    }
//    
//    public void onRefreshComplete() {
//        mState = DONE;
//        lastUpdatedTextView.setText("已加载完成：" + new Date().toLocaleString());
//        changeHeaderViewByState();
//    }
//    
//    public void setonRefreshListener(OnRefreshListener onRefreshListener) {
//        this.mRefreshListener = onRefreshListener;
//        mISRefreshable = true;
//    }
//    
//    /** 
//     * 设置触摸事件 总的思路就是 
//     * 
//     * 1 ACTION_DOWN：记录起始位置 
//     * 
//     * 2 ACTION_MOVE：计算当前位置与起始位置的距离，来设置state的状态 
//     * 
//     * 3 ACTION_UP：根据state的状态来判断是否下载 
//     */ 
//    public boolean onTouchEvent(MotionEvent event) 
//    { 
//        isRefreshable = true; 
//        if (isRefreshable) { 
//            switch (event.getAction()) { 
//            case MotionEvent.ACTION_DOWN://按下屏幕 
//                System.out.println("按下屏"); 
//                if (firstItemIndex==0) { 
//                    startY=(int)event.getY(); 
//                    System.out.println("记录down下当前的位置"); 
//                    
//                    
//                } 
//                break; 
//                
//            case MotionEvent.ACTION_MOVE: //移动屏幕 
//                System.out.println("移动下屏"); 
//                int tempY=(int)event.getY(); 
//                if (state ==PULL_TO_REFRESH) { 
//                    setSelection(0);//很重要 
//                    //下拉到可以release_to_refresh的状态 
//                    if ((tempY-startY)/RATIO>=headContentHeight) { 
//                        state=RELEASE_TO_REFRESH; 
//                        isBack=true; 
//                        changeHeaderViewByState();    
//                    } 
//                    //上推到顶了 
//                    else if(tempY-startY<=0){ 
//                        state =DONE; 
//                        changeHeaderViewByState(); 
//                    } 
//                    headView.setPadding(0, -headContentHeight + (tempY - startY) / RATIO, 0, 0); 
//                } 
//                if (state == RELEASE_TO_REFRESH) { 
//                    setSelection(0); 
//                    // 往上推了，推到了屏幕足够掩盖head的程度，但是还没有推到全部掩盖的地步 
//                    if (((tempY - startY) / RATIO < headContentHeight) && (tempY - startY) > 0) { 
//                        state = PULL_TO_REFRESH; 
//                        changeHeaderViewByState(); 
////                        Log.v(TAG, "由松开刷新状态转变到下拉刷新状态"); 
//                    } 
//                    headView.setPadding(0, -headContentHeight + (tempY - startY) / RATIO, 0, 0); 
//                } 
//                // done状态下 
//                if (state == DONE) { 
//                    if (tempY - startY > 0) { 
//                        state = PULL_TO_REFRESH; 
//                        changeHeaderViewByState(); 
//                    } 
//                } 
//                break; 
//            case MotionEvent.ACTION_UP: 
//                System.out.println("ACTION_UP"); 
//                if (state != REFRESHING) { 
//                    // 不在刷新状态 
//                    if (state == PULL_TO_REFRESH) { 
//                        state = DONE; 
//                        changeHeaderViewByState(); 
////                        Log.v(TAG, "下拉刷新状态，到done状态"); 
//                    } 
//                    if (state == RELEASE_TO_REFRESH) { 
//                        state = REFRESHING; 
//                        changeHeaderViewByState(); 
//                        isRefreshable = true; 
////                        Log.v(TAG, "松开刷新状态，到done状态"); 
//                    } 
//                } 
//                isBack = false; 
//                break; 
//
//            } 
//        } 
//        return super.onTouchEvent(event); 
//        
//    } 
//    //当状态改变时候，调用 该方法，以更新界面 
//    private void changeHeaderViewByState() 
//    { 
//        switch (state) { 
//        case RELEASE_TO_REFRESH: 
//            arrowImageView.setVisibility(View.VISIBLE); 
//            progressBar.setVisibility(View.GONE); 
//            tipsTextview.setVisibility(View.VISIBLE); 
//
//            arrowImageView.clearAnimation(); 
//            arrowImageView.startAnimation(animation); 
//
//            tipsTextview.setText("松开刷新"); 
//
////            Log.v(TAG, "当前状态，松开刷新"); 
//            break; 
//        case PULL_TO_REFRESH: 
//            progressBar.setVisibility(View.GONE); 
//            tipsTextview.setVisibility(View.VISIBLE); 
//
//            arrowImageView.clearAnimation(); 
//            arrowImageView.setVisibility(View.VISIBLE); 
//            tipsTextview.setText("下拉刷新"); 
//            // 是RELEASE_To_REFRESH状态转变来的 
//            if (isBack) { 
//                isBack = false; 
//                arrowImageView.startAnimation(reverseAnimation); 
//            } 
////            Log.v(TAG, "当前状态，下拉刷新"); 
//            break; 
//
//        case REFRESHING: 
//            headView.setPadding(0, 0, 0, 0); 
//            progressBar.setVisibility(View.VISIBLE); 
//            arrowImageView.clearAnimation(); 
//            arrowImageView.setVisibility(View.GONE); 
//            tipsTextview.setText("正在刷新..."); 
//            break; 
//        case DONE: 
//            headView.setPadding(0, -headContentHeight, 0, 0); 
//            progressBar.setVisibility(View.GONE); 
//            arrowImageView.clearAnimation(); 
//            arrowImageView.setImageResource(R.drawable.user32); 
//            tipsTextview.setText("下拉刷新"); 
//            break; 
//        } 
//    } 
} 