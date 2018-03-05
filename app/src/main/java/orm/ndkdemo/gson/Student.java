package orm.ndkdemo.gson;

import com.google.gson.annotations.SerializedName;

import java.lang.ref.SoftReference;

/**
 * Created by xingyatong on 2018/3/5.
 */
public class Student {

    private String name;
    private int age;
    @SerializedName("my_parent_name")//重命名注解（Json字段名）
    private String mpname;//本地字段名

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
