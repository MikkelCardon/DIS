package DIS09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLTest {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://www.valutakurser.dk/");
        BufferedReader br = new BufferedReader(
                new InputStreamReader(url.openStream()));
        String line;



        while ((line = br.readLine())!=null) {
//            if(line.contains("currencyItem_currencyNameContainer__19YHn")
//                && line.contains("class=\"currencyItem_actualValueContainer__2xLkB\"")){
//                System.out.println("line: " + line);
//            }
            if (line.contains("currencyItem_currencyItemWrapper__2-TKC")){
                String[] divSplit = line.split("div");

                for (String div : divSplit) {

                    if (div.contains("currencyItem_currencyNameContainer__19YHn")){
                        Pattern pattern = Pattern.compile(">(.*?)<");
                        Matcher matcher = pattern.matcher(div);

                        if (matcher.find()){
                            System.out.print(matcher.group(1) + " -  ");
                        }
                    }
                    if (div.contains("currencyItem_actualValueContainer__2xLkB")){
                        Pattern pattern = Pattern.compile(">(.*?)<");
                        Matcher matcher = pattern.matcher(div);

                        if (matcher.find()){
                            System.out.println(matcher.group(1));
                        }
                    }
                }

            }
        }
    }
}

