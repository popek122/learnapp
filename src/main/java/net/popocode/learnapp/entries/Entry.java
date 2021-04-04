package net.popocode.learnapp.entries;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

public class Entry {
    public String original;
    private String translation;

    public Entry(String original,String translation){
        this.original=original;
        this.translation=translation;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
    @Override
    public String toString(){
        return original+";"+translation;
    }
}
