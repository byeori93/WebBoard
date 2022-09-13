package com.board.study.aop;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggerAspect {
	@Around("execution(* com.board.study.domain..*Controller.*(..)) || execution(* com.board.study.domain..*Service.*(..)) || execution(* com.board.study.domain..*Mapper.*(..))")
	public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
		String name = joinPoint.getSignature().getDeclaringTypeName();
		String type = "";
		
		if (name.contains("Controller")) {
			type = "Controller ===>";
		} else if (name.contains("Service")) {
			type = "Service ===>";
		} else if (name.contains("Mapper")) {
			type = "Mapper ===>";
		}
		
		log.debug(type + name + "." + joinPoint.getSignature().getName() + "()");

		return joinPoint.proceed();
	}
}
