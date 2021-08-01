package ex.autocomplete.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import ex.autocomplete.AutoComplete;
import ex.autocomplete.QueryParam;
import ex.autocomplete.QueryResult;


@Service
public class AutoCompleteSql implements AutoComplete {

	@Autowired 
	CityMapper cityMapper;
	
	@Override
	public QueryResult query(QueryParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void initData(String originDataFile) throws Exception {
		
		//check if data exist
		int count=this.cityMapper.selectCount(new QueryWrapper<>());
		if (count>0) return;
		
		InputStream stream = this.getClass().getResourceAsStream(originDataFile);
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		//this.cityMapper.delete(new QueryWrapper<>()); //delete all
		
		String line = null;
		while ((line = reader.readLine()) != null){
			String[] fields=line.split("\t");
			System.out.println(Arrays.asList(fields));
			CityData city=new CityData();
			city.setId(fields[0]);
			city.setName(fields[1]);
			city.setDistrict(fields[17]);
			city.setLatitude(Double.parseDouble(fields[4]));
			city.setLongtitude(Double.parseDouble(fields[5]));
			long t1=Calendar.getInstance().getTimeInMillis();
			cityMapper.insert(city);
			long t2=Calendar.getInstance().getTimeInMillis();
			System.out.println(t2-t1);
			
		}
		reader.close();
	}

}
