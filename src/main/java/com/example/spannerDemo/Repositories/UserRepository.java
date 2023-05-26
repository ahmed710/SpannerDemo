package com.example.spannerDemo.Repositories;

import com.example.spannerDemo.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.print.Pageable;
import java.util.List;

public interface UserRepository extends ReactiveCrudRepository<User, String> {
    Flux<User> findByAge(Integer age);

    Flux<User> findByAge(Integer age, Sort sort);

    Flux<User> findByAgeOrderByNameDesc(Integer age);

    Flux<User> findAllByOrderByAge();

    Flux<User> findByAgeNot(Integer age);

    Flux<User> findByNameAndAge(String name, Integer age);

    Flux<User> findByFavoriteDrink(String drink);

    Flux<User> findByAgeGreaterThanAndAgeLessThan(Integer age1, Integer age2);

    Flux<User> findByAgeGreaterThan(Integer age);

    Flux<User> findByAgeGreaterThan(Integer age, Sort sort);

    Flux<User> findByAgeGreaterThan(Integer age, Pageable pageable);

    Flux<User> findByAgeIn(List<Integer> ages);

    Flux<User> findByAgeNotIn(List<Integer> ages);

    Flux<User> findByAgeAndPetsContains(Integer age, List<String> pets);

    Flux<User> findByNameAndPetsContains(String name, List<String> pets);

    Flux<User> findByPetsContains(List<String> pets);

    Flux<User> findByPetsContainsAndAgeIn(String pets, List<Integer> ages);

    Mono<Long> countByAgeIsGreaterThan(Integer age);
    Mono<User> save(User user);

    Mono<User> findById(String id);

    Mono<Void> deleteById(String id);

    default Mono<User> update(User user) {
        return findById(user.getId())
                .flatMap(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setAge(user.getAge());
                    existingUser.setFavoriteDrink(user.getFavoriteDrink());
                    existingUser.setCountry(user.getCountry());
                    existingUser.setPets(user.getPets());
                    return save(existingUser);
                });
    }
}
