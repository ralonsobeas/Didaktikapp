package com.app.didaktikapp.wordsearch.data.xml;

import android.content.Context;
import android.content.res.AssetManager;


import com.app.didaktikapp.wordsearch.data.WordDataSource;
import com.app.didaktikapp.wordsearch.model.Word;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by abdularis on 31/07/17.
 */

public class WordXmlDataSource implements WordDataSource {

    private static final String ASSET_WORD_BANK_FILE = "words_zumeltzegi.xml";

    private AssetManager mAssetManager;

    @Inject
    public WordXmlDataSource(Context context) {
        mAssetManager = context.getAssets();
    }


    public List<Word> getWords(String palabras) {
        try {
            XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            SaxWordBankHandler wordBankHandler = new SaxWordBankHandler();
            reader.setContentHandler(wordBankHandler);
            reader.parse(getXmlInputSource(palabras));

            return wordBankHandler.getWords();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private InputSource getXmlInputSource(String palabras) throws IOException {
        return new InputSource(mAssetManager.open(palabras));
    }


}
