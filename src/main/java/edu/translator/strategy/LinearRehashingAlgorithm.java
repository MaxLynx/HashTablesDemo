package edu.translator.strategy;


public class LinearRehashingAlgorithm extends HashingAlgorithm {

    public LinearRehashingAlgorithm(){
        super();
    }

    @Override
    protected boolean tryToWrite(String word, int attempt){
        int hashCode = (toHash(word) + attempt) % TABLE_SIZE;
        if(hashTable[hashCode].equals(TABLE_FILLER)){
            hashTable[hashCode] = word;
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    protected boolean tryToWriteMock(String word, int attempt){
        int hashCode = (toHash(word) + attempt) % TABLE_SIZE;
        return hashTable[hashCode].equals(TABLE_FILLER);
    }


    @Override
    protected void fillInPercentage(int percentage){
        while(measureTableFilling() > 100 - percentage) {

            int attempt = 0;
            String word = generateWord();
            while(!tryToWrite(word, attempt)) {
                attempt++;
            }

        }
    }

    @Override
    protected double fillInMock(){
        int wordCount = 0;
        int tryCount = 0;

        while(wordCount < 100) {

            wordCount++;
            int attempt = 0;
            String word = generateWord();
            while(!tryToWriteMock(word, attempt)) {
                attempt++;
            }
            tryCount += attempt + 1;

        }

        return tryCount*1.0/wordCount;
    }


    @Override
    public void test(){

        System.out.println(propertiesResourceBundle.getString("linear.rehashing.method.name"));

        super.test();
    }
}
