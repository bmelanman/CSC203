public class Lab00{

    public static int char_count(String s, char c){
        int count = 0;
        char string_to_char[] = s.toCharArray();

        for(int j = 0; j < s.length(); j++){
            if (string_to_char[j] == c){
                count++;
            }
        }

        return count;
    }
    public static void main(String[] args){
        // Declaring and initializing some variables
        int x = 5;
        String y = "hello";
        double z = 9.8;

        // Printing the variables
        System.out.println("x: " + x + " y: " + y + " z: " + z);

        // An array
        int nums[] = {3, 6, -1, 2};
        for(int i = 0; i < nums.length; i++){
            System.out.println(nums[i]);
        }

        // Call a function
        int numFound = char_count(y, 'l');
        System.out.println("Found: " + numFound);

        // A counting for loop
        for (int i = 1; i < 11; i++){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
