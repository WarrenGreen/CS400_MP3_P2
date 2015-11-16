import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by wsgreen on 11/15/15.
 */
public class BernolliNaiveBayes {
  private Map<String, Category> categories = new HashMap<>();
  private int totalClassRecords = 0;
  private int totalUniqueWords = 0;

  public BernolliNaiveBayes(String trainFile) {

    readTrainFile(trainFile);
    createProbabilities();

  }

  public void displayConfusionMatrix() {
    System.out.print("  |   ");
    for(Category c: categories.values()) {
      System.out.print(c.getCategory() + "  |   ");
    }
    System.out.println();

    for(Category c: categories.values()) {
      String out = c.getCategory()+ " | ";
      for(String c2: categories.keySet()) {
        if(c.categorized.containsKey(c2))
          out += String.format("%04d | ",c.categorized.get(c2));
        else
          out += "0000 | ";
      }
      System.out.println(out);
    }

    System.out.println();
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

      Set<String> words = new HashSet<>();

      for(int i=1;i<line.length;i++) {
        String[] word= line[i].split(":");
        words.add(word[0]);
      }

      for(Category c:categories.values()){
        double value = c.getPrior();
        for(String word: c.getWords()) {
          double p =  c.wordConditionals.get(word);
          if(words.contains(word)) {
            value += Math.log(p);
          }else{
            value += Math.log(1 - p);
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


      double k = 0.1;
      double v = 2.0;
      for(String word: c.getWords()) {
        double p = (c.getWordCount(word)+k) / (c.getCount() + k * v);
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
        words.add(word[0].trim());
        categories.get(line[0]).addWord(word[0].trim(), 1);
      }

    }

    totalUniqueWords = words.size();
  }
}
