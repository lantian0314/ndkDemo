package orm.ndkdemo.RecyclerView;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import orm.ndkdemo.R;

/**
 * Created by xingyatong on 2018/3/9.
 */
public class RecyclerActivity extends AppCompatActivity {
    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    private List<ApplicationInfo> mAppList;

    private RecyclerAdapter recyclerAdapter;

    private int mCurrentCounter;

    private boolean isErr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mrecycler);
        ButterKnife.bind(this);
        mAppList = getPackageManager().getInstalledApplications(0);
        recyclerAdapter = new RecyclerAdapter(getApplicationContext(), R.layout.item_list_app, mAppList);
        //recyclerAdapter.openLoadAnimation();//开启动画(默认为渐显效果)
        recyclerAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //动画默认只执行一次,如果想重复执行可设置
        recyclerAdapter.isFirstOnly(false);
        recyclerAdapter.setUpFetchEnable(true);
        mRecyclerView.setAdapter(recyclerAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        setClickListener();
    }

    private void setClickListener() {
        recyclerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(RecyclerActivity.this, "onItemClick" + position, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mCurrentCounter >= mAppList.size()) {
                            //数据全部加载完毕
                            recyclerAdapter.loadMoreEnd();
                        } else {
                            if (isErr) {
                                //成功获取更多数据
                                ApplicationInfo info=mAppList.get(0);
                                recyclerAdapter.addData(info);
                                mCurrentCounter = recyclerAdapter.getData().size();
                                recyclerAdapter.loadMoreComplete();
                            } else {
                                //获取更多数据失败
                                isErr = true;
                                Toast.makeText(RecyclerActivity.this, "网络错误", Toast.LENGTH_LONG).show();
                                recyclerAdapter.loadMoreFail();

                            }
                        }
                    }
                },500);
            }
        },mRecyclerView);
        /**
         * 下拉加载，查找历史记录
         */
//        recyclerAdapter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
//            @Override
//            public void onUpFetch() {
//                startUpFetch();
//            }
//        });
    }

    private void startUpFetch() {
        recyclerAdapter.setUpFetching(true);
        /**
         * get data from internet.
         */
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                ApplicationInfo info=mAppList.get(0);
                recyclerAdapter.addData(0, info);

                recyclerAdapter.setUpFetching(false);

            }
        }, 5000);
    }

}
