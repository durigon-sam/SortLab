// Sam Durigon
// University of Pittsburgh
// CS0445 Spring 2021
// Assignment 4

public class MedOfThree<T extends Comparable<? super T>> implements Partitionable<T>
{
  public int partition(T[] a, int first, int last)
  {
    int mid = (first + last)/2;
    sortFirstMiddleLast(a, first, mid, last);

    //move pivot to next-to-last position in array
    swap(a, mid, last-1);
    int pivIndex = last-1;
    T pivot = a[pivIndex];

    int indexFromLeft = first+1;
    int indexFromRight = last-2;
    boolean done = false;
    while(!done)
    {
      //Starting at beginning of array, leave elements that are < pivot and
      // lovate first element that is >= pivot. One will be found since last
      // element is >= pivot
      while (a[indexFromLeft].compareTo(pivot) < 0)
        indexFromLeft++;

      //Starting at the end of the array, leave the elements that are > pivot
      // and lovate the first element that is <= pivot. One will be found since
      // first element is <= pivot
      while (a[indexFromRight].compareTo(pivot) > 0)
        indexFromRight--;

      assert a[indexFromLeft].compareTo(pivot) >= 0 &&
             a[indexFromRight].compareTo(pivot) <= 0;

      if (indexFromLeft < indexFromRight)
      {
        swap(a, indexFromLeft, indexFromRight);
        indexFromLeft++;
        indexFromRight--;
      }else
        done = true;
    }

    //Place pivot between smaller and larger subarrays
    swap(a, pivIndex, indexFromLeft);
    pivIndex = indexFromLeft;
    return pivIndex;
  }

  private void sortFirstMiddleLast(T[] a, int first, int mid, int last)
  {
    order(a, first, mid);
    order(a, mid, last);
    order(a, first, mid);
  }

  private void order(T[] a, int i, int j)
  {
    if (a[i].compareTo(a[j]) > 0)
      swap(a, i, j);
  }

  private void swap(Object[] a, int i, int j)
  {
    Object temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }
}
