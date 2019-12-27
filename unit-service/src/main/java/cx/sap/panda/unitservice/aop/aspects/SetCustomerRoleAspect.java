package cx.sap.panda.unitservice.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SetCustomerRoleAspect
{
	@Around("execution(* cx.sap.panda.unitservice.clients.UnitClient.createCustomerForUnit(..))")
	public void createCustomAround(ProceedingJoinPoint joinPoint) throws Throwable
	{
		System.out.println("Before create custom");
		joinPoint.proceed();
		System.out.println("After create custom");
	}
}
