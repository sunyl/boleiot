import com.boleiot.BoleiotApplication;
import com.boleiot.service.DeviceService;
import com.boleiot.utils.UidUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BoleiotApplication.class)
@WebAppConfiguration
public class MainTest {

    @Autowired
    private DeviceService deviceService;

    @Test
    public void testUUID() {
        System.out.println("uuid = " + UidUtil.getUUID());
    }

    @Test
    public void testActivate() {
        String no = "aabfaeaf5b3743d89f8468e1fa4963bd";
        String password = "123456";
        //deviceService.activate(no, password);
    }
}
