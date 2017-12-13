package org.javacream.util.aspects.aspectj;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;


@Aspect
public class TracingAspect{

	/*
	 * Jokerzeichen in den Aspect-Ausdrücken
	 * .. in runden Klammern: beliebige Parameterliste
	 * * beliebige Zeichenkette ohne Punkte
	 * .. ausserhalb einer Parameterklammer ist eine beliebige Zeichenkette mit beginnendem und endendem Punkt
	 * 
	 * select method form CLASSPATH where returntype like '%' and parameters like '%' and classname like 'org.javacream%'
	 */
	@Around("execution(* org.javacream..impl.*.*(..))")
	public Object trace(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
		Method invokedMethod = methodSignature.getMethod();
		String methodName = invokedMethod.getName();
		try {
			System.out.println("entering method " + methodName);
			Object result = proceedingJoinPoint.proceed();
			System.out.println("returning from method " + methodName);
			return result;
		}
		catch(Throwable t) {
			System.out.println("throwing from method " + methodName);
			throw t;
		}
	}
}
