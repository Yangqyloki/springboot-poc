package cx.sap.panda.unitservice.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExtensionPointTemplateAspect
{
	@Around("@annotation(cx.sap.panda.unitservice.aop.annotations.ExtensionPointTemplate)")
	public Object extensionPointTemplateAround(ProceedingJoinPoint joinPoint) throws Throwable
	{
		System.out.println("Before ExtensionPointTemplate");
		Object obj = joinPoint.proceed();
		System.out.println("After ExtensionPointTemplate");
		return obj;
	}
}
