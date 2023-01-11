import java.util.Scanner;

public class wordle 
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        
        int wordLength = fileLength("shuffled.txt");
        int solLength = fileLength("solutions.txt");
        //checks if the files have anything in them
        if(wordLength == 0|| solLength == 0)
        {
            System.out.println("Error! One of your Text Files is empty!");
            System.exit(0);
        }
        String word = "";
        String[] solArray = new String[solLength];
        String[] wordArray = new String[wordLength];
        String[] guesses = new String[6];
        final int GTOTAL = 6;
        int gRemain = 6;
        int gCount = 0;
        final int LETTERCOUNT = 5;
        for (int i = 0; i < gCount; i++) 
        {
            guesses[i] = "";
        }
        solArray = arrayMaker(solArray, "solutions.txt", solLength);
        wordArray = arrayMaker(wordArray, "shuffled.txt", wordLength);
        selectionSort(wordArray);
        int random = (int)(Math.random()*(solLength));
        String ans = solArray[random];
        for(int i = 0; i < GTOTAL; i++)
        {
            System.out.println("Enter your guess: ");
            word = scan.next();
            guesses[i] = word;
            gCount++;
            if(guesses[i].equals(ans))
            {
                highlight(word, ans);
                System.out.println(" Correct!");
                break;
            }
            else if(guesses[i].length() == LETTERCOUNT && binarySearch(wordArray, word))
            {
                highlight(word, ans);
                System.out.println(" You have " + (gRemain - 1) + " guesses remaining");
                gRemain--;
            }
            else if(guesses[i].length() != LETTERCOUNT)
            {
                System.out.println("Not 5 letters, try again.");
                i--;
                continue;
            }
            else if(binarySearch(wordArray, word) == false)
            {
                System.out.println("Not a real word, try again.");
                i--;
                continue;
            }
            else if(usedBefore(guesses, word, gCount))
            {
                System.out.println("You fool! You've already used that!");
                i--;
            }
            if(gRemain == 0)
            {
                System.out.println("You lost! The word was " + ans);
            }
        }

    }

    public static boolean usedBefore(String[] guesses, String word, int gCount)
    {
        boolean isUsed = false;

        for (int z = 0; z < guesses.length; z++)
        {
            if(gCount != (z) && word.equalsIgnoreCase(guesses[z]))
            {
                isUsed = true;
                break;
            }
        }

        if(isUsed)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void highlight(String word, String ans)
    {
        boolean yellow = false;
        for(int i = 0; i < 5; i++)
        {
            if(word.charAt(i) == ans.charAt(i))
            {
                System.out.print(WordleLib.highlightGreen(String.valueOf(word.charAt(i))));
                continue;
            }
            for(int j = 0; j < 5; j++)
            {   
                if(i != j && word.charAt(i) == ans.charAt(j) && word.charAt(j) != ans.charAt(j))
                {
                    System.out.print(WordleLib.highlightYellow(String.valueOf(word.charAt(i))));
                    yellow = true;
                    continue;
                }
            }
            if(!yellow)
            {
                System.out.print(String.valueOf(word.charAt(i)));
                continue;
            }
            yellow = false;
        }
    }

    public static void selectionSort(String[] wordArray)
    {
        int min = 0;
        String temp = "";
        for(int i = 0; i < wordArray.length - 1; i++)
        {
            min = i;
            for(int j = i + 1; j < wordArray.length; j++)
            {
                if(wordArray[j].compareTo(wordArray[min]) < 0)
                {
                    min = j;
                }
            }
            temp = wordArray[i];
            wordArray[i] = wordArray[min];
            wordArray[min] = temp;
        }
    }

    public static int fileLength(String solution) 
    {
        Scanner file = WordleLib.createFileScanner(solution);
        if (file == null) {
            System.out.println("File is not found.");
            System.exit(0);
        }
        
        int numWords = 0;
        while(file.hasNextLine())
        {
            file.nextLine();
            numWords++;
        }
        return numWords;
    }

    public static String[] arrayMaker(String[] nArray, String solution, int solLength)
    {
        Scanner file = WordleLib.createFileScanner(solution);
        for(int i = 0; i < solLength; i++)
        {
            nArray[i] = file.nextLine();
        }
        return nArray;
    }

    public static boolean binarySearch(String[] wordArray, String word)
    {
        int low = 0;
        int high = wordArray.length-1;
        int mid = (low + high)/2;

        while(!wordArray[mid].equalsIgnoreCase(word) && low <= high)
        {
            if(wordArray[mid].compareTo(word) > 0)
            {
                high = mid - 1;
            }
            else
            {
                low = mid + 1;
            }
            mid = (high + low)/2;
        }

        if(wordArray[mid].equalsIgnoreCase(word))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}