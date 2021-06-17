package com.shouguouo.codewars.sixkyu;

/**
 * 功能说明:
 *
 * @author shouguouo~
 * @date 2020/3/2 - 10:04
 */
public class MorseCodeDecoder {

    public static String decode(String morseCode) {
        // your brilliant code here, remember that you can access the preloaded Morse code table through MorseCode.get(code)
        StringBuilder res = new StringBuilder();
        morseCode = morseCode.trim();
        StringBuilder sub = new StringBuilder();
        for (int i = 0; i < morseCode.length(); i++) {
            if (i == morseCode.length() - 1) {
                sub.append(morseCode.charAt(i));
                res.append(MorseCode.get(sub.toString()));
            } else if (' ' != morseCode.charAt(i)) {
                sub.append(morseCode.charAt(i));
            } else if (' ' == morseCode.charAt(i) && ' ' == morseCode.charAt(i + 1)) {
                res.append(MorseCode.get(sub.toString()));
                sub = new StringBuilder();
                res.append(" ");
                i += 2;
            } else {
                res.append(MorseCode.get(sub.toString()));
                sub = new StringBuilder();
            }
        }
        return res.toString();
    }

    public static class MorseCode {
        public static String get(String str) {
            return "return something internal";
        }
    }

}
