package ex.autocomplete.test;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import ex.autocomplete.AutoComplete;
import ex.autocomplete.QueryResult;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AutoCompleteApiIT {

	@Autowired
	AutoComplete autoComplete;
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void contextLoads() {
		assertThat(autoComplete).isNotNull();
		
	}
	
	private String getHostPart() {
		return String.format("http://localhost:%s",port);
	}
	
	
	@Test
	public void testQuery() throws Exception {
		String q="Lon";
		QueryResult result=this.restTemplate.getForObject(String.format("%s/suggestions?q=%s",this.getHostPart(),q),QueryResult.class);
		System.out.println(result.getSuggestions());
		assertThat(result.getSuggestions()!=null);
		assertThat(result.getSuggestions().size()>0);
		assertThat(result.getSuggestions().get(0).getName().startsWith("Lon"));
	}
	
	
	@Test 
	public void testQueryWithLocation() throws Exception {
		String q="London";
		String latitude="45";
		String longitude="-73";
		QueryResult result=this.restTemplate.getForObject(String.format("%s/suggestions?q=%s&latitude=%s&longitude=%s",this.getHostPart(),q,latitude,longitude),QueryResult.class);
		System.out.println(result.getSuggestions());
		assertThat(result.getSuggestions()!=null);
		assertThat(result.getSuggestions().size()>0);
		assertThat(result.getSuggestions().get(0).getName().startsWith("Lon"));
	}

}
