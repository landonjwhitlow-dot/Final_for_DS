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

    // ---------- MERGE SORT ----------
    private void mergeSort(Assignment[] arr, int left, int right) {
        if (left >= right) return;

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

        int i = 0, j = 0, k = left;

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
            arr[k++] = leftArr[i++];
        }

        while (j < rightSize) {
            arr[k++] = rightArr[j++];
        }
    }

    private int compareDueDates(String d1, String d2) {
        return dateToNumber(d1) - dateToNumber(d2);
    }

    private int dateToNumber(String date) {
        String[] parts = date.split("/");

        if (parts.length != 3) return 0;

        try {
            int month = Integer.parseInt(parts[0]);
            int day = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            return year * 10000 + month * 100 + day;
        } catch (Exception e) {
            return 0;
        }
    }

    // ---------- SAVE TO FILE ----------
    public void saveToFile() {
        try {
            java.io.PrintWriter writer = new java.io.PrintWriter("assignments.txt");

            Assignment[] arr = list.toArray();

            for (Assignment a : arr) {
                writer.println(
                        a.getTitle() + "," +
                        a.getCourse() + "," +
                        a.getDueDate() + "," +
                        a.getIsCompleted()
                );
            }

            writer.close();
            System.out.println("Assignments saved to file.");
        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }

    // ---------- LOAD FROM FILE ----------
    public void loadFromFile() {
        try {
            java.io.File file = new java.io.File("assignments.txt");

            if (!file.exists()) return;

            java.util.Scanner scanner = new java.util.Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 4) {
                    Assignment a = new Assignment(parts[0], parts[1], parts[2]);

                    if (parts[3].equals("true")) {
                        a.setIsCompleted(true);
                    }

                    list.add(a);
                }
            }

            scanner.close();
            System.out.println("Assignments loaded from file.");
        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
    }
}
