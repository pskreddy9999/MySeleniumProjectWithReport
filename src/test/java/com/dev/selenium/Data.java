package com.dev.selenium;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

public class Data {
	
	
	private static final ThreadLocal<Data> Dt = new ThreadLocal<>();
	
	public static ThreadLocal<Data> get(){
		return Dt;
	}
	
	public static void set(Data data){
		Dt.set(data);
	}
	

	public static WebDriver driver;
	public static HashMap<String, String> tcData;

}
