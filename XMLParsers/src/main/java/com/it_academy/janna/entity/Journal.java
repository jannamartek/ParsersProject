package com.it_academy.janna.entity;

import java.util.List;
import java.util.Objects;

public class Journal {
    private String title_journal;
    private Contacts contacts;
    private List<Article> articles;

    public String getTitle_journal() {
        return title_journal;
    }

    public void setTitle_journal(String title_journal) {
        this.title_journal = title_journal;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "journal_title='" + title_journal + '\'' +
                ", contacts=" + contacts +
                ", articles=" + articles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journal journal = (Journal) o;
        return Objects.equals(title_journal, journal.title_journal) && Objects.equals(contacts, journal.contacts) && Objects.equals(articles, journal.articles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title_journal, contacts, articles);
    }
}
