#include <iostream>
#include <string>
using namespace std;

struct Node
{
    string name;
    string phone;
    Node* next;

    Node(string n, string p)
    {
        name = n;
        phone = p;
        next = NULL;
    }
};

class HashTable
{
    Node** table;
    int size;

public:
    HashTable(int s)
    {
        size = s;
        table = new Node*[size];

        for (int i = 0; i < size; i++)
            table[i] = NULL;
    }

    int hashFunction(string name)
    {
        int sum = 0;

        for (int i = 0; i < name.length(); i++)
            sum += name[i];

        return sum % size;
    }

    void insert(string name, string phone)
    {
        int index = hashFunction(name);
        Node* newNode = new Node(name, phone);

        if (table[index] == NULL)
        {
            table[index] = newNode;
        }
        else
        {
            Node* temp = table[index];

            while (temp->next != NULL)
                temp = temp->next;

            temp->next = newNode;
        }

        cout << "Contact inserted at index "
             << index << endl;
    }

    void display()
    {
        cout << "\n===== TELEPHONE BOOK =====\n";

        for (int i = 0; i < size; i++)
        {
            cout << i << " : ";
            Node* temp = table[i];

            if (temp == NULL)
            {
                cout << "NULL";
            }
            else
            {
                while (temp != NULL)
                {
                    cout << "[" << temp->name
                         << " : " << temp->phone << "]";

                    if (temp->next != NULL)
                        cout << " -> ";

                    temp = temp->next;
                }
            }

            cout << endl;
        }
    }
    void search(string name)
    {
        int index = hashFunction(name);
        Node* temp = table[index];

        while (temp != NULL)
        {
            if (temp->name == name)
            {
                cout << "Contact Found!" << endl;
                cout << "Name: " << temp->name << endl;
                cout << "Phone: " << temp->phone << endl;
                return;
            }

            temp = temp->next;
        }

        cout << "Contact Not Found!" << endl;
    }

    ~HashTable()
    {
        for (int i = 0; i < size; i++)
        {
            Node* temp = table[i];

            while (temp != NULL)
            {
                Node* nextNode = temp->next;
                delete temp;
                temp = nextNode;
            }
        }

        delete[] table;
    }
};
int main()
{
    cout << "NAME     = Sayali Shankar Shinde" << endl;
    cout << "Roll No. = 1241" << endl;

    int size, n;
    string name, phone;

    cout << "Enter hash table size: ";
    cin >> size;

    if (size <= 0)
    {
        cout << "Invalid hash table size!" << endl;
        return 0;
    }

    HashTable h(size);

    cout << "Enter number of contacts: ";
    cin >> n;

    if (n < 0)
    {
        cout << "Invalid number of contacts!" << endl;
        return 0;
    }

    for (int i = 0; i < n; i++)
    {
        cout << "\nEnter name: ";
        cin >> name;

        cout << "Enter phone number: ";
        cin >> phone;

        h.insert(name, phone);
    }

    h.display();

    cout << "\nEnter name to search: ";
    cin >> name;

    h.search(name);

    return 0;
}
