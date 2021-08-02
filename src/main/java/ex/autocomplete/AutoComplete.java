package ex.autocomplete;

import org.springframework.stereotype.Service;

/**
 * 
 * @author zhouquan
 *
 * AutoComplete engine interface
 * 
 */
@Service
public interface AutoComplete {

	QueryResult query(QueryParam param);
	
	void initData(String originDataFile) throws Exception;
}
