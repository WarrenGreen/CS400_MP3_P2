import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by wsgreen on 11/15/15.
 */
public class MultinomialNaiveBayes {
  private Map<String, Category> categories = new HashMap<>();
  private int totalClassRecords = 0;
  private int totalUniqueWords = 0;

  public MultinomialNaiveBayes(String trainFile) {

    readTrainFile(trainFile);
    createProbabilities();

  }

  public void displayConfusionMatrix() {
    int total = 0;
    int wrong = 0;
    System.out.print("  |   ");
    for(Category c: categories.values()) {
      System.out.print(c.getCategory() + "  |   ");
      total += c.totalCategorized;
      wrong += c.wrong;
    }
    System.out.println();

    for(Category c: categories.values()) {
      String out = c.getCategory()+ " | ";
      for(String c2: categories.keySet()) {
        if(c.categorized.containsKey(c2))
          out += String.format("%.2f | ",c.categorized.get(c2)/(c.totalCategorized*1.0));
        else
          out += "0000 | ";
      }
      System.out.println(out);
    }

    System.out.println("Accuracy: " + Math.round(100 * (total - wrong * 1.0) / total) + "%");

    System.out.println();
  }

  public void printTopTwenties() {
    for(Category c: categories.values()) {
      System.out.println("Class: "+c.getCategory());
      List<String> twenty = c.topTwentyWords();

      for(int i=0;i<twenty.size();i++) {
        System.out.println(i+": "+twenty.get(i));
      }

      System.out.println();
    }
  }

  public void testModel(String testFile) {
    Scanner in = null;
    try {
      in = new Scanner(new File(testFile));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.exit(-1);
    }

    String[] line = null;
    while(in.hasNextLine()) {
      line = in.nextLine().split(" ");
      Category trueCat = categories.get(line[0]);
      double max = -Double.MAX_VALUE;
      Category maxCat = null;

      for(Category c:categories.values()){
        double value = c.getPrior();
        for(int i=1;i<line.length;i++) {
          String[] word= line[i].split(":");
          if(c.containsWord(word[0])){
            value *= Math.pow(c.wordConditionals.get(word[0]), Integer.parseInt(word[1]));
          }
        }

        if(max < value) {
          max = value;
          maxCat = c;
        }
      }

      if(maxCat != null)
        trueCat.addCategorized(maxCat);


    }
  }

  private void createProbabilities() {

    //Priors
    for(Category c: categories.values()) {
      c.setPrior((c.getCount()*1.0)/ totalClassRecords);


      double denom =( c.getTotalWords() *1.0)/ totalUniqueWords;
      for(String word: c.getWords()) {
        double p =(c.getWordCount(word)+1.0) / denom;
        c.wordConditionals.put(word, p);
      }
    }

  }

  private void readTrainFile(String trainFile) {
    Set<String> words= new HashSet<>();
    Scanner in = null;
    try {
      in = new Scanner(new File(trainFile));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.exit(-1);
    }

    String[] line = null;
    while(in.hasNextLine()) {
      totalClassRecords++;
      line = in.nextLine().split(" ");

      if(!categories.containsKey(line[0])) {
        categories.put(line[0], new Category(line[0]));
      }else {
        categories.get(line[0]).incrementCount();
      }

      for(int i=1;i<line.length;i++) {
        String[] word =  line[i].split(":");
        words.add(word[0]);
        categories.get(line[0]).addWord(word[0], Integer.parseInt(word[1]));
      }

    }

    totalUniqueWords = words.size();
  }
}
