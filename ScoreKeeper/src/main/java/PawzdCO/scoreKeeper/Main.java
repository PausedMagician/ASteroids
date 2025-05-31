package PawzdCO.scoreKeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Main {

    private int score = 0;
    
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/score")
    public int getScore() {
        return score;
    }

    @GetMapping("/score/set")
    public int setScore(@RequestParam(value = "points") int points) {
        score = points;
        return score;
    }

    @GetMapping("/score/add")
    public int addScore(@RequestParam(value = "points") int points) {
        score += points;
        return score;
    }

    // @GetMapping("/score")
    // public int calculateHealt(@RequestParam(value = "point") Long point) {
    //     totalScore += point;
    //     return totalScore ;
    // }

}