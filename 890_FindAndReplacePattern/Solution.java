class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        /**
         *  思路：遍历每一个word，用2个map来保存当前字母以发现的字母映射规则。
                 逐个遍历每个字母，
                 (1) 若map中不存在word.char的映射、且当前pattern.char也没有映射为其他字母时，
                    在map1 中添加 <word.char, pattern.char>的key value
                    在map2中添加 <pattern.char, word.char>的key value
                    (1.1) 若当前pattern.char已经映射为其他字母，表明当前的word不匹配pattern，开始看下一个word
                 (2) 若map1 中已经存在该word。char的映射，且value就是当前的pattern.char，则继续遍历下一个字母
                 (3) 若map1中已经存在该word.char的映射，且value != pattern.char, 表明当前的word不匹配pattern，开始看下一个word
         */
         Map<Character, Character> wordToPattern = null;
         Map<Character, Character> patternToWord = null;

         int wlen = words.length;
         int l = words[0].length();

         List<String> result = new ArrayList<>();

         for (int i = 0; i < wlen; i++) {
             String word = words[i];
             wordToPattern = new HashMap<>();
             patternToWord = new HashMap<>();
             boolean flag = false;
             for (int j = 0; j < l; j++) {
                 char wc = word.charAt(j);
                 char pc = pattern.charAt(j);
                 if (!wordToPattern.containsKey(wc)) {
                     if (!patternToWord.containsKey(pc)) {
                         wordToPattern.put(wc, pc);
                         patternToWord.put(pc, wc);
                         continue;
                     } else {
                         flag = true;
                         break;
                     }
                 } else {
                     char mappedPc = wordToPattern.get(wc);
                     if (mappedPc == pc) {
                         continue;
                     } else {
                         flag = true;
                         break;
                     }
                 }
             }
             if (!flag) {
                 // 表明这个word匹配pattern
                result.add(word);
             }
         }

         return result;
    }
}
