package com.chejiawang.android.studentclient.base;

import android.support.v4.app.Fragment;

public abstract class BasedFragment extends Fragment {
	
	protected static final int STATE_NONE = 0;
	protected static final int STATE_REFRESH = 1;
	protected static final int STATE_LOADMORE = 2;
	protected int mState = STATE_NONE;
	//public abstract void getParentMessage(Message msg);
    /** Fragment当前状态是否可见 */
    protected boolean isVisible;
     
     
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//         
//        if(getUserVisibleHint()) {
//            isVisible = true;
//            onVisible();
//        } else {
//            isVisible = false;
//            onInvisible();
//        }
//    }
     
     
//    /**
//     * 可见
//     */
//    protected void onVisible() {
//        lazyLoad();     
//    }
     
     
//    /**
//     * 不可见
//     */
//    protected void onInvisible() {
//         
//    }
     
     
//    /** 
//     * 延迟加载
//     * 子类必须重写此方法
//     */
//    protected abstract void lazyLoad();
}
