package com.it_academy.janna.entity;

import java.util.Objects;

public class Contacts {
    private String address;
    private String tel;
    private String email;
    private String url_journal;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl_journal() {
        return url_journal;
    }

    public void setUrl_journal(String url_journal) {
        this.url_journal = url_journal;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", journal_url='" + url_journal + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacts contacts = (Contacts) o;
        return Objects.equals(address, contacts.address) && Objects.equals(tel, contacts.tel) && Objects.equals(email, contacts.email) && Objects.equals(url_journal, contacts.url_journal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, tel, email, url_journal);
    }
}
