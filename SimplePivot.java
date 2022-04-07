// Sam Durigon
// University of Pittsburgh
// CS0445 Spring 2021
// Assignment 4

public class SimplePivot<T extends Comparable<? super T>> implements Partitionable<T>
{

  public int partition(T[] a, int first, int last)
  {
    int pivIndex = last; // simply picks pivot as the rightmost element
    T pivot = a[pivIndex];

    int indexFromLeft = first;
    int indexFromRight = last - 1;

    boolean done = false;
    while (!done)
    {
      //Start at beginning of array, leave elements that are < pivot and
      // locate first element that is >= pivot
      while (a[indexFromLeft].compareTo(pivot) < 0)
        indexFromLeft++;

      //Starting at end of array, leave elements that are > pivot and locate
      // first element that is <= pivot
      while (a[indexFromRight].compareTo(pivot) > 0 && indexFromRight > first)
				indexFromRight--;

      //Assertion: a[indexFromLeft] >= pivot and a[indexFromRight] <= pivot
      if (indexFromLeft < indexFromRight)
      {
        swap(a, indexFromLeft, indexFromRight);
        indexFromLeft++;
        indexFromRight--;
      }else
        done = true;
    }

    //place pivot between smaller and larger subarrays
    swap(a, pivIndex, indexFromLeft);
    pivIndex = indexFromLeft;

    // Assertion:
		// Smaller = a[first..pivotIndex-1]
		// Pivot = a[pivotIndex]
		// Larger  = a[pivotIndex + 1..last]

    return pivIndex;
  }

  private void swap(Object [] a, int i, int j)
  {
    Object temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }
}
