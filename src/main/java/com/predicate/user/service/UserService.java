package com.predicate.user.service;

import com.predicate.user.model.PageUnit;
import com.predicate.user.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  15:14 2018/4/21
 * @ModefiedBy:
 */
public interface UserService {

    Set<String> selectPermissionsByPhone(String phone);


    User selectPasswordByPhone(String phone);

    User getSubjectByPhoneAndPassword(Map<String,String> map);

    List<User> selectAllUser() throws Exception;
    List<PageUnit> selectAllUnit(String key) throws Exception;
    /*封装成实体类在mapper.xml里遍历*/
    void insertUserInfo(List<User> users) throws Exception;
    /*封装成Map在mapper.xml里遍历*/
    void importExcelByMap(List<Map<String, Object>> maps) throws Exception;
    /*通过帐号密码查找用户*/
    User selectUserByIdAndPass(Map<String,String> map) throws Exception;
    int addNewUser(Map<String,Object> map);
    int updateUser(Map<String, Object> map);

    void updateAfterInsertUser();

    void insertMedicalInfoTrain(Map<String, Object> map);

    List<Map<String, String>> seelctAllFromDq();

    List<Map<String, String>> selctAllfromSch();

    List<Map<String, String>> selectAllFromNmd();


    List<Map<String, String>> selectByGroup_(String groups);

    Map<String, Object> selectUserById(int id);
    void updateAUserById(int id,Map<String,Object> map);


    void insertAUser(HttpServletRequest request, HttpServletResponse response);
    void deleteAllUnits(String key);


    void insertMedicalInfo(Map<String, Object> map);

}
