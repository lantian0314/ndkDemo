package orm.ndkdemo;

/**
 * Created by xingyatong on 2018/3/2.
 */
public class JniTest {
    /**
     * 加载so库或jni库，在使用到该库之前加载就行，不一定非要写在这个类内
     * 系统自己会判断扩展名是dll还是so,这里加载libJNI_ANDROID_TEST.so
     */
    static {
        System.loadLibrary("JNI_ANDROID_TEST");
    }

    /**
     * 将用C++代码实现，在android代码中调用的方法：获取当前app的包名
     *
     * @param o
     * @return
     */
    public static native String getCString();

    /**
     * 计算两数之和
     * @param a
     * @param b
     * @return
     */
    public static native int calAAndB(int a, int b);
}
