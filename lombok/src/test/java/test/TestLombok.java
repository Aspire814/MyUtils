package test;

import org.junit.Test;

import lombok.entity.GameInfo;

public class TestLombok {

    @Test
    public void test(){
        GameInfo game = GameInfo.builder().game_name("游戏名").platform(1).build();
        System.out.println(game.getGame_name());
    }

    @Test
    public  void test1(){

    }
}
