package com.predicate.quartz.mapper;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  13:05 2018/5/1
 * @ModefiedBy:
 */
public interface QuartzMapper {
    String selectFirstRecord() throws Exception;
    void deleteRecord(String name) throws Exception;
}
