package com.predicate.lucene.demo;


import com.predicate.lucene.POJO.Article;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  16:09 2018/5/7
 * @ModefiedBy:
 */
public class Lucene_CRUD_Demo {

    /**
     * 　　* @Description: 创建索引,非文件
     * 　　* @param ${tags}
     * 　　* @return ${return_type}
     * 　　* @throws
     * 　　* @author 付风松
     * 　　* @date 2018/5/7 16:09
     */
    public void createIndexNotFile() {
        Directory directory = null;
        IndexWriterConfig indexWriterConfig = null;
        IndexWriter indexWriter = null;
        Document document = null;

        Article article = new Article();
        article.setId("1");
        article.setTitle("Hello,index");
        article.setContent("Solr是基于Lucene框架的搜索莹莹程序，是一个开放源代码的全文检索引擎。");
        try {
        /*第一步，创建directory对象。创建directory对象有两种方式，一个是在内存中的，一个是在硬盘上的.
        下面我们用的是创建在硬盘上的索引。其中Path.get(localPath),localPath是索引存放的路径
        * */
            directory = FSDirectory.open(Paths.get("E:/lucene/example"));
            /*第二部创建IndexWriterConfig*/
            indexWriterConfig = new IndexWriterConfig(new StandardAnalyzer());
            //indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.APPEND);
            indexWriter = new IndexWriter(directory, indexWriterConfig);
           /*第三部，为Document对象添加Field*/
            document = new Document();
            document.add(new TextField("content", article.getContent(), Field.Store.YES));
            document.add(new TextField("title", article.getTitle(), Field.Store.YES));
            document.add(new TextField("id", article.getId(), Field.Store.YES));
               /*第四步，通过IndexWriter把这些文档添加到索引*/
            indexWriter.addDocument(document);

            /*}*/
            if (indexWriter != null) {
                indexWriter.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 　　* @Description: 给文件创建索引
     * 　　* @param ${tags}
     * 　　* @return ${return_type}
     * 　　* @throws
     * 　　* @author 付风松
     * 　　* @date 2018/5/7 21:10
     */
    public void createIndexForFile() {
        Directory directory = null;
        Analyzer analyzer = null;
        IndexWriterConfig config = null;
        IndexWriter indexWriter = null;
        Document document = null;
        try {
            /*第一步，使用FSDirectory创建硬盘路径,其中E:/lucene/instance是索引的存放的位置*/
            directory = FSDirectory.open(Paths.get("E:/lucene/instance"));
            /*第二步，创建分词器*/
            analyzer = new StandardAnalyzer();
            /*第三步，创建IndexWriterConfig对象*/
            config = new IndexWriterConfig(analyzer);
            /*如果是第一次为某个东西创建索引，那么就应该选择IndexWriterConfig.OpenMode.CREATE模式*/
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            /*第四步，创建indexWriter对象，顾名思义，它的作用是用来写索引*/
            indexWriter = new IndexWriter(directory, config);
            File file = new File("E:/lucene/article");
            File[] fileList = file.listFiles();
            for (File file1 : fileList) {
                /*创建Document对象*/
                document = new Document();
                /*对于Field的介绍
                * Field是Lucene里的域。一个Document文档可以包含多个域。可以类比数据库的多个字段。
                * 里面有三个参数，第一个参数是Field的名字，第二个参数是value，第三个参数是选择是否存储.
                * Field的类型有以下几种：
                * IntField，LongField，FloatField，StringField，TextField
                * StringField和TextField的区别是：
                * StringField只索引不分词，TextField既索引又分词
                * */
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file1.getAbsolutePath()));//.txt文档,不设置格式会乱码
                BufferedReader bufferedReader = new BufferedReader(isr);
                String content = "";
                String savedContent = "";
                while ((content = bufferedReader.readLine()) != null) {
                    savedContent += content;
                }
                System.out.println("  " + savedContent);
                /*Field.Store.NO
                * Field.Store.Yes表示把这个域中的内容存储到文件。方便进行文本的还原
                * Field.Store.No，表示不把这个域中的内容存储到文件，但是可以被索引，此时内容无法被还原
                * 一般而言，文章内容是不会存储到文件的。像浏览器，索引时只保存文章id，再从数据库查找文章内容
                * */
                document.add(new TextField("content", savedContent, Field.Store.YES));
                document.add(new TextField("fileName", file1.getName(), Field.Store.YES));
                System.out.println(" get fileName  " + file1.getName());
                document.add(new TextField("get  path", file1.getAbsolutePath(), Field.Store.YES));
                System.out.println("  path " + file1.getAbsolutePath());
                indexWriter.addDocument(document);
            }
            if (indexWriter != null) {
                indexWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*查询索引*/
    public List<String> SearchLists() {
        List<String> stringList = new ArrayList<>();
        try {
            Directory directory = FSDirectory.open(Paths.get("E:/lucene/instance"));
            Analyzer analyzer = new StandardAnalyzer();//分析器
            //单条件
            //关键词解析
            //QueryParser queryParser=new QueryParser("content",analyzer);
            //Query query=queryParser.parse(queryString);

            /*演示多条件查询*/
            /*它的意思是从内容content里查询含有better字段的词*/
            Query query = MultiFieldQueryParser.parse(new String[]{"better"}, new String[]{"content"}, analyzer);
            IndexReader indexReader = DirectoryReader.open(directory);//索引阅读器
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);//查询
            /*indexReader.maxDoc();表示inedxReader能搜索到的最多的词条数。包括回收站内的。
            * Lucene也有回收站机制，以防删错词条
            * indexReader.numDocs()表示当前能搜索到的词条，不包括回收站的
            * */
            System.out.println("  indexReader.maxDoc();  " + indexReader.maxDoc());
            System.out.println("  indexReader.numDocs(); " + indexReader.numDocs());
            // TopDocs 指向相匹配的搜索条件的前N个搜索结果。它是指针的简单容器指向它们的搜索结果输出的文档。
            TopDocs topDocs = indexSearcher.search(query, 3);
            /*命中的查询的总数*/
            long count = topDocs.totalHits;
            stringList.add(String.valueOf(count));
            /*ScoreDoc[] scoreDocs -- 排名靠前的查询。*/
            ScoreDoc[] scoreDoc = topDocs.scoreDocs;
            for (ScoreDoc doc : scoreDoc) {
                /*doc.doc,获取索引的标志位id*/
                Document document = indexSearcher.doc(doc.doc);
                stringList.add(document.get("content"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringList;
    }

    /**
     * 　　* @Description: 测试删除 在合并前
     * 使用deleteDocuments删除后实际文档数减一，其实就是做了个标记未真正
     * 删除，最大文档数未变
     * 　　* @param ${tags}
     * 　　* @return ${return_type}
     * 　　* @throws
     * 　　* @author 付风松
     * 　　* @date 2018/5/9 18:29
     */
    public void deleteIndex() {
        try {
            Directory directory = FSDirectory.open(Paths.get("E:/lucene/instance"));
            IndexWriterConfig writerConfig = new IndexWriterConfig(new StandardAnalyzer());
            IndexWriter indexWriter = new IndexWriter(directory, writerConfig);
            /*new Term()
            * Term 这个类是搜索的最低单位。它是在索引过程中类似Field。建立搜索单元
            * */
            /*此时，文档并不会被完全删除，而是会放在回收站内*/
            indexWriter.deleteDocuments(new Term("fileName", "father.txt"));
            indexWriter.commit();
            /*indexWriter.maxDoc()，最大能搜索到的词条数，包括回收站内的*/
            System.out.println("  indexWriter.maxDoc()   " + indexWriter.maxDoc());
            /*实际能搜索到的词条数，不包括回收站的*/
            System.out.println("   indexWriter.numDocs()  " + indexWriter.numDocs());
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*使用forceMergeDeletes()进行真正意义的删除
    *但是索引量大时不建议使用
    * 删除后实际文档数减一，最大文档数减一
    * */
    public void deleteAndMerge() {
        try {
            Directory directory = FSDirectory.open(Paths.get("E:/lucene/instance"));
            IndexWriterConfig writerConfig = new IndexWriterConfig(new StandardAnalyzer());
            IndexWriter indexWriter = new IndexWriter(directory, writerConfig);
            /*new Term()
            * Term 这个类是搜索的最低单位。它是在索引过程中类似Field。建立搜索单元
            * */
            /*此时，文档并不会被完全删除，而是会放在回收站内*/
            indexWriter.deleteDocuments(new Term("fileName", "father.txt"));
            indexWriter.forceMergeDeletes();//强制合并删除的索引信息，索引量大的时候不推荐使用，真正的删除
            indexWriter.commit();
            /*indexWriter.maxDoc()，最大能搜索到的词条数，包括回收站内的*/
            System.out.println("  indexWriter.maxDoc()   " + indexWriter.maxDoc());
            /*实际能搜索到的词条数，不包括回收站的*/
            System.out.println("   indexWriter.numDocs()  " + indexWriter.numDocs());
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateIndex() {
        try {
            Directory directory=FSDirectory.open(Paths.get("E:/lucene/instance"));
            IndexWriterConfig config=new IndexWriterConfig(new StandardAnalyzer());
            IndexWriter writer=new IndexWriter(directory,config);
            Document document=new Document();
            document.add(new TextField("fileName","father.txt",Field.Store.YES));
            document.add(new TextField("content","import org.apache.lucene.analysis.Analyzer;\n" +
                    "import org.apache.lucene.analysis.standard.StandardAnalyzer;\n" +
                    "import org.apache.lucene.document.Document;\n" +
                    "import org.apache.lucene.document.Field;\n" +
                    "import org.apache.lucene.document.TextField;\n" +
                    "import org.apache.lucene.index.IndexWriter;\n" +
                    "import org.apache.lucene.index.IndexWriterConfig;\n" +
                    "import org.apache.lucene.store.Directory;\n" +
                    "import org.apache.lucene.store.FSDirectory;",Field.Store.NO));
            document.add(new TextField("get  path","E:/lucene/instance/father.txt",Field.Store.YES));
            writer.updateDocument(new Term("fileName","father.txt"),document);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
