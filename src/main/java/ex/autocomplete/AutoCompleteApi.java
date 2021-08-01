package ex.autocomplete;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;

@RestController
@RequestMapping("/autocomplete")
@Api("AutoComplete API")
public class AutoCompleteApi {

	@Autowired
	AutoComplete autoComplete;

	@PostConstruct
	public void init() throws Exception {
		this.autoComplete.initData("/cities500.txt");
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public QueryResult query(@RequestParam String q)
	{
		return this.autoComplete.query(new QueryParam(q));
	}
}
