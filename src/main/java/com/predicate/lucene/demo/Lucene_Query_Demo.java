package com.predicate.lucene.demo;


import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * @Author:付风松
 * @Description:Lucene的多种查询方式。 Lucene最常用的搜索种类：
 * TermQuery,BooleanQuery,RegexQuery
 * @Date:Created in  18:51 2018/5/9
 * @ModefiedBy:
 */
public class Lucene_Query_Demo {
    IndexReader reader = null;
    IndexSearcher searcher = null;

    @BeforeTest
    public void setup() {
        try {
            Directory directory = FSDirectory.open(Paths.get("E:/lucene/instance"));
            reader = DirectoryReader.open(directory);
            searcher = new IndexSearcher(reader);
            System.out.println(" over ");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getResult(TopDocs topDocs) {
        try {
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            Document document = null;
            for (ScoreDoc doc : scoreDocs) {
                document = searcher.doc(doc.doc);

                System.out.println(" content  " + document.get("content"));
                System.out.println(" fileName   " + document.get("fileName"));
                System.out.println(" get  path " + document.get("get  path"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /*测试TermQuery。
    * 一个Term相当于文本中的一个单词，term是一个搜索单元，由两部分组成：域的名称和单词文本
    * TermQuery不会对你提供的fieldValue做任何处理
    * */
    @Test
    public void termQueryDemo() {
        try {

            Term term = new Term("content", "silence");
            Query query = new TermQuery(term);
            TopDocs topDocs = searcher.search(query, 100);
            System.out.println("hit records  " + topDocs.totalHits);
            getResult(topDocs);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*测试BooleanQuery
    * BooleanQuery在检索中的用处：我们可能需要搜索一个文件中的多个字段。
    * 不同的字段之间存在与或非的运算关系。BooleanClouse是在BooleanQuery的一个子句。
    * 该类中的最终内部类Occur定义了BooleanQuery的运算符
    * Occur.MUST：必须满足此条件，相当于and
    * Occur.SHOULD：应该满足，但是不满足也可以，相当于or
    * Occur.MUST_NOT：必须不满足。相当于not
    * */
    @Test
    public void testBooleanQuery() throws IOException {

        try {
            Term termIndex = new Term("content", "better");
            TermQuery termQueryIndex = new TermQuery(termIndex);
            BooleanClause booleanClauseIndex = new BooleanClause(termQueryIndex, BooleanClause.Occur.MUST_NOT);
            Term termGood = new Term("content", "good");
            TermQuery termQueryGood = new TermQuery(termGood);
            BooleanClause booleanClauseGood = new BooleanClause(termQueryGood, BooleanClause.Occur.SHOULD);
            Term termLove = new Term("content", "love");
            TermQuery termQueryLove = new TermQuery(termLove);
            BooleanClause booleanClauseLove = new BooleanClause(termQueryLove, BooleanClause.Occur.SHOULD);
            BooleanQuery booleanQuery = new BooleanQuery.Builder().add(booleanClauseIndex).add(booleanClauseGood).add(booleanClauseLove).build();
            TopDocs topDocs = searcher.search(booleanQuery, 100);
            System.out.println("hit records : " + topDocs.totalHits);
            getResult(topDocs);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*测试前缀搜索
    *  前缀搜索(搜索起始位置符合要求的结果)
    * */
    @Test
    public void testPrefixQuery() throws IOException {
        Term term = new Term("content", "bet");
        PrefixQuery prefixQuery = new PrefixQuery(term);
        TopDocs topDocs = searcher.search(prefixQuery, 50);
        getResult(topDocs);
    }

    /*短语搜索，根据零碎的短语组合成新的词组进行搜索
    * 其中setSlop的参数是设置两个关键字之间允许间隔的最大值
    * Sets the number of other words permitted between words in query phrase
    * 这里的距离指的是，两个单词之间相隔的单词的数目.需要移动位置的次数
    * */
    @Test
    public void testParseQuery() throws IOException {
        Term termBet = new Term("content", "sometimes");
        Term termLov = new Term("content", "silence");
        PhraseQuery phraseQuery = new PhraseQuery.Builder().add(termBet).add(termLov).setSlop(4).build();

        TopDocs topDocs = searcher.search(phraseQuery, 50);
        getResult(topDocs);

    }

    /*
        MultiTermQuery是一个抽象类，继承自它的一种有3个，分别为：FuzzyQuery、WildcardQuery、RegexQuery
        * FuzzyQuery查询，即模糊查询。
        *在FuzzyQuery类定义中定义了两个成员变量：
        *private float minimumSimilarity;
         *private int prefixLength;
         *minimumSimilarity是最小相似度，取值范围为0.0~1.0，包含0.0但不包含1.0，默认值为0.5。prefixLength是前缀长度，默认为0。
         *
        */
    @Test
    public void testFuzzyQuery() throws IOException {
        Term term = new Term("content", "Lov");
        FuzzyQuery fuzzyQuery = new FuzzyQuery(term);
        TopDocs topDocs = searcher.search(fuzzyQuery, 50);
        getResult(topDocs);
    }

    /*对QueryParser的测试，Queryparser一定是很实用的东西
      *他能够根据用户的输入进行解析，自动构建合适的Query对象
        */
    @Test
    public void testQueryParser() throws ParseException, IOException {
        QueryParser parser = new QueryParser("content", new StandardAnalyzer());
        /*这里我们输入"every cry",QueryParser分析成"or"的关系。它会检索既有“every”，又有“cry”的文档。
         *如果我们想查“And”，关系只需要改变QueryParser默认的布尔逻辑
         *parser.setDefaultOperator(QueryParser.Operator.AND);
         */
        /*parser.setDefaultOperator(QueryParser.Operator.AND);
        */
        String queryStr = "Rome is not built in one day";
        Query query = parser.parse(queryStr);
        TopDocs topDocs = searcher.search(query, 10);
        getResult(topDocs);
    }

    @Test
    public void testMultiQueryParser() throws ParseException, IOException {
        Query query = MultiFieldQueryParser.parse(new String[]{"home", "every"}, new String[]{"content", "content"}, new StandardAnalyzer());
        TopDocs topDocs = searcher.search(query, 10);
        getResult(topDocs);
    }

    @Test
    public void testQueryParserTruth() throws ParseException, IOException {
        QueryParser parser=new QueryParser("content",new StandardAnalyzer());
        Query query=parser.parse("truth");
        TopDocs topDocs=searcher.search(query,1);
        getResult(topDocs);
    }


}
