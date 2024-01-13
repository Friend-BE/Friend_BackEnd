package com.friend.friend;

import com.friend.friend.domain.Member;
import com.friend.friend.domain.enums.AccountStatusEnum;
import com.friend.friend.domain.enums.DistanceEnum;
import com.friend.friend.domain.enums.DrinkingEnum;
import com.friend.friend.domain.enums.GenderEnum;
import com.friend.friend.domain.enums.RoleEnum;
import com.friend.friend.domain.enums.SmokingEnum;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();

    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {

//            Member member = createMember("userA", "부산", "1", "1111");
//            em.persist(member);
//
//            Book book1 = createBook("JPA_Book1",10000,10);
//            em.persist(book1);
//
//            Book book2 = createBook("JPA_Book2", 20000, 10);
//            em.persist(book2);
//
//            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
//            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);
//
//            Delivery delivery = new Delivery();
//            delivery.setAddress(member.getAddress());
//            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
//            em.persist(order);
            Member member1 = new Member();
            member1.setEmail("test@email.com");
            member1.setPassword("password1231243124");
            member1.setRole(RoleEnum.USER);
            member1.setNickname("TestUser");
            member1.setPhone("123456789");
            member1.setBirthday("1990-01-01");
            member1.setGender(GenderEnum.MALE);
            member1.setHeight(175.5);
            member1.setRegion("TestRegion");
            member1.setDepartment("TestDepartment");
            member1.setDistance(DistanceEnum.LONG);
            member1.setSmoking(SmokingEnum.NONSMOKER);
            member1.setDrinking(DrinkingEnum.DRINKER);
            member1.setIntroduction("Hello, I am a test user.");
            member1.setPreference("Test Preference");
            member1.setNondepartment("TestNonDepartment");
            member1.setNonstudentid("TestNonStudentId");
            member1.setNonage("TestNonAge");
            member1.setImage("test_image.jpg"); // 이미지 파일 이름 또는 경로
            member1.setStatus(AccountStatusEnum.ACTIVE);
            em.persist(member1);
        }

        public void dbInit2() {
            Member member2 = new Member();

            member2.setEmail("test@email.com");
            member2.setPassword("password1231243124");
            member2.setRole(RoleEnum.USER);
            member2.setNickname("TestUser2");
            member2.setPhone("123456789");
            member2.setBirthday("1990-01-01");
            member2.setGender(GenderEnum.MALE);
            member2.setHeight(175.5);
            member2.setRegion("TestRegion");
            member2.setDepartment("TestDepartment");
            member2.setDistance(DistanceEnum.LONG);
            member2.setSmoking(SmokingEnum.NONSMOKER);
            member2.setDrinking(DrinkingEnum.DRINKER);
            member2.setIntroduction("Hello, I am a test user.");
            member2.setPreference("Test Preference");
            member2.setNondepartment("TestNonDepartment");
            member2.setNonstudentid("TestNonStudentId");
            member2.setNonage("TestNonAge");
            member2.setImage("test_image.jpg"); // 이미지 파일 이름 또는 경로
            member2.setStatus(AccountStatusEnum.ACTIVE);

            em.persist(member2);
        }

    }
}
