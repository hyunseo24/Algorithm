/*
문제 설명
Quine-McCluskey Method
Sum-of-Product 형태의 논리식을 Quine-McCluskey 방법으로 모든 Prime Implicant를 출력하는 코드를 작성하세요.
코드는 함수 형태로 작성하며, 함수의 인자(입력)와 출력(반환값)은 다음과 같이 정의합니다.

입력 인자
인자 형식: 정수 배열
[변수의 개수, minterm의 개수, minterm #1, minterm #2, ....]
예) [4, 7, 0, 1, 2, 3, 10, 11, 12] => 4개의 변수, 7개의 minterm

출력 값 형식: 문자열 배열
모든 Prime Implicant를 각각 문자열로 출력합니다. 각 변수 자리에 '0', '1', 혹은 '-'를 출력합니다. '-'는 해당 자리에 값 2가 있는 것으로 계산해서 오름차순으로 정렬하여 출력합니다.

입출력 예
Input: [3, 6, 0, 1, 2, 5, 6, 7]
output: ["00-", "0-0", "11-", "1-1", "-01", "-10"]
*/

#include <string>
#include <vector>
#include <algorithm>
#include <set>
#include <iostream>
using namespace std;

string intToBinary(int n, int bits) {
    string result = "";
    for(int i = bits-1; i >= 0; i--)
        result += ((n >> i) & 1) ? '1' : '0';
    return result;
}

string compareStrings(string first, string second) {
    int difference = 0;
    for(int i = 0; i < first.length(); i++)
        if(first[i] != second[i])
            difference++;
    if(difference == 1) {
        for(int i = 0; i < first.length(); i++)
            if(first[i] != second[i])
                first[i] = '-';
        return first;
    }
    return "-";
}

bool customSort(string a, string b) {
    for (size_t i = 0; i < a.size() && i < b.size(); i++) {
        if (a[i] == b[i])
            continue;
        if (a[i] == '-')
            return false;
        if (b[i] == '-')
            return true;
        return a[i] < b[i];
    }
    return a.size() < b.size();
}

vector<string> solution(vector<int> minterm) {
    int bits = minterm[0];
    int numMinterms = minterm[1];
    vector<string> minterms(numMinterms);
    for(int i = 0; i < numMinterms; i++){
        minterms[i] = intToBinary(minterm[i+2], bits);
    }
    bool change = true;
    while(change) {
        change = false;
        set<string> newMinterms;
        vector<bool> checked(minterms.size(), false);
        for(int i = 0; i < minterms.size(); i++) {
            for(int j = i+1; j < minterms.size(); j++) {
                string newMinterm = compareStrings(minterms[i], minterms[j]);
                if(newMinterm != "-") {
                    newMinterms.insert(newMinterm);
                    checked[i] = true;
                    checked[j] = true;
                    change = true;
                }
            }
        }
        for(int i = 0; i < minterms.size(); i++)
            if(!checked[i])
                newMinterms.insert(minterms[i]);
        minterms = vector<string>(newMinterms.begin(), newMinterms.end());
    }
    sort(minterms.begin(), minterms.end(), customSort);
    return minterms;
}
