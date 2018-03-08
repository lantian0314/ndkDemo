package orm.ndkdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import orm.aidl.demo.IMyAidlInterface;
import orm.ndkdemo.Fragment.FragmentMainActivity;
import orm.ndkdemo.gson.GsonDemo;
import orm.ndkdemo.view.GridPwdView;
import orm.ndkdemo.view.MFilterMenu;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txt_jni)
    TextView txt_test = null;
    @BindView(R.id.txt_sum)
    TextView txt_sum = null;
    @BindView(R.id.txt_aidl)
    TextView txt_aidl = null;
    private IMyAidlInterface myAidlInterface = null;
    @BindView(R.id.btn_fragment)
    Button btn_fragment;
    @BindView(R.id.btn_pwd)
    Button btn_pwd;
    @BindView(R.id.btn_filterMenu)
    Button btn_filtermenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        String name = JniTest.getCString();
        int sum = JniTest.calAAndB(3, 5);
        txt_test.setText(name);
        txt_sum.setText("3+5和是: " + sum);
        bindAidlServer();
        GsonDemo gsonDemo = new GsonDemo();
        gsonDemo.beanToJson();
        gsonDemo.MapToJson();
        gsonDemo.JsonToBean();
        gsonDemo.JsonPrimitive();
        gsonDemo.creatJsonObject();
        gsonDemo.JsonExpose();
        gsonDemo.sinceVersion();
    }

    @OnClick({R.id.btn_fragment, R.id.btn_pwd,R.id.btn_filterMenu})
    public void viewClick(Button button) {
        switch (button.getId()) {
            case R.id.btn_fragment:
                Intent intent = new Intent(MainActivity.this, FragmentMainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_pwd:
                Intent pwdnItent = new Intent(MainActivity.this, GridPwdView.class);
                startActivity(pwdnItent);
                break;
            case R.id.btn_filterMenu:
                Intent menuItent = new Intent(MainActivity.this, MFilterMenu.class);
                startActivity(menuItent);
                break;
        }
    }

    private void bindAidlServer() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("orm.aidlserver", "orm.aidlserver.AidlServer"));
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MainActivity.this, "aidl server connect", Toast.LENGTH_SHORT).show();
            myAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            try {
                int aidl_sum = myAidlInterface.Sum(3, 3);
                txt_aidl.setText("3+3AIDL求和是：" + aidl_sum);
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "崩溃", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MainActivity.this, "aidl server disconnect", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
