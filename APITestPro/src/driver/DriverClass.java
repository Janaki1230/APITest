package driver;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import org.testng.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.*;

import actionLibrary.ActionsClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import utilities.TestDataActions;

public class DriverClass extends TestDataActions{
	Properties prop;
	ActionsClass ac=new ActionsClass();
@BeforeTest
public void initialization() throws IOException{
	FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"\\"+"GlobalVariables.properties");
	prop= new Properties();
	prop.load(fs);
		
}
@Test
public void driverMethod() throws IOException{
	System.out.println("API Request1");
	ArrayList<String> all=dataToMap("TestData.xlsx","TestData","Name");
	Iterator it=all.iterator();
	while(it.hasNext()){
	String name=it.next().toString();
	RestAssured.baseURI=prop.getProperty("endPoint1");
	String Response=given().queryParam("name",name).header("content-Type","application/json").
			        when().get().
			        then().assertThat().statusCode(200).contentType(ContentType.JSON).extract().response().asString();
	JsonPath js=new JsonPath(Response);
	int age=js.get("age");
	System.out.println();
	System.out.println("Age::"+age);
	if(age>40){
		ac.APIRequest(name, prop);
		
	}else{
		System.out.println("Person is younger than 40");
		System.out.println(Response);
	}
	}
	
}


@AfterTest
public void closeTest(){
	System.out.println("Close Test");
}



}
