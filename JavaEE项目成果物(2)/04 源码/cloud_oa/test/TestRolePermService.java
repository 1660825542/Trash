import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.system.pojo.Permission;
import com.icss.oa.system.service.PermissionService;


public class TestRolePermService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("application*.xml");
		PermissionService service = (PermissionService) context.getBean("permissionService");
		Map<String, Object> map = service.selectNotToRole(1,2);
		List<Permission> list = (List<Permission>) map.get("notHaveList");
		for (Permission permission : list) {
			System.out.println(permission.getPermId());
		}
	}

}
