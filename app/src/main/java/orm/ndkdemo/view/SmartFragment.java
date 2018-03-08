package orm.ndkdemo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import orm.ndkdemo.R;

/**
 * Created by xingyatong on 2018/3/8.
 */
public class SmartFragment extends Fragment {
    TextView txt_itemtitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.smart_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = FragmentPagerItem.getPosition(getArguments());
        txt_itemtitle = (TextView) view.findViewById(R.id.txt_itemtitle);
        txt_itemtitle.setText(String.valueOf(position));
    }
}
