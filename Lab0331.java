import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class Lab0331 {

    static Scanner keyb = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {

        // An ARRAY is a way to store lots of values all accessible from a single variable.
        // The values stored in the array are called ELEMENTS.
        // To access (store or retrieve) a value from the array, use an INDEX.
        //
        //   0    1    2       <-- These are the indexes and they always start at zero.
        // ----------------
        // | 11 | 33 | 22 |    <-- These are the elements, all must be the same data type.
        // ----------------
        
        // We must CREATE an array before we can use it.
        // Let's create an array to store 3 integers.
        int[] numbers = new int[3];

        // Creating an array just reserves memory, we still have to INITIALIZE the array.
        numbers[0] = 11;
        numbers[1] = 33;
        numbers[2] = 22;

        // Now we have a single variable named {numbers} has 3 values.

        // Let's output the array
        System.out.println("\nCan't output the elements of an array this way: " + numbers);

        System.out.println("\nHere are the elements in the {numbers} array:");
        System.out.println("\t Index \t Element");
        for (int index = 0; index < numbers.length; index = index + 1) {  // first index ALWAYS 0
            int element = numbers[index];
            System.out.printf("\t (%d) \t %d\n", index, element);
        }

        System.out.println("\nFancy way to output an array: " + Arrays.toString(numbers));


        // The data in 'courses.txt' has the format
        // ART 1090|Art Appreciation|Explorations|Creative Expression & Engagement|3
        // course,  title,           stage,       area,    and the course's credit hours.


        // Create an array, store the first 5 areas from 'courses.txt', and then output the array.
        String[] areas = new String[5];
        try (Scanner sc = new Scanner(new File("courses.txt"))) {
            for (int index = 0; index < 5; index = index + 1) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|");
                areas[index] = parts[3];
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("\nThe areas of the first 5 courses:");
        for (int i = 0; i < 5; i = i + 1) {  // indexes ALWAYS start at 0
            System.out.printf("\t %d \t %s\n", i, areas[i]);
        }



        // TODO: #11 (15 points) - Create an array to store the first 5 titles from 'courses.txt'
        // and then output the array in reverse order.
        String[] titles = new String[5];
        try (Scanner sc = new Scanner(new File("courses.txt"))) {
            for (int ndx = 0; ndx < 5; ndx++) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|");
                titles[ndx] = parts[1];
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("\nThe titles of the first 5 courses:");
        for (int i = 4; i >= 0; i--) {
            System.out.printf("\t %d \t %s\n", i, titles[i]);
        }

        

        // TODO: #12 (15 points) - Now we have two arrays of length 5, one array holds the areas and
        // the other array holds the titles.  Output a table of this data using the format
        // {index} {tab} {title} {tab} {area}
        System.out.println("\nFirst 5 courses:");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%d \t %s \t %s\n", i, titles[i], areas[i]);
        }
        


        // TODO: #21 (35 points) - The user wants to build a {numHours} schedule.  Create a
        // user-defined function that sequentially selects courses from the input file
        // until {numHours} unique courses (with at most 6 courses and no more than 21 hours) are
        // selected.  This function outputs the titles of the schedule in reverse order and also returns the
        // number of titles that were output.
        int numTitles = createUniqueSchedule();
        System.out.println(numTitles + " titles were selected\n");



        // TODO: #22 (35 points) - Create a user-defined function that stores in an array, the first
        // {maxNumTitles} course titles that are shorter than {numChars} characters.  The only
        // parameters to this function are the (String) filename, the {maxNumTitles}, and
        // {numChars}. After creating this array, your function should return the (up to)
        // {numTitles} titles.

        

    }


    // WRITE YOUR FUNCTION DEFINITIONS HERE!

    // TODO #21
    static int createUniqueSchedule() {
        int numCourses = 0;
        ArrayList<String> selectedCourses = new ArrayList<>();
        System.out.print("\nWhat is your filename? ");
        String filename = keyb.nextLine();

        int numHours = 0;
        System.out.print("How many credit hours? ");
        while (numHours == 0) {
            numHours = Integer.parseInt(keyb.nextLine());
            if (numHours > 21) {
                numHours = 0;
                System.out.print("Too many hours, try again: ");
            }
        }

        try (Scanner sc = new Scanner(new File(filename))) {
            int totalHours = 0;
            while (totalHours < numHours && numCourses < 6) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|");
                String course = parts[0];
                String title = parts[1];
                int crseHours = Integer.parseInt(parts[4]);
                if (!selectedCourses.contains(course)) {
                    numCourses++;
                    selectedCourses.add(title);
                    totalHours += crseHours;
                }
            }

            System.out.println("Created Schedule:");
            for (int i = selectedCourses.size() - 1; i >= 0; i--) {
                System.out.printf("  %s\n", selectedCourses.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return numCourses;
    }
}
