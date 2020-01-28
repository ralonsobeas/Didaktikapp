package com.app.didaktikapp.wordsearch.data;

import com.app.didaktikapp.wordsearch.model.Word;

import java.util.List;

/**
 * Created by abdularis on 18/07/17.
 */

public interface WordDataSource {

    List<Word> getWords(String palabras);

}
