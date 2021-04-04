package net.popocode.learnapp.repositories;

import net.popocode.learnapp.entries.Entry;
import net.popocode.learnapp.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;

@Repository
public class EntryRepositories {

    private List<Entry> entires;

    @Autowired
    EntryRepositories(FileService fileService) {
        try {
            this.entires = fileService.readAllFile();
        } catch (IOException e) {
            entires = new ArrayList<>();
        }
    }
     public List<Entry>getAll(){
        return entires;
    }

     public Set<Entry> getRandomEnties(int number){
        Random random=new Random();
        Set<Entry> randoEntries=new HashSet<>();
        while (randoEntries.size()<number&&randoEntries.size()<entires.size()){
            randoEntries.add(entires.get(random.nextInt(entires.size())));
        }
        return randoEntries;
    }
   public void add(Entry entry) {
        entires.add(entry);
    }

     public int size() {
        return entires.size();
    }

    public boolean isEmpty() {
        return entires.isEmpty();
    }


}
