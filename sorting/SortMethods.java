package sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack Pope
 * @author Frank M. Carrano Methods taken from lecture notes/textbook
 */
public class SortMethods
{
	static int elementCounter = 0;
	static int operationCounter = 0;

	/*
	 * Selection sort searches for the smallest in a list and swaps it for the
	 * first index it then moves on to the second index and the next smallest
	 * number and so on until the index reaches the end of the list.
	 */
	public static <T extends Comparable<? super T>> void selectionSort(T[] a,
			int n)
	{
		System.out.println("Selection Sort initiated...");
		for (int index = 0; index < n - 1; index++)
		{
			int indexOfNextSmallest = getIndexOfSmallest(a, index, n - 1);
			operationCounter++;
			swap(a, index, indexOfNextSmallest);
			operationCounter++;
			elementCounter++;
			// Assertion: a[0] <= a[1] <= . . . <= a[index] <= all other a[i]
		} // end for
		System.out.println("Number of elements processed: " + elementCounter);
		System.out.println("Number of operations: " + operationCounter);
	} // end selectionSort

	// Finds the index of the smallest value in a portion of an array a.
	// Precondition: a.length > last >= first >= 0.
	// Returns the index of the smallest value among
	// a[first], a[first + 1], . . . , a[last].
	private static <T extends Comparable<? super T>> int getIndexOfSmallest(
			T[] a, int first, int last)
	{
		T min = a[first];
		int indexOfMin = first;
		for (int index = first + 1; index <= last; index++)
		{
			if (a[index].compareTo(min) < 0)
			{
				min = a[index];
				indexOfMin = index;
			} // end if
				// Assertion: min is the smallest of a[first] through a[index].
		} // end for

		return indexOfMin;
	} // end getIndexOfSmallest

	// Swaps the array entries a[i] and a[j].
	private static void swap(Object[] a, int i, int j)
	{
		Object temp = a[i];
		a[i] = a[j];
		a[j] = temp;

	} // end swap

	/*
	 * Insertion sort creates a sorted section on one side and gradually take
	 * pieces of the unsorted side and inserts it into the expanded sorted side"
	 */
	public static <T extends Comparable<? super T>> void insertionSort(T[] a,
			int first, int last)
	{
		System.out.println("Insertion Sort initiated...");
		for (int unsorted = first + 1; unsorted <= last; unsorted++)
		{
			// correct sequence; insertInOrder() is 2nd
			T firstUnsorted = a[unsorted];
			operationCounter++;
			insertInOrder(firstUnsorted, a, first, unsorted - 1);
			operationCounter++;
			elementCounter++;
		}
		System.out.println("Number of elements processed: " + elementCounter);
		System.out.println("Number of operations: " + operationCounter);
	}

	private static <T extends Comparable<? super T>> void insertInOrder(
			T anEntry, T[] a, int begin, int end)
	{
		int index = end;
		// find the min (max) in the remaining sequence to put in the left-most
		// spot
		while ((index >= begin) && (anEntry.compareTo(a[index]) < 0))
		{
			a[index + 1] = a[index]; // Make room
			index--;
		}
		a[index + 1] = anEntry; // Insert
	}

	/*
	 * Shell sort sorts pairs of elements across a gap, then on each cycle it
	 * reduces the gap between items to sort until the collection is sorted
	 */
	public static <T extends Comparable<? super T>> void shellSort(T[] a,
			int first, int last)
	{
		System.out.println("Shell Sort initiated...");
		int n = last - first + 1; // number of array entries
		for (int space = n / 2; space > 0; space = space / 2)
		{
			operationCounter++;
			for (int begin = first; begin < first + space; begin++)
			{
				incrementalInsertionSort(a, begin, last, space);
				elementCounter++;
				operationCounter++;
			}
		} // end for
		System.out.println("Number of elements processed: " + elementCounter);
		System.out.println("Number of operations: " + operationCounter);
	} // end shellSort

	/*
	 * Helper method for shell sort Sorts equally spaced entries of an array
	 * into ascending order
	 */
	private static <T extends Comparable<? super T>> void incrementalInsertionSort(
			T[] a, int first, int last, int space)
	{
		int unsorted, index;
		for (unsorted = first + space; unsorted <= last; unsorted = unsorted
				+ space)
		{
			T nextToInsert = a[unsorted];
			for (index = unsorted - space; (index >= first)
					&& (nextToInsert.compareTo(a[index]) < 0); index = index
							- space)
			{
				a[index + space] = a[index];
			} // end for
			a[index + space] = nextToInsert;
		} // end for
	} // end incrementalInsertionSort

	/*
	 * Merge sort uses a divide and conquer algorithm by repeatedly splitting
	 * the array into halves and then combining them into one array
	 */

	public static <T extends Comparable<? super T>> void mergeSort(T[] a,
			int first, int last)
	{
		System.out.println("Merge Sort initiated...");
		// the cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] tempArray = (T[]) new Comparable<?>[a.length]; // unchecked cast
		mergeSort(a, first, last, tempArray);
	} // end mergeSort

	public static <T extends Comparable<? super T>> void mergeSort(T[] a, T[] b, T[] c,
			int lo, int hi, T[] temp)
	{
		
		if (lo >= hi)
		{
			operationCounter++;
			return; /* a[lo] is sorted already */
		}
		int mid = (lo + hi) / 2;
		operationCounter++;
		mergeSort(a, lo, mid, temp); /* Sort sublist a[lo..mid] */
		operationCounter++;
		mergeSort(a, mid + 1, hi, temp); /* Sort sublist a[mid+1..hi] */
		operationCounter++;
		int k, tmp_lo = lo, tmp_hi = mid + 1;
		for (k = lo; k <= hi; k++)
		{ /* Merge sorted sublists */
			if ((tmp_lo <= mid)
					&& ((tmp_hi > hi) || (a[tmp_lo].compareTo(a[tmp_hi]) < 0)))
			{
				temp[k] = a[tmp_lo++];
				operationCounter++;
				elementCounter++;
			} else
			{
				temp[k] = a[tmp_hi++];
				operationCounter++;
				elementCounter++;
			}
		}
		for (k = lo; k <= hi; k++)
		{
			a[k] = temp[k]; /* Copy back to a */
			operationCounter++;
			elementCounter++;
		}
	}
	
	public static <T extends Comparable<? super T>> void mergeSort(T[] a,
			int lo, int hi, T[] temp)
	{
		
		if (lo >= hi)
		{
			operationCounter++;
			return; /* a[lo] is sorted already */
		}
		int mid = (lo + hi) / 2;
		operationCounter++;
		mergeSort(a, lo, mid, temp); /* Sort sublist a[lo..mid] */
		operationCounter++;
		mergeSort(a, mid + 1, hi, temp); /* Sort sublist a[mid+1..hi] */
		operationCounter++;
		int k, tmp_lo = lo, tmp_hi = mid + 1;
		for (k = lo; k <= hi; k++)
		{ /* Merge sorted sublists */
			if ((tmp_lo <= mid)
					&& ((tmp_hi > hi) || (a[tmp_lo].compareTo(a[tmp_hi]) < 0)))
			{
				temp[k] = a[tmp_lo++];
				operationCounter++;
				elementCounter++;
			} else
			{
				temp[k] = a[tmp_hi++];
				operationCounter++;
				elementCounter++;
			}
		}
		for (k = lo; k <= hi; k++)
		{
			a[k] = temp[k]; /* Copy back to a */
			operationCounter++;
			elementCounter++;
		}
	}

	/*
	 * Quick Sort uses a pivot value and sorts data to either side of that pivot
	 * based on relation to the pivot value keeps finding new pivot value until
	 * there is nothing left to sort.
	 */
	public static <T extends Comparable<? super T>> void quickSort(T[] a,
			int left, int right)
	{
		int index = partition(a, left, right);
		if (left < index - 1)
		{
			quickSort(a, left, index - 1);
			operationCounter++;
			elementCounter++;
		}
		if (index < right)
		{
			quickSort(a, index, right);
			operationCounter++;
			elementCounter++;
		}
	
	}

	/*
	 * Helper method for Quick Sort Partitions an array into two subarrays. A
	 * pivot is then used to sort the subarrays
	 */
	private static <T extends Comparable<? super T>> int partition(T[] a,
			int left, int right)
	{
		int i = left, j = right;
		T tmp;
		T pivot = a[(left + right) / 2];
		while (i <= j)
		{
			while (a[i].compareTo(pivot) < 0)
				i++;
			while (a[j].compareTo(pivot) > 0)
				j--;
			if (i <= j)
			{
				tmp = a[i];
				a[i] = a[j];
				a[j] = tmp;
				i++;
				j--;
			}
		}

		return i;
	}

	/*
	 * Radix sort looks at a position in the string and sorts them sorts it into
	 * 1 of 10 buckets depending on the character of said position, this is
	 * repeated until all positions in the string have been sorted
	 */
	public static void radixsort(int[] input)
	{
		System.out.println("Radix Sort initiated...");
		final int RADIX = 10; // number of buckets
		// declare and initialize bucket[]
		List<Integer>[] bucket = new ArrayList[RADIX];
		for (int i = 0; i < bucket.length; i++)
		{
			bucket[i] = new ArrayList<Integer>();
			operationCounter++;
		}
		// sort
		boolean maxLength = false;
		int tmp = -1, placement = 1;
		while (!maxLength)
		{
			maxLength = true;
			// split input between lists
			for (Integer i : input)
			{
				tmp = i / placement;
				bucket[tmp % RADIX].add(i);
				operationCounter++;
				elementCounter++;
				if (maxLength && tmp > 0)
				{
					maxLength = false;
				}
			}
			int a = 0; // empty lists into input array
			for (int b = 0; b < RADIX; b++)
			{
				for (Integer i : bucket[b])
				{
					input[a++] = i;
					operationCounter++;
					elementCounter++;

				}
				bucket[b].clear();
				operationCounter++;
			}
			placement *= RADIX; // move to next digit
			operationCounter++;
		}
		System.out.println("Number of elements processed: " + elementCounter);
		System.out.println("Number of operations: " + operationCounter);
	}
}
