package com.it_academy.janna.entity;

import java.util.Objects;

public class Hotkeys {
    private String hotkey;

    public String getHotkey() {
        return hotkey;
    }

    public void setHotkey(String hotkey) {
        this.hotkey = hotkey;
    }

    @Override
    public String toString() {
        return "Hotkeys{" +
                "hotkey='" + hotkey + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotkeys hotkeys = (Hotkeys) o;
        return Objects.equals(hotkey, hotkeys.hotkey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotkey);
    }
}
