package orm.ndkdemo.Fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import orm.ndkdemo.R;

/**
 * Created by xingyatong on 2018/3/6.
 */
public class FragmentMainActivity extends AppCompatActivity {

    //消息
    @BindView(R.id.txt_message)
    TextView txt_message;
    //联系人
    @BindView(R.id.txt_contact)
    TextView txt_contact;
    //动态
    @BindView(R.id.txt_dynamic)
    TextView txt_dynamic;
    //实现Tab滑动效果
    @BindView(R.id.vPager)
    ViewPager mViewPager;

    //动画图片
    @BindView(R.id.cursor)
    ImageView cursor;

    //动画图片偏移量
    private int offset = 0;
    private int position_one;
    private int position_two;

    //当前页卡编号
    private int currIndex = 0;

    //存放Fragment
    private ArrayList<Fragment> fragmentArrayList;

    //管理Fragment
    private FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        ButterKnife.bind(this);

        //初始化ImageView
        InitImageView();
        //初始化Fragment
        InitFragment();
        //初始化ViewPager
        InitViewPager();
    }

    /**
     * 初始化页卡内容区
     */
    private void InitViewPager() {
        mViewPager.setAdapter(new MFragmentPagerAdapter(fragmentManager, fragmentArrayList));

        //让ViewPager缓存2个页面
        mViewPager.setOffscreenPageLimit(2);

        //设置默认打开第一页
        mViewPager.setCurrentItem(0);

        //将顶部文字恢复默认值
        resetTextViewTextColor();
        txt_message.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));

        //设置viewpager页面滑动监听事件
        mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * 初始化动画
     */
    private void InitImageView() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        // 获取分辨率宽度
        int screenW = dm.widthPixels;
        int imageWidth = (screenW / 3);
        //设置动画图片宽度
        setImageWidth(cursor, imageWidth);

        offset = 0;
        //动画图片偏移量赋值
        position_one = (int) (screenW / 3.0);
        position_two = position_one * 2;
    }

    /**
     * 设置动画图片宽度
     *
     * @param mWidth
     */
    private void setImageWidth(ImageView imageView, int mWidth) {
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.width = mWidth;
        imageView.setLayoutParams(params);
    }

    /**
     * 初始化Fragment，并添加到ArrayList中
     */
    private void InitFragment() {
        fragmentArrayList = new ArrayList<Fragment>();
        fragmentArrayList.add(new MessageFragment());
        fragmentArrayList.add(new ConstantFragment());
        fragmentArrayList.add(new DynamicFragment());
        fragmentManager = getSupportFragmentManager();
    }

    /**
     * 页卡切换监听
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            Animation animation = null;
            Toast.makeText(getApplicationContext(), "" + position + " " + currIndex, Toast.LENGTH_SHORT).show();
            switch (position) {
                //当前为页卡1
                case 0:
                    //从页卡2跳转转到页卡1
                    if (currIndex == 1) {
                        animation = new TranslateAnimation(position_one, 0, 0, 0);
                    } else if (currIndex == 2) {//从页卡3跳转转到页卡1
                        animation = new TranslateAnimation(position_two, 0, 0, 0);
                    }
                    resetTextViewTextColor();
                    txt_message.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                    break;

                //当前为页卡2
                case 1:
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_one, 0, 0);
                    } else if (currIndex == 2) { //从页卡3跳转转到页卡2
                        animation = new TranslateAnimation(position_two, position_one, 0, 0);
                    }
                    resetTextViewTextColor();
                    txt_contact.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                    break;

                //当前为页卡3
                case 2:
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_two, 0, 0);
                    } else if (currIndex == 1) {//从页卡2跳转转到页卡3
                        animation = new TranslateAnimation(position_one, position_two, 0, 0);
                    }
                    resetTextViewTextColor();
                    txt_dynamic.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                    break;
            }
            currIndex = position;
            animation.setFillAfter(true);// true:图片停在动画结束位置
            animation.setDuration(300);
            cursor.startAnimation(animation);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    ;


    /**
     * 将顶部文字恢复默认值
     */
    private void resetTextViewTextColor() {
        txt_message.setTextColor(getResources().getColor(R.color.main_top_tab_color));
        txt_contact.setTextColor(getResources().getColor(R.color.main_top_tab_color));
        txt_dynamic.setTextColor(getResources().getColor(R.color.main_top_tab_color));
    }

    @Override
    protected void onResume() {
        /**
         * 设置为竖屏
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }

    @OnClick({R.id.txt_message, R.id.txt_contact, R.id.txt_dynamic})
    public void txtClick(TextView txtView) {
        switch (txtView.getId()) {
            case R.id.txt_message:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.txt_contact:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.txt_dynamic:
                mViewPager.setCurrentItem(2);
                break;
        }
    }
}
