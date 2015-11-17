import java.util.*;

public class Category {
  private int totalWords = 0;
  private int count;
  private int testCount = 0;
  private Map<String, Integer> wordCount = new HashMap<>();
  private String category;
  public Map<String, Double> wordConditionals = new HashMap<>();

  public Map<String, Integer> categorized = new HashMap<>();
  public int totalCategorized = 0;
  public int wrong = 0;

  private double prior = 0;

  public Category(String cat) {
    category = cat;
    count = 1;
  }

  public List<String> topTwentyWords() {
    List<String> ret = new ArrayList<>();
    PriorityQueue<Map.Entry<String, Double>> queue = new PriorityQueue<>(new Comparator<Map.Entry<String, Double>>() {
      @Override
      public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
        return Double.compare(o2.getValue(), o1.getValue());
      }
    });

    queue.addAll(wordConditionals.entrySet());

    for(int i=0;i<20;i++){
      Map.Entry<String, Double> e = queue.poll();
      ret.add(e.getKey());
    }

    return ret;
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

  public int getTestCount() {
    return testCount;
  }

  public void incrementTestCount() {
    testCount++;
  }

  public Integer getWordCount(String k) {
    if(wordCount.containsKey(k))
      return wordCount.get(k);
    else
      return 0;
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

    if(c.category.compareTo(this.category) != 0)
      wrong++;

    totalCategorized++;
  }

}
