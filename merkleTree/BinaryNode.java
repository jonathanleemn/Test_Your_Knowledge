package merkleTree;


/*
 * Modified a binary node class that I found online to suit my purposes
 * Source: http://www.cs.columbia.edu/~bauer/cs3134-f15/code/week5/BinaryTree.java
 * 
 */

class BinaryNode
{

	public String transactionID; // the data
	public String hash;
	public BinaryNode leftChild; // left subtree
	public BinaryNode rightChild; // right subtree
	public String leftChildHash, rightChildHash;

	/**
	 * Construct a new binary node.
	 */
	public BinaryNode(String transactionID, String hash)
	{
		this.transactionID = transactionID;
		this.hash = hash;
	}
	
	public BinaryNode(String transactionID)
	{
		this.transactionID = transactionID;
	}
	

	public BinaryNode(String transactionID, String hash, BinaryNode leftChild, BinaryNode rightChild, String leftChildHash,
			String rightChildHash)
	{
		this.transactionID = transactionID;
		this.hash = hash;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.leftChildHash = leftChildHash;
		this.rightChildHash = rightChildHash;
	}

	/**
	 * Print tree rooted at current node using preorder traversal.
	 */
	public void printTree(int indent)
	{
		for (int i = 0; i < indent; i++)
			System.out.print("  ");

		System.out.println(transactionID); // Node
		if (leftChild != null)
		{
			System.out.println("Left child of " + transactionID);
			leftChild.printTree(indent + 1); // Left
		}
		if (rightChild != null)
		{
			System.out.println("Right child of " + transactionID);
			rightChild.printTree(indent + 1); // Right
		}
	}

	/**
	 * Return a bracketed string represention of this tree.
	 */
	public String toString()
	{

		if (leftChild == null && rightChild == null) // if this is a leaf
			return transactionID.toString();

		StringBuilder sb = new StringBuilder("(");
		sb.append(transactionID);
		sb.append(" ");
		if (leftChild != null)
			sb.append(leftChild.toString());
		else
			sb.append("*");
		sb.append(" ");
		if (rightChild != null)
			sb.append(rightChild.toString());
		else
			sb.append("*");
		sb.append(")");
		return sb.toString();
	}

	public String getHash()
	{
		return hash;
	}

	public void setHash(String hash)
	{
		this.hash = hash;
	}

	public String getLeftChildHash()
	{
		return leftChildHash;
	}

	public void setLeftChildHash(String leftChildHash)
	{
		this.leftChildHash = leftChildHash;
	}

	public String getRightChildHash()
	{
		return rightChildHash;
	}

	public void setT2Hash(String rightChildHash)
	{
		this.rightChildHash = rightChildHash;
	}

}