package ims.ics.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class EmployeeClassLogger {

	@AroundInvoke
	public Object intercept(InvocationContext iCtx) throws Exception {
		System.out.println("*** ClassInterceptor: " + iCtx.getMethod().getName());
		return iCtx.proceed();
	}
}
