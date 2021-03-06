package com.shiro.springbootshiro.mapper;

import com.shiro.springbootshiro.bean.Memory;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MemoryMapper {
	Memory findMemoryById(Integer id);
	List<Memory> findAllMemory();
}
