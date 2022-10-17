package com.it_academy.janna.parsers.stax;

import com.it_academy.janna.entity.Article;
import com.it_academy.janna.entity.Contacts;
import com.it_academy.janna.entity.Hotkeys;
import com.it_academy.janna.entity.Journal;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StaxParserDemo {
    public static void main(String[] args) throws XMLStreamException {
        List<Journal> journalTitleList = null;
        List<Contacts> contactsList = null;
        List<Article> articleList = null;
        List<Hotkeys> hotkeysList = null;
        Journal currJournalTitle = null;
        Contacts currContacts = null;
        Article currArticle = null;
        Hotkeys currHotkeys = null;
        String tagContent = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader =
                factory.createXMLStreamReader(
                        ClassLoader.getSystemResourceAsStream("journal.xml"));

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    if ("journal".equals(reader.getLocalName())){
                        journalTitleList = new ArrayList<>();
                    }
                    if ("title_journal".equals(reader.getLocalName())){
                        currJournalTitle = new Journal();
                        currJournalTitle.setTitle_journal(reader.getAttributeValue(0));
                    }
                    if ("contacts".equals(reader.getLocalName())){
                        contactsList = new ArrayList<>();
                    }
                    if ("address".equals(reader.getLocalName())){
                        currContacts = new Contacts();
                        currContacts.setAddress(reader.getAttributeValue(0));
                    }
                    if ("articles".equals(reader.getLocalName())){
                        articleList = new ArrayList<>();
                    }
                    if ("article".equals(reader.getLocalName())){
                        currArticle = new Article();
                        currArticle.setId(reader.getAttributeValue(0));
                    }
                    if ("hotkeys".equals(reader.getLocalName())){
                        hotkeysList = new ArrayList<>();
                    }
                    if ("hotkey".equals(reader.getLocalName())){
                        currHotkeys = new Hotkeys();
                        currHotkeys.setHotkey(reader.getAttributeValue(0));
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    tagContent = reader.getText().trim();
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    switch(reader.getLocalName()){
                        case "journal"-> journalTitleList.add(currJournalTitle);
                        case "title_journal" -> currJournalTitle.setTitle_journal(tagContent);
                        case "contacts" -> contactsList.add(currContacts);
                        case "address" -> currContacts.setAddress(tagContent);
                        case "tel" -> currContacts.setTel(tagContent);
                        case "email" -> currContacts.setEmail(tagContent);
                        case "url_journal" -> currContacts.setUrl_journal(tagContent);
                        case "articles" -> articleList.add(currArticle);
                        case "ID" -> currArticle.setId(tagContent);
                        case "title" -> currArticle.setTitle(tagContent);
                        case "author" -> currArticle.setAuthor(tagContent);
                        case "url" -> currArticle.setUrl(tagContent);
                        case "hotkeys" -> hotkeysList.add(currHotkeys);
                        case "hotkey" -> currHotkeys.setHotkey(tagContent);
                    }
                    break;

                case XMLStreamConstants.START_DOCUMENT:
                    journalTitleList = new ArrayList<>();
                    contactsList = new ArrayList<>();
                    articleList = new ArrayList<>();
                    hotkeysList = new ArrayList<>();
                    break;

            }
        }

        for (Journal journal : journalTitleList){
            System.out.println(journal);
        }
        for (Contacts contacts : contactsList){
            System.out.println(contacts);
        }
        for (Article article : articleList){
            System.out.println(article);
        }
        for (Hotkeys hotkeys : hotkeysList){
            System.out.println(hotkeys);
        }
    }
}