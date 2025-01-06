class MySorts
{
    // Bubble Sort - should be overloaded for a String array
    int[] BubbleSort (int list[])
    {
	int x;
	for (int i = 0 ; i < list.length - 1 ; i++)
	{
	    for (int j = 1 ; j < list.length ; j++)
	    {
		if (list [j - 1] > list [j])
		{
		    x = list [j - 1];
		    list [j - 1] = list [j];
		    list [j] = x;
		}
	    }
	}
	return list;
    }
    
    String[] BubbleSort (String list[])
    {
	String x;
	for (int i = 0 ; i < list.length - 1 ; i++)
	{
	    for (int j = 1 ; j < list.length ; j++)
	    {
		if (list [j - 1].compareTo(list [j]) > 0)
		{
		    x = list [j - 1];
		    list [j - 1] = list [j];
		    list [j] = x;
		}
	    }
	}
	return list;
    }


    // Selection Sort - should be overloaded for a String array
    int[] SelectionSort (int list[])
    {
	int k, small;
	for (int i = 0 ; i < list.length - 1 ; i++)
	{
	    k = i;
	    small = list [i];
	    for (int j = i + 1 ; j < list.length ; j++)
	    {
		if (list [j] < small)
		{
		    k = j;
		    small = list [j];
		}
	    }
	    list [k] = list [i];
	    list [i] = small;
	}
	return list;
    }
    
    String[] SelectionSort (String list[])
    {
	int k;
	String small;
	for (int i = 0 ; i < list.length - 1 ; i++)
	{
	    k = i;
	    small = list [i];
	    for (int j = i + 1 ; j < list.length ; j++)
	    {
		if (list [j].compareTo(small) < 0)
		{
		    k = j;
		    small = list [j];
		}
	    }
	    list [k] = list [i];
	    list [i] = small;
	}
	return list;
    }


    // InsertSort Method - should be overloaded for an integer array
    String[] InsertSort (String list[])
    {
	String temp;
	for (int j = 0 ; j < list.length ; j++)
	{
	    int i = 0;
	    while (i != j & list [i].compareTo (list [j]) < 0)
	    {
		i++;
	    }
	    temp = list [j];
	    for (int k = j ; k >= i + 1 ; k--)
	    {
		list [k] = list [k - 1];
	    }
	    list [i] = temp;
	}
	return list;
    }
    int[] InsertSort (int list[])
    {
	int temp;
	for (int j = 0 ; j < list.length ; j++)
	{
	    int i = 0;
	    while (i != j & list [i]< list [j])
	    {
		i++;
	    }
	    temp = list [j];
	    for (int k = j ; k >= i + 1 ; k--)
	    {
		list [k] = list [k - 1];
	    }
	    list [i] = temp;
	}
	return list;
    }


    // LINEAR SEARCH - should be overloaded for an integer array
    int LinearSearch (String list[], String key)
    {
	for (int count = 0 ; count < list.length ; count++)
	{
	    if (list [count].equals (key))
	    {
		return count;
	    }
	}
	return -1;
    }


    int LinearSearch (int list[], int key)
    {
	for (int count = 0 ; count < list.length ; count++)
	{
	    if (list [count] == (key))
	    {
		return count;
	    }
	}
	return -1;
    }


    // BINARY SEARCH - should be overloaded for an integer array
    int BinarySearch (String list[], String key)
    {
	int first = 0;
	int middle;
	int last = list.length - 1;
	do
	{
	    middle = (first + last) / 2;
	    if (list [middle].compareTo (key) >= 0)
	    {
		// if it's in the first half, ignore the last half
		last = middle;
	    }
	    else
	    {
		// it's in the second half, ignore the first half
		first = middle + 1;
	    }
	}
	while (first != last);
	if (key.equals (list [first]))
	{
	    return first;
	}
	else
	{
	    return -1;
	}
    }


    int BinarySearch (int list[], int key)
    {
	int first = 0, last = list.length - 1;
	while (first <= last)
	{
	    int middle = (first + last) / 2;
	    if (list [middle] == key)
		return middle;
	    else
	    {
		if (list [middle] > key)
		    last = middle - 1;
		else
		    first = middle + 1;
	    }
	}
	return -1;
    }
}
