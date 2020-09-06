package sorting;
import java.util.List;

/*
 * pre-processes data into a comparable array
 * handles null values by removing them before converting
 */
class Preprocess<T extends Comparable<? super T>>
{

	//converts a list data structure into a Long[]
	@SuppressWarnings("unchecked")
	public T[] convertToLong(List<T> toConvert)
	{

		if (toConvert.contains(null))
		{
			toConvert.remove(null);
		}
		
		T[] converted = (T[]) new Long[toConvert.size()];

		for (int i = 0; i < toConvert.size(); i++)
		{

			if (!(toConvert.get(i) == null))
			{
				converted[i] = toConvert.get(i);
			}
		}
		return converted;
	}
	
	//converts a list data structure into a int[]
	public int[] convertToInt(List<T> toConvert)
	{

		if (toConvert.contains(null))
		{
			toConvert.remove(null);
		}
		
		int[] converted = new int[toConvert.size()];

		for (int i = 0; i < toConvert.size(); i++)
		{

			if (!(toConvert.get(i) == null))
			{
				converted[i] = (int) toConvert.get(i);
			}
		}
		return converted;
	}

}
