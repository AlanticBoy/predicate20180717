package com.predicate.lucene.demo;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.flexible.standard.QueryParserUtil;
import org.apache.lucene.queryparser.xml.builders.NumericRangeQueryBuilder;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;
import org.aspectj.lang.annotation.Before;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  17:08 2018/5/11
 * @ModefiedBy:
 */
public class Lucene_Boost_Demo {

    private IndexWriter indexWriter = null;

    private Directory directory = null;
    private IndexSearcher searcher = null;

    /* String[] ids = {"1", "2", "3", "4", "5", "6"};*/
    Integer[] ids = {1, 2, 3, 4, 5, 6};
    //下面是邮件
    String[] emails = {"aa@qq.com", "bb@sina.edu", "cc@yahu.org", "ss@sina.com", "dd@gmail.com", "ee@163.com"};
    // 下面是邮件内容
    String[] content = {"welcom to visited the space", "hello boy", "come on baby,boy", "first blood", "I like football", "my girlfriend is so beatiful"};
    int[] attaches = {2, 5, 6, 5, 8, 4};//附件数量
    // 发件人名字
    String[] names = {"Tom", "Jack", "goudan", "alibaba", "jerry", "kitty"};
    Map<String, Float> map = null;
    Date[] dates = {new Date(2011, 12, 31), new Date(2012, 12, 31), new Date(2013, 12, 31), new Date(2014, 12, 31),
            new Date(2015, 12, 31), new Date(2016, 12, 31)};

    @BeforeTest
    public void setup() {

        map = new HashMap<>();
        map.put("qq.com", 2.0f);
        map.put("yahu.org", 1.5f);

        try {
            directory = FSDirectory.open(Paths.get("E:/lucene/boost"));
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            indexWriter = new IndexWriter(directory, config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddIndexBoost() throws IOException {

        Document document;

        for (int i = 0; i < ids.length; i++) {
            document = new Document();
            /*document.add(new StringField("ids", String.valueOf(ids[i]), Field.Store.YES));*/
            document.add(new IntField("id", ids[i], Field.Store.YES));
            document.add(new TextField("emails", emails[i], Field.Store.YES));
            TextField contextField = new TextField("content", content[i], Field.Store.YES);
            document.add(contextField);
            /*Lucene5.3.1存储数字的方式，也就是对日期数字进行索引*/
            Field field = new LongField("attaches", attaches[i], Field.Store.YES);
            document.add(field);
            document.add(new TextField("names", names[i], Field.Store.YES));
            document.add(new LongField("date", dates[i].getTime(), Field.Store.YES));
            String et = emails[i].substring(emails[i].lastIndexOf("@") + 1);
            if (map.containsKey(et)) {
                /*加权的作用，权值高的内容容易被被搜索出来，而且排在前面
                * 给谁加权，就会给谁评分，评分越高，就越排在最前面。
                * 4.3版本之后给Field加权，3.5之前给Document加权
                * 如果不加权的话，权默认是1.0f
                * */
                contextField.setBoost(map.get(et));
            } else {
                contextField.setBoost(0.5f);
            }
            indexWriter.addDocument(document);
        }

        if (indexWriter != null) {
            indexWriter.close();
        }
    }


    /*所以这个TermRangeQuery了解就行，用途不太大，一般数字范围查询NumericRangeQuery用的比较多一点，
    比如价格，年龄，金额，数量等等都涉及到数字，数字范围查询需求也很普遍。
    */
    @Test
    public void testNumericRangeQuery() throws IOException {
        NumericRangeQuery<Integer> query = NumericRangeQuery.newIntRange("id", 2, 6, true, true);
        System.out.println(query.toString());
        IndexReader reader = DirectoryReader.open(directory);
        searcher = new IndexSearcher(reader);
        TopDocs topDocs = searcher.search(query, 10);
        getResult(topDocs);

    }


    @Test
    public void testQueryParse() {


    }

    @Test
    public void searchIndex() throws IOException {
        IndexReader reader = DirectoryReader.open(directory);
        searcher = new IndexSearcher(reader);
        TopDocs topDocs = searcher.search(new TermQuery(new Term("content", "blood")), 10);
        getResult(topDocs);
    }


    public void getResult(TopDocs topDocs) {
        try {
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            Document document = null;

            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            for (ScoreDoc doc : scoreDocs) {
                document = searcher.doc(doc.doc);
                System.out.println(" ids  " + document.get("id"));
                System.out.println(" content  " + document.get("content"));
                System.out.println(" emails  " + document.get("emails"));
                System.out.println(" attaches  " + document.get("attaches"));
                System.out.println(" names  " + document.get("names"));
                Long date = Long.valueOf(document.get("date"));
                Date date1 = new Date(date);
                System.out.println(" date  " + format.format(date1));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
