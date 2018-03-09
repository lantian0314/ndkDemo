package orm.ndkdemo.RecyclerView;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import orm.ndkdemo.R;
import orm.ndkdemo.gson.Student;

/**
 * Created by xingyatong on 2018/3/9.
 */
public class RecyclerAdapter extends BaseQuickAdapter<ApplicationInfo, BaseViewHolder> {
    private Context mContext;

    public RecyclerAdapter(Context context, int layoutResId, List data) {
        super(layoutResId, data);
        this.mContext=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ApplicationInfo item) {
        helper.setImageDrawable(R.id.iv_icon, item.loadIcon(mContext.getPackageManager()));
        helper.setText(R.id.tv_name, item.loadLabel(mContext.getPackageManager()));
    }
}
