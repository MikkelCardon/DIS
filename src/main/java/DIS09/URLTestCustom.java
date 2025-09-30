package DIS09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLTestCustom {

    static BufferedReader br;
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://www.valutakurser.dk/");
        br = new BufferedReader(
                new InputStreamReader(url.openStream()));

        String nameToLookFor = "Amerikanske dollar";
        String line;


        while ((line = br.readLine())!=null) {
            if (line.contains("currencyItem_currencyItemWrapper__2-TKC")){
                String[] divSplit = line.split("div");

                for (String div : divSplit) {

                    if (div.contains("currencyItem_currencyNameContainer__19YHn")){
                        String extractedName = customRegex(div);
                        System.out.print(extractedName + " - ");
                    }
                    if (div.contains("currencyItem_actualValueContainer__2xLkB")){
                        String extractedValue = customRegex(div);
                        System.out.println(extractedValue);
                    }
                }
            }
        }
    }

    public static String customRegex(String line){
        String regex = "";
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '>'){
                i++;
                while(line.charAt(i) != '<' && i < line.length()){
                    regex += line.charAt(i);
                    i++;
                }
                break;
            }
        }

        return regex;
    }
}

