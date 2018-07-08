package com.fusong.test;

import com.predicate.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  16:15 2018/6/18
 * @ModefiedBy:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext_mybatis.xml")
public class MedicalDemo {

    @Autowired
    private UserService userService;

    @Test
    public void testMedical() throws Exception {
        List<Map<String, String>> dQMapList = userService.seelctAllFromDq();
        List<Map<String, String>> schMapList = userService.selctAllfromSch();
        List<Map<String, String>> nmdMapList = userService.selectAllFromNmd();
        Integer schMapSize = schMapList.size();
        Integer nmdMapSize = nmdMapList.size();
        List<Map<String, String>> eventualMapList = new ArrayList<>();
        System.out.println(" dqSize " + dQMapList.size() + "  schMapSize " + schMapList.size() + " nmdMapSize " + nmdMapList.size());
        for (int i = 0; i < dQMapList.size(); i++) {
            Map<String, String> dQMap = dQMapList.get(i);
            Integer schSize = 0;
            Integer nmdSize = 0;
             /*compared with second file*/
            for (int n = 0; n < schMapList.size(); n++) {
                boolean flag = true;
                Map<String, String> schMap = schMapList.get(n);
                Iterator<String> iterator = schMap.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    String val1 = dQMap.get(key);
                    String val2 = schMap.get(key);
                    if ((val1 == null) || (val1.equals(""))) {
                        continue;
                    }
                    if (!val1.equals(val2)) {
                        flag = false;
                        break;
                    }
                }
                if (flag == false) {
                    schSize++;
                }
            }
            /*compared with third file*/
            for (int m = 0; m < nmdMapList.size(); m++) {
                boolean flag = true;
                Map<String, String> nmdMap = nmdMapList.get(m);
                Iterator<String> iterator = nmdMap.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    String val1 = dQMap.get(key);
                    String val2 = nmdMap.get(key);
                    if ((val1 == null) || (val1.equals(""))) {
                      continue;
                    }
                    if (!val1.equals(val2)) {
                        flag = false;
                        break;
                    }
                }
                if (flag == false) {
                    nmdSize++;
                }
            }
            System.out.println(" schSize "+schSize+"  nmdSize "+nmdSize);
            if ((Objects.equals(schSize, schMapSize)) && (Objects.equals(nmdSize, nmdMapSize))) {
                System.out.println(" current value of i is " + i + " add to eventuallMap ");
                eventualMapList.add(dQMap);
            }
        }

        ExcelWithoutResponse.ExportNoResponseWithThird(eventualMapList);

    }

}
