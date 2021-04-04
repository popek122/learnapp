package net.popocode.learnapp.controllers;

import net.popocode.learnapp.entries.Entry;
import net.popocode.learnapp.repositories.EntryRepositories;
import net.popocode.learnapp.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

@Controller
public class LearnController {
    private static final int UNDEFINED=-1;
    private static final int ADD_ENTRY=0;
    private static final int TEST=1;

    private static final int CLOSE_APP=3;

    private EntryRepositories entryRepositories;
    private FileService fileService;
    private Scanner scanner;
    @Autowired
    LearnController(EntryRepositories entryRepositories, FileService fileService, Scanner scanner) {
        this.entryRepositories = entryRepositories;
        this.fileService = fileService;
        this.scanner = scanner;
    }
    public void mainLoop(){
        System.out.println("Witaj w aplikacji LearnApp");
        int option=UNDEFINED;
        while (option!=CLOSE_APP){
            printMenu();
            option=choseOption();
            executeOption(option);
        }
    }

    private int choseOption() {
        int option;
        try {
            option=scanner.nextInt();
        }catch (InputMismatchException e){
            option=UNDEFINED;
        }finally {
            scanner.nextLine();
        }
        if (option>UNDEFINED&&option<=CLOSE_APP){
            return option;
        }else return UNDEFINED;
    }

    private void executeOption(int option) {
        switch (option) {
            case ADD_ENTRY:
                addEntry();
                break;
            case TEST:
                test();
                break;
            case CLOSE_APP:
                close();
                break;
            default:
                System.out.println("Opcja Niezdefiniowana");
        }
    }

    private void addEntry() {
        System.out.println("Podaj orginalna fraze");
        String original=scanner.nextLine();
        System.out.println("Podaj tlumaczenie");
        String translation=scanner.nextLine();
        Entry entry=new Entry(original,translation);
        entryRepositories.add(entry);
    }
    private void close(){
        try {
            fileService.saveEntries(entryRepositories.getAll());
            System.out.println("Zapisano stan aplikacji");
        }catch (IOException e){
            System.out.println("Nie udalo Sie zapisac stanu Aplikacji");
        }
        System.out.println("Bye Bye");
    }

    private void printMenu(){
        System.out.println("Wybierz opcje:");
        System.out.println("0 - Dodaj Slowo".toUpperCase());
        System.out.println("1 - TEST");
        System.out.println("2 - NAUKA SLOWEK");
        System.out.println("3 - Koniec Programu".toUpperCase());
    }

    private void test() {
        if(entryRepositories.isEmpty()) {
            System.out.println("Dodaj przynajmniej jedną frazę do bazy.");
            return;
        }
        final int testSize = entryRepositories.size() > 10? 10 : entryRepositories.size();
        Set<Entry> randomEntries = entryRepositories.getRandomEnties(testSize);
        int score = 0;
        for (Entry entry : randomEntries) {
            System.out.printf("Podaj tłumaczenie dla :\"%s\"\n", entry.getOriginal());
            String translation = scanner.nextLine();
            if(entry.getTranslation().equalsIgnoreCase(translation)) {
                System.out.println("Odpowiedź poprawna");
                score++;
            } else {
                System.out.println("Odpowiedź niepoprawna - " + entry.getTranslation());
            }
        }
        System.out.printf("Twój wynik: %d/%d\n", score, testSize);
    }
}
