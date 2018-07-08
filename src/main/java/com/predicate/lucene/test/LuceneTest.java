package com.predicate.lucene.test;

import com.predicate.lucene.demo.Lucene_CRUD_Demo;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  16:34 2018/5/7
 * @ModefiedBy:
 */
public class LuceneTest {

    @Test
    public void test1() {
        Lucene_CRUD_Demo demo = new Lucene_CRUD_Demo();
        demo.createIndexNotFile();

    }

    @Test
    public void test2() {
        Lucene_CRUD_Demo demo = new Lucene_CRUD_Demo();

        demo.createIndexForFile();
    }

    @Test
    public void test3() {
        Lucene_CRUD_Demo demo = new Lucene_CRUD_Demo();

        List<String> strings = demo.SearchLists();
        for (String s : strings) {
            System.out.println("  " + s);
        }
    }

    @Test
    public void test5() {
        Lucene_CRUD_Demo demo = new Lucene_CRUD_Demo();

        demo.deleteIndex();
    }

    @Test
    public void test6() {
        Lucene_CRUD_Demo demo = new Lucene_CRUD_Demo();

        demo.deleteAndMerge();
    }

    @Test
    public void update() {
        Lucene_CRUD_Demo demo = new Lucene_CRUD_Demo();
        demo.updateIndex();
    }

}
