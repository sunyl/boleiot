import com.boleiot.BoleiotApplication;
import com.boleiot.model.Menu;
import com.boleiot.service.DeviceService;
import com.boleiot.service.MenuService;
import com.boleiot.service.UserService;
import com.boleiot.utils.UidUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BoleiotApplication.class)
@WebAppConfiguration
public class MainTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

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

    @Test
    public void testShiroPassword() {
        String cryptedPwd = new Md5Hash("123").toString();
        System.out.println("cryptedPwd = " + cryptedPwd);

    }

    @Test
    public void testShrio() {
        Set<String> setRoles = userService.findRoles("admin");
        Iterator<String> it = setRoles.iterator();
        while (it.hasNext()) {
            String str = it.next();
            System.out.println("role:" + str);
        }
    }

    @Test
    public void testMenu() {
        List<Menu> menus = menuService.getMenuList("");
        List<Menu> treeMenu = Menu.createTreeMenus(menus);
        System.out.println("meuns size  = " + menus.size());
    }
}
