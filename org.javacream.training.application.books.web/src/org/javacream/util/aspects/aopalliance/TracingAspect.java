package org.javacream.util.aspects.aopalliance;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TracingAspect implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		Method invokedMethod = methodInvocation.getMethod();
		String methodName = invokedMethod.getName();
		try {
			System.out.println("entering method " + methodName);
			Object result = methodInvocation.proceed();
			System.out.println("returning from method " + methodName);
			return result;
		}
		catch(Throwable t) {
			System.out.println("throwing from method " + methodName);
			throw t;
		}
	}
}
