package orm.ndkdemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.jungly.gridpasswordview.GridPasswordView;
import com.jungly.gridpasswordview.PasswordType;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import orm.ndkdemo.R;

/**
 * Created by xingyatong on 2018/3/8.
 */
public class GridPwdView extends AppCompatActivity {
    @BindView(R.id.pswView)
    GridPasswordView gridPasswordView;
    @BindView(R.id.btn_getpwd)
    Button btn_getpwd;
    @BindView(R.id.btn_clearpwd)
    Button btn_clearpwd;
    @BindView(R.id.btn_txtype_pwd)
    Button btn_txtype_pwd;
    @BindView(R.id.btn_numtype_pwd)
    Button btn_numtype_pwd;
    @BindView(R.id.btn_pwdvisible)
    Button btn_pwdvisible;
    @BindView(R.id.btn_pwdinvisible)
    Button btn_pwdinvisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridpwd);
        ButterKnife.bind(this);
        //gridPasswordView.setPassword("");//设置密码
        //gridPasswordView.setPasswordType(PasswordType.TEXT);
        //gridPasswordView.setPasswordVisibility(true);
        //gridPasswordView.setPasswordType(PasswordType.TEXTVISIBLE);
    }

    @OnClick({R.id.btn_getpwd, R.id.btn_clearpwd, R.id.btn_txtype_pwd, R.id.btn_pwdvisible, R.id.btn_pwdinvisible, R.id.btn_numtype_pwd})
    public void pwdClick(Button button) {
        switch (button.getId()) {
            case R.id.btn_getpwd:
                String pwd = gridPasswordView.getPassWord();
                Toast.makeText(getApplicationContext(), pwd, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_clearpwd:
                gridPasswordView.clearPassword();
                break;
            case R.id.btn_txtype_pwd:
                gridPasswordView.setPasswordType(PasswordType.TEXT);
                break;
            case R.id.btn_numtype_pwd:
                gridPasswordView.setPasswordType(PasswordType.NUMBER);
                break;
            case R.id.btn_pwdvisible:
                gridPasswordView.setPasswordVisibility(true);
                break;
            case R.id.btn_pwdinvisible:
                gridPasswordView.setPasswordVisibility(false);
                break;
        }
    }
}
