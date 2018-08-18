package edu.translator;


import java.util.Arrays;
import java.util.Random;

public abstract class HashingAlgorithm {

    protected int TABLE_SIZE = 256;
    protected int MAX_WORD_LENGTH = 30;

    protected String[] hashTable;

    protected Random randomizer;

    public HashingAlgorithm(){
        hashTable = new String [TABLE_SIZE];
        Arrays.fill(hashTable, "$");
        randomizer = new Random();
    }

    protected String generateWord(){
        int wordLength = 1 + randomizer.nextInt(MAX_WORD_LENGTH + 1);
        String word = "";
        for(int i = 0; i < wordLength; i++){
            word += (char) (97 + randomizer.nextInt(26));
        }

        return word;
    }

    protected int toHash(String word){
        int hashCode = 0;

        byte[] bytes = word.getBytes();

        for(int i = 0; i < bytes.length; i++){
            hashCode += bytes[i];
        }

        return hashCode % TABLE_SIZE;
    }

    protected long measureTableFilling(){
        int emptyCount = 0;
        for (String cell:
                hashTable) {
            if(cell.equals("$")){
                emptyCount++;
            }
        }
        return Math.round(emptyCount*100.0/TABLE_SIZE);
    }

    protected abstract boolean tryToWrite(String word, int attempt);

    protected abstract boolean tryToWriteMock(String word, int attempt);

    protected abstract void fillInPercentage(int percentage);

    protected abstract double fillInMock();

    public void test(){

        System.out.println("Size of the hash table: " + TABLE_SIZE);
        System.out.println("Maximal length of a generated word: " + MAX_WORD_LENGTH);

        System.out.println("For the table fill of 10%");
        fillInPercentage(10);
        System.out.println(fillInMock());

        System.out.println("For the table fill of 50%");
        fillInPercentage(50);
        System.out.println(fillInMock());

        System.out.println("For the table fill of 90%");
        fillInPercentage(90);
        System.out.println(fillInMock());

        System.out.println();
    }
}
