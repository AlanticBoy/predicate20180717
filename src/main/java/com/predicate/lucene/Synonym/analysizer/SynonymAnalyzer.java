package com.predicate.lucene.Synonym.analysizer;

import com.predicate.lucene.Synonym.EnglishSynonym;
import com.predicate.lucene.Synonym.filter.SynonymFilter;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  20:55 2018/5/20
 * @ModefiedBy:
 */
public class SynonymAnalyzer extends Analyzer {

    private EnglishSynonym engine;

    public SynonymAnalyzer(EnglishSynonym engine) {
        this.engine = engine;
    }

    @Override
    protected TokenStreamComponents createComponents(String s) {
        Tokenizer tokenizer = new StandardTokenizer();
        TokenStream tokenStream = new SynonymFilter(tokenizer, engine);
        tokenStream = new LowerCaseFilter(tokenStream);
        tokenStream = new StopFilter(tokenStream, StopAnalyzer.ENGLISH_STOP_WORDS_SET);

        return new TokenStreamComponents(tokenizer, tokenStream);
    }
}
