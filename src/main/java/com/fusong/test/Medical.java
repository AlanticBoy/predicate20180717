package com.fusong.test;

import com.fusong.utils.MedicalUtils;
import com.predicate.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  7:48 2018/6/7
 * @ModefiedBy:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext_mybatis.xml")
public class Medical {

    @Autowired
    private UserService userService;

    @Test
    public void testMedical() throws Exception {
        List<Map<String, String>> mapList1 = userService.selectByGroup_("dementia");
        Map<Integer, List<Map<String, String>>> typeA = new HashMap<>();

       /* 解释subList（0，30）
        如原来的list中存在0、1、2、3 ... 100共101个数字，
        则调用list.subList（0，30）后，list变为从0开始，到29结束的数值，不包括30。*/
        List<Map<String, String>> typeA_01 = mapList1.subList(0, 910);
        List<Map<String, String>> typeA_02 = mapList1.subList(910, 1820);
        List<Map<String, String>> typeA_03 = mapList1.subList(1820, 2730);

        typeA.put(0, typeA_01);
        typeA.put(1, typeA_02);
        typeA.put(2, typeA_03);


        /*-------------------------------------------------------------------------------*/

        List<Map<String, String>> mapList2 = userService.selectByGroup_("MCI");
        Map<Integer, List<Map<String, String>>> typeB = new HashMap<>();
        List<Map<String, String>> typeB_01 = mapList2.subList(0, 449);
        List<Map<String, String>> typeB_02 = mapList2.subList(449, 898);
        List<Map<String, String>> typeB_03 = mapList2.subList(898, 1347);

        typeB.put(0, typeB_01);
        typeB.put(1, typeB_02);
        typeB.put(2, typeB_03);


         /*-------------------------------------------------------------------------------*/
        List<Map<String, String>> mapList3 = userService.selectByGroup_("Normal");
        Map<Integer, List<Map<String, String>>> typeC = new HashMap<>();
        List<Map<String, String>> typeC_01 = mapList3.subList(0, 126);
        List<Map<String, String>> typeC_02 = mapList3.subList(126, 252);
        List<Map<String, String>> typeC_03 = mapList3.subList(252, 379);

        typeC.put(0, typeC_01);
        typeC.put(1, typeC_02);
        typeC.put(2, typeC_03);

/*-------------------------------------------------------------------------------*/
        List<Map<String, String>> mapList4 = userService.selectByGroup_("VMD");
        Map<Integer, List<Map<String, String>>> typeD = new HashMap<>();
        List<Map<String, String>> typeD_01 = mapList4.subList(0, 272);
        List<Map<String, String>> typeD_02 = mapList4.subList(272, 544);
        List<Map<String, String>> typeD_03 = mapList4.subList(544, 816);

        typeD.put(0, typeD_01);
        typeD.put(1, typeD_02);
        typeD.put(2, typeD_03);


        /*用于存放所有的要到处的数据*/
        Map<Integer, List<Map<String, String>>> allMap_ = new HashMap<>();
        int counts = 0;
        for (int m = 0; m < typeA.size(); m++) {
            List<Map<String, String>> mMapList = new ArrayList<>();
            List<Map<String, String>> mMapLeft = new ArrayList<>();
            mMapList.addAll(typeA.get(m));
            for (int m1 = 0; m1 < typeA.size(); m1++) {
                if (m1 == m)
                    continue;
                mMapLeft.addAll(typeA.get(m1));
            }
            for (int n = 0; n < typeB.size(); n++) {
                List<Map<String, String>> nMapList = new ArrayList<>();
                nMapList.addAll(typeB.get(n));
                List<Map<String, String>> nMapLeft = new ArrayList<>();

                for (int n1 = 0; n1 < typeB.size(); n1++) {
                    if (n1 == n)
                        continue;
                    nMapLeft.addAll(typeB.get(n1));
                }
                for (int o = 0; o < typeC.size(); o++) {
                    List<Map<String, String>> oMapList = new ArrayList<>();
                    oMapList.addAll(typeC.get(n));
                    List<Map<String, String>> oMapLeft = new ArrayList<>();
                    for (int o1 = 0; o1 < typeC.size(); o1++) {
                        if (o1 == o)
                            continue;
                        oMapLeft.addAll(typeC.get(o1));
                    }
                    for (int p = 0; p < typeD.size(); p++) {
                        List<Map<String, String>> pMapList = new ArrayList<>();
                        pMapList.addAll(typeD.get(p));
                        List<Map<String, String>> pMapLeft = new ArrayList<>();
                        for (int p1 = 0; p1 < typeD.size(); p1++) {
                            if (p1 == p)
                                continue;
                            pMapLeft.addAll(typeD.get(p1));
                        }
                        pMapList.addAll(mMapList);
                        pMapList.addAll(nMapList);
                        pMapList.addAll(oMapList);
                        pMapLeft.addAll(mMapLeft);
                        pMapLeft.addAll(nMapLeft);
                        pMapLeft.addAll(oMapLeft);
                        allMap_.put(counts++, pMapList);
                        allMap_.put(counts++, pMapLeft);
                    }
                }
            }
        }
        ExcelWithoutResponse.ExportNoResponse(allMap_);
    }

}
