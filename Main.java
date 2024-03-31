package ControlWorkSpec;

import java.io.*;
import java.util.*;

/*
1. Осуществить подсчет слов:
Напишите программу, которая подсчитывает количество слов в
файле `input.txt`.
2. Найти самое длинное слово:
Создайте программу, которая находит самое длинное слово в
файле.
3. Вычислить частоту слов:
Напишите программу, которая анализирует, сколько раз каждое
слово встречается в файле. Подумайте об этом как о подсчете того,
какие фрукты и овощи самые популярные на вашем пикнике!


 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException{
        String path = "C:\\Users\\sko88\\OneDrive\\Рабочий стол\\GB\\SPEC\\JAVA\\src\\ControlWorkSpec\\input.txt";
        String str = readFile(path);
        int countWord = countWords(str);
        System.out.println("1. Количество слов в файле - " + countWord + "\n\n");

        ArrayList<String> longWord = longestWord(str);

        if (longWord.size() == 1) System.out.println("Саммое длинное слово в файле - " + longWord.get((0)) +
                ". Его длина - " + longWord.get(0).length());
        else {
            System.out.println("Есть "+ longWord.size() + " слов максимальной длины " + longWord.get(0).length() +
                    " букв. Вот эти слова:");
            for (int i = 0; i < longWord.size(); i++) {
                System.out.println(longWord.get(i));
            }
        }
        System.out.println("\nЧастота слов в файле:");
        System.out.println(freqWord(str));
        System.out.println("\nСамые популярные продукты на пикнике:");
        Set<String> popular = popularWord((freqWord(str)));
        System.out.println(popular);
        int popularCount = freqWord(str).get(popular.iterator().next());
        System.out.println("Он встречается " + popularCount + " раз!");

    }

    public static String readFile(String path){
        String str = null;
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            str = scanner.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return str;
    }

    public static int countWords(String str){
        String[] wordList = str.split(" ");
        return wordList.length;
    }

    public static ArrayList<String> longestWord(String str){
        String[] wordList = str.split(" ");
        int len = wordList[0].length();
        ArrayList<String> longest = new ArrayList<>();
        for (int i = 1; i < wordList.length; i++) {
            if (wordList[i].length() > len) len = wordList[i].length();
        }
        for (String word: wordList){
            if (word.length() == len) longest.add(word);
        }
        return longest;
    }

    public static Map<String,Integer> freqWord(String str){
        String[] wordList = str.split(" ");
        Map<String,Integer> mapWords = new HashMap<>();
        int key;
        for (String word: wordList){
            if (!mapWords.containsKey(word)) mapWords.put(word, 1);
            else {
                key = mapWords.get(word);
                mapWords.replace(word,key,key+1);
            }
        }
        return mapWords;
    }

    public static Set<String> popularWord(Map<String,Integer> mapWord){
        int max = 0;
        Set<String> popular = new HashSet<>();
        for (int count: mapWord.values()){
            if (count > max) max = count;
        }
//        for (int count: mapWord.values()){
//            if (count == max)
        popular = getKeys(mapWord, max);
        return popular;
    }

    public static Set<String> getKeys(Map<String, Integer> map, int value) {
        Set<String> keys = new HashSet<>();
        for (String key: map.keySet()){
            if (value == map.get(key)) {
                keys.add(key);
            }
        }
        return keys;
    }
}




