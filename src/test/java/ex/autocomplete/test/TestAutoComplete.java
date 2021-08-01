package ex.autocomplete.test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;

import ex.autocomplete.AutoComplete;
import ex.autocomplete.QueryParam;
import ex.autocomplete.QueryResult;
import ex.autocomplete.impl.AutoCompleteSql;
import lombok.extern.slf4j.Slf4j;



//import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
//@SpringBootTest
public class TestAutoComplete // extends AbstractTestNGSpringContextTests {
{
	AutoComplete autoComplete;
	@Before
	public void init() {
		autoComplete=new AutoCompleteSql();
	}
	
	@Test 
	public void test1() {
		QueryParam param=new QueryParam("Lon");
		QueryResult result=this.autoComplete.query(param);
		log.info("...result:"+result);
		assertNotEquals(result,null);
		
	}
}
