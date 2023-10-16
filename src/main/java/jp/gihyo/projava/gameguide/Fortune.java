package jp.gihyo.projava.gameguide;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Fortune {
    int fn3;

    @GetMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping("/fortune")
    public String start(Model model) {
        String fr = luck();
        String cr = luckyCharacter();
        int fn3 = this.fn3;
        model.addAttribute("fr",fr);
        model.addAttribute("fn3",fn3);
        model.addAttribute("cr",cr);
        return "fortune";
    }
    private String luck() {
        System.out.print("あなたの運勢は");
        try {
            for (int i = 0; i < 3; i++) {
                System.out.print("・");
                Thread.sleep(1000);
            }
            double fn1 = Math.random();
            if (fn1 == 1.0) {
                return "大吉です！！";
            } else if (fn1 >= 0.7) {
                return "吉です！！";
            } else if (fn1 >= 0.4) {
                return "中吉です！！";
            } else if (fn1 >= 0.1) {
                return "小吉です！";
            } else {
                return "凶です。";
            }

        } catch (InterruptedException e) {
            System.out.println("割り込みが発生しました");
        }
        return null;
    }
    private String luckyCharacter () {
        String[] character = {"スライム!", "アリーナだよ", "ホイミン！！", "ゼシカ!!", "パパス！！", "マルティナだよ！", "テリーです", "ベロニカ！！", "ビアンカ！", "カミュだよ！", "ドロヌーバだ～"};
        double fn1 = Math.random();
        int fn2 = ((int) (fn1 * 100)) % 10;
        this.fn3 = fn2;
        return "ラッキーキャラクターは" + character[fn2];
    }

}
