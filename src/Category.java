import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Category {
  private int totalWords = 0;
  private int count;
  private Map<String, Integer> wordCount = new HashMap<>();
  private String category;
  public Map<String, Double> wordConditionals = new HashMap<>();

  public Map<String, Integer> categorized = new HashMap<>();

  private double prior = 0;

  public Category(String cat) {
    category = cat;
    count = 1;
  }

  public void addWord(String word, int count) {
    if(wordCount.containsKey(word)) {
      wordCount.put(word, wordCount.get(word)+count);
    }else{
      wordCount.put(word, count);
    }

    totalWords += count;

  }


  public int getCount() {
    return count;
  }

  public void incrementCount() {
    count++;
  }

  public Integer getWordCount(String k) {
    return wordCount.get(k);
  }

  public Collection<String> getWords() {
    return wordCount.keySet();
  }

  public boolean containsWord(String k) {
    return wordCount.containsKey(k);
  }

  public int getTotalWords() {
    return totalWords;
  }

  public String getCategory() {
    return category;
  }

  public double getPrior() {
    return prior;
  }

  public void setPrior(double prior) {
    this.prior = prior;
  }


  public void addCategorized(Category c) {
    if(categorized.containsKey(c.category)){
      categorized.put(c.category, categorized.get(c.category)+1);
    }else
      categorized.put(c.category, 1);
  }

}
