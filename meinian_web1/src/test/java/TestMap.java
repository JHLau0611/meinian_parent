import java.util.HashMap;
import java.util.Map;

/**
 * @author JHLau
 * @create 2021-09-01 6:53
 */
public class TestMap {
    public static void main(String[] args) {

        Map map = new HashMap();
        map.put("name","张三");
        map.put("age",18);

        System.out.println(map.get("name"));
        System.out.println(map.get("age"));


    }
}
