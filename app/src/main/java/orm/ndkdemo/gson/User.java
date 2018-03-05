package orm.ndkdemo.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

/**
 * Created by xingyatong on 2018/3/5.
 */
public class User {
    @Since(2)
    private  String name;
    @Expose(deserialize = true,serialize = true)
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
