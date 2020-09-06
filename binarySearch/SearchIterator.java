package binarySearch;

import java.util.ArrayList;

/*
 * Methods taken from Jack Pope's lecture notes
 */
public class SearchIterator
{

	public static <T> void display(T[] anArray)
	{
		System.out.println("The array contains the following entries");
		for (int index = 0; index < anArray.length; index++)
		{
			System.out.println(anArray[index] + " " + "INDEX: " + index);
		}
		System.out.println();
	}

	/*
	 * Ideal for unsorted arrays. A sequential search looks at each item in the
	 * array starting with the first until it finds the desired item or until it
	 * goes the entire array without finding said item.
	 * 
	 * Has an efficiency of O(n) for the worst case.
	 */
	public static <T> boolean inArrayIterativeUnsorted(T[] anArray, T anEntry)
	{
		boolean found = false;
		int index = 0;
		while (!found && (index < anArray.length))
		{
			if (anEntry.equals(anArray[index]))
				found = true;
			index++;
		}
		return found;
	}

	public static <T> void find(T[] anArray, T anEntry)
	{
		boolean found = false;
		int index = 0;
		while (!found && (index < anArray.length))
		{
			if (anEntry.equals(anArray[index]))
			{
				found = true;
			}

			index++;
		}
		index--;
		System.out.println("Found at index " + index);
	}

	/*
	 * Recursive method of searching through an array for item. This is a little
	 * more unorthodox because sequential searches are usually done through
	 * iterative means.
	 */
	public static <T> boolean inArrayRecursiveUnsorted(T[] anArray, T anEntry)
	{
		return search(anArray, 0, anArray.length - 1, anEntry);
	}

	// Searches anArray[first] through anArray[last] for desiredItem.
	// first >= 0 and < anArray.length.
	// last >= 0 and < anArray.length.
	private static <T> boolean search(T[] anArray, int first, int last, T desiredItem)
	{
		boolean found = false;
		if (first > last)
			found = false; // No elements to search
		else if (desiredItem.equals(anArray[first]))
			found = true;
		else
			found = search(anArray, first + 1, last, desiredItem);
		return found;
	}

	/*
	 * Binary search only works if the array is sorted. Searches by looking at
	 * the middle index and constantly "cutting" the array in half until it
	 * finds the desired item or until it finds that the item doesn't exist in
	 * the array.
	 * 
	 * Its worst case efficiency is O(logn).
	 */

	public static <T extends Comparable<? super T>> boolean inArrayRecursiveSorted(ArrayList<String> arrayList, T string)
	{
		return binarySearch(arrayList, 0, arrayList.size() - 1, string);
	}

	// Searches of anArray[first] through anArray[last] for desiredItem.
	// first >= 0 and < anArray.length.
	// last >= 0 and < anArray.length.
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<? super T>> boolean binarySearch(ArrayList<String> arrayList, int first, int last,
			T string)
	{
		boolean found;
		int mid = first + (last - first) / 2;
		if (first > last)
			found = false;
		else if (string.equals(arrayList.get(mid)))
			found = true;
		else if (string.compareTo((T) arrayList.get(mid)) < 0)
			found = binarySearch(arrayList, first, mid - 1, string);
		else
			found = binarySearch(arrayList, mid + 1, last, string);
		return found;
	}
}
