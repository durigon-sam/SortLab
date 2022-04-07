// Sam Durigon
// University of Pittsburgh
// CS0445 Spring 2021
// Assignment 4
public class QuickSort<T extends Comparable<? super T>> implements Sorter<T>
{
  private Partitionable<T> partAlgo;
  private int MIN_SIZE;
  // min size to recurse, use InsertionSort for smaller sizes to complete sort
  public QuickSort(Partitionable<T> part)
  {
      partAlgo = part;
      MIN_SIZE = 3;
  }

  public void sort(T[] a, int size)
  {
    quickSort(a, 0, size-1);
  }

  public void quickSort(T[] a, int first, int last)
  {
    if (last - first + 1 < MIN_SIZE)
    {
      insertionSort(a, first, last);
    }else{
      // create the partition: smaller | pivot | Larger
      int pivIndex = partAlgo.partition(a, first, last);

      // sort subarrays smaller and Larger
      quickSort(a, first, pivIndex-1);
      quickSort(a, pivIndex+1, last);
    }
  }

  public void setMin(int minSize)
  {
    MIN_SIZE = minSize;
  }

  public void insertionSort(T[] a, int first, int last)
  {
    int unsorted;

    for (unsorted = first+1; unsorted <= last; unsorted++)
    {
      T firstUnsorted = a[unsorted];
      insertInOrder(firstUnsorted, a, first, unsorted-1);
    }
  }

  private void insertInOrder(T element, T[] a, int begin, int end)
  {
    int index;

		for (index = end; (index >= begin) && (element.compareTo(a[index]) < 0); index--)
		{
			a[index + 1] = a[index]; // make room
		}

		a[index + 1] = element;  // insert
  }
}
