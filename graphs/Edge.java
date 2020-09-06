package graphs;

/*
 * Source: Lecture notes
 */
class Edge<T>
{
	Vertex<T> source, destination;
	Object weight;

	Edge(Vertex<T> source, Vertex<T> destination, Object weight)
	{
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}
}
