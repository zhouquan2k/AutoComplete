package ex.autocomplete;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;

/**
 * 
 * @author zhouquan
 * 
 * Rest API implementation
 * 
 */
@RestController
@RequestMapping("/")
@Api("AutoComplete API")
public class AutoCompleteApi {

	@Autowired
	AutoComplete autoComplete;
	
	@Value("${app.city-data-file}")
	String cityDataFile;

	@PostConstruct
	public void init() throws Exception {
		this.autoComplete.initData(this.cityDataFile);
	}
	
	@RequestMapping(value = "suggestions", method = RequestMethod.GET)
	public QueryResult query(@RequestParam String q,@RequestParam(defaultValue="0") double latitude,@RequestParam(defaultValue="0") double longitude)
	{
		return this.autoComplete.query(new QueryParam(q,latitude,longitude));
	}
}
