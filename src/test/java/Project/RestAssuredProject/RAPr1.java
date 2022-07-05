package Project.RestAssuredProject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.internal.path.json.mapping.JsonObjectDeserializer;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class RAPr1 
{
	
	@DataProvider(name="testcases")
	public Object[][] data () 
	{
		Object[][] userdata= new Object[2][8];
		
		userdata[0][0]=0;
		userdata[0][1]="dsf";
		userdata[0][2]="Dohm";
		userdata[0][3]="Fias";
		userdata[0][4]="lkj@hg.com";
		userdata[0][5]="45678";
		userdata[0][6]="987654323";
		userdata[0][7]= 1;
		userdata[1][0]=0;
		userdata[1][1]="jkl";
		userdata[1][2]="leo";
		userdata[1][3]="messi";
		userdata[1][4]="lm10@barca.com";
		userdata[1][5]="45678";
		userdata[1][6]="9999999888";
		userdata[1][7]= 0;
		
		return userdata;
		
	}
	@Test(enabled=false,dataProvider="testcases")
	public void CreateUser(int id, String usn, String fnm , String lnm, String eml, String pswd, String fone, int us)
	
	{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		JSONObject obj = new JSONObject();
		
		obj.put("id", id);
		obj.put("username",usn);
		obj.put("firstName",fnm);
		obj.put("lastName",lnm);
		obj.put("email",eml);
		obj.put("password",pswd);
		obj.put("phone",fone);
		obj.put("userStatus", us);
		
		given()
			.contentType(ContentType.JSON)
			.body(obj.toJSONString()).
			
		when()	
			.post("/user").
		then()
			.statusCode(200)
			.log()
			.all();	
	}
	@Test(enabled=false,dependsOnMethods="CreateUser")
	public void GetUser()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		
		given()
			//.queryParam("username", "dsf").
			.get("/user/dsf"). 
		then()
			.statusCode(200)
			.log()
			.all();
		
	}
	@Test(enabled=false,dependsOnMethods="GetUser")
	public void LoginUser()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		
		given()
			.queryParam("username", "dsf")
			.queryParam("password", "45678").
		when()
			.get("/user/login"). 
		then()
			.statusCode(200)
			.log()
			.all();
	}
	
	@Test(enabled=false,dependsOnMethods="LoginUser")
	public void ModifyUser()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		
		
		JSONObject ob= new JSONObject();
		
		
		ob.put("username", "dsf");
		ob.put("firstName", "Mohammed");
		ob.put("lastName", "Saif");
		ob.put("email", "dg@up.com");
		ob.put("password", "Uttar Pradesh");
		ob.put("phone", "9876543210");
		
		
		given()
			//.queryParam("username", "dsf")
			.contentType(ContentType.JSON)
			.body(ob.toJSONString()).
			
		when()
			.put("/user/dsf"). 
			
		then()
			.statusCode(200)
			.log()
			.all();
	}
	
	@Test(enabled=false,dependsOnMethods="LoginUser")
	public void DeleteUser()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		
		given()
			//.queryParam("username", "dsf"). 
			.delete("/user/dsf").
		then()
			.statusCode(200)
			.log()
			.all();
		}
	@Test(enabled=false,dependsOnMethods="LoginUser")
	public void LogoutUser()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		
		given()
			.get("/user/logout/").
		then()
			.statusCode(200)
			.log()
			.all();
	}
	
/*	@Test(enabled=false)
	public void tc8()
	{
		RestAssured.baseURI="http://localhost:3000/";
		
		JSONObject parent= new JSONObject();
			
		parent.put("id",1);
		parent.put("name","Tommy");
		parent.put("status","avaialble");
		
		JSONObject CategoryObj= new JSONObject();
			CategoryObj.put("id", 0);
			CategoryObj.put("name", "Dog");
			
			parent.put("category", CategoryObj);
		
		JSONArray obj = new JSONArray();
			obj.add("photo1");
			obj.add("photo2");
			
			parent.put("photoUrls", obj);
		
		JSONObject tagsObj= new JSONObject();
			tagsObj.put("id", 0);
			tagsObj.put("name", "cat");
			
		JSONObject tagsObj1 = new JSONObject();
			tagsObj1.put("id",1);
			tagsObj1.put("name","fish");
		
		JSONArray tagsAr= new JSONArray();
			tagsAr.add(tagsObj);
			tagsAr.add(tagsObj1);
			
			parent.put("tags", tagsAr);
		
		System.out.println(parent);
		
		
		/*given()
			.contentType(ContentType.JSON)
			.body(body)
		
		*/
		
	/*}
	
	@Test(enabled=true)
	public void tc9(ITestContext var)
	
	{
		RestAssured.baseURI="http://localhost:3000/";
		
		Response obj = given()
				.get("/ibmstudents/6").
			then()
				.statusCode(200)
				.log().all().extract().response();
		
		String IDV= obj.jsonPath().getString("id");
		System.out.println(IDV);
		
		
		
	}*/

}






