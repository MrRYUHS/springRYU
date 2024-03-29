package org.example.base;

import org.example.domain.QuotationController;

import java.util.Scanner;

public class App {
    private Scanner scanner;

    public App() {
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        QuotationController quotationController = new QuotationController(scanner);

        while (true) {
            System.out.print("명령) ");

            String cmd = scanner.nextLine();

            Rq rq = new Rq(cmd);

            switch (rq.getAction()) {
                case "종료":
                    return;
                case "등록":
                    quotationController.actionWrite();
                    break;
                case "목록":
                    quotationController.actionList();
                    break;
                case "삭제":
                    quotationController.actionRemove(rq);
                    break;
                case "수정":
                    quotationController.actionModify(rq);
                    break;
            }
        }
    }
}