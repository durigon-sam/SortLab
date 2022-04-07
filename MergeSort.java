// Sam Durigon
// University of Pittsburgh
// CS0445 Spring 2021
// Assignment 4

public class MergeSort<T extends Comparable<? super T>> implements Sorter<T>
{
  private int MIN_SIZE; // min size to recurse, use InsertionSort for smaller sizes
  public MergeSort()
  {
     MIN_SIZE = 3;
  }

  public void setMin(int minSize)
  {
    MIN_SIZE = minSize;
  }

  public void sort(T[] a, int n)
  {
    mergeSort(a, 0, n-1);
  }

  public void mergeSort(T[] a, int first, int last)
  {
    T[] tempArray = (T[])new Comparable<?>[a.length];
    mergeSort(a, tempArray, first, last);
  }

  public void mergeSort(T[] a, T[] tempArray, int first, int last)
  {
    if (first < last)
    {
      int mid = (first+last)/2; //index of midpoint
      mergeSort(a, tempArray, first, mid); //Sort left half of array
      mergeSort(a, tempArray, mid+1, last); //Sort right half of array

      if (a[mid].compareTo(a[mid+1]) > 0)
        merge(a, tempArray, first, mid, last); //merge the two halves
    }
  }

  public void merge(T[] a, T[] tempArray, int first, int mid, int last)
  {
    // Two adjacent subarrays are a[beginHalf1..endHalf1] and a[beginHalf2..endHalf2].
		int beginHalf1 = first;
		int endHalf1 = mid;
		int beginHalf2 = mid + 1;
		int endHalf2 = last;

		// while both subarrays are not empty, copy the
	   // smaller item into the temporary array
		int index = beginHalf1; // next available location in
								            // tempArray
		for (; (beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2); index++)
	   {  // Invariant: tempArray[beginHalf1..index-1] is in order

	      if (a[beginHalf1].compareTo(a[beginHalf2]) <= 0)
	      {
	      	tempArray[index] = a[beginHalf1];
	        beginHalf1++;
	      }
	      else
	      {
	      	tempArray[index] = a[beginHalf2];
	        beginHalf2++;
	      }  // end if
	   }  // end for

	   // finish off the nonempty subarray

	   // finish off the first subarray, if necessary
	   for (; beginHalf1 <= endHalf1; beginHalf1++, index++)
	      // Invariant: tempArray[beginHalf1..index-1] is in order
	      tempArray[index] = a[beginHalf1];

	   // finish off the second subarray, if necessary
		for (; beginHalf2 <= endHalf2; beginHalf2++, index++)
	      // Invariant: tempa[beginHalf1..index-1] is in order
	      tempArray[index] = a[beginHalf2];

	   // copy the result back into the original array
	   for (index = first; index <= last; index++)
	      a[index] = tempArray[index];
  }

}
