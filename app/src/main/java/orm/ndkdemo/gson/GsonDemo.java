package orm.ndkdemo.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xingyatong on 2018/3/5.
 */
public class GsonDemo {

    private Gson gson = null;
    private  String JsonString = "{\n" +
            "    \"age\": 26,\n" +
            "    \"name\": \"xiao ming\"\n" +
            "}";

    public GsonDemo() {
        gson = new Gson();
        gson = new GsonBuilder().setLenient()// json宽松
                .enableComplexMapKeySerialization()//支持Map的key为复杂对象的形式
                .serializeNulls()//智能null
                .setPrettyPrinting()// 调教格式
                .disableHtmlEscaping() //默认是GSON把HTML 转义的
                .create();
    }

    /**
     * 数据对象转为json
     *
     * @return
     */
    public String beanToJson() {
        Student student = new Student();
        student.setName("xiao ming");
        student.setAge(26);
        String string = gson.toJson(student);
        return string;
    }

    public String MapToJson() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "xiao ming");
        map.put("age", "26");
        String string = gson.toJson(map);
        return string;
    }

    public Student JsonToBean() {
        Student student = gson.fromJson(JsonString, Student.class);
        return student;
    }

    /**
     *  JsonPrimitive非常有意思,我们知道如果json转换成字符串 可能包含引号的转义，但是通过JsonPrimative我们可以获得为转义的字符串
     */
    public void JsonPrimitive(){
        JsonPrimitive jsonPrimitive=new JsonPrimitive(JsonString);
        String jsonToString=jsonPrimitive.toString();
        String asString=jsonPrimitive.getAsString();
    }

    public void creatJsonObject(){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("name","xiao ming");
        jsonObject.addProperty("age",26);
    }

    /**
     * 过滤注解,默认既可以序列化又可以反序列化
     */
    public void JsonExpose(){
        Gson mGson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()//默认不可序列化
                .create();
        //反序列化
        User user=mGson.fromJson(JsonString, User.class);

        User user1=new User();
        user1.setName("xiao ming");
        user1.setAge(26);
        //序列化
        String string=mGson.toJson(user1);
    }

    /**
     *  @Since(float v)注解  版本控制 结合GsonBuilder.setVersion(n)使用 当n>=v时 才会序列化解析
     *  @Util(float v)注解 版本控制 当gson的setVersion(n) n<v 才解析
     */
    public void sinceVersion(){
        Gson mGson = new GsonBuilder().setVersion(1).create();
        User user=mGson.fromJson(JsonString, User.class);

        Gson mGson2 = new GsonBuilder().setVersion(2).create();
        User user2=mGson2.fromJson(JsonString, User.class);
    }
}

