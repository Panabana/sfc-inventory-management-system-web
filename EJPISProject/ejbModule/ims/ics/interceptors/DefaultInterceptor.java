package ims.ics.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class DefaultInterceptor {
	
//	@AroundInvoke
//	public Object defaultLogger(InvocationContext iCtx) throws Exception {
//		System.out.println("*** DefaultInterceptor: " + iCtx.getMethod().getName());
//		System.out.println("Default - Target class: " + iCtx.getTarget().getClass());
//		System.out.println("Default - Parameters: " + iCtx.getParameters().length);
//		
//		for (int i = 0; i < iCtx.getParameters().length; i++) {
//			System.out.println("Parameter: " + iCtx.getParameters()[i].toString());
//		}
//		
//		System.out.println("*********************************");
//		return iCtx.proceed();
//	}
}
