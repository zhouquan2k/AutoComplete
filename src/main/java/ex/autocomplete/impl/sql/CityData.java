package ex.autocomplete.impl.sql;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;

import ex.autocomplete.City;
import lombok.Data;

@Data
@Table(name="t_city")
@TableName("t_city")
public class CityData {
	@Column(isKey=true)
	String id;
	@Column
	String name;
	@Column
	String district;
	@Column
	double latitude;
	@Column
	double longitude;
	
	//not a column
	double score;
	
	public City toCity() {
		return new City(name+","+district,latitude,longitude,score);
	}
}
