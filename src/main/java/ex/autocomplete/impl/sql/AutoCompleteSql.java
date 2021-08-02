package ex.autocomplete.impl.sql;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import ex.autocomplete.AutoComplete;
import ex.autocomplete.City;
import ex.autocomplete.QueryParam;
import ex.autocomplete.QueryResult;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author zhouquan
 * 
 * AutoComplete Engine: SQL implementation,
 * use any sql relational database, use mybatis/mybatis plus as orm
 * use sql to do the query
 */
@Slf4j
@Service
public class AutoCompleteSql implements AutoComplete {

	@Autowired 
	CityMapper cityMapper;
	
	@Override
	public QueryResult query(QueryParam param) {
		
		param.setQ(param.getQ()+"%"); // for sql like 
		List<CityData> cities=cityMapper.queryCities(param);
		QueryResult ret=new QueryResult();
		
		for (CityData c:cities) {
			City city=c.toCity();
			//city.setScore((double)originQ.length()/(double)c.getName().length()*0.5+c.getDistance()*0.5);
			ret.getSuggestions().add(city);
		}
		/* refactored: using sql sort
		//resort suggestions according to score
		Collections.sort(ret.getSuggestions(),new Comparator<City>() {
			@Override
			public int compare(City o1, City o2) {
				return (o1.getScore()-o2.getScore())==0?0:(o1.getScore()-o2.getScore()>0?-1:1);
			}
		});
		*/
		
		return ret;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void initData(String originDataFile) throws Exception {
		
		//check if data exist
		int count=this.cityMapper.selectCount(new QueryWrapper<>());
		if (count>0) return;
		
		log.info("importing city data from {}...",originDataFile);
		
		InputStream stream = this.getClass().getResourceAsStream(originDataFile);
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		//this.cityMapper.delete(new QueryWrapper<>()); //delete all
		
		String line = null;
		List<CityData> datas=new Vector<CityData>();
		final int maxBufCnt=1000;
		
		while ((line = reader.readLine()) != null){
			String[] fields=line.split("\t");
			//System.out.println(Arrays.asList(fields));
			CityData city=new CityData();
			city.setId(fields[0]);
			city.setName(fields[1]);
			city.setDistrict(fields[17]);
			city.setLatitude(Double.parseDouble(fields[4]));
			city.setLongitude(Double.parseDouble(fields[5]));
			
			datas.add(city);
			if (datas.size()>maxBufCnt) {
				//long t1=Calendar.getInstance().getTimeInMillis();
				cityMapper.insertBatch(datas);
				//long t2=Calendar.getInstance().getTimeInMillis();
				//System.out.println(t2-t1);
				System.out.println(datas.size());
				datas.clear();
			}
		}
		if (datas.size()>0) cityMapper.insertBatch(datas);
		reader.close();
		
		count=this.cityMapper.selectCount(new QueryWrapper<>());
		log.info("imported cities: {} ",count);
	}

}
