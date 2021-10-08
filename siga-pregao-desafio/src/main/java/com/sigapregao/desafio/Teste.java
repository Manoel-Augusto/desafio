package com.sigapregao.desafio;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * POST example
 * 
 * @author iampayload
 *
 */
public class Teste {

    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:65.0) Gecko/20100101 Firefox/65.0";

    private final String urlPost = "https://www.huawei.com/en/accounts/PersonalPost";

    // main class
    public static void main(String[] args) throws Exception {
    Teste http = new Teste();
    http.sendPost();
    }

    // HTTP Post request
    private void sendPost() throws Exception {

    Map<String, String> postData = new HashMap<>();
    postData.put("username", "xxxx");
    postData.put("cmd", "login");
    postData.put("password", "yyyyy");

    Document doc = Jsoup.connect(urlPost).ignoreContentType(true).userAgent(USER_AGENT).data(postData).post();
    }
}
