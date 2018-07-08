package com.predicate.lucene.Synonym;

import java.io.IOException;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  20:36 2018/5/20
 * @ModefiedBy:
 */
/*同义词列表接口*/
public interface SynonymEngine {
    String[] getSynonyms(String s) throws IOException;
}
