package org.example;

public class Main {
    public static void main(String[] args) {
        전사 a전사 = new 전사();
        a전사.이름 = "카니";
        a전사.나이 = 22;
        a전사.a무기 = new 칼();
        a전사.공격();
        // 출력 : 전사가 칼로 공격합니다.

        a전사.이름 = "초코";
        a전사.나이 = 22;
        a전사.a무기 = new 활();
        a전사.공격();
        // 출력 : 전사가 활로 공격합니다.
    }
}
class 전사{
    String 이름;
    int 나이;
    무기 a무기;
    void 공격(){
        a무기.작동(이름, 나이);
    }
}
class 무기{
    String 무기명;
    void 작동(String name, int age){
        System.out.println(age + "살 전사 " + name + "(이)가 " + 무기명 + "(으)로 공격합니다");
    }
}
class 칼 extends 무기{
    칼(){
        무기명 = "칼";
    }
}
class 활 extends 무기{
    활(){
        무기명 = "활";
    }
}