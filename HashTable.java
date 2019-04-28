/*
 * Purpose: Data Structure and Algorithms Lab 12 Problem 1
 * Status: Complete and thoroughly tested
 * Last update: 04/24/19
 * Submitted:  04/24/19
 * Comment:
 * @author: Scott Zockoll
 * @version: 2019.04.24
 */

package structs;

public class HashTable<K, V> implements HashTableInterface<K, V> {

    private final int TABLE_SIZE = 3;

    private ChainNode[] table;     // hash table
    private int size = 0;          // size of table, i.e. number of entries ((key,value) associations)

    public HashTable() {
        table = new ChainNode[TABLE_SIZE];
    }  // end default constructor

    // table operations
    public boolean tableIsEmpty() {
        return size == 0;
    }  // end tableIsEmpty

    public int tableLength() {
        return size;
    }  // end tableLength


//implement the following 5 methods

    // inserts  association (key,value) only if key is not already in HashTable
    // and returns true; returns false if the key already has an associated
    // value in HashTable and does not insert
    public boolean tableInsert(K key, V value) {
        int index = hashIndex(key);
        boolean result = false;
        if (tableRetrieve(key) == null) {
            result = true;
            ChainNode chain = table[index];
            if (chain == null) {
                table[index] = new ChainNode(key, value, null);
                size++;
            } else {
                ChainNode curr = chain;
                while (curr.getNext() != null) {
                    curr = curr.getNext();
                }
                curr.setNext(new ChainNode(key, value, null));
                size++;
            }
        }

        return result;
    }

    // deletes the key and its association from the HashTable
    // if it is in the table and returns true; returns false if
    // key is not in the HashTable
    public boolean tableDelete(K searchKey) {
        int index = hashIndex(searchKey);
        boolean result = false;
        boolean running = true;

        if (table[index] != null) {
            if (table[index].getKey().equals(searchKey)) {
                table[index] = table[index].getNext();
                result = true;
                size--;
            } else {
                ChainNode curr = table[index];
                while (curr.getNext() != null &&
                        running) {
                    if (curr.getNext().getKey().equals(searchKey)) {
                        curr.setNext(curr.getNext().getNext());
                        running = false;
                        result = true;
                        size--;
                    } else {
                        curr = curr.getNext();
                    }
                }
            }
        }

        return result;
    }

    // returns the value associated with searchkey in
    // HashTable or null if there is no association
    public V tableRetrieve(K searchKey) {
        int index = hashIndex(searchKey);
        V result = null;
        boolean running = true;

        if (table[index] != null) {
            if (table[index].getKey().equals(searchKey)) {
                result = (V) table[index].getValue();
            } else {
                ChainNode curr = table[index];
                while (curr != null && running) {
                    if (curr.getKey().equals(searchKey)) {
                        result = (V) curr.getValue();
                        running = false;
                    }
                    curr = curr.getNext();
                }
            }
        }
        return result;
    }

    public int hashIndex(K key) {
        char[] chars = key.toString().toCharArray();
        int start = (int) 'A';
        int[] indices = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            indices[i] = (int) chars[i] - start + 1;
        }
        int sum = 0;
        for (int i = indices.length - 1, j = 0; i >= 0; i--, j++) {
            sum += indices[j] << 5 * i;
        }
        return sum % TABLE_SIZE;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        String separator = "";
        boolean first = true;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                ChainNode curr = table[i];
                while (curr != null) {
                    sb.append(separator + curr.getKey() + "=" + curr.getValue());
                    if (first) {
                        separator = ", ";
                        first = false;
                    }
                    curr = curr.getNext();
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }


}