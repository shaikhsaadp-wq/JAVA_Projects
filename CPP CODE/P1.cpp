#include <iostream>
using namespace std;
void selectionsort(float a[], int n)
{
	int Comparisons = 0;
	for(int i = 0; i<n-1; i++)
	{
		int min = i;
		for(int j = i+1; j<n; j++)
		{
			Comparisons++;
			if(a[j] < a[min])
			min = j;
		}
		float temp = a[i];
		a[i] = a[min];
		a[min] = temp;
	}
	cout<<"\nsorted percentage:\n";
	for(int i =0; i<n; i++)
	cout << a[i] << " ";
	
	cout << "\n Top Five Scores:";
	for(int i = n-1; i>=n-5 && i>=0; i--)
	cout << a[i]<<" ";
	
	cout << "\nTotal Comparisons = " <<Comparisons<<endl;
}
void bubblesort(float a[], int n)
{
	int Comparisons = 0;
	for(int i = 0; i<n-1; i++)
	{
		for(int j = 0; j<n-i-1; j++)
	{
		Comparisons++;
		if(a[j] > a[j+1])
		{
			float temp = a[j];
			a[j] = a[j+1];
			a[j+1] = temp;
		}
	}
}
cout<<"\n sorted percentage:\n";
for(int i=0; i<n; i++)
cout<<a[i]<<" ";
cout<<"\nTop Five Scores:";
for(int i = n-1; i>=n-5 && i>=0; i--)
cout<<a[i]<<" ";
cout<<"\nTotal Comparisons=" <<Comparisons<<endl;
}
int main()
{
  cout << "NAME : Saad Shaikh" << endl;

	int n, choice;
	cout<<"Enter number of students:";
	cin>>n;
	float per[n],temp[n];
	cout<<"Enter percentage:\n";
	for(int i=0; i<n; i++)
	cin>>per[i];
	do
	{
		for(int i=0; i<n; i++)
	    temp[i] = per[i];
	    
	    cout<<"\n===Menu===";
	    cout<<"\n1.selection sort";
	    cout<<"\n2.bubble sort";
	    cout<<"\n3.Exit";
	    cout<<"\nEnter Choice: ";
	    cin>>choice;
	    switch(choice)
{
    case 1:
    	cout<<"\n---selection sorting---\n";
    	selectionsort(temp,n);
    	break;
    case 2:
    	cout<<"\n---bubble sorting---\n";
    	bubblesort(temp,n);
    	break;
    case 3:
    	cout<<"\nProgram Terminated ";
    	break;
    default:
    	cout<<"\nInvalid choice!";
    	cout<<"\nPlease enter correct choice!";
    }
} while(choice != 3);
  return 0;
} 
