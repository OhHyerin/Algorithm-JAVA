package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A14500 {
    //테트로미노(골드5)

    static int n,m;
    static int[][] paper;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line[] = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);

        paper = new int[n][m];
        for(int i=0;i<n;i++){
            line = br.readLine().split(" ");
            for(int j=0;j<m;j++){
                paper[i][j] = Integer.parseInt(line[j]);
            }
        }

        int max = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                //paper배열 전체 탐색
                for(int t=0;t<19;t++){
                    //t : 테트로미노 배열
                    int check = 0;
                    int sum = paper[i][j];
                    for(int block=0;block<3;block++){
                        int x = i+tetromino[t][block][0];
                        int y = j+tetromino[t][block][1];
                        if(x>=0 && x<n && y>=0 && y<m){
                            sum += paper[x][y];
                        }else{
                            check=1;
                            break;
                        }
                    }
                    if(check==0){
                        //반복문 3번(4번)을 완전히 돌았으면 check==0
                        //중간에 범위를 벗어났으면 check==1
                        max = Math.max(max, sum);
                    }
                }
            }
        }
        System.out.println(max);

    }

    static int[][][] tetromino = {
            // ㅣ
            {{0,1},{0,2},{0,3}},
            //ㅡ
            {{1,0},{2,0},{3,0}},
            //ㅁ
            {{0,1},{1,0},{1,1}},
            //r
            {{1,0},{0,1},{0,2}},
            //ㄱ
            {{1,0},{1,1},{1,2}},
            //r-
            {{0,1},{1,0},{2,0}},
            //-ㄱ
            {{1,0},{2,0},{2,1}},
            //L
            {{0,1},{0,2},{1,2}},
            //L반대로
            {{1,0},{1,-1},{1,-2}},
            //ㄴ
            {{0,1},{1,1},{2,1}},
            //ㄴ반대로
            {{1,0},{2,0},{2,-1}},
            //지그재그 4가지
            {{0,1},{1,1},{1,2}},
            {{0,1},{1,0},{1,-1}},
            {{1,0},{1,-1},{2,-1}},
            {{1,0},{1,1},{2,1}},
            //ㅗ
            {{1,0},{1,-1},{2,0}},
            //ㅜ
            {{1,0},{2,0},{1,1}},
            //ㅓ
            {{1,0},{1,-1},{1,1}},
            //ㅏ
            {{0,1},{0,2},{1,1}}
    };
}
