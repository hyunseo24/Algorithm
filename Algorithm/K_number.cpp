/*
아래의 예에서와 같이, 1부터 모든 자연수를 차례대로 연속해서 써 나간다고 할 때, 𝐾번째 쓰여진 자리수를 계산하는 프로그램을 작성하시오.
1234567891011121314151617181920212223……

예를 들어, 10번째 자리수는 1이며, 25번째 자리수는 7이다.
*/

#include <iostream>
using namespace std;

int find_digit(int k) {
    int n = 1;
    int digits = 1;

    while (k > digits * 9 * n) {
        k -= digits * 9 * n;
        digits++;
        n = n * 10;
    }
    int number = n + (k - 1) / digits;
    int position = (k - 1) % digits;
    for (int i = 0; i < digits - position - 1; i++) {
        number /= 10;
    }
    return number % 10;
}
int main() {
    int t;
    cin >> t;

    while (t--) {
        int k;
        cin >> k;
        cout << find_digit(k) << endl;
    }
    return 0;
}
