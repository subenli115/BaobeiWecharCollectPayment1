package com.ziran.meiliao.im.activity.fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baserx.RxManager;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.irecyclerview.IRecyclerView;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.im.adapter.UserSpaceAdapter;
import com.ziran.meiliao.im.utils.SharePreferenceManager;
import com.ziran.meiliao.im.utils.ThreadUtil;
import com.ziran.meiliao.ui.bean.GiftsReceivedBean;
import com.ziran.meiliao.ui.bean.MeSpaceBean;
import com.ziran.meiliao.ui.bean.UpdateUserNewHeadBean;
import com.ziran.meiliao.ui.bean.UserAccountBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.main.util.NewMainHomeHeadViewUtil;
import com.ziran.meiliao.ui.me.activity.MyActivityActivity;
import com.ziran.meiliao.ui.settings.activity.RechargeActivity;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.Utils;
import com.ziran.meiliao.widget.GlideCircleTransform;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.ziran.meiliao.widget.pupop.UpdatePopupWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;
import rx.functions.Action1;

import static android.app.Activity.RESULT_OK;


/**
 * Created by ${chenyn} on 2017/2/20.
 *
 */

public class MeFragment extends BaseFragment implements View.OnClickListener {
    private static final int UPDATE_BG =1 ;
    private static final int UPDATE_HEAD =2 ;
    private static String cameraScalePath;
    private FragmentActivity mContext;
    private View mRootView;
    private IRecyclerView iRecyclerView;
    public final int REQUEST_CODE_B = 1;
    public final int REQUEST_CODE_A = 2;
    public static final int RequestCrop = 3;
    private NewMainHomeHeadViewUtil newMainHomeHeadViewUtil;
    private ImageView ivMore, ivHead;
    private Toolbar toolbar;
    private boolean mHasShowDownloadActive = false;
    private NestedScrollView nsv;
    private String path;
    private ImageView ivMoney;
    private View contentView;
    private AutoRelativeLayout clBg;
    private TextView tvRedGold;
    private TextView tvGold;
    private UserBean.DataBean dataBean;
    private ImageView ivBg;
    private UserSpaceAdapter meMainAdapter;
    private AutoRelativeLayout arl_content;
    private Toolbar toolbar1;
    private ImageView ivMore1;
    private List<GiftsReceivedBean.DataBean.RecordsBean> giftDatas;
    private RxManager mRxManager;
    private AutoLinearLayout all;
    private String gold;
    private UpdatePopupWindow pop;
    private int type;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getActivity();
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        mRootView = layoutInflater.inflate(R.layout.fragment_main_me1,
                (ViewGroup) getActivity().findViewById(R.id.main_view), false);
        initView();
        mRxManager = new RxManager();
        mRxManager.on(AppConstant.RXTag.UPDATE_USER, new Action1<String>() {
            @Override
            public void call(String balance) {
                    if(balance.length()>0){
                        tvGold.setText(balance + "");
                    }
                    getSpaceData();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup p = (ViewGroup) mRootView.getParent();
        if (p != null) {
            p.removeAllViewsInLayout();
        }
        return mRootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRxManager.clear();
    }



    public void getGiftList() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("receiveUserId", MyAPP.getUserId());
        OkHttpClientManager.getAsyncMore(ApiKey.ADMIN_GIFTRECORD_PAGE, defMap, new
                NewRequestCallBack<GiftsReceivedBean>(GiftsReceivedBean.class) {
                    @Override
                    public void onSuccess(GiftsReceivedBean result) {
                        giftDatas = result.getData().getRecords();
                        getSpaceData();
                    }

                    @Override
                    public void onError(String msg, int code) {

                    }
                });
    }
    private void showPopWindow() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        contentView = LayoutInflater.from(getContext()).inflate(R.layout.pop_get_money_im, null);
        contentView.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.MATCH_PARENT, true);

        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(clBg, Gravity.CENTER, 0, 0);
        TextView qd = contentView.findViewById(R.id.tv_qd);
//        setBackgroundAlpha(0.5f);
        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();

            }
        });
    }
    /**
     * 启动裁剪
     * @param activity 上下文
     * @param sourceFilePath 需要裁剪图片的绝对路径
     * @param requestCode 比如：UCrop.REQUEST_CROP
     * @return
     */
    public static String startUCrop(Activity activity, String sourceFilePath,
                                    int requestCode) {
        Uri sourceUri = Uri.fromFile(new File(sourceFilePath));
        File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        File outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
        //裁剪后图片的绝对路径
        cameraScalePath = outFile.getAbsolutePath();
        Uri destinationUri = Uri.fromFile(outFile);
        //初始化，第一个参数：需要裁剪的图片；第二个参数：裁剪后图片
        UCrop uCrop = UCrop.of(sourceUri, destinationUri);
        //初始化UCrop配置
        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //是否隐藏底部容器，默认显示
        options.setHideBottomControls(true);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(activity, com.yuyh.library.imgsel.R.color.black));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(activity, com.yuyh.library.imgsel.R.color.black));
        //是否能调整裁剪框
        options.setFreeStyleCropEnabled(true);
        //UCrop配置
        uCrop.withOptions(options);
        //设置裁剪图片的宽高比，比如16：9
        uCrop.withAspectRatio(1, 1);
        //uCrop.useSourceImageAspectRatio();
        //跳转裁剪页面
        uCrop.start(activity, requestCode);
        return cameraScalePath;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ImgSelConfig.RequestCode && data != null) {
            final ArrayList<String> imgPaths = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            startUCrop(getActivity(),imgPaths.get(0),RequestCrop);
        }else if(resultCode == RESULT_OK &&requestCode==RequestCrop){
            if(cameraScalePath.length()>0){
                updateUserHead();
            }
        }
        switch (requestCode) {
            case REQUEST_CODE_B:
                if (resultCode == RESULT_OK) {
                }
            case REQUEST_CODE_A:
                if (resultCode == RESULT_OK) {
                    getSpaceData();
                }
            default:
                break;
        }
    }

    private void updateUserHead() {
        upload();
    }

    private void imHeadUpload() {

        ThreadUtil.runInThread(new Runnable() {
            @Override
            public void run() {
                JMessageClient.updateUserAvatar(new File(cameraScalePath), new BasicCallback() {
                    @Override
                    public void gotResult(int responseCode, String responseMessage) {
                        if (responseCode == 0) {
                            SharePreferenceManager.setCachedAvatarPath(cameraScalePath);
                        }else {

                        }
                    }
                });
            }
        });
    }


    private void upload() {
        if(type==UPDATE_HEAD){
            imHeadUpload();
        }
        OkHttpClientManager.postContentAndFiles(ApiKey.ADMIN_FILE_UPLOAD, MapUtils.getDefMap(true), FileUtil.str2File(cameraScalePath), new
                NewRequestCallBack<UpdateUserNewHeadBean>(UpdateUserNewHeadBean.class) {

                    @Override
                    public void onSuccess(UpdateUserNewHeadBean result) {
                        eidtHead(result);
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                    }
                });
    }
    private void eidtHead(UpdateUserNewHeadBean result) {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("id",MyAPP.getUserId());
        if(type==UPDATE_HEAD){
            defMap.put("avatar",result.getData().getUrl());
        }else if(type==UPDATE_BG){
            defMap.put("homepageImages",result.getData().getUrl());
        }
        OkHttpClientManager.putAsyncAddHead(ApiKey.ADMIN_USER_UPDATE, defMap, new
                NewRequestCallBack<UserBean>(UserBean.class) {
                    @Override
                    public void onSuccess(UserBean result) {
                        MyAPP.setmUserBean(result.getData());
                        if(type==UPDATE_HEAD){
                            Glide.with(mContext).load(result.getData().getAvatar()).transform(new GlideCircleTransform(mContext)).into(ivHead);
                        }else if(type==UPDATE_BG){
                            Glide.with(mContext).load(result.getData().getHomepageImages()).into(ivBg);
                        }
                        if (TextUtils.isEmpty(MyAPP.getUserInfo().getHeadImg())) {
                            MyAPP.getUserInfo().setHeadImg(result.getData().getAvatar());
                        }
                    }
                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort("验证码错误");
                    }
                });
    }

    private void initView() {
        clBg = (AutoRelativeLayout) mRootView.findViewById(R.id.arl_bg);
        toolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
        toolbar1 = (Toolbar) mRootView.findViewById(R.id.toolbar1);
        nsv = (NestedScrollView) mRootView.findViewById(R.id.nsv);
        ivHead = (ImageView) mRootView.findViewById(R.id.iv_head);
        ivMore1 = (ImageView) mRootView.findViewById(R.id.iv_more1);
        ivMore = (ImageView) mRootView.findViewById(R.id.iv_more);
        all = (AutoLinearLayout) mRootView.findViewById(R.id.all);
        ivBg = (ImageView) mRootView.findViewById(R.id.iv_bg);
        tvRedGold = (TextView) mRootView.findViewById(R.id.tv_red_gold);
        tvGold = (TextView) mRootView.findViewById(R.id.tv_gold);
        arl_content = (AutoRelativeLayout) mRootView.findViewById(R.id.arl_content);
        ivMoney = (ImageView) mRootView.findViewById(R.id.iv_get_money);
        iRecyclerView = (IRecyclerView) mRootView.findViewById(R.id.recyclerView);
        Toolbar toolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
        iRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        iRecyclerView.setFocusableInTouchMode(false);
        iRecyclerView.setFocusable(false);
        iRecyclerView.setHasFixedSize(true);
        iRecyclerView.setNestedScrollingEnabled(false);
         pop = new UpdatePopupWindow(mContext, getActivity());
        initData();
        setAvatorChange();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUserMoney();
    }

    private void updateUserMoney() {
        OkHttpClientManager.getDataOneHead(ApiKey.ACCOUNT_ACCOUNT_INFO, MyAPP.getUserId(),MyAPP.getAccessToken(), new
                NewRequestCallBack<UserAccountBean>(UserAccountBean.class) {
                    @Override
                    public void onSuccess(UserAccountBean result) {
                        tvGold.setText(gold);
                        tvRedGold.setText( result.getData().getMoney() + "");
                    }

                    @Override
                    public void onError(String msg, int code) {

                    }
                });
    }

    private void initData() {
        dataBean = MyAPP.getmUserBean();
        if(dataBean!=null){
        ivMore.setOnClickListener(this);
        ivMore1.setOnClickListener(this);
        ivHead.setOnClickListener(this);
        ivMoney.setOnClickListener(this);
        all.setOnClickListener(this);
        ivBg.setOnClickListener(this);
        Glide.with(mContext).load(MyAPP.getmUserBean().getAvatar()).error(R.drawable.jmui_head_icon).into(ivHead);
        Glide.with(mContext).load(MyAPP.getmUserBean().getHomepageImages()).into(ivBg);
        }
        getGiftList();
    }




    /**
     * 渐变toolbar背景
     */
    /**
     * 渐变toolbar背景
     */
    private void setAvatorChange() {
        nsv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                float percent =scrollY * 1.0f/(arl_content.getY()-toolbar1.getHeight());
                Rect scrollRect = new Rect();
                nsv.getHitRect(scrollRect);
                //子控件在可视范围内（至少有一个像素在可视范围内）
                if (toolbar.getLocalVisibleRect(scrollRect)) {
                    toolbar1.setVisibility(View.GONE);
                } else {
                    toolbar1.setVisibility(View.VISIBLE);
                }
                if(percent>1){
                    percent=1;
                }
                int i = changeAlpha(Color.parseColor("#FAFAFA"),percent);
                toolbar1.setBackgroundColor(i);
            }
        });
    }


    /**
     * 根据百分比改变颜色透明度
     */
    public int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }


    @Override
    public void onClick(View view) {
        if (Utils.isFastDoubleClick()) {
            return;
        }else {
            switch (view.getId()) {
                case R.id.iv_bg:
                    pop.setTitle("更换主页封面",false);
                    PopupWindowUtil.show(mContext, pop);
                    type=UPDATE_BG;
                    break;
                case R.id.iv_more:
                case R.id.iv_more1:
                    MyActivityActivity.startAction(mContext);
                    break;
                case R.id.iv_head:
                    pop.setTitle("修改头像",false);
                    PopupWindowUtil.show(mContext, pop);
                    type=UPDATE_HEAD;
                    break;
                case R.id.iv_get_money:
                    if (isFastLoadAdDoubleClick()) {
                        return;
                    }
                    break;
                case R.id.all:
                    RechargeActivity.startAction(mContext, "");
                    break;
            }
        }
    }
    private static long lastClickTime;
    public static boolean isFastLoadAdDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if ( 0 < timeD && timeD < 4000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public void getSpaceData() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("userId",MyAPP.getUserId());
        OkHttpClientManager.getAsyncMore(ApiKey.ADMIN_SPACE_IMGPAGE,defMap, new
                NewRequestCallBack<MeSpaceBean>(MeSpaceBean.class) {
                    @Override
                    public void onSuccess(MeSpaceBean result) {
                            iRecyclerView.setAdapter(meMainAdapter);
                           newMainHomeHeadViewUtil = new NewMainHomeHeadViewUtil(iRecyclerView, MyAPP.getmUserBean(),true);
                    }

                    @Override
                    public void onError(String msg, int code) {

                    }
                });

    }



}
