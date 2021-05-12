package actionLibrary;

import static io.restassured.RestAssured.given;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class ActionsClass {
	public void APIRequest(String name,Properties prop){
		RestAssured.baseURI=prop.getProperty("endPoint2");
		String Response=given().queryParam("name",name).header("content-Type","application/json").
				        when().get().
				        then().assertThat().statusCode(200).contentType(ContentType.JSON).extract().response().asString();
		JsonPath js=new JsonPath(Response);
		String gender=js.get("gender");
		String probDataType=js.get("probability").getClass().getSimpleName();
		if(probDataType.contentEquals("Integer"))
		{
			int prob2=js.get("probability");
			int count=js.get("count");
			if(gender.contentEquals("male") && prob2>0.90){
				String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
				System.out.println("TimeStamp::"+timeStamp);
				System.out.println("Person Name::"+name);
				System.out.println("Count::"+count);
			}
			else if(gender.contentEquals("female")){
				System.out.println("Person Name::"+name);
				System.out.println("Probability::"+prob2);
			}
		}
		else if(probDataType.contentEquals("Float")){
			float prob2=js.get("probability");
			int count=js.get("count");
			if(gender.contentEquals("male") && prob2>0.90){
				String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
				System.out.println("TimeStamp::"+timeStamp);
				System.out.println("Person Name::"+name);
				System.out.println("Count::"+count);
			}
			else if(gender.contentEquals("female")){
				System.out.println("Person Name::"+name);
				System.out.println("Probability::"+prob2);
			}
		}
		
		
		
	}

}
