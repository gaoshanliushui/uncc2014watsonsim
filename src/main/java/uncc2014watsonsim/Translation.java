/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uncc2014watsonsim;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rahul
 */
public class Translation {

    public static String getIndriQuery(String question) {
        String indriResultsFilter = "#filrej( portal.title #filrej( "
                + "template.title #filrej(index.title #filrej( "
                + "list.title #filrej( wikipedia.title #combine(%s))))))";

        question = question.replaceAll("[^0-9a-zA-Z ]+", " ");
        String processed = null;
        try {
            processed = StopFilter.filtered(question);
        } catch (IOException ex) {
            Logger.getLogger(Translation.class.getName()).log(Level.SEVERE, null, ex);
        }
        String words[] = processed.split(" ");
        String query = indriResultsFilter;

        for (String word : words) {
            query = "#filrej(" + word + ".title " + query + " )";
        }
        return query;
    }

    public static String getLuceneQuery(String question) {
        String luceneResultsFilter = " NOT title:*\\:*" + " NOT title:list*"
                + " NOT title:index*";
        question = question.replaceAll("[^0-9a-zA-Z ]+", " ");
        String processed = null;
        try {
            processed = StopFilter.filtered(question);
        } catch (IOException ex) {
            Logger.getLogger(Translation.class.getName()).log(Level.SEVERE, null, ex);
        }
        String words[] = processed.split(" ");

        String query = luceneResultsFilter;

        for (String word : words) {
            query = "NOT title:" + word + " " + query ;
        }
        return query;
    }

    

}