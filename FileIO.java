
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import binarySearch.SearchIterator;
import heaps.MaxHeap;
import merkleTree.MerkleTrees;
import stacks.ArrayStack;

public class FileIO
{
	BufferedReader input;
	String currentLine;
	String[] fields;
	// an example of Lists
	ArrayList<Node<String, String, String>> OOP, generics, complexity, lists, recursion, bags, sortedList, binarySearch,
			sorting, stacksAndQueues, hashing, trees, heaps, graphs;
	ArrayList<String> answers, studInitial;
	ArrayStack<String> stack;

	FileIO() throws IOException
	{
		input = new BufferedReader(new FileReader("questionBank.txt"));
		OOP = new ArrayList<Node<String, String, String>>();
		generics = new ArrayList<Node<String, String, String>>();
		complexity = new ArrayList<Node<String, String, String>>();
		lists = new ArrayList<Node<String, String, String>>();
		recursion = new ArrayList<Node<String, String, String>>();
		bags = new ArrayList<Node<String, String, String>>();
		sortedList = new ArrayList<Node<String, String, String>>();
		binarySearch = new ArrayList<Node<String, String, String>>();
		sorting = new ArrayList<Node<String, String, String>>();
		stacksAndQueues = new ArrayList<Node<String, String, String>>();
		hashing = new ArrayList<Node<String, String, String>>();
		trees = new ArrayList<Node<String, String, String>>();
		heaps = new ArrayList<Node<String, String, String>>();
		graphs = new ArrayList<Node<String, String, String>>();
		answers = new ArrayList<String>();
		studInitial = new ArrayList<String>();
		stack = new ArrayStack<String>();

		while ((currentLine = input.readLine()) != null)
		{

			fields = currentLine.split("::");
			if (fields.length == 7 && !fields[2].contains(" "))
			{
				String category = fields[3].replace(" ", "").toLowerCase();
				addQuestionToList(category);
				answers.add(fields[2]);
				studInitial.add(fields[5]);
			}
		}

		// an example of sorting
		// sorts the list of student initials alphabetically
		studInitial.sort(null);

		// an example of binary search
		// checks to see if a student has submitted anything to the questionbank
		// text file.
		SearchIterator.inArrayRecursiveSorted(studInitial, "JL");

		createStack(stack);
		createMaxHeap(stack);
		createMerkleTree();

		input.close();
	}

	// an example of OOP
	// demonstrates encapsulation
	public void addQuestionToList(String questionType)
	{
		switch (questionType)
		{
		case "ooprogramming":
			OOP.add(new Node<String, String, String>(fields[0], fields[1], fields[2], fields[3]));
			break;
		case "generics":
			generics.add(new Node<String, String, String>(fields[0], fields[1], fields[2], fields[3]));
		case "complexity&efficiency":
			complexity.add(new Node<String, String, String>(fields[0], fields[1], fields[2], fields[3]));
			break;
		case "lists":
			lists.add(new Node<String, String, String>(fields[0], fields[1], fields[2], fields[3]));
			break;
		case "recursion":
			recursion.add(new Node<String, String, String>(fields[0], fields[1], fields[2], fields[3]));
			break;
		case "bags&sets":
			bags.add(new Node<String, String, String>(fields[0], fields[1], fields[2], fields[3]));
			break;
		case "sortedlist":
			sortedList.add(new Node<String, String, String>(fields[0], fields[1], fields[2], fields[3]));
			break;
		case "binarysearch/iterators":
			binarySearch.add(new Node<String, String, String>(fields[0], fields[1], fields[2], fields[3]));
			break;
		case "sorting":
			sorting.add(new Node<String, String, String>(fields[0], fields[1], fields[2], fields[3]));
			break;
		case "stacks&queues":
			stacksAndQueues.add(new Node<String, String, String>(fields[0], fields[1], fields[2], fields[3]));
			break;
		case "assocarrays/hashing":
			hashing.add(new Node<String, String, String>(fields[0], fields[1], fields[2], fields[3]));
		case "trees":
			trees.add(new Node<String, String, String>(fields[0], fields[1], fields[2], fields[3]));
			break;
		case "heaps/balancedsearchtrees":
			heaps.add(new Node<String, String, String>(fields[0], fields[1], fields[2], fields[3]));
			break;
		case "graphs":
			graphs.add(new Node<String, String, String>(fields[0], fields[1], fields[2], fields[3]));
		}
	}

	// an example of stacks
	void createStack(ArrayStack<String> stack)
	{
		for (int i = 0; i < studInitial.size(); i++)
		{
			stack.push(studInitial.get(i));
		}
	}

	// an example of graphs, trees, and hashing
	// the answers are hashed and then put into a merkle tree
	void createMerkleTree()
	{
		MerkleTrees mt = new MerkleTrees(answers);
		mt.merkle_tree();
	}

	// an example of heaps
	void createMaxHeap(ArrayStack<String> stack)
	{
		MaxHeap<String> heap = new MaxHeap<String>();
		while (!stack.isEmpty())
			heap.add(stack.pop());

	}
}
