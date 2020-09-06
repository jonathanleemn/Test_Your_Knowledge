package sortedList;

/*
 * Author: Frank M. Carrano 
 * Code found from the book
 */
public class BaseList<T>
{
	class Node
	{
		private T data; // Entry in list
		private Node next; // Link to next node

		Node(T dataPortion)
		{
			data = dataPortion;
			next = null;
		} // end constructor

		Node(T dataPortion, Node nextNode)
		{
			data = dataPortion;
			next = nextNode;
		} // end constructor

		T getData()
		{
			return data;
		} // end getData

		void setData(T newData)
		{
			data = newData;
		} // end setData

		Node getNextNode()
		{
			return next;
		} // end getNextNode

		void setNextNode(Node nextNode)
		{
			next = nextNode;
		} // end setNextNode
	} // end Node
}
