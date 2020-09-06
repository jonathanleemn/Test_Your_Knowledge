package sortedList;
import sortedList.BaseList.Node;

/*
 * Author: Frank M. Carrano
 * Source code found from the textbook and methods taken from
 * http://www.mathcs.richmond.edu/~barnett/cs221/source/LList.java
 */
public class SortedList<T extends Comparable<? super T>> extends BaseList<T> implements SortedListInterface<T>
{
	private Node firstNode; // Reference to first node of chain
	private Node lastNode;
	private int numberOfEntries;

	public SortedList()
	{
		firstNode = null;
		lastNode = null;
		numberOfEntries = 0;
	} // end default constructor

	/*
	 * < Implementations of the sorted list operations go here.> . . .
	 */

	@Override
	public void add(T newEntry)
	{
		firstNode = add(newEntry, firstNode);
		numberOfEntries++;
	} // end add

	private Node add(T newEntry, Node currentNode)
	{
		if ((currentNode == null) || (newEntry.compareTo(currentNode.getData()) <= 0))
		{
			currentNode = new Node(newEntry, currentNode);
		}
		else
		{
			Node nodeAfter = add(newEntry, currentNode.getNextNode());
			currentNode.setNextNode(nodeAfter);
		} // end if
		return currentNode;
	} // end add

	@Override
	public boolean remove(T anEntry)
	{
		boolean result = false;
		int position = getPosition(anEntry);
		if (position > 0)
		{
			result = true;
		} // end if
		return result;
	} // end remove

	@Override
	public int getPosition(T anEntry)
	{
		int position = 1;
		Node currentNode = firstNode;
		while ((currentNode != null) && (anEntry.compareTo(currentNode.getData()) > 0))
		{
			currentNode = currentNode.getNextNode();
			position++;
		}
		if ((currentNode == null) || anEntry.compareTo(currentNode.getData()) != 0)
			position = -position;
		return position;
	}

	private Node getNodeBefore(T anEntry)
	{
		Node currentNode = firstNode;
		Node nodeBefore = null;
		while ((currentNode != null) && (anEntry.compareTo(currentNode.getData()) > 0))
		{
			nodeBefore = currentNode;
			currentNode = currentNode.getNextNode();
		}
		return nodeBefore;
	}

	public T getEntry(int givenPosition)
	{
		T result = null; // result to return

		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			assert !isEmpty();
			result = getNodeAt(givenPosition).getData();
		} // end if

		return result;
	} // end getEntry

	private Node getNodeAt(int givenPosition)
	{
		assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = firstNode;

		// traverse the list to locate the desired node
		for (int counter = 1; counter < givenPosition; counter++)
			currentNode = currentNode.getNextNode();

		assert currentNode != null;
		return currentNode;
	} // end getNodeAt

	public T remove(int givenPosition)
	{
		T result = null; // return value

		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			assert !isEmpty();

			if (givenPosition == 1) // case 1: remove first entry
			{
				result = firstNode.getData(); // save entry to be removed
				firstNode = firstNode.getNextNode();
				if (numberOfEntries == 1)
					lastNode = null; // solitary entry was removed
			}
			else // case 2: givenPosition > 1
			{
				Node nodeBefore = getNodeAt(givenPosition - 1);
				Node nodeToRemove = nodeBefore.getNextNode();
				Node nodeAfter = nodeToRemove.getNextNode();
				nodeBefore.setNextNode(nodeAfter); // disconnect the node to be
													// removed
				result = nodeToRemove.getData(); // save entry to be removed

				if (givenPosition == numberOfEntries)
					lastNode = nodeBefore; // last node was removed
			} // end if

			numberOfEntries--;
		} // end if

		return result; // return removed entry, or
		// null if operation fails
	} // end remove

	public boolean contains(T anEntry)
	{
		return getPosition(anEntry) > 0;
	} // end contains

	public void clear()
	{
		firstNode = null;
		numberOfEntries = 0;
	}

	public int getLength()
	{
		return numberOfEntries;
	}

	public boolean isEmpty()
	{
		return numberOfEntries == 0;
	}

	@Override
	public T[] toArray()
	{
		int numberOfEntries = getLength();
		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Comparable[numberOfEntries];
		int index = 0;
		Node currentNode = getFirstNode();
		while ((index < numberOfEntries) && (currentNode != null))
		{
			result[index] = currentNode.getData();
			currentNode = currentNode.getNextNode();
			index++;
		}
		return result;
	}

	private Node getFirstNode()
	{
		return firstNode;
	}

} // end LinkedSortedList
