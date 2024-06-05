package kostomarov.courseproject.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("translite")
public class TransliterateUtils {
    private static final Map<Character, String> transliterationMap = new HashMap<>();

    static {
        transliterationMap.put('а', "a");
        transliterationMap.put('б', "b");
        transliterationMap.put('в', "v");
        transliterationMap.put('г', "h");
        transliterationMap.put('ґ', "g");
        transliterationMap.put('д', "d");
        transliterationMap.put('е', "e");
        transliterationMap.put('є', "ye");
        transliterationMap.put('ж', "zh");
        transliterationMap.put('з', "z");
        transliterationMap.put('и', "y");
        transliterationMap.put('і', "i");
        transliterationMap.put('ї', "i");
        transliterationMap.put('й', "i");
        transliterationMap.put('к', "k");
        transliterationMap.put('л', "l");
        transliterationMap.put('м', "m");
        transliterationMap.put('н', "n");
        transliterationMap.put('о', "o");
        transliterationMap.put('п', "p");
        transliterationMap.put('р', "r");
        transliterationMap.put('с', "s");
        transliterationMap.put('т', "t");
        transliterationMap.put('у', "u");
        transliterationMap.put('ф', "f");
        transliterationMap.put('х', "kh");
        transliterationMap.put('ц', "ts");
        transliterationMap.put('ч', "ch");
        transliterationMap.put('ш', "sh");
        transliterationMap.put('щ', "shch");
        transliterationMap.put('ю', "yu");
        transliterationMap.put('я', "ya");
        transliterationMap.put(' ',"");
    }

    public String transliterate(String text) {
        StringBuilder result = new StringBuilder();
        text = text.toLowerCase();
        for (char ch : text.toCharArray()) {
            if (transliterationMap.containsKey(ch)) {
                result.append(transliterationMap.get(ch));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
}
