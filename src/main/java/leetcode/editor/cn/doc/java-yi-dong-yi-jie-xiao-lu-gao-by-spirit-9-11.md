1.定义头尾两个指针;
2.left指针从左向右,直到不符合条件停下来,注意循环完毕后,left指针只能到数组倒数第二个数位置,right亦是如此;
3.判断指针是否落在同一个元素上作为返回值;
4.完结。

        public boolean validMountainArray(int[] A) {
            if (A.length < 3)
                return false;

            int left = 0, right = A.length - 1;
            while (left < A.length - 2 && A[left] < A[left + 1])//注意循环完毕后,left指针只能到数组倒数第二个数位置
                left++;
            while (right > 1 && A[right] < A[right - 1])//和left指针条件一样
                right--;

            return left == right;
        }