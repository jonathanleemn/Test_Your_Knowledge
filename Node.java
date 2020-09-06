
//an example of generics
class Node<M, T, K>
{

	public T questionType, question, answer, category; // the data

	/**
	 * Construct a new binary node.
	 */
	public Node(T questionType, T question, T answer, T category)
	{
		this.questionType = questionType;
		this.question = question;
		this.answer = answer;
		this.category = category;
	}
	

	public T getQuestionType()
	{
		return (questionType);
	}

	public void setQuestionType(T questionType)
	{
		this.questionType = questionType;
	}

	public T getQuestion()
	{
		return question;
	}

	public void setQuestion(T question)
	{
		this.question = question;
	}

	public T getAnswer()
	{
		return answer;
	}

	public void setAnswer(T answer)
	{
		this.answer = answer;
	}


	public T getCategory()
	{
		return category;
	}


	public void setCategory(T category)
	{
		this.category = category;
	}
	
	

}