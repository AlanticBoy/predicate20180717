package com.predicate.user.service;

import com.predicate.user.mapper.UserMapper;
import com.predicate.user.model.PageUnit;
import com.predicate.user.model.User;
import com.predicate.utils.RequestUtil;
import com.predicate.utils.ResponseUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  15:14 2018/4/21
 * @ModefiedBy:
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Set<String> selectPermissionsByPhone(String phone) {
        return userMapper.selectPermissionsByPhone(phone);
    }

    @Override
    public User selectPasswordByPhone(String phone) {
        return userMapper.selectPasswordByPhone(phone);
    }

    @Override
    public User getSubjectByPhoneAndPassword(Map<String, String> map) {
        return userMapper.getSubjectByPhoneAndPassword(map);
    }


    /*#################################测试根据key进行缓存和缓存清空#################################*/
    /*注意这里的key值是从方法里的参数获取的*/
    @Cacheable(value = "definedCache", key = "#id")
    @Override
    public Map<String, Object> selectUserById(int id) {
        return userMapper.selectUserById(id);
    }

    @CacheEvict(value = "definedCache", key = "#id")
    @Override
    public void updateAUserById(int id, Map<String, Object> map) {
        userMapper.updateAUserById(map);
    }


    @Override
    @Cacheable(value = "studentCache")
    public List<User> selectAllUser() throws Exception {
        return userMapper.selectAllUser();
    }

    @Override
    public List<PageUnit> selectAllUnit(String key) throws Exception {
        return userMapper.selectAllUnit();
    }

    @CacheEvict(value = "studentCache", key = "#key")
    @Override
    public void deleteAllUnits(String key) {
        Map map = new HashMap();
        userMapper.updateUser(map);
    }

    @Override
    public void insertMedicalInfo(Map<String, Object> map) {
        userMapper.insertMedicalInfo(map);
    }





  /*#################################测试根据key进行缓存和缓存清空完毕#################################*/

    @Override
    public void insertAUser(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = RequestUtil.getParamMap(request);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(" get Date from jsp where key = " + entry.getKey() + "  value =   " + entry.getValue());
        }
        userMapper.addNewUser(map);
        Map<String, Object> mesgMap = new HashMap<>();
        mesgMap.put("mseg", "Congradutions");
        ResponseUtil.response2Client(response, mesgMap);
    }

    @Override
    public void insertUserInfo(List<User> users) throws Exception {
        userMapper.insertUserInfo(users);
    }

    @Override
    public void importExcelByMap(List<Map<String, Object>> maps) throws Exception {
        userMapper.importExcelByMap(maps);
    }

    /*通过帐号密码查找用户*/
    @Override
    public User selectUserByIdAndPass(Map<String, String> map) throws Exception {
        return userMapper.selectUserByIdAndPass(map);
    }

    @Override
    public int addNewUser(Map<String, Object> map) {
        return userMapper.addNewUser(map);
    }

    @Override
    public int updateUser(Map<String, Object> map) {
        return userMapper.updateUser(map);
    }

    @Override
    public void updateAfterInsertUser() {
        Map<String, Object> insertMap = new HashMap<>();
        insertMap.put("phone", "1912863524");
        insertMap.put("name", "huasheng");
        insertMap.put("password", "admin");
        userMapper.insertAUser(insertMap);
        /*经过测试，加入这行代码，这个方法可以保证原子性操作*/
        /*int a=10/0;*/
        System.out.println(" get new insert Id " + insertMap.get("userId"));
        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("phone", "1912863524");
        updateMap.put("name", "sherlock");
        userMapper.updateAUser(updateMap);
        System.out.println("  get new update  Id " + updateMap.get("userId"));
    }

    @Override
    public void insertMedicalInfoTrain(Map<String, Object> map) {
        userMapper.insertMedicalInfoTrain(map);
    }

    @Override
    public List<Map<String, String>> seelctAllFromDq() {
        return userMapper.seelctAllFromDq();
    }

    @Override
    public List<Map<String, String>> selctAllfromSch() {
        return userMapper.selctAllfromSch();
    }

    @Override
    public List<Map<String, String>> selectAllFromNmd() {
        return userMapper.selectAllFromNmd();
    }

    @Override
    public List<Map<String, String>> selectByGroup_(String groups) {
        return userMapper.selectByGroup_(groups);
    }


}
