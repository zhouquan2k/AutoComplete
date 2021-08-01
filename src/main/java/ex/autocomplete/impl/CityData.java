package ex.autocomplete.impl;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;

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
	double longtitude;
}
