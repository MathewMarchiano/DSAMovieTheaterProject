/*
 * Purpose: Data Structure and Algorithms Lab 8 Problem 3
 * Status: Complete and thoroughly tested
 * Last update: 03/31/19
 * Submitted:  04/01/19
 * Comment: Uses Binary Search II
 * @author: Scott Zockoll
 * @version: 2019.03.31
 */

package structs;

public class AOSList extends RABList<String> implements AOSListInterface {

    @Override
    public void add(String item) throws ListIndexOutOfBoundsException {
        int searchResult = search(item);

        if (searchResult <= 0) {
            if (searchResult == 0) {
                if (!isEmpty() && get(searchResult).equals(item)) {
                    // do nothing
                }
                else {
                    add(0, item);
                }
            }
            else {
                int position = (-1) * searchResult;
                add(position, item);
            }
        }
    }

    // Implements Binary Search II
    // (eager advancing and postponing equality
    // checking until the sublist is of size 1)
    @Override
    public int search(String item) {

        int low = 0;
        int high = numItems;
        int mid = 0;

        int result;

        // Checks for empty collection
        // so index out of bounds exception
        // will not be thrown
        if (!isEmpty()) {

            // cut down lists through only
            // comparison checks
            while (low < high) {
                mid = (low + high) / 2;
                if (item.compareTo(get(mid)) <= 0) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }

            // finally do the only equality check
            if (low < numItems && item.compareTo(get(low)) == 0) {
                result = low;
            } else {

                // Negate answer if not in list
                // the absolute of this value
                // will be where the item needs to
                // be inserted.

                // Note: The user of this method
                // will have to handle the zero
                // case because it could either
                // be successful or unsuccessful.
                result = (-1) * (low);
            }
        } else {
            result = 0;
        }

        return result;

    }

    @Override
    public void clear() {
        removeAll();
    }

}

