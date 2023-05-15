/*
11의 배수 문제 
2 ms 976 Byte
*/

#include <iostream>
#include <string>
using namespace std;

int main() {
    int t;
    cin >> t;

    while (t--) {
        string num;
        cin >> num;

        int even_sum = 0;
        int odd_sum = 0;

        for (int i = 0; i < num.length(); ++i) {
            if (i % 2 == 0) {
                even_sum += num[i] - '0';
            } else {
                odd_sum += num[i] - '0';
            }
        }

        int diff = abs(even_sum - odd_sum);

        if (diff % 11 == 0) {
            string quot = "";
            int carry = 0;

            for (int i = 0; i < num.length(); ++i) {
                int curr = carry * 10 + (num[i] - '0');
                quot += (curr / 11) + '0';
                carry = curr % 11;
            }

            while (quot.length() > 1 && quot[0] == '0') {
                quot.erase(0, 1);
            }

            cout << quot << endl;
        } else {
            cout << "0" << endl;
        }
    }

    return 0;
}
