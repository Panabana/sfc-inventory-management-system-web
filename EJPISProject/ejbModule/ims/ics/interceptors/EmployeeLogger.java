package ims.ics.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
public class EmployeeLogger {

	@AroundInvoke
	public Object logMethod(InvocationContext iCtx) throws Exception {
		System.out.println("**** Entering method: " + iCtx.getMethod().getName());
		System.out.println("Target class: " + iCtx.getTarget().getClass());
		System.out.println("Parameters: " + iCtx.getParameters().length);
		
		for (int i = 0; i < iCtx.getParameters().length; i++) {
			System.out.println("Parameter: " + iCtx.getParameters()[i].toString());
		}
		
		System.out.println("****************************************************");
		return iCtx.proceed();
	}

}
