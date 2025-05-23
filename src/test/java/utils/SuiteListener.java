package utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import base.BaseTest;

public class SuiteListener extends BaseTest implements ITestListener, IAnnotationTransformer {
	
	public void onTestFailure(ITestResult result) {	
		captureScreenshot(result.getTestContext().getName()+ "_"+ result.getMethod().getMethodName()+ ".png");
	}
	
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}

}
