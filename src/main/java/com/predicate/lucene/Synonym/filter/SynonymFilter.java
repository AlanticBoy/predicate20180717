package com.predicate.lucene.Synonym.filter;

import com.predicate.lucene.Synonym.EnglishSynonym;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.AttributeSource;

import java.io.IOException;
import java.util.Stack;

/**
 * @Author:付风松
 * @Description:自定义同义词过滤器
 * @Date:Created in  20:39 2018/5/20
 * @ModefiedBy:
 */
public class SynonymFilter extends TokenFilter {

    public static final String TOKEN_TYPE_SYNONYM = "SYNONYM";
    private Stack<String> stack;
    private EnglishSynonym synonym;
    /*添加是否有同义词的判断变量属性，保存当前元素的状态信息*/
    private AttributeSource.State current;
    /*存储分词数据*/
    private CharTermAttribute cta = null;
    /*存储语汇单元的位置信息*/
    private PositionIncrementAttribute pia = null;

    public SynonymFilter(TokenStream input, EnglishSynonym synonym) {
        super(input);
        stack = new Stack<>();
        this.synonym = synonym;
        this.cta = addAttribute(CharTermAttribute.class);
        this.pia = addAttribute(PositionIncrementAttribute.class);
    }


    @Override
    public boolean incrementToken() throws IOException {
        if (stack.size() > 0) {
            String syn = stack.pop();
            restoreState(current);
            cta.copyBuffer(syn.toCharArray(), 0, syn.length());
            pia.setPositionIncrement(0);
            return true;
        }
        if (!this.input.incrementToken()) {
            return false;
        }
        if (addAliasesToStack()) {
            current = captureState();
        }
        return false;
    }

    private boolean addAliasesToStack() throws IOException {
        String[] synonyms = synonym.getSynonyms(cta.toString());
        if (synonyms == null)
            return false;
        for (String s : synonyms) {
            stack.push(s);
        }
        return true;
    }
}
