package com.predicate.lucene.demo;

import com.fusong.utils.RandomUtil;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  20:27 2018/5/15
 * @ModefiedBy:
 */
public class Lucene_SerarchAfter_Demo {


    public IndexWriter indexWriter;

    public IndexReader indexReader;
    public IndexSearcher indexSearcher;

    @BeforeTest
    public void setup() throws IOException {

        Directory directory = FSDirectory.open(Paths.get("E:/lucene/userInfo"));
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        indexWriter = new IndexWriter(directory, config);
        indexReader = DirectoryReader.open(directory);
        indexSearcher = new IndexSearcher(indexReader);
    }

    @Test
    public void createIndexForFile() throws IOException {
        String[] name = new String[50];
        String[] content = new String[50];
        String[] email = new String[50];
        for (int i = 0; i < 50; i++) {
            email[i] = RandomUtil.randomNumberString(5) + "@" + RandomUtil.randomString(3) + ".com";
            content[i] = RandomUtil.randomString(15) + " like";
            name[i] = RandomUtil.randomString(5);
        }
        Document document;
        for (int i = 0; i < name.length; i++) {
            document = new Document();
            document.add(new TextField("name", name[i], Field.Store.YES));
            document.add(new TextField("content", content[i], Field.Store.YES));
            document.add(new TextField("email", email[i], Field.Store.YES));
            indexWriter.addDocument(document);
        }
        if (indexWriter != null) {
            indexWriter.close();
        }
    }

    public void getResult(TopDocs topDocs) {
        try {
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            Document document = null;
            int count = 0;
            for (ScoreDoc doc : scoreDocs) {
                document = indexSearcher.doc(doc.doc);

                System.out.println(" content  " + document.get("content"));
                System.out.println(" name   " + document.get("name"));
                System.out.println(" email " + document.get("email"));
                count++;
            }
            System.out.println(" counts number  " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testQuerySearch() throws ParseException, IOException {
        QueryParser parser = new QueryParser("content", new StandardAnalyzer());
        Query query = parser.parse("like");

        TopDocs topDocs = indexSearcher.search(query, 30);
        System.out.println(" 查询到的总条数  " + topDocs.totalHits);
        getResult(topDocs);
    }

    @Test
    public void testSearchAfterDemo() throws ParseException, IOException {
        QueryParser parser = new QueryParser("content", new StandardAnalyzer());
        Query query = parser.parse("like");
        TopDocs topDocs = indexSearcher.search(query, 100);

        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        int last = 2;
        topDocs = indexSearcher.searchAfter(scoreDocs[last], query, 10);
        getResult(topDocs);
        System.out.println("-------------------------------------------------------");
        int end=2;
        topDocs=indexSearcher.searchAfter(scoreDocs[end],query,5);
        getResult(topDocs);
        System.out.println("-------------------------------------------------------");
        int ends=3;
        topDocs=indexSearcher.searchAfter(scoreDocs[ends],query,5);
        getResult(topDocs);
    }

}



