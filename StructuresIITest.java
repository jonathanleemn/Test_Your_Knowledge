
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

import bags.Bag;
import sortedList.SortedList;

public class StructuresIITest<T>
{
	static SecureRandom rng = new SecureRandom();
	static Bag<Node<String, String, String>> questionBag = new Bag<Node<String, String, String>>();
	static Scanner scan = new Scanner(System.in);
	// an example of SortedList
	static SortedList<Integer> sortedScores = new SortedList<Integer>();
	static int finalScore = 0;
	static int oopScore, genericsScore, complexityScore, listsScore, recursionScore, bagsScore, sortedListScore,
			binarySearchScore, sortingScore, stacksQueuesScore, hashingScore, treesScore, heapsScore, graphsScore;
	static BufferedWriter output;
	static Timestamp timestamp = new Timestamp(System.currentTimeMillis());

	public static void main(String[] args) throws IOException
	{
		FileIO fio = new FileIO();
		populateBag(fio);
		
		System.out.print("Enter userID: ");
		Scanner scan = new Scanner(System.in);
		String userID = scan.nextLine();
		output = new BufferedWriter(new FileWriter(userID + ".txt"));
		
		takeTest();
		printResults();
		
		System.out.println("Enter 1 to retake test, enter 0 to finish.");
		System.out.print("Input: ");
		int answer = scan.nextInt();
		if (answer == 1)
		{
			populateBag(fio);
			finalScore = 0;
			oopScore = 0;
			genericsScore = 0;
			complexityScore = 0;
			listsScore = 0;
			recursionScore = 0;
			bagsScore = 0;
			sortedListScore = 0;
			binarySearchScore = 0;
			sortingScore = 0;
			stacksQueuesScore = 0;
			hashingScore = 0;
			treesScore = 0;
			heapsScore = 0;
			graphsScore = 0;
			output.close();
			output = new BufferedWriter(new FileWriter(userID + " 2nd attempt.txt"));
			takeTest();
			printResults();
		}
	}

	static void takeTest() throws IOException
	{
		Node<String, String, String> question = questionBag.grab();
		System.out.println(question.getQuestionType() + "\t" + question.getQuestion());
		System.out.print("Input: ");
		Scanner scan = new Scanner(System.in);
		String answer = scan.nextLine().replace(" ", "").toLowerCase();
		if (answer.equalsIgnoreCase(question.getAnswer().replace(" ", "").replace(")", "").replace("true", "t")
				.replace("false", "f").toLowerCase()))
		{
			System.out.println("Correct");
			incrementCategoryScore(question);
			finalScore++;
			writeToFileCorrect(question);
		}
		else
		{
			System.out.println("Incorrect");
			System.out.println("Answer: " + question.getAnswer());
			writeToFileIncorrect(question);
		}
		System.out.println("Questions remaining: " + questionBag.getCurrentSize());
		System.out.println("Current Score: " + finalScore + "/42");
		System.out.println();

		if (questionBag.isEmpty())
		{
			System.out.println("Final Score for this attempt: " + finalScore);
			sortedScores.add(finalScore);
		}
		// an example of recursion
		else
		{
			takeTest();
		}
	}

	// this method has an efficiency of O(N^2)
	// an example of time complexity & efficiency
	static void add3QuestionsToBag(ArrayList<Node<String, String, String>> fioList,
			Bag<Node<String, String, String>> newList)
	{
		ArrayList<Integer> numList = new ArrayList<Integer>();
		int questionCounter = 3;

		for (int i = 0; i < questionCounter; i++)
		{
			int questionSelector = rng.nextInt(fioList.size());
			while (numList.contains(questionSelector))
			{
				questionSelector = rng.nextInt(fioList.size());
			}
			numList.add(questionSelector);
			newList.add(fioList.get(numList.get(i)));
		}
	}

	// an example of Bags
	// uses a bag to store the questions
	private static void populateBag(FileIO fio)
	{
		add3QuestionsToBag(fio.OOP, questionBag);
		add3QuestionsToBag(fio.generics, questionBag);
		add3QuestionsToBag(fio.complexity, questionBag);
		add3QuestionsToBag(fio.lists, questionBag);
		add3QuestionsToBag(fio.recursion, questionBag);
		add3QuestionsToBag(fio.bags, questionBag);
		add3QuestionsToBag(fio.sortedList, questionBag);
		add3QuestionsToBag(fio.binarySearch, questionBag);
		add3QuestionsToBag(fio.sorting, questionBag);
		add3QuestionsToBag(fio.stacksAndQueues, questionBag);
		add3QuestionsToBag(fio.hashing, questionBag);
		add3QuestionsToBag(fio.trees, questionBag);
		add3QuestionsToBag(fio.heaps, questionBag);
		add3QuestionsToBag(fio.graphs, questionBag);

	}

	static <M, T, K> void incrementCategoryScore(Node<M, T, K> node)
	{
		switch (((String) node.getCategory()).replace(" ", "").toLowerCase())
		{
		case "ooprogramming":
			oopScore++;
			break;
		case "generics":
			genericsScore++;
			break;
		case "complexity&efficiency":
			complexityScore++;
			break;
		case "lists":
			listsScore++;
			break;
		case "recursion":
			recursionScore++;
			break;
		case "bags&sets":
			bagsScore++;
			break;
		case "sortedlist":
			sortedListScore++;
			break;
		case "binarysearch/iterators":
			binarySearchScore++;
			break;
		case "sorting":
			sortingScore++;
			break;
		case "stacks&queues":
			stacksQueuesScore++;
			break;
		case "assocarrays/hashing":
			hashingScore++;
			break;
		case "trees":
			treesScore++;
			break;
		case "heaps/balancedsearchtrees":
			heapsScore++;
			break;
		case "graphs":
			graphsScore++;
			break;
		}
	}

	static void writeToFileCorrect(Node<String, String, String> node) throws IOException
	{

		output.write("Correct!" + "\t" + node.getQuestionType() + "\t" + node.getQuestion() + "\t" + "Answer: "
				+ node.getAnswer() + "\t" + "Category: " + node.getCategory());
		output.newLine();
	}

	static void writeToFileIncorrect(Node<String, String, String> node) throws IOException
	{

		output.write("Incorrect!" + "\t" + node.getQuestionType() + "\t" + node.getQuestion() + "\t" + "Answer: "
				+ node.getAnswer() + "\t" + "Category: " + node.getCategory());
		output.newLine();
	}


	static void printResults() throws IOException
	{
		System.out.println("\nHighest Score: " + sortedScores.getEntry(sortedScores.getLength()));
		System.out.println("________________________________________________________________________");
		System.out.println("OOP Score: " + oopScore);
		System.out.println("Generics Score: " + genericsScore);
		System.out.println("Complexity Score: " + complexityScore);
		System.out.println("Lists Score: " + listsScore);
		System.out.println("Recursion Score: " + recursionScore);
		System.out.println("Bags Score: " + bagsScore);
		System.out.println("Sorted List Score: " + sortedListScore);
		System.out.println("Binary Search Score: " + binarySearchScore);
		System.out.println("Sorting Score: " + sortingScore);
		System.out.println("Stacks and Queue Score: " + stacksQueuesScore);
		System.out.println("Hashing Score: " + hashingScore);
		System.out.println("Trees Score: " + treesScore);
		System.out.println("Heaps Score: " + heapsScore);
		System.out.println("Graphs Score: " + graphsScore);
		output.write("Time Completed: " + timestamp.toString() + "\n");
		output.write("Final Score: " + finalScore + "\n");
		output.write(
				"Percentage Correct: " + (double) ((sortedScores.getEntry(sortedScores.getLength()) * 100 / 42)) + "%");
		output.close();
	}

}
