package com.atguigu.mapper.mine_mappers;

import tk.mybatis.mapper.common.base.select.SelectAllMapper;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;

/**
 * 注意：此接口不能放在 com.atguigu.mapper.mappers 包下，
 * 必须放在单独的包中，如本例中的 com.atguigu.mapper.mine_mappers 包，否则编译出错。
 * 因为 mappers 包下面的 EmployeeMapper extends MyMapper<Employee> 接口，是定义具体的实体类的接口，泛型中使用了具体的实体类，
 * 而这里的 MyMapper<T> 接口，是定义的泛型接口，泛型 <T> 无法被通用 mapper 程序解析，因此报错。
 * @param <T>
 */
public interface MyMapper<T> 
		extends SelectAllMapper<T>,SelectByExampleMapper<T> {

}
