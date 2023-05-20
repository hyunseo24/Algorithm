/*
각 경기자가 놓은 돌의 위치들이 연속적으로 주어졌을 때, 최종적으로 게임판에 놓여진 돌의 색깔을 계산하는 오셀로 게임의 프로그램을 작성하시오.
*/

#include <iostream>
#include <vector>

using namespace std;

const int dx[8] = {-1, -1, 0, 1, 1, 1, 0, -1};
const int dy[8] = {0, 1, 1, 1, 0, -1, -1, -1};

vector<vector<char> > board(10, vector<char>(10, '+'));

void boardStart() {
    for(int i=1; i<=8; ++i) {
        for(int j=1; j<=8; ++j) {
            board[i][j] = '+';
        }
    }
    board[4][4] = board[5][5] = 'O';
    board[4][5] = board[5][4] = 'X';
}

void change(int x, int y, char currentStone) {
    for(int dir=0; dir<8; ++dir) {
        int nx = x + dx[dir], ny = y + dy[dir];
        bool canFlip = false;
        while(board[nx][ny] == (currentStone == 'O' ? 'X' : 'O')) {
            nx += dx[dir];
            ny += dy[dir];
            if(board[nx][ny] == currentStone) {
                canFlip = true;
                break;
            }
        }
        if(canFlip) {
            nx = x + dx[dir], ny = y + dy[dir];
            while(board[nx][ny] == (currentStone == 'O' ? 'X' : 'O')) {
                board[nx][ny] = currentStone;
                nx += dx[dir];
                ny += dy[dir];
            }
        }
    }
}

int main() {

    int t, n;
    cin >> t;

    while(t--) {
        boardStart();

        cin >> n;
        char currentStone = 'X';

        for(int i=0; i<n; ++i) {
            int x, y;
            cin >> x >> y;
            board[x][y] = currentStone;
            change(x, y, currentStone);
            currentStone = (currentStone == 'O' ? 'X' : 'O');
        }

        int blackCnt = 0, whiteCnt = 0;
        for(int i=1; i<=8; ++i) {
            for(int j=1; j<=8; ++j) {
                if(board[i][j] == 'O') whiteCnt++;
                else if(board[i][j] == 'X') blackCnt++;
            }
        }
        cout << blackCnt << " " << whiteCnt << "\n";

        for(int i=1; i<=8; ++i) {
            for(int j=1; j<=8; ++j) {
                cout << board[i][j];
            }
            cout << "\n";
        }
    }

    return 0;
}
