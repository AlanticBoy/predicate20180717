package com.predicate.lucene.Synonym.test;

import com.predicate.lucene.Synonym.EnglishSynonym;
import com.predicate.lucene.Synonym.analysizer.SynonymAnalyzer;
import com.predicate.lucene.Synonym.utils.AnalyzerUtils;
import org.apache.lucene.analysis.Analyzer;

import java.io.IOException;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  21:15 2018/5/20
 * @ModefiedBy:
 */
public class SynonmDemo {
    public static void main(String[] args) throws IOException {
        String text = "The quick brown fox jumps over the lazy dog";
        Analyzer analyzer=new SynonymAnalyzer(new EnglishSynonym());
        AnalyzerUtils.displayTokens(analyzer,text);
    }
}
