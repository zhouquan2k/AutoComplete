package component.mybatisex;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * base mapper to support batch insert and update
 * 
 * @author zhouquan
 *
 * @param <T>
 */
public interface BasicMapper <T> extends BaseMapper<T> {

    int insertBatch(@Param("list") List<T> list);

    int updateBatch(@Param("list") List<T> list);

}
