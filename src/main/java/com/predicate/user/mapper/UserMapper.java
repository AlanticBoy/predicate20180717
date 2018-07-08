package com.predicate.user.mapper;

import com.fusong.utils.ExcelUtils;
import com.predicate.user.model.PageUnit;
import com.predicate.user.model.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  15:09 2018/4/21
 * @ModefiedBy:
 */
public interface UserMapper {

    Set<String> selectPermissionsByPhone(String phone);

    User selectPasswordByPhone(String phone);

    User getSubjectByPhoneAndPassword(Map<String, String> map);

    List<User> selectAllUser() throws Exception;

    List<PageUnit> selectAllUnit() throws Exception;


    void insertMedicalInfo(Map<String, Object> map);

    void insertMedicalInfoTrain(Map<String, Object> map);


    List<Map<String, String>> selectByGroup_(String groups);

    /*封装成实体类在mapper.xml里遍历*/
    void insertUserInfo(List<User> users) throws Exception;

    /*封装成Map在mapper.xml里遍历*/
    void importExcelByMap(List<Map<String, Object>> maps) throws Exception;

    /*通过帐号密码查找用户*/
    User selectUserByIdAndPass(Map<String, String> map) throws Exception;

    int addNewUser(Map<String, Object> map);

    int updateUser(Map<String, Object> map);

    int insertAUser(Map<String, Object> map);

    int updateAUser(Map<String, Object> map);

    void deleteAllUser();

    List<Map<String, String>> seelctAllFromDq();

    List<Map<String, String>> selctAllfromSch();

    List<Map<String, String>> selectAllFromNmd();

    void updateAUserById(Map<String, Object> map);

    Map<String, Object> selectUserById(int id);

}
