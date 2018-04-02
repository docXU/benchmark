package runner.controller;

import bigbang.e.ErrorBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import provider.domain.ApiBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Matt Xu on 2018/3/30
 */

@RestController
public class ApiFormatShow {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayFormat() {
        List<ApiBody> list = new ArrayList<>();

        list.add(new ApiBody(ApiBody.GET, "/businesses/{bid}", null));
        list.add(new ApiBody(ApiBody.POST, "/businesses", new HashMap<>()));
        list.add(new ApiBody(ApiBody.PUT, "/businesses/{bid}", null));

        return new ErrorBody().setCode(101).setMessage("接口列表未完全定义").setTime(new Date()).toString();
    }
}
