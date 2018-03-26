import bigbang.i.IBusinessService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import provider.domain.Business;
import runner.App;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

/**
 * Created by Matt Xu on 2018/3/26
 */

@RunWith(SpringRunner.class)
//加上参数webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT 可以模拟网络请求进行测试
@SpringBootTest(classes = App.class)
@EnableAutoConfiguration
public class BusinessHandlerTest {
    @Resource
    private IBusinessService iBusinessService;

    @Test
    public void getBusinessMaxId() throws Exception {
        Assert.assertEquals(iBusinessService.getBusinessMaxId(), 1);
    }

    @Test
    public void createBusiness() throws Exception {
        int currentMaxId = iBusinessService.getBusinessMaxId();
        Business newOne = new Business();
        newOne.setShop_name("新测试用户");
        newOne.setCreate_time(new Date(System.currentTimeMillis()));
        newOne.setTelephone("13049474755-1");
        newOne.setAddress("haven");
        newOne.setCity_code(0001);
        newOne.setPassword("11sdddd");
        newOne.setType(6);
        newOne.setLast_update_time(System.currentTimeMillis());
        iBusinessService.create(newOne);
        Assert.assertEquals(iBusinessService.getBusinessMaxId(), currentMaxId + 1);
    }
}
