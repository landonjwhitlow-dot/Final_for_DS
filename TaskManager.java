/**
 * TaskManager.java
 * Acts as the controller between Main and the LinkedList.
 * Responsibilities:
 *Add, remove, and update assignments
 *Sort assignments using Merge Sort
 * PRIORITY:
 *Instead of a built-in priority queue, tasks are sorted by due date.
 *The earliest due date is treated as the highest priority.
 *Author: Landon Whitlow
 */
public class TaskManager {

    private CustomLinkedList list;

    public TaskManager() {
        this.list = new CustomLinkedList();
    }


    public void addTask(String title, String course, String dueDate) {
        Assignment a = new Assignment(title, course, dueDate);
        list.add(a);
        System.out.println("Added: " + a.toString());
    }


    public void removeTask(String title) {
        boolean removed = list.remove(title);
        if (removed) {
            System.out.println("Removed assignment: " + title);
        } else {
            System.out.println("No assignment found with title: " + title);
        }
    }
    public void markComplete(String title) {
        Assignment a = list.find(title);
        if (a == null) {
            System.out.println("No assignment found with title: " + title);
        } else {
            a.setIsCompleted(true);
            System.out.println("Marked complete: " + a.toString());
        }
    }
    public Assignment[] getSortedTasks() {
        Assignment[] arr = list.toArray();
        mergeSort(arr, 0, arr.length - 1);
        return arr;
    }
    private void mergeSort(Assignment[] arr, int left, int right) {

        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;


        mergeSort(arr, left, mid);

        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

    private void merge(Assignment[] arr, int left, int mid, int right) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;


        Assignment[] leftArr = new Assignment[leftSize];
        Assignment[] rightArr = new Assignment[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < rightSize; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }


        int i = 0;
        int j = 0;
        int k = left;

        while (i < leftSize && j < rightSize) {

            if (compareDueDates(leftArr[i].getDueDate(), rightArr[j].getDueDate()) <= 0) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }


        while (i < leftSize) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }


        while (j < rightSize) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    private int compareDueDates(String d1, String d2) {
        int n1 = dateToNumber(d1);
        int n2 = dateToNumber(d2);
        return n1 - n2;
    }


    private int dateToNumber(String date) {
        // Split the string by "/"
        String[] parts = date.split("/");


        if (parts.length != 3) {
            return 0;
        }

        try {
            int month = Integer.parseInt(parts[0]);
            int day   = Integer.parseInt(parts[1]);
            int year  = Integer.parseInt(parts[2]);
            return year * 10000 + month * 100 + day;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
