package utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.ITestAnnotation;

import base.BaseTest;

public class SuiteListener extends BaseTest implements ITestListener, IAnnotationTransformer {
	
	public void onTestSuccess(ITestResult result) {	
		captureScreenshotPass(result.getTestContext().getName()+ "_"+ result.getMethod().getMethodName());
		storePassScreenshotInReport(result.getTestContext().getName()+ "_"+ result.getMethod().getMethodName());
	}
	
	public void onTestFailure(ITestResult result) {	
		captureScreenshotFail(result.getTestContext().getName()+ "_"+ result.getMethod().getMethodName());
		storeFailScreenshotInReport(result.getTestContext().getName()+ "_"+ result.getMethod().getMethodName());
	}
	
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}
}
