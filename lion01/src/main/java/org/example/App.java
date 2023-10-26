package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner scanner;
    int lastQuotationId;
    List<Quotation> quotations;
    App(){
        scanner = new Scanner(System.in);
        lastQuotationId = 0;
        quotations = new ArrayList<>();
    }
    void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                break;
            }
            else if (cmd.equals("등록")) {
                actionWrite();
            }
            else if (cmd.equals("목록")){
                actionList();
            }
            else if(cmd.equals("삭제?")){
                actionRomove(cmd);
            }
        }
    }
    void actionWrite(){
        System.out.println("명언 : ");
        String content = scanner.nextLine();
        System.out.println("작가 : ");
        String authorName = scanner.nextLine();

        lastQuotationId++;
        int id = lastQuotationId;

        Quotation quotation = new Quotation(id, content, authorName);
        quotations.add(quotation);

        System.out.printf("명언 : %s\n작가 : %s\n", content, authorName);
        System.out.printf("%d번 명언이 등록되었습니다.\n", lastQuotationId);
    }
    void actionList(){
        System.out.println("번호 / 작가 / 명언");

        System.out.println("------------------------");

        if(quotations.isEmpty()){
            System.out.println("등록된 명언이 없습니다");
        }
        for (int i = quotations.size() - 1; i >= 0; i--){
            Quotation quotation = quotations.get(i);
            System.out.printf("%d / %s / %s\n", quotation.id, quotation.content, quotation.authorName);
        }
    }
    void actionRomove(String cmd){
        String[] cmdBits = cmd.split("\\?", 2);
        String action = cmdBits[0];
        String queryString = cmdBits[1];

        String[] queryStringBits = queryString.split("&");

        int id = 0;

        for(int i = 0; i < queryStringBits.length; i++){
            String queryParamStr = queryStringBits[i];

            String[] queryParamStrBits = queryParamStr.split("=",2);

            String paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];

            if(paramName.equals("id")){
                id = Integer.parseInt(paramValue);
            }
        }
        System.out.printf("%d번 명언을 삭제합니다.\n", id);
    }
}