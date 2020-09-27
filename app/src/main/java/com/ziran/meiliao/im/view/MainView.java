package com.ziran.meiliao.im.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ziran.meiliao.R;

/**
 * Created by ${chenyn} on 2017/2/20.
 */

public class MainView extends RelativeLayout {

    private Button[] mBtnList;
    private int[] mBtnListID;
    private ScrollControlViewPager mViewContainer;
    private TextView mAllContactNumber;
    private ImageView ivfb;

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initModule() {
        mBtnListID = new int[] {
                R.id.actionbar_contact_btn,   R.id.actionbar_community_btn,
                R.id.actionbar_msg_btn, R.id.actionbar_me_btn};
        mBtnList = new Button[mBtnListID.length];
        for (int i = 0; i < mBtnListID.length; i++) {
            mBtnList[i] = (Button) findViewById(mBtnListID[i]);
        }
        ivfb = (ImageView) findViewById(R.id.iv_fb);
        mViewContainer = (ScrollControlViewPager) findViewById(R.id.viewpager);
        mViewContainer.setOffscreenPageLimit(2);
        mBtnList[0].setTextColor(getResources().getColor(R.color.actionbar_pres_color));
        mBtnList[0].setSelected(true);
    }

    public void setOnClickListener(OnClickListener onclickListener) {
        for (int i = 0; i < mBtnListID.length; i++) {
            mBtnList[i].setOnClickListener(onclickListener);
        }
        ivfb.setOnClickListener(onclickListener);
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        mViewContainer.addOnPageChangeListener(onPageChangeListener);
    }

    public void setViewPagerAdapter(FragmentPagerAdapter adapter) {
        mViewContainer.setAdapter(adapter);
    }

    public void setCurrentItem(int index, boolean scroll) {
        mViewContainer.setCurrentItem(index, scroll);
    }

    public void setButtonColor(int index) {
        for (int i = 0; i < mBtnListID.length; i++) {
            if (index == i) {
                mBtnList[i].setSelected(true);
                mBtnList[i].setTextColor(getResources().getColor(R.color.actionbar_pres_color));
            } else {
                mBtnList[i].setSelected(false);
                mBtnList[i].setTextColor(getResources().getColor(R.color.action_bar_txt_color));
            }
        }
    }


}
