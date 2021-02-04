```
class Solution {
    public char findTheDifference(String s, String t) {
        char[] schar = s.toCharArray();
        char[] tchar = t.toCharArray();
        Arrays.sort(schar);
        Arrays.sort(tchar);
        int j = 0;
        for(int i = 0; i<s.length(); i++){
            if(schar[i] !=tchar[j]){
                return tchar[j];
            }
            j++;
        }
        return tchar[t.length() - 1];
    }
}
```
