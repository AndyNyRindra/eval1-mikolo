package com.eval1.util;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class PdfUtil {


    public static Document getXHTMLDocument(File inputHTML) throws IOException {
        Document document = Jsoup.parse(inputHTML, "UTF-8");
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        return document;
    }

    public static String drawImage(String imagePath, String webServerName) throws IOException {
        String fullPath = webServerName + imagePath;
        return String.format("<div><img src=\"%s\" width=\"500\" height=\"auto\"></div>", fullPath);
    }
}
