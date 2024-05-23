package com.junt.studybasic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Slf4j
@Service
public class RandomService {



    public Set<Integer> getRandom() {

        Random random = new Random(); // 랜덤 객체 생성
        Set<Integer> numbers = new HashSet<>(); // 중복을 허용하지 않는 HashSet 사용

        while (numbers.size() < 6) { // 번호가 6개가 될 때까지 반복
            int number = random.nextInt(45) + 1; // 1부터 45까지의 랜덤 번호 생성
            numbers.add(number); // Set에 번호 추가 (자동으로 중복 제거)
        }

        return numbers; // 생성된 로또 번호 Set 반환
    }
}
