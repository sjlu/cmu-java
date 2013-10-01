public class test
{
    public static void main(String[] args)
    {
        System.out.println("Enter your sentence:");
        String word = IO.readString();
        String first_letter = reverse(word).trim().substring(0,1);
        IO.outputStringAnswer(first_letter + reverse(word).substring(1,reverse(word).length()) + ".");
    }
    
    public static String reverse(String word)
    {
        //ALL YOUR BASE BELONG TO ME
        if (word.trim() == "" || word.trim() == null)
        {
            return "";
        }
        word.toLowerCase().trim();
        String answer = reverse(word.substring(word.IndexOf(" "),word.IndexOf(".")));
        word = word.substring(0,word.lastIndexOf(" "));
        return answer + word;
    }
}