package com.github.mzule.androidweekly.api.parser;

import java.io.IOException;
import java.util.List;

/**
 * Created by CaoDongping on 4/15/16.
 */
public interface ArticleParser {
    List<Object> parse(String issue) throws IOException;
}
