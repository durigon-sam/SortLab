import java.util.*;

public class Assig4
{
  public static Random r = new Random();

  //Sorter Data will be an ArrayList of Sorter<Integer> objects
  private ArrayList<Sorter<Integer>> sorts;
  //Data to sort will be an array of Integer
  private Integer [] A;
  private int size, runs;
  private boolean sortCheck;
  private String s;
  private double simpleBest, simpleWorst, medBest, medWorst, randomBest,
    randomWorst, mergeBest, mergeWorst, best, worst, average, sec;
  private double oBest, oWorst;
  private int simpleBestMin, simpleWorstMin, medBestMin, medWorstMin,
    randomBestMin, randomWorstMin, mergeBestMin, mergeWorstMin, oBestMin,
    oWorstMin, bestMin, worstMin;
  private long start, stop, diff;
  private String bestType, worstType;
  //Fill array with random data
  public void fillArray()
  {
    if (sortCheck) //If data is sorted
    {
      for (int i = 0; i < A.length; i++)
        A[i] = i;
    }else{ //If data is randomized
      for (int i = 0; i < A.length; i++)
        A[i] = new Integer(r.nextInt(1000000000));
    }

  }

  public Assig4(String [] a)
  {
    size = Integer.parseInt(a[0]);
    runs = Integer.parseInt(a[1]);
    sortCheck = Boolean.parseBoolean(a[2]);
    System.out.println("Initializing Information: ");
    System.out.println("  Array Size: " + size);
    System.out.println("  Number of Runs per test: " + runs);
    if (sortCheck)
      System.out.println("  Initial data: Sorted");
    else
      System.out.println("  Initial data: Random");
    // Put the sorting objects into the ArrayList.  Note how each object is being
		// created.  The QuickSort<T> objects are passed implementations of the
		// Partitionable<T> interface in order to allow the 3 different ways of
		// partitioning the data.
		sorts = new ArrayList<Sorter<Integer>>();
		sorts.add(new QuickSort<Integer>(new SimplePivot<Integer>()));
		sorts.add(new QuickSort<Integer>(new MedOfThree<Integer>()));
		sorts.add(new QuickSort<Integer>(new RandomPivot<Integer>()));
		sorts.add(new MergeSort<Integer>());

    A = new Integer[size];

    for (int i = 0; i < sorts.size(); i++) //Iterates through algorithms
    {
      worst = 0;//Reset best and worst variables
      best = 9999999;
      worstMin = 0;
      bestMin = 0;

      for (int j = 5; j <= 75; j += 10) //Iterates through minRecurse values
      {
        r.setSeed(42069101); //Resets seed so we have same numbers
        sorts.get(i).setMin(j);
        average = 0;

        for (int k = 0; k < runs; k++) //Iterates through runs
        {

          fillArray();
          start = System.nanoTime();
          sorts.get(i).sort(A, A.length); //timing and sorting
          stop = System.nanoTime();
          diff = stop-start;
          sec = (double) diff / 1000000000;
          average += sec; // adds all run times to an average
        }

        average = average/runs; //Calculates sample average

        //Checks if new Average is better or worse than our current best and
        // worst varaibles and changes those if need be.
        if (average < best)
        {
          best = average;
          bestMin = j;
        }
        if (average > worst)
        {
          worst = average;
          worstMin = j;
        }
      }

      //Welcome to the neverending wall of logic gates and print statements.
      // This records best and worsts for simplePivot, and sets those equal
      // to our first overall best and worst variables. For each else if after,
      // it will compare the new best and worst to the current overall best and
      // worst to see if there is a new one
      if (i==0)
      {
        simpleBest = best;
        simpleBestMin = bestMin;
        simpleWorst = worst;
        simpleWorstMin = worstMin;
        oBest = simpleBest;
        bestType = "Simple Pivot Quicksort";
        oBestMin = simpleBestMin;
        oWorst = simpleWorst;
        worstType = "Simple Pivot Quicksort";
        oWorstMin = simpleWorstMin;

      }else if (i==1){
        medBest = best;
        medBestMin = bestMin;
        medWorst = worst;
        medWorstMin = worstMin;
        if (medBest < oBest)
        {
          oBest = medBest;
          bestType = "Median of Three Quicksort";
          oBestMin = medBestMin;
        }
        if (medWorst > oWorst){
          oWorst = medWorst;
          worstType = "Median of Three Quicksort";
          oWorstMin = medWorstMin;
        }
      }else if (i==2){
        randomBest = best;
        randomBestMin = bestMin;
        randomWorst = worst;
        randomWorstMin = worstMin;
        if (randomBest < oBest)
        {
          oBest = randomBest;
          bestType = "Random Pivot QuickSort";
          oBestMin = randomBestMin;
        }
        if (randomWorst > oWorst){
          oWorst = randomWorst;
          worstType = "Random Pivot Quicksort";
          oWorstMin = randomWorstMin;
        }
      }else if (i==3){
        mergeBest = best;
        mergeBestMin = bestMin;
        mergeWorst = worst;
        mergeWorstMin = worstMin;
        if (mergeBest < oBest)
        {
          oBest = mergeBest;
          bestType = "Mergesort";
          oBestMin = mergeBestMin;
        }
        if (mergeWorst > oWorst){
          oWorst = mergeWorst;
          worstType = "Mergesort";
          oWorstMin = mergeWorstMin;
        }
      }
    }

    if (sortCheck)
      s = "sorted";
    else
      s = "random";
    System.out.println();
    System.out.println("After the tests, here is the best setup: ");
    System.out.println("  Algorithm: " + bestType);
    System.out.println("  Data Status: " + s);
    System.out.println("  Min Recurse: " + oBestMin);
    System.out.println("  Average: " + oBest);
    System.out.println();
    System.out.println("After the tests, here is the worst setup: ");
    System.out.println("  Algorithm: " + worstType);
    System.out.println("  Data Status: " + s);
    System.out.println("  Min Recurse: " + oWorstMin);
    System.out.println("  Average: " + oWorst);
    System.out.println();
    System.out.println("Here are the per algorithm results: ");
    System.out.println("Algorithm: Simple Pivot Quicksort");
    System.out.println("  Best Result: ");
    System.out.println("    Min Recurse: " + simpleBestMin);
    System.out.println("    Average: " + simpleBest);
    System.out.println("  Worst Result: ");
    System.out.println("    Min Recurse: " + simpleWorstMin);
    System.out.println("    Average: " + simpleWorst);
    System.out.println();
    System.out.println("Algorithm: Median of Three Quicksort");
    System.out.println("  Best Result: ");
    System.out.println("    Min Recurse: " + medBestMin);
    System.out.println("    Average: " + medBest);
    System.out.println("  Worst Result: ");
    System.out.println("    Min Recurse: " + medWorstMin);
    System.out.println("    Average: " + medWorst);
    System.out.println();
    System.out.println("Algorithm: Random Pivot Quicksort");
    System.out.println("  Best Result: ");
    System.out.println("    Min Recurse: " + randomBestMin);
    System.out.println("    Average: " + randomBest);
    System.out.println("  Worst Result: ");
    System.out.println("    Min Recurse: " + randomWorstMin);
    System.out.println("    Average: " + randomWorst);
    System.out.println();
    System.out.println("Algorithm: Mergesort");
    System.out.println("  Best Result: ");
    System.out.println("    Min Recurse: " + mergeBestMin);
    System.out.println("    Average: " + mergeBest);
    System.out.println("  Worst Result: ");
    System.out.println("    Min Recurse: " + mergeWorstMin);
    System.out.println("    Average: " + mergeWorst);
    System.out.println();
  }

  public static void main(String [] args)
  {
    new Assig4(args);
  }
}
