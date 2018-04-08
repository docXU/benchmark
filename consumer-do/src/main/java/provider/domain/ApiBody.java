package provider.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Matt Xu on 2018/3/30
 */

//TODO：全局异常处理
public class ApiBody implements Serializable {
    public static String GET = "GET";
    public static String PUT = "PUT";
    public static String POST = "POST";
    public static String DELETE = "DELETE";

    private String method;
    private String url;
    private Map<String, String> params;

    public ApiBody(String method, String url, Map<String, String> params) {
        this.method = method;
        this.url = url;
        this.params = params;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{method: ").append(method).append(", url: '").append(url).append("', params: [");
        if (params != null) {
            sb.append(params);
        }
        sb.append("]}");
        return sb.toString();
    }
}
