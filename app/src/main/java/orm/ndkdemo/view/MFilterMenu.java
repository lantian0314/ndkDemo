package orm.ndkdemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.linroid.filtermenu.library.FilterMenu;
import com.linroid.filtermenu.library.FilterMenuLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import orm.ndkdemo.R;

/**
 * Created by xingyatong on 2018/3/8.
 */
public class MFilterMenu extends AppCompatActivity {
    @BindView(R.id.filter_menu)
    FilterMenuLayout filterMenuLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtermenu);
        ButterKnife.bind(this);
        FilterMenu filterMenu = new FilterMenu.Builder(this).addItem(R.drawable.icon1).addItem(R.drawable.icon1).addItem(R.drawable.icon1).attach(filterMenuLayout).withListener(new FilterMenu.OnMenuChangeListener() {
            @Override
            public void onMenuItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        Toast.makeText(getApplicationContext(),"点击position:"+position,Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(),"点击position:"+position,Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"点击position:"+position,Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onMenuExpand() {

            }

            @Override
            public void onMenuCollapse() {

            }
        }).build();

//        <!--circle radius size when menu expanded-->
//                custom:fm_expandedRadius
//                <!--circle radius size when menu collapsed-->
//                custom:fm_collapsedRadius
//                <!--set the position of circle, the menu will auto align.
//        You should only set two directions at most.-->
//                custom:fm_center[Left|Top|Right|Bottom]
//                <!-- If true, centers the circle horizontally.-->
//                custom:fm_centerHorizontal
//                <!-- If true, centers the circle vertically.-->
//                custom:fm_centerVertical
//                <!--primary color-->
//                custom:fm_primaryColor
//                <!--color of inner circle when menu expanded-->
//                custom:fm_primaryDarkColor
    }
}
