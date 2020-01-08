package cx.sap.panda.unitservice.epstarter;

import cx.sap.panda.unitservice.aop.annotations.UnitAdminValidator;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.Set;


public class RegisterExtensionPointConfiguration implements ApplicationContextAware
{

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException
	{
//		Set<Class<?>> classes = new Reflections("", new TypeAnnotationsScanner()).getTypesAnnotatedWith(RegisterEP.class, true);
//		classes.forEach(e -> {
//			System.out.println("************************** " + e.getName());
//		});
//		if (!CollectionUtils.isEmpty(classes))
//		{
//
//			Set<Method> methodsAnnotatedWith = new Reflections(classes.iterator().next().getAnnotation(RegisterEP.class).packagePrefix(), new MethodAnnotationsScanner()).getMethodsAnnotatedWith(UnitAdminValidator.class);
//		}
		System.out.println("########## This method will get all methods which use **UnitAdminValidator** Annotation ##########");
		System.out.println("########## These points can be provided by config(DS must provide), or this method scan them");
		Set<Method> methodsAnnotatedWith = new Reflections("", new MethodAnnotationsScanner()).getMethodsAnnotatedWith(UnitAdminValidator.class);
		methodsAnnotatedWith.forEach(e -> {

			System.out.println("########## " + e.getName()+" ##########");
		});
		System.out.println("########## Call API to Save these points to EP ##########");
	}
}
