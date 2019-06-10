import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.carapply.service.CarApplyProcessService;
import com.icss.oa.process.service.LeaveProcessService;
import com.icss.oa.process.service.ReimProcessService;



public class TestPublicProcess {
	ApplicationContext context = new ClassPathXmlApplicationContext("application*.xml");

	LeaveProcessService service = (LeaveProcessService) context.getBean("leaveProcessService");
	ReimProcessService reimService = (ReimProcessService) context.getBean("reimProcessService");
	CarApplyProcessService carService = (CarApplyProcessService) context.getBean("carApplyProcessService");

	@Test
	public void deploy(){
		service.deployProcess();
	}
	
	@Test
	public void deployReim(){
		reimService.deployProcess();
	}
	
	@Test
	public void deployCar(){
		carService.deployProcess();
	}
}
