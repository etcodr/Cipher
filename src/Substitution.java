import java.util.Scanner;

public class Substitution {
    public static void main(String[] args) {
        // ensure there are exactly one CLI args
        if (args.length != 1) {
            System.out.println("Usage java Substitiution key");
            System.exit(1);
        }

        String key = args[0];

        // ensure the key entered is valid thusly
        if (!validateKey(key))
            System.exit(1);

        // prompt user for a plaintext message
        Scanner in = new Scanner(System.in);
        System.out.print("plaintext:\t");
        String plaintext = in.nextLine();

        // print converted message and perform end of job housekeeping
        String ciphertext = substitute(key, plaintext);
        System.out.printf("ciphertext:\t%s\n", ciphertext);
        in.close();
        System.exit(0);
    }

    public static boolean validateKey(String key) {
        // create temporary String to check against duplicates
        String temp = "";

        // ensure key is exactly 26 letters long
        if (key.length() != 26) {
            System.out.println("Key must contain 26 characters.");
            return false;
        }

        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            boolean b = Character.isAlphabetic(ch);

            // ensure char in key is part of the alphabet
            if (!b) {
                System.out.println("Key must only contain alphabetical characters.");
                return false;
            }

            // loop through temp to check against duplicates
            for (int j =0; j < temp.length(); j++) {
                char ch2 = temp.charAt(j);
                if (Character.toLowerCase(ch) == Character.toLowerCase(ch2)) {
                    System.out.println("Key cannot contain duplicate characters.");
                    return false;
                }
            }
            temp += ch;
        }
        return true;
    }

    public static String substitute(String key, String plaintext) {
        // initialize a new StringBuilder object with which to create the ciphertext
        StringBuilder builder = new StringBuilder();

        // substitute each char in plaintext according to the key
        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);
            boolean b = Character.isAlphabetic(ch);

            // check if char in plaintext is alphabetical
            if (b) {
                // check if char is uppercase
                if (Character.isUpperCase(ch)) {
                    // convert ASCII to alphabetical index
                    ch -= 65;
                    // add new char value to ciphertext string
                    builder.append(Character.toUpperCase(key.charAt((int) ch)));
                }

                // check if char is lowercase
                if (Character.isLowerCase(ch)) {
                    // convert ASCII to alphabetical index
                    ch -= 97;
                    // add new char value to ciphertext string
                    builder.append(Character.toLowerCase(key.charAt((int) ch)));
                }
            }
            else
                builder.append(ch);
        }
        String ciphertext = builder.toString();
        return ciphertext;
    }
}
