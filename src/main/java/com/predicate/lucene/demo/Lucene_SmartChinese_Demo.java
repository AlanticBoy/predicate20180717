package com.predicate.lucene.demo;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;


/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  18:03 2018/5/12
 * @ModefiedBy:
 */
public class Lucene_SmartChinese_Demo {

    private IndexWriter indexWriter;
    private IndexReader indexReader;
    private IndexSearcher indexSearcher;

    @BeforeTest
    public void setup() {
        try {
            Directory directory = FSDirectory.open(Paths.get("E:/lucene/chinese"));
            SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            indexWriter = new IndexWriter(directory, config);
            indexReader = DirectoryReader.open(directory);
            indexSearcher = new IndexSearcher(indexReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createIndex() throws IOException {
        Integer ids[] = {1, 2, 3};
        String[] citys = {"上海", "广州", "武汉"};
        String[] descs = {"之前我们创建索引库的时候，就用到了官方推荐的标准分析器",
                "现在我们就来看看其分词效果",
                "那么回归标题，为什么要使用中文分词器，原因就是"};
        Document document;
        for (int i = 0; i < ids.length; i++) {
            document = new Document();
            document.add(new IntField("ids", ids[i], Field.Store.YES));
            document.add(new StringField("citys", citys[i], Field.Store.YES));
            document.add(new TextField("descs", descs[i], Field.Store.YES));
            indexWriter.addDocument(document);
        }
        if (indexWriter != null) {
            indexWriter.close();
        }
    }

    @Test
    public void searchByChineseAnalysier() throws IOException, ParseException {
        /*查询中文步骤*/
        /*第一步，建立中文分析器*/
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        /*第二步，建立QueryParser，把analyzer和要查找的域作为参数*/
        QueryParser parser = new QueryParser("descs", analyzer);
        /*第三步，通过parser获取Query对象
        * 如果我们在下面输入，索引标题，那么会出现两条记录
        * */
        Query query = parser.parse("分析");

        TopDocs topDocs = indexSearcher.search(query, 10);
        getResult(topDocs);
    }

    /*输出结果高亮显示*/
    public void getHighLightResult(TopDocs topDocs, Query query, Analyzer analyzer) {
        try {
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            Document document = null;
            /*使结果高亮显示,此处是加入高亮部分的代码*/
            /*==========================================*/
            /*第一步，创建SimpleHTMLFomatter对象。如果不指定SimpleHTMLFormatter参数的话，默认是加粗*/
            SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
          /*第二步，创建QueryScorer对象。
          * scorer首先初始化查询内容对应的出现位置的下标，然后对tokenstream添加PositionIncrementAttribute，此类记录单词出现的位置
          * */
            QueryScorer queryScorer = new QueryScorer(query);
            /* 第三步，创建Fragmenter对象。作用是将原始字符串拆分成独立的片段。*/
            Fragmenter fragmenter = new SimpleSpanFragmenter(queryScorer);
            /*第四步，创建HighLighter对象。需要的参数，SimpleHTMLFomatter，QueryScore*/
            Highlighter highlighter = new Highlighter(formatter, queryScorer);
            /*设置Fragementer*/
            highlighter.setTextFragmenter(fragmenter);
            for (ScoreDoc doc : scoreDocs) {
                document = indexSearcher.doc(doc.doc);
                System.out.println(" ids  " + document.get("ids"));
                System.out.println(" citys  " + document.get("citys"));
                String desc = document.get("descs");
                /*显示高亮部分*/
                if (desc != null) {/*
                    TokenStream:分词流，即将对象分词后所得的Token在内存中以流的方式存在
                    分词对象可以是文档文本，也可以是查询文本。*/
                    TokenStream tokenStream = analyzer.tokenStream("descs", new StringReader(desc));
                    String summary = highlighter.getBestFragment(tokenStream, desc);
                    System.out.println(summary);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidTokenOffsetsException e) {
            e.printStackTrace();
        }

    }

    public void getResult(TopDocs topDocs) {
        try {
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            Document document = null;
            /*使结果高亮显示*/

            for (ScoreDoc doc : scoreDocs) {
                document = indexSearcher.doc(doc.doc);
                System.out.println(" ids  " + document.get("ids"));
                System.out.println(" citys  " + document.get("citys"));
                System.out.println(" descs  " + document.get("descs"));


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
