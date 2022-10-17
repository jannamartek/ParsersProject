package com.it_academy.janna.parsers.dom;

import com.it_academy.janna.entity.Article;
import com.it_academy.janna.entity.Contacts;
import com.it_academy.janna.entity.Hotkeys;
import com.it_academy.janna.entity.Journal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMParserDemo {
    private static final String XML_PATH = "journal.xml";
    private static List<Article> articles = new ArrayList<>();
    private static List<Contacts> contacts = new ArrayList<>();
    private static List<Hotkeys> hotkeys = new ArrayList<>();
    private static List<Journal> journals = new ArrayList<>();

    private static Article setArticleWithXMLChildNodeValues(Article article, Node node) {
        String content = node
                .getLastChild()
                .getTextContent()
                .trim();
        switch (node.getNodeName()) {
            case "title" -> article.setTitle(content);
            case "author" -> article.setAuthor(content);
            case "url" -> article.setUrl(content);
            case "hotkey" -> article.setHotkeys(article.getHotkeys());
        }
        return article;
    }

    private static void setArticleWithXMLNodeValues(NodeList nodeList) {
        DOMParserUtils.getNodeListStream(nodeList).forEach(node -> {
            if (node instanceof Element) {
                Article article = new Article();
                article.setId(node.getAttributes().getNamedItem("ID").getNodeValue());

                NodeList childNodes = node.getChildNodes();
                DOMParserUtils.getNodeListStream(childNodes).forEach(childNode -> {
                    if (childNode instanceof Element) {
                        setArticleWithXMLChildNodeValues(article, childNode);
                    }
                });
                articles.add(article);
            }
        });
    }

    private static Contacts setContactsWithXMLChildNodeValues(Contacts contact, Node node) {
        String content = node
                .getLastChild()
                .getTextContent()
                .trim();
        switch (node.getNodeName()) {
            case "address":
                contact.setAddress(content);
            case "tel":
                contact.setTel(content);
            case "email":
                contact.setEmail(content);
            case "url_journal":
                contact.setUrl_journal(content);
                break;
        }
        return contact;
    }

    private static void setContactsWithXMLNodeValues(NodeList nodeList) {
        DOMParserUtils.getNodeListStream(nodeList).forEach(node -> {
            Contacts contact = new Contacts();
            if (node instanceof Element) {
                NodeList childNodes = node.getChildNodes();
                DOMParserUtils.getNodeListStream(childNodes).forEach(childNode -> {
                    if (childNode instanceof Element) {
                        setContactsWithXMLChildNodeValues(contact, childNode);
                    }
                });
                contacts.add(contact);
            }
        });
    }

    private static Hotkeys setHotkeyWithXMLChildNodeValues(Hotkeys hotkey, Node node) {
        String content = node
                .getLastChild()
                .getTextContent()
                .trim();
        switch (node.getNodeName()) {

            case "hotkey":
                hotkey.setHotkey(content);
        }
        return hotkey;
    }

    private static void setHotkeyWithXMLNodeValues(NodeList nodeList) {
        DOMParserUtils.getNodeListStream(nodeList).forEach(node -> {
            Hotkeys hotkey = new Hotkeys();
            if (node instanceof Element) {
                setHotkeyWithXMLChildNodeValues(hotkey, node);
                NodeList childNodes = node.getChildNodes();
                DOMParserUtils.getNodeListStream(childNodes).forEach(childNode -> {
                    if (childNode instanceof Element) {
                        setHotkeyWithXMLChildNodeValues(hotkey, childNode);
                    }
                });
                hotkeys.add(hotkey);
            }
        });
    }

    private static Journal setTitleWithXMLChildNodeValues(Journal journal, Node node) {
        String content = node
                .getLastChild()
                .getTextContent()
                .trim();
        switch (node.getNodeName()) {

            case "title_journal":
                journal.setTitle_journal(content);
        }
        return journal;
    }

    private static void setTitleWithXMLNodeValues(NodeList nodeList) {
        DOMParserUtils.getNodeListStream(nodeList).forEach(node -> {
            Journal journal = new Journal();
            if (node instanceof Element) {
                setTitleWithXMLChildNodeValues(journal, node);
                NodeList childNodes = node.getChildNodes();
                DOMParserUtils.getNodeListStream(childNodes).forEach(childNode -> {
                    if (childNode instanceof Element) {
                        setTitleWithXMLChildNodeValues(journal, childNode);
                    }
                });
                journals.add(journal);
            }
        });
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        Document document = DOMParserUtils.parseXMLDocument(XML_PATH);
        NodeList nodeJournalTitle = document.getElementsByTagName("title_journal");
        setTitleWithXMLNodeValues(nodeJournalTitle);
        System.out.println(journals.toString());
        NodeList nodeContacts = document.getElementsByTagName("contacts");
        setContactsWithXMLNodeValues(nodeContacts);
        System.out.println(contacts.toString());
        NodeList nodeArticles = document.getElementsByTagName("article");
        setArticleWithXMLNodeValues(nodeArticles);
        System.out.println(articles.toString());
        NodeList nodeHotkeys = document.getElementsByTagName("hotkey");
        setHotkeyWithXMLNodeValues(nodeHotkeys);
        System.out.println(hotkeys.toString());
    }
}